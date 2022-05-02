package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Async
    @Override
    public void sendEmail(String subject) {
        LOGGER.info("Sending email with subject {}", subject);
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            LOGGER.info("Email with subject {} sent", subject);
        } catch (InterruptedException e) {
            LOGGER.error("Error sending email", e);
        }
    }
}
