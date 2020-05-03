package com.viggys.explorer.configuration;

import com.viggys.explorer.util.SystemUtil;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableCaching
public class GlobalAppConfiguration {

    @PostConstruct
    private void initialize() {
        initializeStaticContext();
    }

    private void initializeStaticContext() {
        SystemUtil.load();
    }

}
