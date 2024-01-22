package com.ex.novatech.bankinc.controller;

import com.ex.novatech.bankinc.dto.CardEnrollDTO;
import com.ex.novatech.bankinc.dto.TransactionCreateDTO;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.model.Transaction;
import com.ex.novatech.bankinc.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(final TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.OK)
    public Transaction transactionPurchase(@Valid @RequestBody TransactionCreateDTO body){
        return this.transactionService.createTransaction(body.getCardId(), body.getPrice());
    }
}
