package com.banqmasr.gatewayservice.entities;

import com.banqmasr.gatewayservice.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;

@Entity
@Data
@ToString
public class DeviceReqMsg {
    @Id
    private UUID id;
    @Column
    private String deviceImei;
    @Column
    private Double waterLevel;
    @Column
    private DeviceStatus status;
    @Column
    private long ts;

}
