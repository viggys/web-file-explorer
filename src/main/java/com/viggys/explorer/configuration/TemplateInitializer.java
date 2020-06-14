package com.viggys.explorer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class TemplateInitializer {

    @Autowired
    private ApplicationContext context;

    private final Set<String> htmlTemplatePatterns = new HashSet<>();
    private final Set<String> cssTemplatePatterns = new HashSet<>();
    private final Set<String> jsTemplatePatterns = new HashSet<>();

    @PostConstruct
    private void initialize() {
        htmlTemplatePatterns.add("/views/");
        cssTemplatePatterns.add("/styles/");
        jsTemplatePatterns.add("/scripts/");
    }

    @Bean
    @Description("Template Resolver")
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setPrefix("classpath:/templates");
        templateResolver.setHtmlTemplateModePatterns(htmlTemplatePatterns);
        templateResolver.setCSSTemplateModePatterns(cssTemplatePatterns);
        templateResolver.setJavaScriptTemplateModePatterns(jsTemplatePatterns);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    @Description("Template Engine")
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) throws IOException {
        log.info("Template Resolver :: {}", templateResolver);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /*@Bean
    @Description("View Resolver")
    public ThymeleafViewResolver viewResolver() throws IOException {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }*/
}
