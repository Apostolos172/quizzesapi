package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.QGapFillingQuestion;
import com.so.quizzes.dto.basic.qAndA.GapFillingAnswerDto;
import com.so.quizzes.dto.basic.qAndA.GapFillingQuestionDto;
import com.so.quizzes.dto.mini.GapFillingQuestionMiniDto;
import com.so.quizzes.repositories.qAndA.GapFillingQuestionRepository;
import com.so.quizzes.services.GapFillingAnswerService;
import com.so.quizzes.services.GapFillingQuestionService;
import com.so.quizzes.services.QuestionTagService;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GapFillingQuestionServiceImpl implements GapFillingQuestionService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private GapFillingQuestionRepository gapFillingQuestionRepository;

    @Autowired
    private GapFillingAnswerService gapFillingAnswerService;
    @Autowired
    private QuestionTagService questionTagService;

    FactoryExpression<GapFillingQuestionDto> gapFillingQuestionDtoFactoryExpression(QGapFillingQuestion qGapFillingQuestion) {
        return Projections.bean(GapFillingQuestionDto.class,
                qGapFillingQuestion.id,
                qGapFillingQuestion.questionType,
                qGapFillingQuestion.difficultyLevel,
                qGapFillingQuestion.pronunciation,
                qGapFillingQuestion.numberOfGaps);
    }

    @Override
    public GapFillingQuestionDto getQuestion(Long questionId) {
        var qGapFillingQuestion = QGapFillingQuestion.gapFillingQuestion;
        var factoryExpression = gapFillingQuestionDtoFactoryExpression(qGapFillingQuestion);

        var result = queryFactory
                .select(factoryExpression)
                .from(qGapFillingQuestion)
                .where(qGapFillingQuestion.id.eq(questionId))
                .fetchFirst();
        if(result==null) throw new EntityNotFoundException("No gapFillingQuestion with id %s found".formatted(questionId));
        result.setTags(questionTagService.getTagsOfQuestionNativeQuery(questionId));
        result.setPossibleAnswers(gapFillingAnswerService.getGapFillingAnswersByQuestionId(questionId));
        return result;
    }

    @Override
    @Transactional
    public GapFillingQuestionDto saveGapFillingQuestionAndAnswers(GapFillingQuestionDto questionDto) {
        var gapFillingQuestion = questionDto.toEntity();

        gapFillingQuestion.setNumberOfGaps(gapFillingQuestion.calculateTheNumberOfGaps());

        gapFillingQuestionRepository.save(gapFillingQuestion);
        for (GapFillingAnswerDto gapFillingAnswerDto : questionDto.getPossibleAnswers()) {
            gapFillingAnswerDto.setQuestionId(gapFillingQuestion.getId());
        }
        gapFillingAnswerService.saveGapFillingAnswerList(questionDto.getPossibleAnswers());

        return getQuestion(gapFillingQuestion.getId());
    }

    FactoryExpression<GapFillingQuestionMiniDto> gapFillingQuestionMiniDtoFactoryExpression(QGapFillingQuestion qGapFillingQuestion) {
        return Projections.bean(GapFillingQuestionMiniDto.class,
                qGapFillingQuestion.id,
                qGapFillingQuestion.pronunciation,
                qGapFillingQuestion.questionType,
                qGapFillingQuestion.numberOfGaps);
    }

    @Override
    public GapFillingQuestionMiniDto getQuestionForCompletion(Long questionId) {
        var qGapFillingQuestion = QGapFillingQuestion.gapFillingQuestion;
        var factoryExpression = gapFillingQuestionMiniDtoFactoryExpression(qGapFillingQuestion);

        var result = queryFactory
                .select(factoryExpression)
                .from(qGapFillingQuestion)
                .where(qGapFillingQuestion.id.eq(questionId))
                .fetchFirst();
        return result;
    }
}
