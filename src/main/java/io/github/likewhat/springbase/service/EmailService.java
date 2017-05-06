package io.github.likewhat.springbase.service;


import io.github.likewhat.springbase.model.Email;


public interface EmailService extends BaseService<Email, Long> {


    void sendAsync(Email email);
}
