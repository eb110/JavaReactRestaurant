package figura.ApiRestaurant;

import figura.ApiRestaurant.email_notification.dto.NotificationDto;
import figura.ApiRestaurant.email_notification.services.NotificationService;
import figura.ApiRestaurant.email_notification.services.NotificationServiceImpl;
import figura.ApiRestaurant.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
@RequiredArgsConstructor
public class ApiRestaurantApplication {

    private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestaurantApplication.class, args);
	}

//    @Bean
//    CommandLineRunner runner(){
//        return args -> {
//            NotificationDto notificationDto = NotificationDto.builder()
//                .recipient("wfigura@op.pl")
//                .subject("test food java api")
//                .body("abalonga abale")
//                .type(NotificationType.EMAIL)
//                .build();
//
//            notificationService.sendEmail(notificationDto);
//        };
//    }
}
