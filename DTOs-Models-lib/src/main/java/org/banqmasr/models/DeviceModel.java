package org.banqmasr.models;

import org.banqmasr.enums.DeviceStatus;
import lombok.Data;

@Data
public class DeviceModel  implements Model {
    private String id;

    private String imei;
    private String model;
    private DeviceStatus status;

    private long waterLevelPerMin;

}
