package com.so.quizzes.controllers;

import com.so.quizzes.services.MultipleChoiceAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/multipleChoiceAnswersForQuestionView")
public class MultipleChoiceAnswersController {
    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;

    @GetMapping("/ofQuestion")
    public List<Long> getAnswersIds(@RequestParam Long questionId) {
        return multipleChoiceAnswerService.getAnswersIds(questionId);
    }
}
