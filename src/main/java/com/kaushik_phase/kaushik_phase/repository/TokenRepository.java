package com.kaushik_phase.kaushik_phase.repository;

import com.kaushik_phase.kaushik_phase.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByValue(String value);
}
