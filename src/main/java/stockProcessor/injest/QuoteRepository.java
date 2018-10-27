package stockProcessor.injest;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuoteRepository {
    private Map<String, StockData> data;

    public QuoteRepository(){
        this.data = new HashMap<>();
    }

    public Map<String, StockData> getData() {
        return data;
    }

    public void saveQuote(Quote quote){
        System.out.println("Saving quote in repository: " + quote.getSymbol() + ", with price: " + quote.getLatestPrice());
        StockData stockData = data.get(quote.getSymbol());
        if(stockData == null){
            stockData = new StockData();
            stockData.setLastQuote(quote);
            stockData.setPreviousQuote(quote);
        }else{
            Quote lastQuote = stockData.getLastQuote();
            stockData.setLastQuote(quote);
            stockData.setPreviousQuote(lastQuote);
        }
        data.put(quote.getSymbol(), stockData);
    }


}
