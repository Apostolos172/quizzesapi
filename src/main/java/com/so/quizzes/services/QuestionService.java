package com.so.quizzes.services;

import com.so.quizzes.dto.basic.qAndA.QuestionDto;

import java.util.List;

public interface QuestionService {

    /**
     * Get a question with the possible answers if it is necessary to display them,
     * in order to display a quiz for completion by the participants
     */
    QuestionDto getQuestionWithAnswers(Long questionId);

    /**
     * Get a question (pronunciation, id, and type)
     */
    QuestionDto getQuestion(Long questionId);

    /**
     * Get all questions for listing purposes
     * (id, pronunciations, types)
     * (dropdown menus)
     */
    List<QuestionDto> getQuestions();

    /**
     * Delete a question and its associated answers
     */
    Boolean deleteQuestionById(Long questionId);

    /**
     * Get all questions relevant to tags, for listing purposes
     * (id, pronunciations, types)
     */
    List<QuestionDto> getQuestionsRelevantToTags(String tags);

    String getTypeOfQuestion(Long questionId);
}
