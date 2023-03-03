package com.banqmasr.gatewayservice.models;

import lombok.Data;

@Data
public class DeviceReqMsgModel {
    private String deviceImei;

    private Double waterLevel;

    private String status;

    private long ts;
}
