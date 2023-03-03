package com.banqmasr.coreservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Region {
    @Id
    private UUID id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn
    private Zone zone;
}
