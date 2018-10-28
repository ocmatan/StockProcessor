package stockProcessor.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stockProcessor.injest.QuoteRepository;
import stockProcessor.injest.StockData;
import stockProcessor.notification.Notification;

import java.util.List;

@RestController
public class PublicApiController {

    @Autowired
    private QuoteRepository quoteRepository;

    @RequestMapping(method =  RequestMethod.GET, value = "/getStockData")
    public StockData getStockData(@RequestParam String symbol) {
        System.out.println("PublicApiController : Got a new stock data request for : " + symbol);
        return quoteRepository.getData().get(symbol);
    }

}
