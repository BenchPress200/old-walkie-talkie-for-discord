package com.bluestarfish.walkietalkie;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class WalkieTalkieApplication implements CommandLineRunner {
    private final App app;

    public static void main(String[] args) {
        SpringApplication.run(WalkieTalkieApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        app.start();
    }

}
