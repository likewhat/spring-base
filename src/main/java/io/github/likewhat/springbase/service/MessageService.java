package io.github.likewhat.springbase.service;


public interface MessageService {


    String getMessage(String code);


    String getMessage(String code, Object[] args);


    String getMessage(String code, Object[] args, String defaultMessage);
}


