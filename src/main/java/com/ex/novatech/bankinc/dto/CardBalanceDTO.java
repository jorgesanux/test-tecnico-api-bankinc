package com.ex.novatech.bankinc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardBalanceDTO {
    @NotNull
    private UUID cardId;

    @Positive
    @NotNull
    private double balance;
}
