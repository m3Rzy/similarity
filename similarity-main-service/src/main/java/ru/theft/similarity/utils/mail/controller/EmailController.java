package ru.theft.similarity.utils.mail.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.theft.similarity.utils.mail.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Отправка простого письма
    @PostMapping("/sendSimple")
    public String sendSimpleEmail(@RequestParam String to,
                                  @RequestParam String subject,
                                  @RequestParam String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return "Simple email sent to " + to;
    }

    // Отправка HTML письма
    @PostMapping("/sendHtml")
    public String sendHtmlEmail(@RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String htmlContent) throws MessagingException {
        emailService.sendHtmlMessage(to, subject, htmlContent);
        return "HTML email sent to " + to;
    }
}