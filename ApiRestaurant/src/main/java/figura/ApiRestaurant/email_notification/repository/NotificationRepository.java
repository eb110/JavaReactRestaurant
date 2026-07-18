package figura.ApiRestaurant.email_notification.repository;

import figura.ApiRestaurant.email_notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
