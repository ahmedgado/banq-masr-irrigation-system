package com.banqmasr.platform.repo;

import com.banqmasr.platform.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegionRepo extends JpaRepository<Region, UUID> {
    Region findByName (String name);
}
