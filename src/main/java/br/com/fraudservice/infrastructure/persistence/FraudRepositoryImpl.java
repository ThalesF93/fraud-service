package br.com.fraudservice.infrastructure.persistence;

import br.com.fraudservice.domain.entity.FraudAlert;
import br.com.fraudservice.domain.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FraudRepositoryImpl implements FraudRepository {

    private final FraudRepositoryJPA repository;

    @Override
    public void save(FraudAlert fraud) {
        repository.save(fraud);
    }

    @Override
    public Optional<FraudAlert> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
