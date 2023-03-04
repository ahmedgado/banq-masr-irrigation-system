package com.banqmasr.platform.services;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.entities.Region;
import com.banqmasr.platform.models.DeviceCommand;
import com.banqmasr.platform.repo.DeviceRepo;
import com.banqmasr.platform.repo.PlotRepo;
import org.banqmasr.exceptions.BusinessException;
import org.banqmasr.models.DeviceModel;
import org.banqmasr.models.DeviceReqMsgModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public DeviceCommand processReading (DeviceReqMsgModel msg)
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

    private void validateDevice (DeviceModel deviceModel) throws BusinessException
    {
        if (deviceModel.getImei() == null || deviceModel.getImei().isEmpty())
            throw new BusinessException("Imei No. is mandatory");
        if (deviceModel.getModel() == null || deviceModel.getModel().isEmpty())
            throw new BusinessException("Model is mandatory");
        if (deviceModel.getWaterLevelPerMin() == 0)
            throw new BusinessException("Water/Min is mandatory");
    }

    private Device convertFromModelToEntity (DeviceModel deviceModel)
    {
        Converter<String, Region> regionConverter = new AbstractConverter<String, Region>() {
            protected Region convert(String source) {
                if(source !=null )
                {

                    Region region = new Region(source,null);
                    return region;

                } else
                    return null;
            }
        };

        modelMapper.addConverter(regionConverter);

        Device device = modelMapper.map(deviceModel,Device.class);
        return device;

    }
    public Device saveDevice (DeviceModel deviceModel)
    {
        validateDevice(deviceModel);
       Device device = convertFromModelToEntity(deviceModel);
       if(device.getId() == null)
       device.setId(UUID.randomUUID());
       deviceRepo.save(device);
       return device;
    }

    public List<Device> listAll() {
        return  deviceRepo.findAll();
    }

    public Device getDevice(String deviceId) {
        Device device =  deviceRepo.findById(UUID.fromString(deviceId)).get();
        if(device == null)
            throw new BusinessException("Device with ID : " + deviceId + " is not found");
        return device;
    }


    public List<String> getInactive(long timeInMin) {
        long timeInMs = timeInMin * 60000;
        long timeOfInactiveDiffNow = (new Date()).getTime() - timeInMs ;
        return deviceRepo.findInActivePlots(timeOfInactiveDiffNow);
    }
}
