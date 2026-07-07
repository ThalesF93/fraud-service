package br.com.fraudservice.domain.entity;

import br.com.fraudservice.domain.enums.FraudScore;
import br.com.fraudservice.domain.enums.FraudStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class FraudAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID transactionId;

    @Column
    private UUID accountId;

    @Column
    @Enumerated(EnumType.STRING)
    private FraudScore score;

    @Column
    @Enumerated(EnumType.STRING)
    private FraudStatus status;

    @Column
    private String ruleTriggered;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    }


