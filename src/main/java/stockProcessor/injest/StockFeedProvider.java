package stockProcessor.injest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockFeedProvider {

    private RestTemplate restTemplate = new RestTemplate();

    private String quoteUriPrefix = "https://api.iextrading.com/1.0/stock/";


    public Quote getQuote(String stock){
        ResponseEntity<Quote> quoteResponseEntity = restTemplate.getForEntity(quoteUriPrefix + stock + "/quote", Quote.class);
        return quoteResponseEntity.getBody();
    }


}
