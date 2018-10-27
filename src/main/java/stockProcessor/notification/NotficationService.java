package stockProcessor.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stockProcessor.injest.InjestService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
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


    @Scheduled(initialDelay=1000, fixedRate=15000)//every 15 seconds//TODO - config
    public void processNotifications(){
        System.out.println("Scheduled notification process started at : " + LocalDateTime.now());
        injestService.injestAndTrackQuotes(stocks);
        List<Notification> newNotifications = notificationRuleProcessor.process();
        notificationRepository.add(newNotifications);
        System.out.println("Scheduled notification process done at : " + LocalDateTime.now());
    }

}
