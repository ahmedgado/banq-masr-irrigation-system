package com.banqmasr.platform.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Zone {
    @Id
    private UUID id;
    @Column
    private String name;

    public Zone(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Zone() {

    }
}
