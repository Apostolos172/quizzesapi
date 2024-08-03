package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.QMultipleChoiceQuestion;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceQuestionDto;
import com.so.quizzes.dto.mini.MultipleChoiceQuestionMiniDto;
import com.so.quizzes.repositories.qAndA.MultipleChoiceQuestionRepository;
import com.so.quizzes.services.MultipleChoiceAnswerService;
import com.so.quizzes.services.MultipleChoiceQuestionService;
import com.so.quizzes.services.QuestionTagService;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;
    @Autowired
    private QuestionTagService questionTagService;

    FactoryExpression<MultipleChoiceQuestionDto> multipleChoiceQuestionDtoFactoryExpression(QMultipleChoiceQuestion qMultipleChoiceQuestion) {
        return Projections.bean(MultipleChoiceQuestionDto.class,
                qMultipleChoiceQuestion.id,
                qMultipleChoiceQuestion.questionType,
                qMultipleChoiceQuestion.difficultyLevel,
                qMultipleChoiceQuestion.activeHintForNumberOfSelections,
                qMultipleChoiceQuestion.pronunciation);
    }

    @Override
    public MultipleChoiceQuestionDto getQuestion(Long questionId) {
        var qMultipleChoiceQuestion = QMultipleChoiceQuestion.multipleChoiceQuestion;
        var factoryExpression = multipleChoiceQuestionDtoFactoryExpression(qMultipleChoiceQuestion);

        var result = queryFactory
                .select(factoryExpression)
                .from(qMultipleChoiceQuestion)
                .where(qMultipleChoiceQuestion.id.eq(questionId))
                .fetchFirst();
        if(result==null) throw new EntityNotFoundException("No multipleChoiceQuestion with id %s found".formatted(questionId));
        result.setTags(questionTagService.getTagsOfQuestionNativeQuery(questionId));
        result.setPossibleAnswers(multipleChoiceAnswerService.getMultipleChoiceAnswersByQuestionId(questionId));
        return result;
    }

    FactoryExpression<MultipleChoiceQuestionMiniDto> multipleChoiceQuestionMiniDtoFactoryExpression(QMultipleChoiceQuestion qMultipleChoiceQuestion) {
        return Projections.bean(MultipleChoiceQuestionMiniDto.class,
                qMultipleChoiceQuestion.id,
                qMultipleChoiceQuestion.questionType,
                qMultipleChoiceQuestion.pronunciation);
    }

    @Override
    public MultipleChoiceQuestionMiniDto getQuestionForCompletion(Long questionId) {
        var qMultipleChoiceQuestion = QMultipleChoiceQuestion.multipleChoiceQuestion;
        var factoryExpression = multipleChoiceQuestionMiniDtoFactoryExpression(qMultipleChoiceQuestion);

        var result = queryFactory
                .select(factoryExpression)
                .from(qMultipleChoiceQuestion)
                .where(qMultipleChoiceQuestion.id.eq(questionId))
                .fetchFirst();
        result.setPossibleAnswers(multipleChoiceAnswerService.getMultipleChoiceAnswersByQuestionIdForCompletion(questionId));
        return result;
    }

    @Override
    @Transactional
    public MultipleChoiceQuestionDto saveMultipleChoiceQuestionAndAnswers(MultipleChoiceQuestionDto questionDto) {
        var multipleChoiceQuestion = questionDto.toEntity();

        multipleChoiceQuestionRepository.save(multipleChoiceQuestion);
        for (MultipleChoiceAnswerDto multipleChoiceAnswerDto : questionDto.getPossibleAnswers()) {
            multipleChoiceAnswerDto.setQuestionId(multipleChoiceQuestion.getId());
        }
        multipleChoiceAnswerService.saveMultipleChoiceAnswerList(questionDto.getPossibleAnswers());

        return getQuestion(multipleChoiceQuestion.getId());
    }

}
