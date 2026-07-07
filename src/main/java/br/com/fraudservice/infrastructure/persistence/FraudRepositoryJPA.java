package br.com.fraudservice.infrastructure.persistence;

import br.com.fraudservice.domain.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FraudRepositoryJPA extends JpaRepository<FraudAlert, UUID> {
}
