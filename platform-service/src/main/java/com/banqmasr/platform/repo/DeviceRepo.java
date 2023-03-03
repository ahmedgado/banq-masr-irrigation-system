package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, UUID> {
    boolean existsByImei (String imei);
}
