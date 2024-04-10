package com.kaushik_phase.kaushik_phase.repository;

import com.kaushik_phase.kaushik_phase.model.Mail;
import com.kaushik_phase.kaushik_phase.model.Mail.MailState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailScheduler {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Value("${mail.cron.expression}")
    private String cronExpression;

    @Scheduled(cron = "${mail.cron.expression}")
    public void dispatchMails() {
        System.out.println("Scheduled task for sending emails triggered.");

        List<Mail> mailsToSend = mailRepository.findByState(Mail.MailState.QUEUED);

        for (Mail mail : mailsToSend) {
            sendEmail(mail);
        }

        System.out.println("Scheduled task for sending emails completed.");
    }

    private void sendEmail(Mail mail) {
        System.out.println("Sending email with details: " + mail);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setCc(mail.getCc());
            message.setBcc(mail.getBcc());
            message.setText(mail.getBody());

            emailSender.send(message);
            mail.setState(Mail.MailState.SENT);
            mailRepository.save(mail);

            System.out.println("Email sent successfully.");

        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}