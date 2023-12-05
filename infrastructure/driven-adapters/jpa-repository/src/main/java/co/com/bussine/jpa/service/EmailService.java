package co.com.bussine.jpa.service;

import co.com.bussine.jpa.helper.MessageHtml;
import co.com.bussine.model.common.BusinessException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailService {
    @Value("${bet_billions.email.sender}")
    private String emailSender;
    private final JavaMailSender mailSender;
    private final Scheduler scheduler;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.scheduler = Schedulers.boundedElastic();
    }

    public Mono<Void> sendEmailWelcome(String name, String email) {
        MimeMessage message = mailSender.createMimeMessage();
        String welcome = MessageHtml.welcome(name);
        return Mono.fromRunnable(() -> {
            try {
                message.setSubject("Bienvenido a bet billions");
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(emailSender);
                helper.setTo(email);
                helper.setText(welcome, true);
                mailSender.send(message);
            } catch (Exception e) {
                Mono.error(new BusinessException(BusinessException.Type.ERROR_ENVIO_DE_CORREO));
            }
        }).subscribeOn(scheduler).then();
    }

}
