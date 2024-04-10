package com.kaushik_phase.kaushik_phase.controller;

import com.kaushik_phase.kaushik_phase.model.Mail;
import com.kaushik_phase.kaushik_phase.model.Token;
import com.kaushik_phase.kaushik_phase.repository.MailRepository;
import com.kaushik_phase.kaushik_phase.repository.TokenRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.kaushik_phase.kaushik_phase.service.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

import static org.slf4j.Logger.*;

@RestController
public class MailController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MailRepository mailRepository;

    public String extractToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            return null;
        }
    }

    @Transactional
    @PostMapping("/api/mail/send")
    public void sendMail(@RequestBody Mail mail, @RequestHeader("Authorization") String authorizationHeader) {

        String tokenValue = extractToken(authorizationHeader);
        if (tokenValue !=null) {
            mail.setState(Mail.MailState.QUEUED);
            mailRepository.save(mail);
            mailRepository.flush();
        } else {
             throw new RuntimeException("Invalid token");
        }

    }

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/generate-token")
    public String generateToken(@RequestHeader("operation") String operationHeader) {
        if ("generate".equals(operationHeader)) {
            String tokenValue = JwtTokenProvider.generate1Token();

            Token token = new Token();
            token.setValue(tokenValue);
            tokenRepository.save(token);

            return tokenValue;
        } else {
            return "Invalid operation Header";
        }
    }

    @GetMapping("/{id}")
    public Optional<Mail> getMailById(@PathVariable Long id) {
        return mailRepository.findById(id);
    }

    @GetMapping("/all")
    public List<Mail> getAllMails() {
        return mailRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteMail(@PathVariable Long id) {
        mailRepository.deleteById(id);
    }

}
