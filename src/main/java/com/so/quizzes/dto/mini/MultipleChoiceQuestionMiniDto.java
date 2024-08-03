package com.so.quizzes.dto.mini;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceQuestion;
import com.so.quizzes.dto.basic.qAndA.QuestionDto;

import java.util.List;

/**
 * DTO for {@link MultipleChoiceQuestion}
 */
public class MultipleChoiceQuestionMiniDto extends QuestionDto {
    private List<MultipleChoiceAnswerMiniDto> possibleAnswers;

    public List<MultipleChoiceAnswerMiniDto> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<MultipleChoiceAnswerMiniDto> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}