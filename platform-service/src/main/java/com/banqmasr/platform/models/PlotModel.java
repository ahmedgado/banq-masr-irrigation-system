package com.banqmasr.platform.models;

import lombok.Data;


@Data
public class PlotModel {

    private String id;

    private String name;

    private String region;

    private long maxWaterLevel;
    private long lastTimeUpdated;
}
