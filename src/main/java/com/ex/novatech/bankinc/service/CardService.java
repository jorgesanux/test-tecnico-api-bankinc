package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.helper.CardHelper;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Client;
import com.ex.novatech.bankinc.repository.CardRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.constraintvalidation.HibernateConstraintViolationBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final ClientService clientService;

    public CardService(
            final CardRepository cardRepository,
            final ClientService clientService
    ){
        this.cardRepository = cardRepository;
        this.clientService = clientService;
    }

    public Card createCardWithProductId(String productId){
        //TODO: Agregar un faker para los datos del cliente
        Client newClient = new Client("Jorge", "Sanabria");
        this.clientService.save(newClient);

        String cardNumber = CardHelper.generateRandomCardNumber(productId);
        LocalDate futureDate = CardHelper.generateCardExpirationDate();
        Card newCard = Card.builder()
                .cardNumber(cardNumber)
                .type(Card.CardType.CREDIT_CARD) //TODO: Agregar faker
                .expirationMonth(futureDate.getMonthValue())
                .expirationYear(futureDate.getYear())
                .balance(0.0)
                .status(Card.CardStatus.INACTIVE)
                .client(newClient)
                .build();
        return this.cardRepository.saveAndFlush(newCard);
    }

    public Card enrollCard(UUID cardId){
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        card.setStatus(Card.CardStatus.ACTIVE);
        return this.cardRepository.save(card);
    }

    public void blockCard(UUID cardId){
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        card.setBlocked(true);
        card.setBlockedAt(Timestamp.from(Instant.now()));
        this.cardRepository.save(card);
    }

    public Card addBalance(UUID cardId, double balance){
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        double currentBalance = card.getBalance();
        card.setBalance(currentBalance + balance);
        return this.cardRepository.save(card);
    }

    public Optional<Card> getCardById(UUID cardId){
        return this.cardRepository.findById(cardId);
    }

    public Card save(Card card){
        return this.cardRepository.save(card);
    }

    public void subtractFromBalance(Card card, double value) throws ConstraintViolationException{
        double result = card.getBalance() - value;
        if(result < 0)
            throw new ConstraintViolationException(String.format("There is not enough balance to deduct %.2f", value), null);
        card.setBalance(result);
    }

}
