package com.bluestarfish.walkietalkie;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class WalkieTalkieApplication implements CommandLineRunner {
    private final App app;

    public static void main(String[] args) {
        SpringApplication.run(WalkieTalkieApplication.class, args);
    }

    @Override
    public void run(String... args) {
        app.start();
    }

}
