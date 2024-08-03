package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceAnswer;
import org.springframework.beans.BeanUtils;

/**
 * DTO for {@link MultipleChoiceAnswer}
 */
public class MultipleChoiceAnswerDto extends AnswerDto {
    private String selectionText;
    private Boolean isCorrect;

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public MultipleChoiceAnswer toEntity() {
        var multipleChoiceAnswer = new MultipleChoiceAnswer();
        BeanUtils.copyProperties(this, multipleChoiceAnswer);
        return multipleChoiceAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoiceAnswerDto{" +
                "selectionText='" + selectionText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}