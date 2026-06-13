package com.payflow.switchservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxEvent {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String topicName;

    @Column(
            columnDefinition = "TEXT"
    )
    private String payload;

    private boolean published;

    private LocalDateTime createdAt;
}