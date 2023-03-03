package com.banqmasr.coreservice.services;

import com.banqmasr.coreservice.entities.Device;
import com.banqmasr.coreservice.entities.Plot;
import com.banqmasr.coreservice.exceptions.BusinessException;
import com.banqmasr.coreservice.models.DeviceCommand;
import com.banqmasr.coreservice.models.DeviceModel;
import com.banqmasr.coreservice.models.DeviceReqMsg;
import com.banqmasr.coreservice.repo.DeviceRepo;
import com.banqmasr.coreservice.repo.PlotRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private PlotRepo plotRepo;

    @Autowired
    private ModelMapper modelMapper;


    public boolean checkDeviceExistOrNot (String imei)
    {
      return  deviceRepo.existsByImei(imei);
    }

    private DeviceCommand createCommandForDevice (long duration)
    {
        DeviceCommand command = new DeviceCommand("SEED",duration);
        return command;
    }

    public DeviceCommand processReading (DeviceReqMsg msg)
    {
        Plot plot = plotRepo.findByDeviceImei(msg.getDeviceImei());

        if(plot == null)
        {
            throw new BusinessException("Device " + msg.getDeviceImei()
                    + " not assigned to Plot ..");
        }

        if(msg.getWaterLevel() < plot.getMaxWaterLevel())
        {
            //save Plot attribute in IOT style


            // Plot need to seed
                //calculate duration
                long remainingLevel = plot.getMaxWaterLevel() - msg.getWaterLevel();
                long duration = remainingLevel / plot.getDevice().getWaterLevelPerMin();
                return createCommandForDevice(duration);

        }else {
            return DeviceCommand.getEmptyCommand();
        }

    }

    public Device saveDevice (DeviceModel deviceModel)
    {
       Device device = modelMapper.map(deviceModel,Device.class);
       if(device.getId() == null)
       device.setId(UUID.randomUUID());
       deviceRepo.save(device);
       return device;
    }

}
