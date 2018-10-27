package stockProcessor.notification;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NotificationRepository {

    List<Notification> notificationsList = new LinkedList<>();

    public void add(List<Notification> notifications){
        notificationsList.addAll(notifications);
    }

    public List<Notification> getNotificationsFromTimeStamp(long timestamp){
        List<Notification> result = new LinkedList<>();
        notificationsList.forEach(notification -> {
            if(notification.getTimestamp() > timestamp){
                result.add(notification);
            }
        } );
        return result;
    }


}
