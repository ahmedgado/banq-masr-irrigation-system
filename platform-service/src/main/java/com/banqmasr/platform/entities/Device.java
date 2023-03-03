package com.banqmasr.platform.entities;
import com.banqmasr.platform.entities.enums.DeviceStatus;
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
