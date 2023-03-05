package com.banqmasr.alertservice;

import com.banqmasr.alertservice.entities.Alert;
import com.banqmasr.alertservice.repo.AlertRepo;
import org.banqmasr.constants.ServicesURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CheckPlot {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${inactive.periodPerMin}")
    private String periodPerMin;

    @Autowired
    private AlertRepo alertRepo;


    private List<String> getInActiveDevices ()
    {
        long periodInMin = Long.parseLong(periodPerMin);
        List<String> plotModels = restTemplate.
                getForObject(ServicesURLs.inActiveDevice + periodInMin, ArrayList.class);

        if(plotModels!=null)
        {
            return plotModels;
        }else {
            return new ArrayList<>();
        }
    }

    /*
    * Run Each one hour
    * */
    @Scheduled(fixedRateString = "${inactive.checker.PeriodPerMs}", initialDelay = 5000)
    public void execute()
    {
        System.out.println("Start Service ...");
        List<String> devicesImeis = getInActiveDevices();
        System.out.println("Devices inactive count ..."+devicesImeis.size() );

        devicesImeis.forEach(devicesImei -> {
            sendAlert(devicesImei);
        });

    }

    private void sendAlert(String deviceImei) {
        Alert alert = new Alert();
        alert.setId(UUID.randomUUID());
        alert.setAlertMessage("Device " + deviceImei + " is inactive.");
        alertRepo.save(alert);
    }
}
