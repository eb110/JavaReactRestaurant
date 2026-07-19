package figura.ApiRestaurant.email_notification.services;

import figura.ApiRestaurant.email_notification.dto.NotificationDto;

public interface NotificationService {

    void sendEmail(NotificationDto notificationDto);

}
