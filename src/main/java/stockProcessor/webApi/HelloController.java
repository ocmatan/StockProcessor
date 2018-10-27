package stockProcessor.webApi;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping(method =  RequestMethod.GET, value = "/test")
    public String test123() {
        return "Greetings from Spring Boot! test 1 2 3";
    }

}
