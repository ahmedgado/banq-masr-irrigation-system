package com.banqmasr.alertservice.repo;

import com.banqmasr.alertservice.entities.Alarm;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AlarmRepo extends CrudRepository<Alarm, UUID> {
}
