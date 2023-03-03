package com.banqmasr.platform.entities;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.entities.Region;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Plot {
    @Id
    private UUID id;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private Region region;
    @OneToOne
    @JoinColumn
    private Device device;
    @Column
    private long maxWaterLevel;

    @Column
    private long lastTimeUpdated;
}
