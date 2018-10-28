package stockProcessor.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stockProcessor.injest.InjestService;

import javax.annotation.PostConstruct;
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

    @Value("${app.stocks}")
    private String[] stocksArray;

    private List<String> stocks;

    @PostConstruct
    public void setup(){
        stocks = Arrays.asList(stocksArray);
    }

    @Scheduled(initialDelay=1000, fixedRateString="${app.scheduler_interval_millis}")
    public void processNotifications(){
        System.out.println("Scheduled notification process started at : " + LocalDateTime.now());
        injestService.injestAndTrackQuotes(stocks);
        List<Notification> newNotifications = notificationRuleProcessor.process();
        notificationRepository.add(newNotifications);
        System.out.println("Scheduled notification process done at : " + LocalDateTime.now());
    }

}
