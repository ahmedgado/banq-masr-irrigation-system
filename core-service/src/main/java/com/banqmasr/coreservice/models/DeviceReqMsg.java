package com.banqmasr.coreservice.models;

import com.banqmasr.coreservice.entities.enums.DeviceStatus;
import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class DeviceReqMsg  implements Model{
    private String id;
    private String deviceImei;
    private Long waterLevel;
    private DeviceStatus status;
    private long ts;

}
