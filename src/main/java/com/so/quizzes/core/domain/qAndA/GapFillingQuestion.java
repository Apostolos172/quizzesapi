package com.so.quizzes.core.domain.qAndA;

import com.so.quizzes.dto.basic.qAndA.GapFillingQuestionDto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

@Entity
@DiscriminatorValue("GapFillingQuestion")
public class GapFillingQuestion extends Question {
    public static String GAP_TEXT_PATTERN = "@@@";
    @Column(name = "number_of_gaps")
    private Integer numberOfGaps = 0;

    public GapFillingQuestion() {
        super();
    }

    public GapFillingQuestionDto toDto() {
        var gapFillingQuestionDto = new GapFillingQuestionDto();
        BeanUtils.copyProperties(this, gapFillingQuestionDto);
        gapFillingQuestionDto.setQuestionType(this.getClass().getSimpleName());
        return gapFillingQuestionDto;
    }

    public Integer getNumberOfGaps() {
        return numberOfGaps;
    }

    public void setNumberOfGaps(Integer numberOfGaps) {
        this.numberOfGaps = numberOfGaps;
    }

    public Integer calculateTheNumberOfGaps() {
        return StringUtils.countOccurrencesOf(this.getPronunciation(), GAP_TEXT_PATTERN);
    }
}
