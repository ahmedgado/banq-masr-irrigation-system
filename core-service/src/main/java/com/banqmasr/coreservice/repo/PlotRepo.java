package com.banqmasr.coreservice.repo;

import com.banqmasr.coreservice.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlotRepo extends JpaRepository<Plot, UUID> {
    Plot findByDeviceImei (String imei);
}
