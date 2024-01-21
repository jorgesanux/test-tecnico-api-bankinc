package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Client;
import com.ex.novatech.bankinc.repository.CardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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

    private String generateRandomCardNumber(String productId){
        long milliSeconds = Instant.now().toEpochMilli(); //Tiene una longitud de 13 digitos
        String payload = String.valueOf(milliSeconds).substring(3);
        return productId + payload;
    }

    private LocalDate generateCardExpirationDate(){
        LocalDate currentDate = LocalDate.now();
        return LocalDate.of(
                currentDate.getYear() + 3,
                currentDate.getMonth(),
                currentDate.getDayOfYear()
        );
    }

    public Card createCardWithProductId(String productId){
        //TODO: Agregar un faker para los datos del cliente
        Client newClient = new Client("Jorge", "Sanabria");
        this.clientService.save(newClient);

        String cardNumber = this.generateRandomCardNumber(productId);
        LocalDate futureDate = this.generateCardExpirationDate();
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
