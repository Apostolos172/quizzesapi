package com.so.quizzes.controllers;

import com.so.quizzes.dto.basic.qAndA.TrueFalseQuestionDto;
import com.so.quizzes.services.TrueFalseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/trueFalseQuestionsWithAnswers")
public class TrueFalseQuestionsController {
    @Autowired
    private TrueFalseQuestionService trueFalseQuestionService;

    @PostMapping(value = "/question", consumes = "application/json")
    public TrueFalseQuestionDto saveTrueFalseQuestionAndAnswers(@RequestBody TrueFalseQuestionDto question) {
        return trueFalseQuestionService.saveTrueFalseQuestionAndAnswers(question);
    }

    @GetMapping(value = "/question", produces = "application/json")
    public TrueFalseQuestionDto getTrueFalseQuestionAndAnswers(@RequestParam Long questionId) {
        return trueFalseQuestionService.getQuestion(questionId);
    }
}
