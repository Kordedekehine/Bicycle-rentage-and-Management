package com.bicycleManagement.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
public class MessagesConfiguration {

//the complete configuration of this class is placed inside the "WEB-INF/messages_en.properties" ..
// Note we're using a
    //ReloadableResourceBundleMessageSource
     @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource =
                 new ReloadableResourceBundleMessageSource();

        reloadableResourceBundleMessageSource.setBasename("classpath:messages");
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return reloadableResourceBundleMessageSource;
    }

    public LocalValidatorFactoryBean getValidator(){ //message source validator source
         LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
         localValidatorFactoryBean.setValidationMessageSource(messageSource());
         return localValidatorFactoryBean;
    }

}
