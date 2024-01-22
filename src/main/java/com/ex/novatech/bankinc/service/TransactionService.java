package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.helper.DateHelper;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Transaction;
import com.ex.novatech.bankinc.repository.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionService {
    final private TransactionRepository transactionRepository;
    final private CardService cardService;

    public TransactionService(
            final TransactionRepository transactionRepository,
            final CardService cardService
    ) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Transaction createTransaction(UUID cardId, double price) throws ConstraintViolationException{
        Card card = this.cardService.getCardById(cardId).orElseThrow();
        Transaction transaction = new Transaction(card, price);

        if(!this.cardService.isCardValid(card, DateHelper.generateCurrentFirstDayDate()))
            throw new ConstraintViolationException("Card is expired", null);

        if(!card.isActive())
            throw new ConstraintViolationException("Card is not active", null);

        if(card.isBlocked())
            throw new ConstraintViolationException("Card is blocked", null);

        this.cardService.subtractFromBalance(card, price);
        return this.transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(UUID transactionId){
        return this.transactionRepository.findById(transactionId).orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionCancellation(UUID cardId, UUID transactionId){
        Card card = this.cardService.getCardById(cardId).orElseThrow();
        Transaction transaction = this.transactionRepository.findById(transactionId).orElseThrow();

        if(!transaction.canTransactionBeCanceled())
            throw new ConstraintViolationException("The transaction exceeded the time allowed for cancellation.", null);

        this.cardService.addBalance(card, transaction.getPrice());
        transaction.setCanceled(true);
        transaction.setCanceledAt(Timestamp.from(Instant.now()));
    }
}
