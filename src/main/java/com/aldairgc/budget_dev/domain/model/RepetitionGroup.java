package com.aldairgc.budget_dev.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "repetition_group")
public class RepetitionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RepeatEnum repeat;

    @OneToOne
    @JoinColumn(name = "original_id")
    private Transaction original;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repetitionGroup")
    private List<Transaction> repetitions;

}
