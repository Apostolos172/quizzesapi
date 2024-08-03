package com.so.quizzes.dto.mini;

import com.so.quizzes.core.domain.qAndA.GapFillingQuestion;
import com.so.quizzes.dto.basic.qAndA.QuestionDto;

/**
 * DTO for {@link GapFillingQuestion}
 */
public class GapFillingQuestionMiniDto extends QuestionDto {
    private Integer numberOfGaps;

    public Integer getNumberOfGaps() {
        return numberOfGaps;
    }

    public void setNumberOfGaps(Integer numberOfGaps) {
        this.numberOfGaps = numberOfGaps;
    }
}