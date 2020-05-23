package com.viggys.explorer.util;

import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;

public class ViewUtil {

    public static ModelAndView getModelViewWithMetadata() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", SpringPropertyUtil.getProperty("spring.application.name"));
        modelAndView.addObject("hostName", SystemUtil.getHostName());
        modelAndView.addObject("userName", SystemUtil.getUserName());
        modelAndView.addObject("ip", SystemUtil.getIPAddress());
        modelAndView.addObject("base", PathUtil.getInspectUrl(Path.of("/")));

        return modelAndView;
    }
}
