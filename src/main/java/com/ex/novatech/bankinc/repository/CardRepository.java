package com.ex.novatech.bankinc.repository;

import com.ex.novatech.bankinc.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {

}
