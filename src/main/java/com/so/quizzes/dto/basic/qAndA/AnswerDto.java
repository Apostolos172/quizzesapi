package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.Answer;

import java.util.Objects;

/**
 * DTO for {@link Answer}
 */
public class AnswerDto {
    private Long id;
    private Long questionId;

    public AnswerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return Objects.equals(id, answerDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
