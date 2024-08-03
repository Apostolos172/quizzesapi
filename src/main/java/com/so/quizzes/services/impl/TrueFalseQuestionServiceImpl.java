package com.so.quizzes.services.impl;

import com.so.quizzes.aspects.OperationNotAllowed;
import com.so.quizzes.core.domain.qAndA.QTrueFalseQuestion;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.dto.basic.qAndA.TrueFalseQuestionDto;
import com.so.quizzes.repositories.qAndA.TrueFalseQuestionRepository;
import com.so.quizzes.services.MultipleChoiceAnswerService;
import com.so.quizzes.services.TrueFalseAnswerService;
import com.so.quizzes.services.TrueFalseQuestionService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrueFalseQuestionServiceImpl implements TrueFalseQuestionService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;
    @Autowired
    private TrueFalseAnswerService trueFalseAnswerService;
    @Autowired
    private TrueFalseQuestionRepository trueFalseQuestionRepository;

    @Override
    public TrueFalseQuestionDto getQuestion(Long questionId) {
        var qTrueFalseQuestion = QTrueFalseQuestion.trueFalseQuestion;
        var result = queryFactory
                .select(qTrueFalseQuestion)
                .from(qTrueFalseQuestion)
                .where(qTrueFalseQuestion.id.eq(questionId))
                .fetchFirst();
        if (result == null)
            throw new EntityNotFoundException("No trueFalseQuestion with id %s found".formatted(questionId));
        var resultDto = result.toDto(multipleChoiceAnswerService.getMultipleChoiceAnswersByQuestionId(questionId));
        return resultDto;
    }

    @Override
    @Transactional
    public TrueFalseQuestionDto saveTrueFalseQuestionAndAnswers(TrueFalseQuestionDto questionDto) {
        var trueFalseQuestion = questionDto.toEntity();

        trueFalseQuestionRepository.save(trueFalseQuestion);
        if (questionDto.getTrueOrFalse() != null) {
            trueFalseAnswerService.saveAnswersOfTrueFalseQuestion(trueFalseQuestion.getId(), questionDto.getTrueOrFalse());
        } else {
            throw new OperationNotAllowed("You must specify if the question is true or false");
        }

        return getQuestion(trueFalseQuestion.getId());
    }

    @Override
    public Boolean isTrueOrFalse(Long questionId) {
        var answers = multipleChoiceAnswerService.getMultipleChoiceAnswersByQuestionId(questionId);
        return answers.stream()
                .filter(MultipleChoiceAnswerDto::getIsCorrect)
                .findFirst()
                .map(multipleChoiceAnswerDto -> Boolean.valueOf(multipleChoiceAnswerDto.getSelectionText()))
                .orElse(null);
    }


}
