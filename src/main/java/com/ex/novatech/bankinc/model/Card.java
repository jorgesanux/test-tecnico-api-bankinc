package com.ex.novatech.bankinc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card extends BaseEntity {
    public enum CardStatus {ACTIVE, INACTIVE }
    public enum CardType { CREDIT_CARD, DEBIT_CARD}

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @NotNull
    @Size(max = 16)
    @Column(name = "card_number", length = 16, nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "expiration_month", nullable = false)
    private int expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private int expirationYear;

    @NotNull
    @Column(name = "balance", columnDefinition = "double precision NOT NULL DEFAULT 0")
    private Double balance;

    //TODO: Ajustar la logica para que se cree el type del enum en la base de datos
    @NotNull
    @Column(name = "type", columnDefinition = "varchar(11) NOT NULL")
    @Enumerated(EnumType.STRING)
    private CardType type;

    @NotNull
    @Column(name = "blocked", columnDefinition = "boolean NOT NULL DEFAULT false")
    private boolean blocked;

    @Column(name = "blocked_at")
    private Timestamp blockedAt;

    //TODO: Ajustar la logica para que se cree el type del enum en la base de datos
    @NotNull
    @Column(name = "status", columnDefinition = "varchar(10) NOT NULL DEFAULT 'inactive'")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    public Card() { }

    @Builder
    public Card(UUID id, Client client, String cardNumber, int expirationMonth, int expirationYear, Double balance, CardType type, boolean blocked, Timestamp blockedAt, CardStatus status, Timestamp createdAt) {
        super(id, createdAt);
        this.client = client;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.balance = balance;
        this.type = type;
        this.blocked = blocked;
        this.blockedAt = blockedAt;
        this.status = status;
    }
}
