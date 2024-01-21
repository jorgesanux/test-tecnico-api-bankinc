package com.ex.novatech.bankinc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends BaseEntity {
    @NotNull
    @Size(max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "lastname", nullable = false, length = 20)
    private String lastName;

    public Client() { }

    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
