package com.okten.carservice.service;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.sendFrom}")
    private final String sendFrom;

    @SneakyThrows
    public void sendMail(String to, String subject, String text, byte[] attachment) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        DataSource attachmentDataSource = new ByteArrayDataSource(attachment, "image/png");
        mimeMessageHelper.addAttachment("car-log", attachmentDataSource);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom(sendFrom);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);

        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendMail(String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom(sendFrom);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);

        mailSender.send(mimeMessage);
    }
}
