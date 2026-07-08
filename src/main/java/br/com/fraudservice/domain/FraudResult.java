package br.com.fraudservice.domain;

import br.com.fraudservice.domain.enums.FraudScore;

import java.util.List;

public record FraudResult(FraudScore score, List<String> triggeredRules) {
}
