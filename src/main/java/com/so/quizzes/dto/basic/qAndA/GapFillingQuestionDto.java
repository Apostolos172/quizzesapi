package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.GapFillingQuestion;
import com.so.quizzes.core.enums.DifficultyLevel;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

/**
 * DTO for {@link GapFillingQuestion}
 */
public class GapFillingQuestionDto extends QuestionDto {
    public static String GAP_TEXT_PATTERN = "@@@";
    public static String GAP_DISPLAY = "_________________";
    private DifficultyLevel difficultyLevel;
    private Set<String> tags;
    private List<GapFillingAnswerDto> possibleAnswers;
    private Integer numberOfGaps;

    public GapFillingQuestionDto() {
    }

    // TODO: 15/12/2023 in order to display GAP_DISPLAY I must do different dto

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

    public List<GapFillingAnswerDto> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<GapFillingAnswerDto> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Integer getNumberOfGaps() {
        return numberOfGaps;
    }

    public void setNumberOfGaps(Integer numberOfGaps) {
        this.numberOfGaps = numberOfGaps;
    }

    public GapFillingQuestion toEntity() {
        var gapFillingQuestion = new GapFillingQuestion();
        BeanUtils.copyProperties(this, gapFillingQuestion);
        return gapFillingQuestion;
    }

    @Override
    public String toString() {
        return "GapFillingQuestionDto{" +
                "id=" + getId() +
                ", difficultyLevel=" + difficultyLevel +
                ", tags=" + tags +
                ", possibleAnswers=" + possibleAnswers +
                ", numberOfGaps=" + numberOfGaps +
                '}';
    }
}