package com.so.quizzes.controllers;

import com.so.quizzes.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteAnswer(@RequestParam Long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}