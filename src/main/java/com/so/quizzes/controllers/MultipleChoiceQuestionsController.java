package com.so.quizzes.controllers;

import com.so.quizzes.dto.basic.qAndA.MultipleChoiceQuestionDto;
import com.so.quizzes.services.MultipleChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/multipleChoiceQuestionsWithAnswers")
public class MultipleChoiceQuestionsController {
    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @PostMapping(value = "/question", consumes = "application/json")
    public MultipleChoiceQuestionDto saveMultipleChoiceQuestionAndAnswers(@RequestBody MultipleChoiceQuestionDto question) {
        return multipleChoiceQuestionService.saveMultipleChoiceQuestionAndAnswers(question);
    }

    @GetMapping(value = "/question", produces = "application/json")
    public MultipleChoiceQuestionDto getMultipleChoiceQuestionAndAnswers(@RequestParam Long questionId) {
        return multipleChoiceQuestionService.getQuestion(questionId);
    }
}
