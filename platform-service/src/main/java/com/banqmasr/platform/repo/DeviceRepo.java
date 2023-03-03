package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepo extends CrudRepository<Device, UUID> {
    boolean existsByImei (String imei);
}
