package com.drone.dronespringserver.nonblockingapi;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class droneTestService {
    @Async
    public CompletableFuture<String> processDroneRequestAsync(String data) throws InterruptedException {
        // 비동기 작업 처리
        Thread.sleep(2000);
        return CompletableFuture.completedFuture("Processed: " + data);
    }

    public Flux<String> streamDroneData() {
        // WebFlux Flux를 이용한 스트림 데이터 처리
        return Flux.just("Data1", "Data2", "Data3");
    }
}
