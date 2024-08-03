package com.so.quizzes.core.domain.qAndA;

import jakarta.persistence.*;

@Entity
@Table(name = "free_form_answers")
@PrimaryKeyJoinColumn(name = "answer_id")
public class FreeFormAnswer extends Answer {
    @Column(name = "answer_text")
    private String answerText;

    public FreeFormAnswer() {
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
