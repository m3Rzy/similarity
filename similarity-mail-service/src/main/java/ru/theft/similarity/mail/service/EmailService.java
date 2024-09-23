package ru.theft.similarity.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    public void sendHtmlMessage(String to, String subject, String titleTemplate, String taskTitle)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        Context context = new Context();
        context.setVariable("taskName", taskTitle);

        String htmlContent = templateEngine.process(titleTemplate, context);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
