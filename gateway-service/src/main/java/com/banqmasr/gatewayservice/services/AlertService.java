package com.banqmasr.gatewayservice.services;

import org.banqmasr.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertService {
    @Autowired
    private RestTemplate restTemplate;

    public String createAlert (String message)
    {
        String response = restTemplate.
                getForObject("http://alert-service/alert?msg = " + message, String.class);

        if(response!=null)
        {
            return response;
        }else {
            throw new BusinessException("Failed to send message to Alert Service");
        }
    }
}
