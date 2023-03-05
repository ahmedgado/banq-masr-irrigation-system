package com.banqmasr.alertservice.controllers;

import com.banqmasr.alertservice.repo.AlertRepo;
import com.banqmasr.alertservice.services.AlertService;
import org.banqmasr.models.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {
    @Autowired
    private AlertService alertService;

    @PostMapping("/alert")
    public void createAlert (AlertMessage message)
    {
        alertService.createAlert(message);
    }

}
