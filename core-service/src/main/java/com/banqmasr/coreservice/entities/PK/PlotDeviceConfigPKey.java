package com.banqmasr.coreservice.entities.PK;

import com.banqmasr.coreservice.entities.Device;
import com.banqmasr.coreservice.entities.Plot;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class PlotDeviceConfigPKey implements Serializable {
    @JoinColumn
    @OneToOne
    private Plot plot;
    @JoinColumn
    @OneToOne
    private Device device;
}
