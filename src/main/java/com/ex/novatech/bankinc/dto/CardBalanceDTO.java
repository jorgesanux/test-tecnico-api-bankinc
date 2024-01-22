package com.ex.novatech.bankinc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID cardId;

    @Positive
    @NotNull
    private double balance;
}
