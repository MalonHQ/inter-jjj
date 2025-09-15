package com.example.staffps.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}