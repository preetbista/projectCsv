package np.com.esewa.learn.sampleapplication.notification.repository;

import np.com.esewa.learn.sampleapplication.notification.model.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("notificationDataSource")
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
