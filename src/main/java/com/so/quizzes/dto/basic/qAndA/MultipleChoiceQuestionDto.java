package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceQuestion;
import com.so.quizzes.core.enums.DifficultyLevel;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link MultipleChoiceQuestion}
 */
public class MultipleChoiceQuestionDto extends QuestionDto {
    private DifficultyLevel difficultyLevel;
    private Set<String> tags = new HashSet<>();
    private Boolean activeHintForNumberOfSelections;
    private List<MultipleChoiceAnswerDto> possibleAnswers;

    public List<MultipleChoiceAnswerDto> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<MultipleChoiceAnswerDto> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
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

    public MultipleChoiceQuestion toEntity() {
        var multipleChoiceQuestion = new MultipleChoiceQuestion();
        BeanUtils.copyProperties(this, multipleChoiceQuestion);
        return multipleChoiceQuestion;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestionDto{" +
                "id=" + getId() +
                ", difficultyLevel=" + difficultyLevel +
                ", tags=" + tags +
                ", activeHintForNumberOfSelections=" + activeHintForNumberOfSelections +
                ", possibleAnswers=" + possibleAnswers +
                '}';
    }
}