package stockProcessor.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stockProcessor.injest.QuoteRepository;
import stockProcessor.injest.StockData;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationRuleProcessor {

    @Autowired
    private QuoteRepository quoteRepository;

    @Value("${app.notification_rule_percent}")
    private double notificationRulePercent;

    @Value("${app.notification_rule_value}")
    private double notificationRuleValue;

    public List<Notification> process(){
        System.out.println("Notification process started");
        List<Notification> result = new ArrayList<>();
        long timestamp = System.currentTimeMillis();
        for(StockData stockData : quoteRepository.getData().values()){
            Notification notification = new Notification();
            notification.setTimestamp(timestamp);
            RuleAnswer ruleAnswer = isStockPercentDifferent(stockData);
            if(ruleAnswer.shouldBeNotified){
                notification.setMessage(ruleAnswer.message);
                result.add(notification);
                continue;
            }
            ruleAnswer = isStockValueDifferent(stockData);
            if(ruleAnswer.shouldBeNotified){
                notification.setMessage(ruleAnswer.message);
                result.add(notification);
                continue;
            }
            ruleAnswer = isStockPercentDifferentMonthly(stockData);
            if(ruleAnswer.shouldBeNotified){
                notification.setMessage(ruleAnswer.message);
                result.add(notification);
                continue;
            }
        }
        if(!result.isEmpty()){
            System.out.println("Found a new notifications: " + result.toString());
        }else{
            System.out.println("New notifications were not found");
        }
        return result;
    }

    private RuleAnswer isStockPercentDifferent(StockData stockData){
        RuleAnswer result = new RuleAnswer();
        result.shouldBeNotified = false;
        double diff = Math.abs(stockData.getLastQuote().getLatestPrice() - stockData.getPreviousQuote().getLatestPrice());
        if(diff / stockData.getPreviousQuote().getLatestPrice() == notificationRulePercent){
            result.shouldBeNotified = true;
            String higherOrLower = "higher";
            if(stockData.getLastQuote().getLatestPrice() < stockData.getPreviousQuote().getLatestPrice()){
                higherOrLower = "lower";
            }
            result.message = stockData.getLastQuote().getSymbol() +  " : The stock is 0.05% " + higherOrLower + " relative to the last quote";
        }
        return result;
    }

    private RuleAnswer isStockValueDifferent(StockData stockData){
        RuleAnswer result = new RuleAnswer();
        result.shouldBeNotified = false;
        double diff = Math.abs(stockData.getLastQuote().getLatestPrice() - stockData.getPreviousQuote().getLatestPrice());
        if(diff == notificationRuleValue){
            result.shouldBeNotified = true;
            String higherOrLower = "higher";
            if(stockData.getLastQuote().getLatestPrice() < stockData.getPreviousQuote().getLatestPrice()){
                higherOrLower = "lower";
            }
            result.message = stockData.getLastQuote().getSymbol() +  " : The stock is 0.1$ " + higherOrLower + " relative to the last quote.";
        }
        return result;
    }

    private RuleAnswer isStockPercentDifferentMonthly(StockData stockData){
        RuleAnswer result = new RuleAnswer();
        result.shouldBeNotified = false;
        return result;//TODO
    }

    private class RuleAnswer{
        public boolean shouldBeNotified;
        public String message;
    }






}
