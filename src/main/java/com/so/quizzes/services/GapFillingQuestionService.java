package com.so.quizzes.services;

import com.so.quizzes.dto.basic.qAndA.GapFillingQuestionDto;
import com.so.quizzes.dto.mini.GapFillingQuestionMiniDto;

public interface GapFillingQuestionService {
    /**
     * Get gap filling question and its answers of the same type
     * for view, (for the question author), for save or update,
     * or during the creation of the quiz
     * if the creator wants to see the details of the question
     * Link to the endpoint which uses this method
     */
    GapFillingQuestionDto getQuestion(Long questionId);

    /**
     * Save or update gap filling question and its answers of the same type
     */
    GapFillingQuestionDto saveGapFillingQuestionAndAnswers(GapFillingQuestionDto questionDto);

    GapFillingQuestionMiniDto getQuestionForCompletion(Long questionId);
}
