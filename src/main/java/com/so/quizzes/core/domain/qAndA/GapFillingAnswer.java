package com.so.quizzes.core.domain.qAndA;

import com.so.quizzes.dto.basic.qAndA.GapFillingAnswerDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

/**
 * This kind of answers define the word and the position
 * (empty spot) in the question text that this word should be
 * positioned
 */
@Entity
@Table(name = "gap_filling_answers")
@PrimaryKeyJoinColumn(name = "answer_id")
public class GapFillingAnswer extends Answer {
    @Column(name = "word")
    private String word;
    /**
     * Η αρίθμηση των κενών ξεκινά από το 0
     */
    @Column(name = "position_in_text")
    private int positionInText;

    @Column(name = "answer_id", insertable=false, updatable=false)
    private Long answerId;

    public GapFillingAnswer() {
    }

    public GapFillingAnswerDto toDto() {
        var gapFillingAnswerDto = new GapFillingAnswerDto();
        BeanUtils.copyProperties(this, gapFillingAnswerDto);
        return gapFillingAnswerDto;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPositionInText() {
        return positionInText;
    }

    public void setPositionInText(int positionInText) {
        this.positionInText = positionInText;
    }

}
