package com.example.bookstoreapi.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CustomMetricsConfig {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "BookstoreAPI");
    }

    @Bean
    public Timer customTimer(MeterRegistry meterRegistry) {
        return Timer.builder("custom.timer")
                .description("A custom timer metric")
                .publishPercentiles(0.5, 0.95) // median and 95th percentile
                .register(meterRegistry);
    }

    // Example of using the custom timer
    public void trackTime(Timer timer) {
        timer.record(() -> {
            // Simulate a task that takes 2 seconds
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
    @Autowired
private Timer customTimer;

public void performTask() {
    customTimer.record(() -> {
        // Task logic here
    });
}

}
