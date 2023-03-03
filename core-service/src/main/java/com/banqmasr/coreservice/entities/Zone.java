package com.banqmasr.coreservice.entities;

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
}
