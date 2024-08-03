package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.QAnswer;
import com.so.quizzes.repositories.qAndA.AnswerRepository;
import com.so.quizzes.services.AnswerService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private AnswerRepository answerRepository;

    private final QAnswer qAnswer = QAnswer.answer;

    @Override
    @Transactional
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public List<Long> findAnswerIdsByQuestionId(Long questionId) {
        return queryFactory
                .select(qAnswer.id)
                .from(qAnswer)
                .where(qAnswer.questionId.eq(questionId))
                .fetch();
    }

    @Override
    @Transactional
    public void deleteAnswersByQuestionId(Long questionId) {
        findAnswerIdsByQuestionId(questionId)
                .forEach(answerId -> answerRepository.deleteById(answerId));
    }

}
