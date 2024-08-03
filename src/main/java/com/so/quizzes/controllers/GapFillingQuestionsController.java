package com.so.quizzes.controllers;

import com.so.quizzes.dto.basic.qAndA.GapFillingQuestionDto;
import com.so.quizzes.services.GapFillingQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gapFillingQuestionsWithAnswers")
public class GapFillingQuestionsController {
    @Autowired
    private GapFillingQuestionService gapFillingQuestionService;

    @GetMapping(value = "/question", produces = "application/json")
    public GapFillingQuestionDto getGapFillingQuestionAndAnswers(@RequestParam Long questionId) {
        return gapFillingQuestionService.getQuestion(questionId);
    }

    @PostMapping(value = "/question", consumes = "application/json")
    public GapFillingQuestionDto saveGapFillingQuestionAndAnswers(@RequestBody GapFillingQuestionDto question) {
        return gapFillingQuestionService.saveGapFillingQuestionAndAnswers(question);
    }
}
