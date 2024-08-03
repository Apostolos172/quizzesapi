package com.so.quizzes.services;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceAnswer;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.dto.mini.MultipleChoiceAnswerMiniDto;

import java.util.List;

public interface MultipleChoiceAnswerService {
    /**
     * Get the multiple choice answers of a question
     */
    List<MultipleChoiceAnswerDto> getMultipleChoiceAnswersByQuestionId(Long questionId);

    /**
     * Get the multiple choice answers of a question
     * without the information if they are correct or not
     */
    List<MultipleChoiceAnswerMiniDto> getMultipleChoiceAnswersByQuestionIdForCompletion(Long questionId);

    /**
     * Save a list of a MultipleChoiceAnswer
     */
    void saveMultipleChoiceAnswerList(List<MultipleChoiceAnswerDto> possibleAnswers);

    /**
     * Delete all answers of a question with specific id
     * (you can remove it and use the same method in answerService)
     */
    void deleteAnswersByQuestionId(Long questionId);

    /**
     * Get the ids of the answers of a question with specific id
     * (you can remove it and use the same method in answerService)
     */
    List<Long> getAnswersIds(Long questionId);

    /**
     * Get an answer
     */
    MultipleChoiceAnswer getAnswer(Long answerId);
}
