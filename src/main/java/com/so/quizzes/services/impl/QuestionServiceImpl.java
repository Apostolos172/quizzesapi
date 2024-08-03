package com.so.quizzes.services.impl;

import com.so.quizzes.aspects.OperationNotAllowed;
import com.so.quizzes.core.domain.exams.QGradedQuestion;
import com.so.quizzes.core.domain.qAndA.QQuestion;
import com.so.quizzes.core.domain.qAndA.Question;
import com.so.quizzes.dto.basic.qAndA.QuestionDto;
import com.so.quizzes.repositories.qAndA.QuestionRepository;
import com.so.quizzes.services.*;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionTagService questionTagService;

    private final QQuestion qQuestion = QQuestion.question;

    FactoryExpression<QuestionDto> questionDtoFactoryExpression(QQuestion qQuestion) {
        return Projections.bean(QuestionDto.class,
                qQuestion.id,
                qQuestion.pronunciation,
                qQuestion.questionType);
    }

    @Override
    public QuestionDto getQuestionWithAnswers(Long questionId) {
        var result = queryFactory.select(qQuestion.questionType)
                .from(qQuestion)
                .where(qQuestion.id.eq(questionId))
                .fetchFirst();
        if (Objects.equals(result, "MultipleChoiceQuestion")) {
            return this.multipleChoiceQuestionService.getQuestionForCompletion(questionId);
        } else if(Objects.equals(result, "GapFillingQuestion")) {
            return this.gapFillingQuestionService.getQuestionForCompletion(questionId);
        }
        return getQuestion(questionId);
    }

    @Override
    public QuestionDto getQuestion(Long questionId) {
        var factoryExpression = questionDtoFactoryExpression(qQuestion);
        var result = queryFactory
                .select(factoryExpression)
                .from(qQuestion)
                .where(qQuestion.id.eq(questionId))
                .fetchFirst();
        return result;
    }

    @Override
    public List<QuestionDto> getQuestions() {
        var factoryExpression = questionDtoFactoryExpression(qQuestion);
        var result = queryFactory
                .select(factoryExpression)
                .from(qQuestion)
                .fetch();
        return result.stream()
                .peek(questionDto -> questionDto.setPronunciation(questionDto.getPronunciation().replace("@@@", "___")))
                .toList();
    }

    @Override
    public List<QuestionDto> getQuestionsRelevantToTags(String tagsSeparatedByComma) {
        var tags = tagsSeparatedByComma.split(",");
        return Arrays.stream(tags)
                .map(tag -> getQuestionsRelevantToTag(tag.trim()))
                .flatMap(questionDtos -> questionDtos.stream())
                .peek(questionDto -> questionDto.setPronunciation(questionDto.getPronunciation().replace("@@@", "___")))
                .toList();
    }

    private List<QuestionDto> getQuestionsRelevantToTag(String tag) {
        return queryFactory
                .select(qQuestion)
                .from(qQuestion)
                .join(qQuestion.tags).fetchJoin()
                .fetch()
                .stream()
                .filter(question -> {
                    var tags = question.getTags();
                    var relevantTagsNumber = tags
                            .stream()
                            .filter(curTag -> curTag.equalsIgnoreCase(tag) || curTag.toLowerCase().contains(tag.toLowerCase()))
                            .count();
                    return relevantTagsNumber > 0;
                })
                .map(Question::toQuestionDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Boolean deleteQuestionById(Long questionId) {
        if (!hasTheQuestionBeenGraded(questionId)) {
            questionRepository.deleteById(questionId);
            answerService.deleteAnswersByQuestionId(questionId);
            return true;
        }
        throw new OperationNotAllowed("DeleteQuestionById failed, because the question exists in quiz");
    }

    private Boolean hasTheQuestionBeenGraded(Long questionId) {
        QGradedQuestion qGradedQuestion = QGradedQuestion.gradedQuestion;
        var gradedQuestionsFromTheSpecificQuestion = queryFactory
                .select(qGradedQuestion)
                .from(qGradedQuestion)
                .where(qGradedQuestion.questionId.eq(questionId))
                .fetch();
        return !gradedQuestionsFromTheSpecificQuestion.isEmpty();
    }

    @Override
    public String getTypeOfQuestion(Long questionId) {
        var q = this.getQuestion(questionId);
        if (q == null) {
            throw new EntityNotFoundException("No such question with id %s".formatted(questionId));
        }
        return this.getQuestion(questionId).getQuestionType();
    }
}
