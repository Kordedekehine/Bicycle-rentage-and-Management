package com.bicycleManagement.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

 private static final Locale locale = new Locale("en");

 private static final Object [] args = null;

  private MessageSource messageSource;

  public String get(String code){
      return messageSource.getMessage(code,args,locale);
  }
}
