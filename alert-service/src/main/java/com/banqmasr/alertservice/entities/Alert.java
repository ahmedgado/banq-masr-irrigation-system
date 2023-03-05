package com.banqmasr.alertservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Alert {
    @Id
    private UUID id;
    @Column
    private String alertMessage;

}
