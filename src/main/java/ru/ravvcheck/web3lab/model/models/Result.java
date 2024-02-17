package ru.ravvcheck.web3lab.model.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "results")
public class Result implements Serializable {
    @Id
    @SequenceGenerator(name = "result_seq", sequenceName = "SEQ_RESULTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_seq")
    @Column(nullable = false, updatable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "x")
    private Double x;

    @Column(nullable = false, name = "y")
    private Double y;

    @Column(nullable = false, name = "r")
    private Double r;

    @Column(nullable = false, name = "hit")
    private boolean isHit;

    @Column(nullable = false, name = "currentTime")
    private String currentTime;
}
