package com.bluestarfish.walkietalkie;

import com.bluestarfish.walkietalkie.manager.JDAEventManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class App {
    private final JDAEventManager jdaEventManager;

    public void start() {
        jdaEventManager.addEvents();
    }

}

