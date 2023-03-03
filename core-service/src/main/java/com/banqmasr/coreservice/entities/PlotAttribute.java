package com.banqmasr.coreservice.entities;

import com.banqmasr.coreservice.entities.PK.PlotAttributePKey;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class PlotAttribute {

    @EmbeddedId
    private PlotAttributePKey id;
    @Column
    private String str_v;
    @Column
    private Long long_v;
    @Column
    private Long ts;

}
