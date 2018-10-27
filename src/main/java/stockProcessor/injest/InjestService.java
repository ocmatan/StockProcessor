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
        for(String stock : stocks){
            Quote quote = stockFeedProvider.getQuote(stock);
                quoteRepository.saveQuote(quote);
        }
    }

}
