package ru.theft.similarity.mail.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.theft.similarity.mail.service.EmailService;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @PostMapping("/sendHtml")
    public ResponseEntity<?> sendHtmlEmail(@RequestParam String to,
                                                @RequestParam String subject) throws MessagingException {
        emailService.sendHtmlMessage(to, subject, "notification");
        return ResponseEntity
                .status(200)
                .body("Уведомление было успешно отправлено на почту: " + to
                        + "\nЗаголовок: " + subject);
    }
}