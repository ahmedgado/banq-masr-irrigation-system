package com.banqmasr.alertservice;

import com.banqmasr.alertservice.entities.Alarm;
import com.banqmasr.alertservice.repo.AlarmRepo;
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
    private AlarmRepo alarmRepo;


    private List<String> getInActiveDevices ()
    {
        long periodInMin = Long.parseLong(periodPerMin);
        List<String> plotModels = restTemplate.
                getForObject("http://localhost:8080/device/inactive-devices?timeInMin=" + periodInMin, ArrayList.class);

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
            sendAlarm(devicesImei);
        });

    }

    private void sendAlarm(String deviceImei) {
        Alarm alarm = new Alarm();
        alarm.setId(UUID.randomUUID());
        alarm.setAlarmMessage("Device " + deviceImei + " is inactive.");
        alarmRepo.save(alarm);
    }
}
