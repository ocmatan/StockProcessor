package stockProcessor.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stockProcessor.injest.InjestService;

import java.util.Arrays;
import java.util.List;

@Service
public class NotficationService {

    @Autowired
    private InjestService injestService;

    @Autowired
    private NotificationRuleProcessor notificationRuleProcessor;

    @Autowired
    private NotificationRepository notificationRepository;


    private List<String> stocks = Arrays.asList("AAPL", "FB");//TODO - config


    @Scheduled(initialDelay=1000, fixedRate=30000)//every 30 seconds//TODO - config
    public void processNotifications(){
        injestService.injestAndTrackQuotes(stocks);
        List<Notification> newNotifications = notificationRuleProcessor.process();
        notificationRepository.add(newNotifications);
    }

}
