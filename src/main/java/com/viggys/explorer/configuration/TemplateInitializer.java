package com.viggys.explorer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.Resource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class TemplateInitializer {

    @Value("classpath:templates")
    private Resource templates;

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
    public FileTemplateResolver templateResolver() throws IOException {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(templates.getFile().getAbsolutePath());
        templateResolver.setHtmlTemplateModePatterns(htmlTemplatePatterns);
        templateResolver.setCSSTemplateModePatterns(cssTemplatePatterns);
        templateResolver.setJavaScriptTemplateModePatterns(jsTemplatePatterns);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    @Description("Template Engine")
    public SpringTemplateEngine templateEngine() throws IOException {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
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
