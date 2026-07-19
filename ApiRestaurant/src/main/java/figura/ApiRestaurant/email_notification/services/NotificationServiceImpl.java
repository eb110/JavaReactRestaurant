package figura.ApiRestaurant.email_notification.services;

import figura.ApiRestaurant.email_notification.dto.NotificationDto;
import figura.ApiRestaurant.email_notification.entity.Notification;
import figura.ApiRestaurant.email_notification.repository.NotificationRepository;
import figura.ApiRestaurant.enums.NotificationType;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String user;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private String smtpPort;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttlsEnable;

    @Override
    @Async
    public void sendEmail(NotificationDto notificationDto) {

        log.info("\n\nEMAIL NOTIFICATION SERVICE STARTED\n\n");

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.auth", smtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", starttlsEnable);

        Session session = Session.getDefaultInstance(properties);

        try{

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(notificationDto.getRecipient()));
            message.setSubject(notificationDto.getSubject());
            message.setText(notificationDto.getBody());

            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            log.info("\n\nEMAIL NOTIFICATION SERVICE FINISHED\n\n");

            //DB SAVE
            Notification notificationToSave = Notification.builder()
                .recipient(notificationDto.getRecipient())
                .subject(notificationDto.getSubject())
                .body(notificationDto.getBody())
                .type(NotificationType.EMAIL)
                .isHtml(notificationDto.isHtml())
                .build();

            repository.save(notificationToSave);

            log.info("\n\nEMAIL NOTIFICATION SAVED TO DB\n\n");

        }catch(Exception e){
            log.error("\n\nEMAIL NOTIFICATION SERVICE FAILED\n\n");
            throw new RuntimeException(e.getMessage());
        }
    }

    //@Override
    @Async
    public void sendEmailO(NotificationDto notificationDto) {

        log.info("\n\nEMAIL NOTIFICATION SERVICE STARTED\n\n");

        try{

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
            );

            helper.setTo(notificationDto.getRecipient());
            helper.setSubject(notificationDto.getSubject());
            helper.setText(notificationDto.getBody(), notificationDto.isHtml());

            mailSender.send(mimeMessage);

            //DB SAVE
            Notification notificationToSave = Notification.builder()
                .recipient(notificationDto.getRecipient())
                .subject(notificationDto.getSubject())
                .body(notificationDto.getBody())
                .type(NotificationType.EMAIL)
                .isHtml(notificationDto.isHtml())
                .build();

            repository.save(notificationToSave);

            log.info("\n\nEMAIL NOTIFICATION SERVICE FINISHED\n\n");

        }catch(Exception e){
            log.error("\n\nEMAIL NOTIFICATION SERVICE FAILED\n\n");
            throw new RuntimeException(e.getMessage());
        }
    }
}
