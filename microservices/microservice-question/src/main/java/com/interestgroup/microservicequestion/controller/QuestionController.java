package com.interestgroup.microservicequestion.controller;

import com.interestgroup.microservicequestion.Repository.QuestionRepository;
import com.interestgroup.microservicequestion.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/{questionId}")
    public Optional<Question> compile(@PathVariable String questionId) {
        Optional<Question> question = questionRepository.findById(Long.valueOf(questionId));
        return question;
    }

}