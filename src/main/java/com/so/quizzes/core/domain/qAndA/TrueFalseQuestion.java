package com.so.quizzes.core.domain.qAndA;

import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.dto.basic.qAndA.TrueFalseQuestionDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@DiscriminatorValue("TrueFalseQuestion")
public class TrueFalseQuestion extends MultipleChoiceQuestion {
    public TrueFalseQuestion() {
    }

    public TrueFalseQuestionDto toDto(List<MultipleChoiceAnswerDto> answersDto) {
        var trueFalseQuestionDto = new TrueFalseQuestionDto();
        BeanUtils.copyProperties(this, trueFalseQuestionDto);
        var trueOfFalse = answersDto.stream()
                .filter(MultipleChoiceAnswerDto::getIsCorrect)
                .map(MultipleChoiceAnswerDto::getSelectionText)
                .findFirst()
                .get();
        trueFalseQuestionDto.setTrueOrFalse(Boolean.valueOf(trueOfFalse));
        trueFalseQuestionDto.setQuestionType(this.getClass().getSimpleName());
        return trueFalseQuestionDto;
    }
}
