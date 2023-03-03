package com.banqmasr.platform.models;

import com.banqmasr.platform.entities.enums.DeviceStatus;
import lombok.Data;

@Data
public class DeviceModel  implements Model {
    private String id;

    private String imei;
    private String model;
    private DeviceStatus status;

}
