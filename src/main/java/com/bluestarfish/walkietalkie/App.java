package com.bluestarfish.walkietalkie;

import com.bluestarfish.walkietalkie.manager.JDAEventManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class App {
    private final JDAEventManager jdaEventManager;

    public void start() {
        jdaEventManager.addEvents();
    }

}

