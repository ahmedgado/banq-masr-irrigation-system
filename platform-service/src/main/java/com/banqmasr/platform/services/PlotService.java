package com.banqmasr.platform.services;

import com.banqmasr.platform.entities.Device;
import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.entities.Region;
import com.banqmasr.platform.repo.PlotRepo;
import com.banqmasr.platform.repo.RegionRepo;
import org.banqmasr.exceptions.BusinessException;
import org.banqmasr.models.DevicePlotAssignReqModel;
import org.banqmasr.models.PlotModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PlotService {
    @Autowired
    private PlotRepo plotRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private DeviceService deviceService;

    private void validatePlotModel (PlotModel plotModel)
    {
        if(plotModel.getName() == null || plotModel.getName().isEmpty())
            throw new BusinessException("Plot name is mandatory");
        if(plotModel.getMaxWaterLevel() == 0)
            throw new BusinessException("Plot water max level is mandatory");
        if(plotModel.getRegion() == null || plotModel.getRegion().isEmpty())
            throw new BusinessException("Region is mandatory");
    }
    public Plot savePlot (PlotModel plotModel)
    {

        validatePlotModel(plotModel);

        Plot plot = modelMapper.map(plotModel,Plot.class);

        if(plot.getId() == null )
            plot.setId(UUID.randomUUID());

        Region region = regionRepo.findByName(plotModel.getRegion());
        if(region == null)
            throw new BusinessException("Region Not Found");

        plot.setRegion(region);
        this.plotRepo.save(plot);

        return plot;
    }


    private Plot getPlot(String plotId) {
        Plot plot = plotRepo.findById(UUID.fromString(plotId)).get();
        if(plot == null)
            throw new BusinessException("Plot with ID : " + plotId + " is not found");
        return plot;
    }

    public Plot assignDeviceToPlot(DevicePlotAssignReqModel devicePlotAssignReqModel) {
        Device device = deviceService.getDevice(devicePlotAssignReqModel.getDeviceId());
        Plot plot = getPlot(devicePlotAssignReqModel.getPlotId());
        plot.setDevice(device);
        plotRepo.save(plot);
        return plot;
    }

    public List<Plot> getPlots() {
        return plotRepo.findAll();
    }

}
