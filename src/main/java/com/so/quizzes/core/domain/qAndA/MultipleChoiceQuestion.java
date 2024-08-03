package com.so.quizzes.core.domain.qAndA;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MultipleChoiceQuestion")
public class MultipleChoiceQuestion extends Question {

    /**
     * Should we give the user a hint about the number of selections that should be done
     */
    @Column(name = "active_hint_for_number_of_selections")
    private Boolean activeHintForNumberOfSelections = true;

    public MultipleChoiceQuestion() {
    }

    public Boolean getActiveHintForNumberOfSelections() {
        return activeHintForNumberOfSelections;
    }

    public void setActiveHintForNumberOfSelections(Boolean activeHintForNumberOfSelections) {
        this.activeHintForNumberOfSelections = activeHintForNumberOfSelections;
    }
}
