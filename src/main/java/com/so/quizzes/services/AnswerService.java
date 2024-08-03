package com.so.quizzes.services;

import java.util.List;

public interface AnswerService {
    /**
     * Delete an answer
     */
    void deleteAnswer(Long answerId);

    /**
     * Get the ids of the answers of a question with specific id
     */
    List<Long> findAnswerIdsByQuestionId(Long questionId);

    /**
     * Delete all answers of a question with specific id
     */
    void deleteAnswersByQuestionId(Long questionId);
}
