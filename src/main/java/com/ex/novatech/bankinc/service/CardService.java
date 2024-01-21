package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.helper.CardHelper;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Client;
import com.ex.novatech.bankinc.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
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

    public Card blockCard(UUID cardId){
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        card.setBlocked(true);
        card.setBlockedAt(Timestamp.from(Instant.now()));
        return this.cardRepository.save(card);
    }

}
