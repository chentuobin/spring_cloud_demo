package com.interestgroup.webappinterview.controller;


import com.google.gson.Gson;
import com.interestgroup.webappinterview.model.ExaminationPaper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public String greetingForm(Model model, @PathVariable("id") Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8004/";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + id, String.class);
        String body = response.getBody();
        Gson gson = new Gson();
        ExaminationPaper examinationPaper = gson.fromJson(body, ExaminationPaper.class);
        model.addAttribute("examinationPaper", examinationPaper);
        return "index";
    }

}