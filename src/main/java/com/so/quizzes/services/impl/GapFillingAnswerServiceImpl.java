package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.QAnswer;
import com.so.quizzes.core.domain.qAndA.QGapFillingAnswer;
import com.so.quizzes.dto.basic.qAndA.GapFillingAnswerDto;
import com.so.quizzes.repositories.qAndA.GapFillingAnswerRepository;
import com.so.quizzes.services.GapFillingAnswerService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GapFillingAnswerServiceImpl implements GapFillingAnswerService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private GapFillingAnswerRepository gapFillingAnswerRepository;

    private FactoryExpression<GapFillingAnswerDto> gapFillingAnswerDtoFactoryExpression(QGapFillingAnswer qGapFillingAnswer,
                                                                                        QAnswer qAnswer) {
        return Projections.bean(GapFillingAnswerDto.class,
                qAnswer.id,
                qGapFillingAnswer.positionInText,
                qGapFillingAnswer.word,
                qAnswer.questionId
        );
    }

    @Override
    public List<GapFillingAnswerDto> getGapFillingAnswersByQuestionId(Long questionId) {
        var qGapFillingAnswer = QGapFillingAnswer.gapFillingAnswer;
        var qAnswer = QAnswer.answer;

        var factoryExpression = gapFillingAnswerDtoFactoryExpression(qGapFillingAnswer, qAnswer);
        var questionPredicate = new BooleanBuilder();
        questionPredicate.and(qAnswer.questionId.eq(questionId));

        var result = queryFactory
                .select(factoryExpression)
                .from(qGapFillingAnswer)
                .leftJoin(qAnswer).on(qGapFillingAnswer.answerId.eq(qAnswer.id))
                .where(questionPredicate)
                .fetch();
        return result;
    }

    @Override
    @Transactional
    public void saveGapFillingAnswerList(List<GapFillingAnswerDto> possibleAnswers) {
        for (GapFillingAnswerDto gapFillingAnswerDto : possibleAnswers) {
            gapFillingAnswerRepository.save(gapFillingAnswerDto.toEntity());
        }
    }

    @Override
    public Map.Entry<Integer, String> getGapFillingAnswer(Long answerId) {
        var gapFillingAnswer = gapFillingAnswerRepository.findById(answerId).orElse(null);
        assert gapFillingAnswer != null;
        var result = Map.entry(gapFillingAnswer.getPositionInText(), gapFillingAnswer.getWord());
        return result;
    }
}
