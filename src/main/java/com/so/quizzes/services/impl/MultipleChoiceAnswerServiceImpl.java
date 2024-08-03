package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceAnswer;
import com.so.quizzes.core.domain.qAndA.QAnswer;
import com.so.quizzes.core.domain.qAndA.QMultipleChoiceAnswer;
import com.so.quizzes.dto.basic.qAndA.AnswerDto;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.dto.mini.MultipleChoiceAnswerMiniDto;
import com.so.quizzes.repositories.qAndA.MultipleChoiceAnswerRepository;
import com.so.quizzes.services.MultipleChoiceAnswerService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultipleChoiceAnswerServiceImpl implements MultipleChoiceAnswerService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    private FactoryExpression<MultipleChoiceAnswerDto> multipleChoiceAnswerDtoFactoryExpression(QMultipleChoiceAnswer qMultipleChoiceAnswer,
                                                                                                QAnswer qAnswer) {
        return Projections.bean(MultipleChoiceAnswerDto.class,
                qAnswer.id,
                qMultipleChoiceAnswer.selectionText,
                qMultipleChoiceAnswer.isCorrect,
                qMultipleChoiceAnswer.questionId
        );
    }

    @Override
    public List<MultipleChoiceAnswerDto> getMultipleChoiceAnswersByQuestionId(Long questionId) {
        var qMultipleChoiceAnswer = QMultipleChoiceAnswer.multipleChoiceAnswer;
        var qAnswer = QAnswer.answer;
        var factoryExpression = multipleChoiceAnswerDtoFactoryExpression(qMultipleChoiceAnswer, qAnswer);

        var questionPredicate = new BooleanBuilder();
        questionPredicate.and(qAnswer.questionId.eq(questionId));

        var result = queryFactory
                .select(factoryExpression)
                .from(qMultipleChoiceAnswer)
                .leftJoin(qAnswer).on(qMultipleChoiceAnswer.answerId.eq(qAnswer.id))
                .where(questionPredicate)
                .fetch();
        return result;
    }

    @Override
    @Transactional
    public void saveMultipleChoiceAnswerList(List<MultipleChoiceAnswerDto> possibleAnswers) {
        for (MultipleChoiceAnswerDto multipleChoiceAnswerDto : possibleAnswers) {
            multipleChoiceAnswerRepository.save(multipleChoiceAnswerDto.toEntity());
        }
    }

    private FactoryExpression<MultipleChoiceAnswerMiniDto> multipleChoiceAnswerMiniDtoFactoryExpression(QMultipleChoiceAnswer qMultipleChoiceAnswer,
                                                                                                QAnswer qAnswer) {
        return Projections.bean(MultipleChoiceAnswerMiniDto.class,
                qAnswer.id,
                qMultipleChoiceAnswer.selectionText,
                qMultipleChoiceAnswer.questionId
        );
    }

    @Override
    public List<MultipleChoiceAnswerMiniDto> getMultipleChoiceAnswersByQuestionIdForCompletion(Long questionId) {
        var qMultipleChoiceAnswer = QMultipleChoiceAnswer.multipleChoiceAnswer;
        var qAnswer = QAnswer.answer;
        var factoryExpression = multipleChoiceAnswerMiniDtoFactoryExpression(qMultipleChoiceAnswer, qAnswer);

        var questionPredicate = new BooleanBuilder();
        questionPredicate.and(qAnswer.questionId.eq(questionId));

        var result = queryFactory
                .select(factoryExpression)
                .from(qMultipleChoiceAnswer)
                .leftJoin(qAnswer).on(qMultipleChoiceAnswer.answerId.eq(qAnswer.id))
                .where(questionPredicate)
                .fetch();
        return result;
    }

    @Override
    public List<Long> getAnswersIds(Long questionId) {
        return getMultipleChoiceAnswersByQuestionId(questionId)
                .stream()
                .map(AnswerDto::getId)
                .collect(Collectors.toList());
    }

    @Override
    public MultipleChoiceAnswer getAnswer(Long answerId) {
        return multipleChoiceAnswerRepository.findById(answerId).get();
    }

    @Override
    @Transactional
    public void deleteAnswersByQuestionId(Long questionId) {
        getMultipleChoiceAnswersByQuestionId(questionId)
                .stream()
                .map(AnswerDto::getId)
                .forEach(answerId -> multipleChoiceAnswerRepository.deleteById(answerId));
    }
}
