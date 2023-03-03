package com.banqmasr.gatewayservice.services;


import com.banqmasr.gatewayservice.entities.DeviceReqMsg;
import com.banqmasr.gatewayservice.enums.DeviceStatus;
import com.banqmasr.gatewayservice.exceptions.BusinessException;
import com.banqmasr.gatewayservice.models.DeviceReqMsgModel;
import com.banqmasr.gatewayservice.repo.DeviceReqMsgRepo;
import com.banqmasr.gatewayservice.services.models.CommandResponse;
import com.fasterxml.jackson.core.JsonToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DeviceReqMsgRepo deviceReqMsgRepo;


    public CommandResponse readDeviceMsg (DeviceReqMsgModel msg) throws BusinessException
    {
        // Validate
            validateDeviceMsg(msg);
        // save
        DeviceReqMsg deviceMessage = saveDeviceMsg(msg);
        //Check Device
        checkIfDeviceRegistered(msg.getDeviceImei());

        return sendToPlatform(deviceMessage);

    }

    private void checkIfDeviceRegistered(String imei)
    {
        Object object = restTemplate.
                getForObject("http://core-service/device/"+imei, Object.class);
        if(object.equals(true))
        {
            System.out.println("Founded" + object);
        }else {
            throw new BusinessException("Device Not Found");
        }
    }

    private DeviceReqMsg saveDeviceMsg (DeviceReqMsgModel msg)
    {
        DeviceReqMsg reqMsg = new DeviceReqMsg();
        reqMsg.setId(UUID.randomUUID());
        modelMapper.map(msg,reqMsg);
        deviceReqMsgRepo.save(reqMsg);
        return reqMsg;
    }

    /*
    * This function will be call core service to send message to it . in Real app I will use
    * Messaging server MQTT , RabbitMQ
    * */
    private CommandResponse sendToPlatform (DeviceReqMsg deviceMessage)
    {
        CommandResponse command = restTemplate.
                postForObject("http://core-service/device/receive", deviceMessage, CommandResponse.class);

        if(command!=null)
        {
            return command;
        }else {
            throw new BusinessException("Failed to send message to platform");
        }

    }

    private void validateDeviceMsg (DeviceReqMsgModel msg) throws BusinessException
    {
        if (msg.getDeviceImei() != null && !msg.getDeviceImei().isEmpty()) {

            if (msg.getStatus() != null && (msg.getStatus().toUpperCase()
                    .equals(DeviceStatus.valueOf("IDLE").name().toUpperCase()) ||
                    msg.getStatus().toUpperCase()
                            .equals(DeviceStatus.valueOf("WORKING").name().toUpperCase()))
                    && msg.getTs() > 0) {

                if(!(msg.getStatus().toUpperCase()
                        .equals(DeviceStatus.valueOf("IDLE").name().toUpperCase()) &&
                                msg.getWaterLevel() > 0 ))
                {
                    throw new BusinessException("WATER_LEVEL_MISSED");
                }

            }else {
                throw new BusinessException("INVALID_MSG");
            }

        }else {
            throw new BusinessException("IMEI not found");
        }

    }
}
