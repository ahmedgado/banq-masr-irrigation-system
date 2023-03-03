package com.banqmasr.coreservice.models;

import com.banqmasr.coreservice.entities.enums.DeviceStatus;
import lombok.Data;

@Data
public class DeviceModel  implements Model{
    private String id;

    private String imei;
    private String model;
    private DeviceStatus status;

}
