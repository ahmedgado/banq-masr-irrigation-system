package com.banqmasr.alertservice.services;

import com.banqmasr.alertservice.entities.Alert;
import com.banqmasr.alertservice.repo.AlertRepo;
import org.banqmasr.models.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private AlertRepo alertRepo;

    public void createAlert(AlertMessage alertMessage)
    {
        if(alertMessage.getMessage()!=null &&
                alertMessage.getMessage().isEmpty()) {
            Alert alert = new Alert();
            alert.setAlertMessage(alertMessage.getMessage());
            alertRepo.save(alert);
        }
    }

}
