package com.banqmasr.platform;

import com.banqmasr.platform.entities.Region;
import com.banqmasr.platform.entities.Zone;
import com.banqmasr.platform.repo.RegionRepo;
import com.banqmasr.platform.repo.ZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Initialization implements ApplicationRunner {

    @Autowired
    private ZoneRepo zoneRepo;
    @Autowired
    private RegionRepo regionRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (zoneRepo.findAll().isEmpty() || regionRepo.findAll().isEmpty()) {
            System.out.println("Start Import Data ... ");
            Zone egyptZone = new Zone("Egypt");
            Zone UAEZone = new Zone("UAE");
            zoneRepo.saveAll(Arrays.asList(egyptZone, UAEZone));
            Region cairoRegion = new Region("Cairo", egyptZone);
            Region alexRegion = new Region("Alex", egyptZone);
            Region dubaiRegion = new Region("Dubai", UAEZone);
            Region abuDhabiRegion = new Region("Abu Dhabi", UAEZone);
            regionRepo.saveAll(Arrays.asList(cairoRegion, alexRegion, dubaiRegion, abuDhabiRegion));
            System.out.println("End Import Data ... ");
        }
    }
}
