package org.banqmasr.models;

import lombok.Data;
import lombok.ToString;
import org.banqmasr.enums.DeviceStatus;


@Data
@ToString
public class DeviceReqMsgModel implements Model {
    private String id;
    private String deviceImei;
    private Long waterLevel;
    private DeviceStatus status;
    private long ts;

}
