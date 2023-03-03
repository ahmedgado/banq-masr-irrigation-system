package com.banqmasr.gatewayservice.repo;

import com.banqmasr.gatewayservice.entities.DeviceReqMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceReqMsgRepo extends JpaRepository<DeviceReqMsg , UUID> {

}
