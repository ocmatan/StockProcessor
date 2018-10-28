package stockProcessor.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockProcessor.notification.Notification;
import stockProcessor.notification.NotificationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationApiService {

    @Autowired
    private NotificationRepository notificationRepository;

    Map<String, Long> clientIdToTimestamp = new HashMap<>();

    public List<Notification> getNotifications(String clientId){
        long timestamp = -1;
        if(clientIdToTimestamp.get(clientId) != null){
           timestamp = clientIdToTimestamp.get(clientId);
        }
        List<Notification> result = notificationRepository.getNotificationsFromTimeStamp(timestamp);
        clientIdToTimestamp.put(clientId, System.currentTimeMillis());
        return result;
    }


}
