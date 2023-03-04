package com.banqmasr.platform.controllers;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.models.DeviceCommand;
import com.banqmasr.platform.services.DeviceService;
import org.banqmasr.exceptions.BusinessException;
import org.banqmasr.models.DeviceModel;
import org.banqmasr.models.DeviceReqMsgModel;
import org.banqmasr.models.PlotModel;
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
    public DeviceCommand handleMessage (@RequestBody DeviceReqMsgModel msg)
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

    @GetMapping("/inactive-devices")
    public List<String> listOfActivePlots(@RequestParam(value = "timeInMin", required = false, defaultValue = "60") long timeInMin)
    {

        return deviceService.getInactive(timeInMin);
    }
}
