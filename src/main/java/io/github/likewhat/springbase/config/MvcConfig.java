package io.github.likewhat.springbase.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import io.github.likewhat.springbase.web.interceptor.MessageInterceptor;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MessageInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public LocaleResolver localeResolver() {
        // use SessionLocaleResolver
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US); // Set default Locale as US
        return slr;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource rrbm = new ReloadableResourceBundleMessageSource();
        // Please make sure the message location is correct
        rrbm.setBasename("classpath:/i18n/messages");
        rrbm.setDefaultEncoding("UTF-8");
        rrbm.setUseCodeAsDefaultMessage(true);
        return rrbm;
    }
}
