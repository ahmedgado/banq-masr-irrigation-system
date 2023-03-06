package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Device;
import org.banqmasr.models.PlotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, UUID> {
    boolean existsByImei (String imei);


    @Query(value = "select imei from device where id in (select device_id from plot  where last_time_updated < ? and last_time_updated is not null)",nativeQuery = true)
    List<String> findInActiveDevices(@Param("ts") long timeInMs);
}
