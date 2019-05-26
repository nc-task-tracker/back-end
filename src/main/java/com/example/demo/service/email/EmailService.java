package com.example.demo.service.email;

import com.example.demo.model.Issue;

public interface EmailService {
    void sendEmail(Issue issue);
}
