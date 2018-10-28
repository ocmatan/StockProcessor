package stockProcessor.notification;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotificationRepository {

    List<Notification> notificationsList =  Collections.synchronizedList(new LinkedList<>());

    public void add(List<Notification> notifications){
        if(!notifications.isEmpty()){
            System.out.println("New notifications are added to notification repository");
            notificationsList.addAll(notifications);
        }
    }

    public List<Notification> getNotificationsFromTimeStamp(long timestamp){
        if(timestamp == -1){
            return notificationsList;//return all for the first request
        }
        List<Notification> result = new LinkedList<>();
        notificationsList.forEach(notification -> {
            if(notification.getTimestamp() > timestamp){
                result.add(notification);
            }
        } );
        return result;
    }


}
