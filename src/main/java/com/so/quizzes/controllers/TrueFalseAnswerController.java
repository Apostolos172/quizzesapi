package com.so.quizzes.controllers;

import com.so.quizzes.services.TrueFalseAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/true-false-answers")
public class TrueFalseAnswerController {
    @Autowired
    private TrueFalseAnswerService trueFalseAnswerService;

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteAnswersByQuestionId(@RequestParam Long questionId) {
        trueFalseAnswerService.deleteAnswers(questionId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}