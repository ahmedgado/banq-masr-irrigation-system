package com.banqmasr.platform.entities;

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

    public Region(String name, Zone zone) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.zone = zone;
    }

    public Region() {

    }
}
