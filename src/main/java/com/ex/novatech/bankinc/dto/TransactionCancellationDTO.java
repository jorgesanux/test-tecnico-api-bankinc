package com.ex.novatech.bankinc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionCancellationDTO {
    @NotNull
    private UUID cardId;

    @NotNull
    private UUID transactionId;
}
