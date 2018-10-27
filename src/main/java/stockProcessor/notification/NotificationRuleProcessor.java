package stockProcessor.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockProcessor.injest.QuoteRepository;
import stockProcessor.injest.StockData;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationRuleProcessor {

    @Autowired
    private QuoteRepository quoteRepository;

    public List<Notification> process(){
        List<Notification> result = new ArrayList<>();
        long timestamp = System.currentTimeMillis();
        for(StockData stockData : quoteRepository.getData().values()){
            Notification notification = new Notification();
            notification.setTimestamp(timestamp);
            if(isStockPercentDifferent(stockData)){
                notification.setMessage(stockData.getLastQuote().getSymbol() +  " The stock is 0.05% higher (or lower) relative to the last quote");
            }else if(isStockValueDifferent(stockData)){
                notification.setMessage(stockData.getLastQuote().getSymbol() +  " The stock is 0.1$ higher (or lower) relative to the last quote");
            }else if(isStockPercentDifferentMonthly(stockData)){
                notification.setMessage(stockData.getLastQuote().getSymbol() +  " The stock is 1% higher (or lower) than the average price for the last month");
            }
        }
        return result;
    }

    private boolean isStockPercentDifferent(StockData stockData){
        return true;//TODO
    }

    private boolean isStockValueDifferent(StockData stockData){
        return true;//TODO
    }

    private boolean isStockPercentDifferentMonthly(StockData stockData){
        return true;//TODO
    }






}
