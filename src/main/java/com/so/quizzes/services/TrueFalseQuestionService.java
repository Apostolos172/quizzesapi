package com.so.quizzes.services;

import com.so.quizzes.dto.basic.qAndA.TrueFalseQuestionDto;

public interface TrueFalseQuestionService {

    /**
     * Get true false question and its answers
     * for view, (for the question author), for save or update,
     * or during the creation of the quiz
     * if the creator wants to see the details of the question
     * Link to the endpoint which uses this method
     */
    TrueFalseQuestionDto getQuestion(Long questionId);

    /**
     * Save or update true false question and its answers
     */
    TrueFalseQuestionDto saveTrueFalseQuestionAndAnswers(TrueFalseQuestionDto question);

    /**
     * Returns if the question is true or false
     */
    Boolean isTrueOrFalse(Long questionId);
}
