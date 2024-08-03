package com.so.quizzes.services.impl;

import com.so.quizzes.core.domain.qAndA.MultipleChoiceAnswer;
import com.so.quizzes.dto.basic.qAndA.MultipleChoiceAnswerDto;
import com.so.quizzes.services.AnswerService;
import com.so.quizzes.services.MultipleChoiceAnswerService;
import com.so.quizzes.services.TrueFalseAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrueFalseAnswerServiceImpl implements TrueFalseAnswerService {
    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;
    @Autowired
    private AnswerService answerService;

    @Override
    public void saveAnswersOfTrueFalseQuestion(Long questionId, Boolean trueOrFalse) {
        multipleChoiceAnswerService.deleteAnswersByQuestionId(questionId);
        var possibleAnswers = new ArrayList<>(List.of(
                new MultipleChoiceAnswer(Boolean.toString(trueOrFalse), true),
                new MultipleChoiceAnswer(Boolean.toString(!trueOrFalse), false)));
        var possibleAnswersDto = new ArrayList<MultipleChoiceAnswerDto>();
        for (MultipleChoiceAnswer multipleChoiceAnswer : possibleAnswers) {
            var tempDto = multipleChoiceAnswer.toDto();
            tempDto.setQuestionId(questionId);
            possibleAnswersDto.add(tempDto);
        }
        multipleChoiceAnswerService.saveMultipleChoiceAnswerList(possibleAnswersDto);
    }

    @Override
    public void deleteAnswers(Long questionId) {
        multipleChoiceAnswerService.getMultipleChoiceAnswersByQuestionId(questionId)
                .forEach(multipleChoiceAnswerDto -> answerService.deleteAnswer(multipleChoiceAnswerDto.getId()));
    }
}
