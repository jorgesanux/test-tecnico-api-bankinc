package com.ex.novatech.bankinc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDTO {
    private Date timestamp = new Date();
    private int status;
    private String path;
    private List<String> errors = new ArrayList<>();

    public ErrorDTO(){ }

    public ErrorDTO(int status, String path) {
        this.status = status;
        this.path = path;
    }

    public void addError(String message) {
        this.errors.add(message);
    }
}