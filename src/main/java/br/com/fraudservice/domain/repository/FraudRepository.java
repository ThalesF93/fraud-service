package br.com.fraudservice.domain.repository;

import br.com.fraudservice.domain.entity.FraudAlert;

import java.util.Optional;
import java.util.UUID;

public interface FraudRepository {
    void save(FraudAlert fraud);

    Optional<FraudAlert> findById(UUID uuid);


}
