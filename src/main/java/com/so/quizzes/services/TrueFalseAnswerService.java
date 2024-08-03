package com.so.quizzes.services;

public interface TrueFalseAnswerService {
    /**
     * Save answers of a TrueFalseQuestion
     */
    void saveAnswersOfTrueFalseQuestion(Long questionId, Boolean trueOrFalse);

    /**
     * Delete all answers of a question with specific id
     * (you can remove it and use the same method in answerService)
     */
    void deleteAnswers(Long questionId);
}
