package com.banqmasr.platform.controllers;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.exceptions.BusinessException;
import com.banqmasr.platform.models.DeviceCommand;
import com.banqmasr.platform.models.DeviceModel;
import com.banqmasr.platform.models.DeviceReqMsg;
import com.banqmasr.platform.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/search/{imei}")
    public Boolean checkIfDeviceExist (@PathVariable String imei)
    {

        return deviceService.checkDeviceExistOrNot(imei);
    }

    @PostMapping("/receive")
    public DeviceCommand handleMessage (@RequestBody DeviceReqMsg msg)
    {
        return deviceService.processReading(msg);
    }
    @PostMapping("/save")
    public Device saveDevice (@RequestBody DeviceModel deviceModel) throws BusinessException
    {
        return deviceService.saveDevice(deviceModel);
    }

    @GetMapping("/list")
    public List<Device> listOfAllDevice()
    {

        return deviceService.listAll();
    }
}
