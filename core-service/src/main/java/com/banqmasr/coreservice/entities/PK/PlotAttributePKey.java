package com.banqmasr.coreservice.entities.PK;

import com.banqmasr.coreservice.entities.Plot;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PlotAttributePKey implements Serializable {

    @Column
    private String key;
    @JoinColumn
    @ManyToOne
    private Plot plot;
}
