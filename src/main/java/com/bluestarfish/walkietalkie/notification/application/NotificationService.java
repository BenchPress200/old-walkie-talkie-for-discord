package com.bluestarfish.walkietalkie.notification.application;

import com.bluestarfish.walkietalkie.notification.domain.enumeration.Gif;
import com.bluestarfish.walkietalkie.notification.domain.enumeration.Quote;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService extends ListenerAdapter {
    private static final String TIMEZONE = "Asia/Seoul";
    private static final String STUDY_TIME_RECORD_MESSAGE = "## ✏️ 개발시간 기록 ㄱㄱ";

    @Value("${discord.bot.channel-quote}")
    private String channelId;

    @Value("${sheets.url}")
    private String sheetsUrl;
    private TextChannel textChannel;

    @Override
    public void onReady(ReadyEvent event) {
        log.info("봇 {} 채널 메시지 전송준비 완료", channelId);
        textChannel = event.getJDA().getTextChannelById(channelId);
    }

    @Scheduled(cron = "0 0 9 * * *", zone = TIMEZONE)
    public void sendDailyQuote() {
        if (textChannel != null) {
            textChannel.sendMessage(Gif.getRandomGif().getUrl()).queue();
            textChannel.sendMessage(Quote.getRandomQuote().getQuote()).queue();
            log.info("일간 메시지 전송");
        }
    }

    @Scheduled(cron = "0 5 2 * * *", zone = TIMEZONE)
    public void sendStudyRecordNotification() {
        log.info("공부시간 기록 알림 메시지 전송 실행");
        if (textChannel != null) {
            textChannel.sendMessage(STUDY_TIME_RECORD_MESSAGE + " 테슽흐").queue();
            textChannel.sendMessage(sheetsUrl).queue();
            log.info("공부시간 기록 알림 메시지 전송");
        }
    }
}
