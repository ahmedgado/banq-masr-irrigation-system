package com.banqmasr.alertservice.repo;

import com.banqmasr.alertservice.entities.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlertRepo extends CrudRepository<Alert, UUID> {
}
