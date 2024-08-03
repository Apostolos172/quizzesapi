package com.so.quizzes.core.domain.qAndA;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FreeFormQuestion")
public class FreeFormQuestion extends Question {
    public FreeFormQuestion() {
    }
}
