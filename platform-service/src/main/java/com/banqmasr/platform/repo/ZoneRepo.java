package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZoneRepo extends JpaRepository<Zone, UUID> {
}
