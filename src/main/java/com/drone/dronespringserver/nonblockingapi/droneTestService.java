package com.drone.dronespringserver.nonblockingapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Flux;

@Service
public class DroneTestService {

    private static final Logger logger = LoggerFactory.getLogger(DroneTestService.class);

    @Async
    public CompletableFuture<String> processDroneRequestAsync(String data) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        logger.info("Processing async request for data: {}", data);

        // 2초간의 처리 지연
        Thread.sleep(2000);
        String result = "Processed: " + data;

        long endTime = System.currentTimeMillis();
        logger.info("Completed async processing for data: {}, Time taken: {} ms", data, (endTime - startTime));

        return CompletableFuture.completedFuture(result);
    }

    public Flux<String> streamDroneData() {
        logger.info("Starting to stream drone data...");
        long startTime = System.currentTimeMillis();

        return Flux.range(1, 10000)
                .map(i -> {
                    String chunk = "DroneDataChunk_" + i;
                    logger.info("Streaming data chunk: {}", chunk);
                    return chunk;
                })
                .doOnComplete(() -> {
                    long endTime = System.currentTimeMillis();
                    logger.info("Completed streaming data. Time taken: {} ms", (endTime - startTime));
                });
    }
}
