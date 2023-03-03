package com.banqmasr.coreservice.controllers;

import com.banqmasr.coreservice.entities.Device;
import com.banqmasr.coreservice.models.DeviceCommand;
import com.banqmasr.coreservice.models.DeviceModel;
import com.banqmasr.coreservice.models.DeviceReqMsg;
import com.banqmasr.coreservice.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{imei}")
    public Boolean checkifDeviceExist (@PathVariable String imei)
    {

        return deviceService.checkDeviceExistOrNot(imei);
    }

    @PostMapping("/receive")
    public DeviceCommand handleMessage (@RequestBody DeviceReqMsg msg)
    {
            return deviceService.processReading(msg);
    }
    @PostMapping("/save")
    public Device saveDevice (@RequestBody DeviceModel deviceModel)
    {
        return deviceService.saveDevice(deviceModel);
    }

}
