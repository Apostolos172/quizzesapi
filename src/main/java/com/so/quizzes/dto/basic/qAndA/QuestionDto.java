package com.so.quizzes.dto.basic.qAndA;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.so.quizzes.core.domain.qAndA.Question;

import java.util.Objects;

/**
 * DTO for {@link Question}
 */
public class QuestionDto {
    private Long id = -1L;
    @JsonProperty("pronunciation")
    private String pronunciation;
    private String questionType;

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(pronunciation, that.pronunciation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pronunciation);
    }
}
