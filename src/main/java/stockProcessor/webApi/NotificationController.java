package stockProcessor.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import stockProcessor.notification.Notification;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationApiService notificationApiService;

    @RequestMapping(method =  RequestMethod.GET, value = "/getNotifications")
    public List<Notification> getNotifications(@RequestParam String clientId) {
        System.out.println("NotificationController : Got a new notification request");
        List<Notification> result = notificationApiService.getNotifications(clientId);
        System.out.println("NotificationController : notification response is: " + result);
        return result;
    }

}
