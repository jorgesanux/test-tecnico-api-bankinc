package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Transaction;
import com.ex.novatech.bankinc.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private CardService cardService;

    public TransactionService(
            final TransactionRepository transactionRepository,
            final CardService cardService
    ) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    public Transaction createTransaction(UUID cardId, double price){
        Card card = this.cardService.getCardById(cardId).orElseThrow();
        Transaction transaction = new Transaction(card, price);

        this.cardService.subtractFromBalance(card, price);
        this.cardService.save(card);
        return this.transactionRepository.save(transaction);
    }
}
