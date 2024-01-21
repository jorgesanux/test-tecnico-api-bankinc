package com.ex.novatech.bankinc.repository;

import com.ex.novatech.bankinc.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}
