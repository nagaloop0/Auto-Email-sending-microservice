package com.kaushik_phase.kaushik_phase.model;

import javax.persistence.*;
@Entity
@Table(name = "Token_table")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
