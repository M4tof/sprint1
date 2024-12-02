package pl.put.poznan.buildinginfo.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;

@RestController
@RequestMapping("/")
public class HomeControler {
    private static final Logger logger = LoggerFactory.getLogger(HomeControler.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get() {
        logger.debug("Home entered");

        return "Hello World";
    }
}