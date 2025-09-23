package com.rokaly.sge.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "forklifts")
public class Forklift {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String series;
    @Column(unique = true)
    private String internalCode;
    private Double hourMeter;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "forklift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<History> history;
}
