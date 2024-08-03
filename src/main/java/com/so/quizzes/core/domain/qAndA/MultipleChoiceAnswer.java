package com.so.quizzes.core.domain.qAndA;

import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

/**
 * Allow multiple orders answers .
 * During the exam the user should select
 * one or more right choices
 */
@Entity
@Table(name = "multiple_choice_answers")
@PrimaryKeyJoinColumn(name = "answer_id")
public class MultipleChoiceAnswer extends Answer {
    @Column(name = "selection_text")
    private String selectionText;
    @Column(name = "is_correct")
    private Boolean isCorrect;
    @Column(name = "answer_id", insertable=false, updatable=false)
    private Long answerId;

    public MultipleChoiceAnswer() {
    }

    public MultipleChoiceAnswer(String selectionText, boolean isCorrect) {
        this.selectionText = selectionText;
        this.isCorrect = isCorrect;
    }

    public MultipleChoiceAnswerDto toDto() {
        var multipleChoiceAnswerDto = new MultipleChoiceAnswerDto();
        BeanUtils.copyProperties(this, multipleChoiceAnswerDto);
        return multipleChoiceAnswerDto;
    }

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
