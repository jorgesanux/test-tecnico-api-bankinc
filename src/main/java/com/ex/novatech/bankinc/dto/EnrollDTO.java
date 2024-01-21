package com.ex.novatech.bankinc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EnrollDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID cardId;
}
