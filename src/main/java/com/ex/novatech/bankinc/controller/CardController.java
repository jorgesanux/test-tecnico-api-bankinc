package com.ex.novatech.bankinc.controller;

import com.ex.novatech.bankinc.dto.CardBalanceDTO;
import com.ex.novatech.bankinc.dto.CardEnrollDTO;
import com.ex.novatech.bankinc.helper.CardHelper;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.service.CardService;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@Validated
public class CardController {

    private final CardService cardService;

    public CardController(final CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/{productId}/number")
    @ResponseStatus(HttpStatus.CREATED)
    public Card createCardWithProductId(
            @PathVariable @Size(min = 6, max = 6) @Positive String productId
    ){
        return this.cardService.createCardWithProductId(productId);
    }

    @PostMapping("/enroll")
    @ResponseStatus(HttpStatus.OK)
    public Card enrollCard(@RequestBody CardEnrollDTO body){
        return this.cardService.enrollCard(body.getCardId());
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void blockCard(
            @PathVariable @UUID @NotEmpty @NotBlank String cardId
    ){
        this.cardService.blockCard(java.util.UUID.fromString(cardId));
    }

    @PostMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public Card addBalanceToCard(@RequestBody CardBalanceDTO body){
        return this.cardService.addBalance(body.getCardId(), body.getBalance());
    }

    @GetMapping("/balance/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardBalanceDTO getCardBalance(
            @PathVariable @UUID @NotEmpty @NotBlank String cardId
    ){
        Card card = this.cardService
                .getCardById(java.util.UUID.fromString(cardId))
                .orElseThrow();
        return CardHelper.mapCardToBalanceDTO(card);
    }

}
