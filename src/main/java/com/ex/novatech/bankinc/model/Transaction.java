package com.ex.novatech.bankinc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

@Entity
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private Card card;

    @Column(name = "canceled", columnDefinition = "boolean NOT NULL DEFAULT false")
    private boolean canceled;

    @Column(name = "canceled_at")
    private Timestamp canceled_at;
}
