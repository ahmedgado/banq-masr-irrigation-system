package com.banqmasr.gatewayservice.services;

import org.banqmasr.constants.ServicesURLs;
import org.banqmasr.exceptions.BusinessException;
import org.banqmasr.models.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertService {
    @Autowired
    private RestTemplate restTemplate;

    public String createAlert (String message)
    {
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.setMessage(message);
        String response = restTemplate.
                postForObject(ServicesURLs.sendAlert,alertMessage, String.class);

        if(response!=null)
        {
            return response;
        }else {
            throw new BusinessException("Failed to send message to Alert Service");
        }
    }
}
