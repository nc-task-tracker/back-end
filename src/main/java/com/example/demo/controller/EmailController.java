package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.model.Issue;
import com.example.demo.service.email.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    private EmailService emailService;
    private ModelMapper modelMapper;

    @Autowired
    public EmailController(EmailService emailService,ModelMapper modelMapper){
        this.emailService = emailService;
        this.modelMapper = modelMapper;
    }

    @PutMapping(value = "/send")
    public ResponseEntity sendEmail(@RequestBody IssueDto issueDto){
        System.out.println(issueDto.toString());
        emailService.sendEmail(modelMapper.map(issueDto, Issue.class));
        return ResponseEntity.noContent().build();
    }
}
