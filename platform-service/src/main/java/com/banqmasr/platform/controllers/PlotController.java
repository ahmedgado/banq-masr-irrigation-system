package com.banqmasr.platform.controllers;

import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.services.PlotService;
import org.banqmasr.models.DevicePlotAssignReqModel;
import org.banqmasr.models.PlotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/plot")
public class PlotController {

    @Autowired
    private PlotService plotService;


    @PostMapping("/save")
    public Plot savePlot (@RequestBody PlotModel plotModel)
    {
        return plotService.savePlot(plotModel);
    }

    @PostMapping("/assign-device")
    public Plot assignDeviceToPlot (@RequestBody DevicePlotAssignReqModel devicePlotAssignReqModel)
    {
       return plotService.assignDeviceToPlot(devicePlotAssignReqModel);
    }
    @GetMapping("/list")
    public List<Plot> listOfPlots ()
    {
        return plotService.getPlots();
    }

}
