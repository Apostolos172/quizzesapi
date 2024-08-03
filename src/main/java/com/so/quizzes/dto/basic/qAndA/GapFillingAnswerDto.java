package com.so.quizzes.dto.basic.qAndA;

import com.so.quizzes.core.domain.qAndA.GapFillingAnswer;
import org.springframework.beans.BeanUtils;

/**
 * DTO for {@link GapFillingAnswer}
 */
public class GapFillingAnswerDto extends AnswerDto{
    private String word;
    private int positionInText;

    public GapFillingAnswerDto() {
    }

    public GapFillingAnswerDto(String word, int positionInText) {
        this.word = word;
        this.positionInText = positionInText;
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

    public GapFillingAnswer toEntity() {
        var gapFillingAnswer = new GapFillingAnswer();
        BeanUtils.copyProperties(this, gapFillingAnswer);
        return gapFillingAnswer;
    }

    @Override
    public String toString() {
        return "GapFillingAnswerDto{" +
                "word='" + word + '\'' +
                ", positionInText=" + positionInText +
                '}';
    }
}