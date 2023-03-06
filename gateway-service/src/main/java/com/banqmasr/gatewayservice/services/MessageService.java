package com.banqmasr.gatewayservice.services;


import com.banqmasr.gatewayservice.entities.DeviceReqMsg;
import com.banqmasr.gatewayservice.models.DeviceReqMsgModel;
import com.banqmasr.gatewayservice.repo.DeviceReqMsgRepo;
import com.banqmasr.gatewayservice.services.models.CommandResponse;
import org.banqmasr.constants.ServicesURLs;
import org.banqmasr.enums.DeviceStatus;
import org.banqmasr.exceptions.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DeviceReqMsgRepo deviceReqMsgRepo;

/*
*  Read device message validate it if valid will be stored into DB for log
* then check if device registered to platform or not
 * if validation failed will fire exception and send it message to client
* */

    public CommandResponse readDeviceMsg (DeviceReqMsgModel msg) throws BusinessException
    {
        validateDeviceMsg(msg);
        DeviceReqMsg deviceMessage = saveDeviceMsg(msg);
        checkIfDeviceRegistered(msg.getDeviceImei());
        return sendToPlatform(deviceMessage);

    }

    /*
    * Call Core Service to check Device status
    *
    *
    * */
    private void checkIfDeviceRegistered(String imei)
    {
        Object object = restTemplate.
                getForObject(ServicesURLs.checkDevice +imei, Object.class);
        if(object.equals(true))
        {
            System.out.println("Founded" + object);
        }else {
            throw new BusinessException("Device Not Found");
        }
    }
/*
* Save Device Message to DB
*
* */

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
                postForObject(ServicesURLs.sendDeviceMessageToPlatform, deviceMessage, CommandResponse.class);

        if(command!=null)
        {
            return command;
        }else {
            throw new BusinessException("Failed to send message to platform");
        }

    }

    /*
    * Validate if Message contain IMEI , STATUS MUST BE IDLE OR WORKING
    * if IDLE must contain waterLevel
    * if validation failed will fire exception and send it message to client
    * */

    private void validateDeviceMsg (DeviceReqMsgModel msg) throws BusinessException
    {
        if (msg.getDeviceImei() != null && !msg.getDeviceImei().isEmpty()) {

            if (msg.getStatus() != null && (msg.getStatus().toUpperCase()
                    .equals(DeviceStatus.valueOf(DeviceStatus.IDLE.name()).name().toUpperCase()) ||
                    msg.getStatus().toUpperCase()
                            .equals(DeviceStatus.valueOf(DeviceStatus.WORKING.name()).name().toUpperCase()))
                    && msg.getTs() > 0) {

                if(!(msg.getStatus().toUpperCase()
                        .equals(DeviceStatus.valueOf(DeviceStatus.IDLE.name()).name().toUpperCase()) &&
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

    public boolean checkDeviceOnline(String deviceImei)
    {
        // call device health check api Device Side
        return true;
    }
}
