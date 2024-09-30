package com.drone.dronespringserver.nonblockingapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/drone")
public class DroneTestController {

    private static final Logger logger = LoggerFactory.getLogger(DroneTestController.class);
    private final DroneTestService droneTestService;

    public DroneTestController(DroneTestService droneTestService) {
        this.droneTestService = droneTestService;
    }

    @GetMapping("/async")
    public String handleAsyncRequest(@RequestParam String data) throws InterruptedException {
        logger.info("Received async request: " + data);
        droneTestService.processDroneRequestAsync(data).thenAccept(result -> {
            logger.info("Processed data asynchronously: " + result);
        });
        return "Request is being processed asynchronously.";
    }

    @GetMapping("/stream")
    public Flux<String> streamDroneData() {
        logger.info("Streaming drone data...");
        return droneTestService.streamDroneData();
    }
}
