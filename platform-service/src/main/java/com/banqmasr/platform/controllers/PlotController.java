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

/*
* save new plot or update data of plot into DB
*
* */
    @PostMapping("/save")
    public Plot savePlot (@RequestBody PlotModel plotModel)
    {
        return plotService.savePlot(plotModel);
    }

    /*
    * used to assign device to plot
    * by send both ids of them
    * */

    @PostMapping("/assign-device")
    public Plot assignDeviceToPlot (@RequestBody DevicePlotAssignReqModel devicePlotAssignReqModel)
    {
       return plotService.assignDeviceToPlot(devicePlotAssignReqModel);
    }

    /*
    * list of all plots from DB
    *
    * */

    @GetMapping("/list")
    public List<Plot> listOfPlots ()
    {
        return plotService.getPlots();
    }

}
