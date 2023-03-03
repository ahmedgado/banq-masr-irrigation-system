package com.banqmasr.coreservice.repo;

import com.banqmasr.coreservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, UUID> {
    boolean existsByImei (String imei);
}
