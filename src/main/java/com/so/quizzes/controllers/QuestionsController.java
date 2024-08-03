package com.so.quizzes.controllers;

import com.so.quizzes.dto.basic.qAndA.QuestionDto;
import com.so.quizzes.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/questionsWithAnswers")
public class QuestionsController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/index/questions")
    public List<QuestionDto> getQuestions() {
        return questionService.getQuestions();
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteQuestion(@RequestParam Long questionId) {
        var done = questionService.deleteQuestionById(questionId);
        return new ResponseEntity<>(done, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<QuestionDto> getQuestionsRelevantToTags(@RequestParam String tagsSeparatedByComma) {
        return questionService.getQuestionsRelevantToTags(tagsSeparatedByComma);
    }

    @GetMapping("/getTypeOfQuestion")
    public ResponseEntity<String> getTypeOfQuestion(@RequestParam Long questionId) {
        return new ResponseEntity<>(questionService.getTypeOfQuestion(questionId), HttpStatus.OK);
    }
}
