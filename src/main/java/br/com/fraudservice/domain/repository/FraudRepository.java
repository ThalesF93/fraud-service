package br.com.fraudservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

public interface FraudRepository {
    void save(FraudRequirement fraud);

    Optional<FraudRequirement> findById(UUID uuid);


}
