package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Plot;
import org.banqmasr.models.PlotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlotRepo extends JpaRepository<Plot, UUID> {
    Plot findByDeviceImei (String imei);

}
