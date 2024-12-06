package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //localhost:8080/
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> get() {
        logger.debug("Home entered");

        // Return a simple structure with home and Data
        return Map.of(
                "/", "/",
                "Data", Map.of(
                        "Add-building", "/Add-building",
                        "Get-buildings", "/Get-buildings"
                )
        );
    }
}
