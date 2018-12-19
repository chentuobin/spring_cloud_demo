package com.interestgroup.microservicepaper.controller;

import com.interestgroup.microservicepaper.Repository.ExaminationPaperRepository;
import com.interestgroup.microservicepaper.model.ExaminationPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExaminationPaperController {
    @Autowired
    private ExaminationPaperRepository examinationPaperRepository;

    @GetMapping("/{examinationPaperId}")
    public Optional<ExaminationPaper> compile(@PathVariable String examinationPaperId) {
        Optional<ExaminationPaper> eaminationPaper = examinationPaperRepository.findById(Long.valueOf(examinationPaperId));
        return eaminationPaper;
    }

}