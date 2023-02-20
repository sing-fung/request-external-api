package com.singfung.demo.controller;

import com.singfung.demo.model.dto.AuthDTO;
import com.singfung.demo.model.dto.SecurityQuestion;
import com.singfung.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sing-fung
 * @since 2/18/2023
 */

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    private DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // List, without token
    @GetMapping("/questions")
    public ResponseEntity<?> getAllSecurityQuestions() {
        List<SecurityQuestion> questions = demoService.getAllSecurityQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/token")
    public ResponseEntity<?> callVerifyTokenAPI(@RequestBody @Validated AuthDTO authDTO) {
        String repsonse = demoService.verifyToken(authDTO.getUsernameOrEmail(), authDTO.getPassword());
        return ResponseEntity.ok(repsonse);
    }
}
