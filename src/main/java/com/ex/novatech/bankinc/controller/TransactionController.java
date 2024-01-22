package com.ex.novatech.bankinc.controller;

import com.ex.novatech.bankinc.dto.TransactionCancellationDTO;
import com.ex.novatech.bankinc.dto.TransactionCreateDTO;
import com.ex.novatech.bankinc.model.Transaction;
import com.ex.novatech.bankinc.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {
    final private TransactionService transactionService;

    public TransactionController(final TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction getTransactionById(
            @PathVariable @UUID @NotEmpty @NotBlank String transactionId
    ){
        return this.transactionService.getTransactionById(java.util.UUID.fromString(transactionId));
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction transactionPurchase(@Valid @RequestBody TransactionCreateDTO body){
        return this.transactionService.createTransaction(body.getCardId(), body.getPrice());
    }

    @PostMapping("/anulation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transactionCancellation(@Valid @RequestBody TransactionCancellationDTO body){
        this.transactionService.transactionCancellation(body.getCardId(), body.getTransactionId());
    }
}
