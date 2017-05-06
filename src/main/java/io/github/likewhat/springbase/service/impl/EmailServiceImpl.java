package io.github.likewhat.springbase.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;

import javax.mail.internet.MimeMessage;

import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.Email;
import io.github.likewhat.springbase.repository.EmailRepository;
import io.github.likewhat.springbase.service.EmailService;


@Service
public class EmailServiceImpl extends BaseServiceImpl<Email, Long> implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    @Value("${app.email.account}")
    private String emailAccount;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }


    @Override
    CrudRepository<Email, Long> getRepository() {
        return emailRepository;
    }


    private boolean doSend(Email email) {
        if (!StringUtils.hasText(emailAccount)) {
            log.error("Email account is not configured properly!");
            return false;
        }

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailAccount);
            helper.setTo(email.getSendTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());
            helper.setSentDate(email.getCreatedDate());

            javaMailSender.send(message);

            log.info("Send email id={} to {} successfully!", email.getId(), email.getSendTo());

            return true;
        } catch (Exception e) {
            log.error("Failed to send email to {}, email id={}", email.getSendTo(), email.getId(), e);
        }
        return false;
    }

    @Override
    public void sendAsync(Email email) {
        Assert.notNull(email, "Email must not be null!");
        Assert.notNull(email.getReceiverId(), "Email receiver id must not be empty!");
        Assert.hasText(email.getSubject(), "Email title must not be empty!");
        Assert.hasText(email.getSendTo(), "Email receiver must not be empty!");

        email.markCreated(Constants.ANONYMOUS_USER);
        Email toSend = create(email, Constants.ANONYMOUS_USER);
        // Send it
        CompletableFuture.runAsync(() -> {
            boolean result = this.doSend(toSend);
            toSend.setSuccess(result);
            this.update(toSend, Constants.ANONYMOUS_USER);
        });
    }

    @Override
    public Email getOrThrow(Long id) {
        Email email = findOne(id);
        if (email == null) {
            // Should never reached
            throw new IllegalArgumentException();
        }
        return email;
    }
}
