package com.so.quizzes.services;

import com.so.quizzes.dto.basic.qAndA.GapFillingAnswerDto;

import java.util.List;
import java.util.Map;

public interface GapFillingAnswerService {
    /**
     * Get the gap filling answers of a question
     */
    List<GapFillingAnswerDto> getGapFillingAnswersByQuestionId(Long questionId);

    /**
     * Save a list of a GapFillingAnswer
     */
    void saveGapFillingAnswerList(List<GapFillingAnswerDto> possibleAnswers);

    /**
     * Get gap filling answer as a map entry
     */
    Map.Entry<Integer, String> getGapFillingAnswer(Long answerId);
}
