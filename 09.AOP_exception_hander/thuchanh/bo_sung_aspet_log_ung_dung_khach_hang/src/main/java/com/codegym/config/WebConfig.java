package com.codegym.config;

import com.codegym.aspect.ErrorLogger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("validation/message");
        return messageSource;
    }

    @Bean
    public ErrorLogger createLogger() {
        return new ErrorLogger();
    }
}
