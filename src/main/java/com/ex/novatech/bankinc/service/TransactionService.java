package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.helper.DateHelper;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Transaction;
import com.ex.novatech.bankinc.repository.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        this.cardService.save(card);
        return this.transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(UUID transactionId){
        return this.transactionRepository.findById(transactionId).orElseThrow();
    }
}
