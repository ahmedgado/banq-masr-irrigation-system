package com.banqmasr.gatewayservice.controllers;

import com.banqmasr.gatewayservice.models.DeviceReqMsgModel;
import com.banqmasr.gatewayservice.services.MessageService;
import com.banqmasr.gatewayservice.services.models.CommandResponse;
import org.banqmasr.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    private MessageService messageService;

    /* IOT Sensors used to be configured to send to only one api
    so here API receive sensor reading message from device to know the level
    of water and push back command to device start work if needed with duration if
    not command will be N/A and duration zero
    */
    @PostMapping("/post-reading")
    public CommandResponse saveReadFromDevice (@RequestBody DeviceReqMsgModel msg)
            throws BusinessException {
             CommandResponse resMsg =  messageService.readDeviceMsg(msg);
             return  resMsg;
    }

}
