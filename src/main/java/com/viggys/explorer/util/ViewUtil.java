package com.viggys.explorer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;

@Slf4j
public class ViewUtil {

    public static ModelAndView getModelViewWithMetadata() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", SpringPropertyUtil.getProperty("spring.application.name"));
        modelAndView.addObject("hostName", SystemUtil.getHostName());
        modelAndView.addObject("userName", SystemUtil.getUserName());
        modelAndView.addObject("ip", SystemUtil.getIPAddress());
        modelAndView.addObject("exploreUri", PathUtil.getInspectLink(Path.of("/")).toUri().getRawPath());

        return modelAndView;
    }
}
