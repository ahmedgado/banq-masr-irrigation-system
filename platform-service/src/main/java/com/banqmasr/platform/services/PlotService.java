package com.banqmasr.platform.services;

import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.entities.Region;
import com.banqmasr.platform.exceptions.BusinessException;
import com.banqmasr.platform.models.PlotModel;
import com.banqmasr.platform.repo.PlotRepo;
import com.banqmasr.platform.repo.RegionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlotService {
    @Autowired
    private PlotRepo plotRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RegionRepo regionRepo;

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


}
