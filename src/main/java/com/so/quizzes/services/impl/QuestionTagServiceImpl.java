package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.QQuestion;
import com.so.quizzes.core.domain.qAndA.Question;
import com.so.quizzes.services.QuestionTagService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class QuestionTagServiceImpl implements QuestionTagService {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private EntityManager entityManager;

    private final QQuestion qQuestion = QQuestion.question;

    @Override
    public Set<String> getTagsOfQuestion(Long questionId) {
        var result = queryFactory
                .select(qQuestion)
                .from(qQuestion)
                .join(qQuestion.tags).fetchJoin()
                .where(qQuestion.id.eq(questionId))
                .fetchFirst();
        var tags = result.getTags();
        return tags;
    }

    @Override
    public Set<String> getTagsOfQuestionJPQL(Long questionId) {
        var sqlString = "SELECT q FROM Question AS q JOIN FETCH q.tags WHERE q.id=:id";
        var query = entityManager.createQuery(sqlString, Question.class);
        var question = query.setParameter("id", questionId).getSingleResult();
        return question.getTags();
    }

    @Override
    public Set<String> getTagsOfQuestionNativeQuery(Long questionId) {
        var q = entityManager.createNativeQuery("select tag from questions_tags where question_id=?;");
        q.setParameter(1, questionId);
        var res = q.getResultList();
        var tags = new HashSet<String>();
        for (Object o : res) {
            tags.add((String) o);
        }
        return tags;
    }
}
