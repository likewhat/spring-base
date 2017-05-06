package io.github.likewhat.springbase.service.impl;

import io.github.likewhat.springbase.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageSource messageSource;

    @Autowired
    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, null);
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage) {
        // Can also get local via RequestContextUtils.getLocale(request);
        Locale locale = LocaleContextHolder.getLocale();
        log.info("Locale country = " + locale.getCountry());

        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

}
