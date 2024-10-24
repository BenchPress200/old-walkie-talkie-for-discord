package com.bluestarfish.walkietalkie.manager;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JDAEventManager {
    private final JDA jda;
    private final List<ListenerAdapter> adapters;

    public void addEvents() {
        log.info("이벤트 리스터 추가 시작");
        for (ListenerAdapter adapter : adapters) {
            jda.addEventListener(adapter);
        }
    }
}

