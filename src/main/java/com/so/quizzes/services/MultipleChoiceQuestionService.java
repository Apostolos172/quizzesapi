package com.so.quizzes.services;

import com.so.quizzes.dto.basic.qAndA.MultipleChoiceQuestionDto;
import com.so.quizzes.dto.mini.MultipleChoiceQuestionMiniDto;

public interface MultipleChoiceQuestionService {
    /**
     * Get multiple choice question and its answers of the same type
     * for view, (for the question author), for save or update,
     * or during the creation of the quiz
     * if the creator wants to see the details of the question
     * Link to the endpoint which uses this method
     */
    MultipleChoiceQuestionDto getQuestion(Long questionId);

    /**
     * Get multiple choice question and its answers of the same type,
     * without the information if they are correct or not
     */
    MultipleChoiceQuestionMiniDto getQuestionForCompletion(Long questionId);

    /**
     * Save or update multiple choice question and its answers of the same type
     */
    MultipleChoiceQuestionDto saveMultipleChoiceQuestionAndAnswers(MultipleChoiceQuestionDto questionDto);

}
