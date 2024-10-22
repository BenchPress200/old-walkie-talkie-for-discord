package com.bluestarfish.walkietalkie.manager;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JDAEventManager {
    private final JDA jda;
    private final List<ListenerAdapter> adapters;

    public void addEvents() {
        for (ListenerAdapter adapter : adapters) {
            jda.addEventListener(adapter);
        }
    }
}

