package com.kaushik_phase.kaushik_phase.model;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String subject;
    @Column(name = "recipient")
    private String to;
    @Column(name = "carbon_copy")
    private String cc;
    @Column(name = "blind_carbon_copy")
    private String bcc;
    @Column(columnDefinition = "TEXT")
    private String body;

    public enum MailState {
        QUEUED,SENT;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MailState state;
    public Mail() {
    }

    public Mail(String subject, String to, String cc, String bcc, String body) {
        this.subject = subject;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.body = body;
        this.state = MailState.QUEUED;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public MailState getState() {
        return state;
    }

    public void setState(MailState state) {
        this.state = state;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}