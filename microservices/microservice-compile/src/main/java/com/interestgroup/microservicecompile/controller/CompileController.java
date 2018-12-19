package com.interestgroup.microservicecompile.controller;

import com.interestgroup.microservicecompile.model.CompileResult;
import com.interestgroup.microservicecompile.services.JavaCompilerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class CompileController {
    @GetMapping("/{base64Code}")
    public CompileResult compile(@PathVariable String base64Code){
        String code;
        String result;
        CompileResult compileResult;

        code = decodeBse64Code(base64Code);

        result = new JavaCompilerImpl(code).compileAndRun();
        compileResult = handleCompileResult(result);
        return compileResult;
    }

    private String decodeBse64Code(String base64Code){
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64Code);
        return new String(base64decodedBytes, StandardCharsets.UTF_8);
    }

    private CompileResult handleCompileResult(String result) {
        CompileResult compileResult = new CompileResult();
        compileResult.setResult(result);
        return compileResult;
    }

}