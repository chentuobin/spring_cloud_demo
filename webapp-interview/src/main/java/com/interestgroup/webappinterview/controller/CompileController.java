package com.interestgroup.webappinterview.controller;

import com.interestgroup.webappinterview.model.Code;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class  CompileController {

    @PostMapping("/compile")
    public @ResponseBody
    String greetingSubmit(@ModelAttribute Code code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String base64encodedString = Base64.getEncoder().encodeToString(code.getContent().getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 比那么字符串 (基本) :" + base64encodedString);
        String fooResourceUrl
                = "http://localhost:8002";
        System.out.println(123);
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + base64encodedString, String.class);
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        //compile(greeting.getContent());
        return response.getBody();
    }

}