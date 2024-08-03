package com.so.quizzes.dto.mini;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceAnswer;
import com.so.quizzes.dto.basic.qAndA.AnswerDto;

/**
 * DTO for {@link MultipleChoiceAnswer}
 */
public class MultipleChoiceAnswerMiniDto extends AnswerDto {
    private String selectionText;

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }

}