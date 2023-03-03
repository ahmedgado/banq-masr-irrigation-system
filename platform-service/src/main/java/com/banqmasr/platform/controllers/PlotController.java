package com.banqmasr.platform.controllers;

import com.banqmasr.platform.entities.Plot;
import com.banqmasr.platform.models.PlotModel;
import com.banqmasr.platform.services.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlotController {

    @Autowired
    private PlotService plotService;

    @PostMapping("/plot")
    public Plot savePlot (@RequestBody PlotModel plotModel)
    {
        return plotService.savePlot(plotModel);
    }

}
