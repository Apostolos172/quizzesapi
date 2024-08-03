package com.so.quizzes.services;

import java.util.Set;

public interface QuestionTagService {
    /**
     * Get the tags of a question
     */
    Set<String> getTagsOfQuestion(Long questionId);

    Set<String> getTagsOfQuestionJPQL(Long questionId);

    Set<String> getTagsOfQuestionNativeQuery(Long questionId);
}
