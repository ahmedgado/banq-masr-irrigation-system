package com.banqmasr.platform.services;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.exceptions.BusinessException;
import com.banqmasr.platform.models.DeviceCommand;
import com.banqmasr.platform.models.DeviceModel;
import com.banqmasr.platform.models.DeviceReqMsg;
import com.banqmasr.platform.repo.DeviceRepo;
import com.banqmasr.platform.repo.PlotRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Device> listAll() {
        return deviceRepo.findAll();
    }
}
