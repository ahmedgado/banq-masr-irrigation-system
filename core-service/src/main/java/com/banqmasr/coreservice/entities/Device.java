package com.banqmasr.coreservice.entities;
import com.banqmasr.coreservice.entities.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.Data;
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
