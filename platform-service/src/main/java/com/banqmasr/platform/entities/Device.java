package com.banqmasr.platform.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.banqmasr.enums.DeviceStatus;

import java.util.UUID;

@Entity
@Data
public class Device {
    @Id
    private UUID id;
    @Column
    private String imei;
    @Column
    private String model;
    @Column
    private DeviceStatus status;
    @Column
    private long waterLevelPerMin;
}
