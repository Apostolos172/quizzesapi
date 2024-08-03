package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.TrueFalseQuestion;
import com.so.quizzes.core.enums.DifficultyLevel;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link TrueFalseQuestion}
 */
public class TrueFalseQuestionDto extends QuestionDto {
    private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
    private Set<String> tags = new HashSet<>();
    private Boolean activeHintForNumberOfSelections = true;
    private Boolean trueOrFalse;

    public Boolean getTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Boolean getActiveHintForNumberOfSelections() {
        return activeHintForNumberOfSelections;
    }

    public void setActiveHintForNumberOfSelections(Boolean activeHintForNumberOfSelections) {
        this.activeHintForNumberOfSelections = activeHintForNumberOfSelections;
    }

    public TrueFalseQuestion toEntity() {
        var trueFalseQuestion = new TrueFalseQuestion();
        BeanUtils.copyProperties(this, trueFalseQuestion);
        return trueFalseQuestion;
    }

    @Override
    public String toString() {
        return "TrueFalseQuestionDto{" +
                "id=" + getId() +
                ", pronunciation=" + getPronunciation() +
                ", trueOrFalse=" + trueOrFalse +
                ", difficultyLevel=" + difficultyLevel +
                ", tags=" + tags +
                ", activeHintForNumberOfSelections=" + activeHintForNumberOfSelections +
                '}';
    }
}