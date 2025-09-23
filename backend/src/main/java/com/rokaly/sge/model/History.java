package com.rokaly.sge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double hourMeter;
    private String description;
    private String dateTime;
    @ManyToOne
    private Forklift forklift;
}
