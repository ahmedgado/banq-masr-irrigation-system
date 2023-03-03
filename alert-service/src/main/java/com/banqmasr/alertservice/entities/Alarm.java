package com.banqmasr.alertservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Alarm {
    @Id
    private UUID id;
    @Column
    private String deviceId;
    @Column
    private String alarmMessage;
}
