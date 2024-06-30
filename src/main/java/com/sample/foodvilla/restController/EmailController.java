package com.sample.foodvilla.restController;

import com.sample.foodvilla.entity.dto.EmailRequest;
import com.sample.foodvilla.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = "recipient@example.com"; // change to actual recipient email
        String subject = "Feedback from " + emailRequest.getFullName();
        String text = emailRequest.getMessage();
        emailService.sendEmail(to, subject, text);
    }
}
