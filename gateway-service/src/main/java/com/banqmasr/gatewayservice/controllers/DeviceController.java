package com.banqmasr.gatewayservice.controllers;

import com.banqmasr.gatewayservice.models.DeviceReqMsgModel;
import com.banqmasr.gatewayservice.services.AlertService;
import com.banqmasr.gatewayservice.services.MessageService;
import com.banqmasr.gatewayservice.services.models.CommandResponse;
import org.banqmasr.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AlertService alertService;
    @Value("${device-health-check-retry}")
    private String checkRetry;

    /* IOT Sensors used to be configured to send to only one api
    so here API receive sensor reading message from device to know the level
    of water and push back command to device start work if needed with duration if
    not command will be N/A and duration zero .
    SaveReFromDevice used for validate messages from device and send it to
    platform. when send command back to device
    */
    @PostMapping("/post-reading")
    public CommandResponse saveReadFromDevice (@RequestBody DeviceReqMsgModel msg)
            throws BusinessException {
             CommandResponse resMsg =  messageService.readDeviceMsg(msg);

             for(int i=0 ; i< Integer.parseInt(checkRetry) ; i++) {
                 if (messageService.checkDeviceOnline(msg.getDeviceImei()))
                     return resMsg;
             }
                alertService.createAlert("Device doesn't receive commands");
                 resMsg.setCommand("ERROR");
                 resMsg.setDuration(0);
                 return resMsg;
    }




}
