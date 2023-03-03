package com.banqmasr.coreservice.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeviceCommand {
    private String command;
    private long duration;

    public DeviceCommand (String command,long duration)
    {
        this.command = command;
        this.duration = duration;
    }

    public static DeviceCommand getEmptyCommand ()
    {
        return new DeviceCommand("N/A" , 0);
    }
}
