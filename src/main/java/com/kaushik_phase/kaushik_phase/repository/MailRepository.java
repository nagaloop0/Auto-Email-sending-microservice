package com.kaushik_phase.kaushik_phase.repository;

import com.kaushik_phase.kaushik_phase.model.Mail;
import com.kaushik_phase.kaushik_phase.model.Mail.MailState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    List<Mail> findByState(MailState state);

}