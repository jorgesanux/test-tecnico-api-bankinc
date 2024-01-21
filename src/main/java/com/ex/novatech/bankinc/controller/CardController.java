package com.ex.novatech.bankinc.controller;

import com.ex.novatech.bankinc.dto.EnrollDTO;
import com.ex.novatech.bankinc.model.Card;
import com.ex.novatech.bankinc.service.CardService;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UUID;
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
    public Card createCardWithProductId(
            @PathVariable @Size(min = 6, max = 6) @Positive String productId
    ){
        return this.cardService.createCardWithProductId(productId);
    }

    @PostMapping("/enroll")
    public Card enrollCard(@RequestBody EnrollDTO body){
        return this.cardService.enrollCard(body.getCardId());
    }

    @DeleteMapping("/{cardId}")
    public Card blockCard(
            @PathVariable @UUID @NotEmpty @NotBlank String cardId
    ){
        return this.cardService.blockCard(java.util.UUID.fromString(cardId));
    }

}
