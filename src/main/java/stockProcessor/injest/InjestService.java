package stockProcessor.injest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InjestService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private StockFeedProvider stockFeedProvider;

    public void injestAndTrackQuotes(List<String> stocks){
        System.out.println("Injest & track quotes start for: " + stocks);
        for(String stock : stocks){
            Quote quote = stockFeedProvider.getQuote(stock);
            System.out.println("Quote received for : " + quote.getSymbol() + ", with price: " + quote.getLatestPrice());
            quoteRepository.saveQuote(quote);
        }
    }

}
