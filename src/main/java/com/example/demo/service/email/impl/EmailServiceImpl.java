package com.example.demo.service.email.impl;

import com.example.demo.dto.IssueDto;
import com.example.demo.model.Issue;
import com.example.demo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Service
public class EmailServiceImpl implements EmailService {
    private final String SUBJECT = "Ticket was removed!";

    private JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Issue issue) {
        if(issue.getReporter() == null || issue.getAssignee() == null)
            return;

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(new String[]{issue.getAssignee().getEmail(),issue.getReporter().getEmail()});
            helper.setSubject(SUBJECT);
            helper.setText(String.format("Ticket that has name %s was removed!",issue.getIssueName()));

            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
