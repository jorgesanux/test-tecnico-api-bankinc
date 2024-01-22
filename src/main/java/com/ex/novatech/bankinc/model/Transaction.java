package com.ex.novatech.bankinc.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private Card card;

    @Column(name = "price", columnDefinition = "double precision NOT NULL")
    private double price;

    @Column(name = "canceled", columnDefinition = "boolean NOT NULL DEFAULT false")
    private boolean canceled;

    @Column(name = "canceled_at")
    private Timestamp canceled_at;

    public Transaction() { }
    @Builder
    public Transaction(UUID id, Timestamp createdAt, Card card, double price, boolean canceled, Timestamp canceled_at) {
        super(id, createdAt);
        this.card = card;
        this.price = price;
        this.canceled = canceled;
        this.canceled_at = canceled_at;
    }

    public Transaction(Card card, double price){
        this.card = card;
        this.price = price;
    }
}
