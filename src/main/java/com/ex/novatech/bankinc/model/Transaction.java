package com.ex.novatech.bankinc.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction extends BaseEntity {
    public static int TIME_CANCELLED = 24;

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private Card card;

    @Column(name = "price", columnDefinition = "double precision NOT NULL")
    private double price;

    @Column(name = "canceled", columnDefinition = "boolean NOT NULL DEFAULT false")
    private boolean canceled;

    @Column(name = "canceled_at")
    private Timestamp canceledAt;

    public Transaction() { }
    @Builder
    public Transaction(UUID id, Timestamp createdAt, Card card, double price, boolean canceled, Timestamp canceledAt) {
        super(id, createdAt);
        this.card = card;
        this.price = price;
        this.canceled = canceled;
        this.canceledAt = canceledAt;
    }

    public Transaction(Card card, double price){
        this.card = card;
        this.price = price;
    }

    public boolean canTransactionBeCanceled(){
        LocalDateTime transactionDate = this.getCreatedAt().toLocalDateTime();
        long hoursDifference = transactionDate.until(LocalDateTime.now(), ChronoUnit.HOURS);
        return hoursDifference < Transaction.TIME_CANCELLED;
    }
}
