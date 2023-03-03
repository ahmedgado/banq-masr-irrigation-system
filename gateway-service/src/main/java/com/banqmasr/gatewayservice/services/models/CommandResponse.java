package com.banqmasr.gatewayservice.services.models;

import lombok.Data;

@Data
public class CommandResponse {
    private String command;
    private long duration;
}
