package com.bluestarfish.walkietalkie.notification.application;

import com.bluestarfish.walkietalkie.notification.domain.enumeration.Gif;
import com.bluestarfish.walkietalkie.notification.domain.enumeration.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService extends ListenerAdapter {
    private static final String TIMEZONE = "Asia/Seoul";
    private static final String STUDY_TIME_RECORD_MESSAGE = "## ✏️ 개발시간 기록 ㄱㄱ";

    private final JDA jda;

    @Value("${discord.bot.channel-quote}")
    private String channelId;

    @Value("${sheets.url}")
    private String sheetsUrl;
    private TextChannel textChannel;

    @Scheduled(cron = "0 0 9 * * *", zone = TIMEZONE)
    public void sendDailyQuote() {
        TextChannel textChannel = jda.getTextChannelById(channelId);
        if (textChannel != null) {
            textChannel.sendMessage(Gif.getRandomGif().getUrl()).queue();
            textChannel.sendMessage(Quote.getRandomQuote().getQuote()).queue();
            log.info("일간 메시지 전송");
        }
    }

    @Scheduled(cron = "0 0 4 * * *", zone = TIMEZONE)
    public void sendStudyRecordNotification() {
        TextChannel textChannel = jda.getTextChannelById(channelId);
        log.info("채널 ID {}", channelId);
        log.info("공부시간 기록 알림 메시지 전송 실행");
        if (textChannel != null) {
            textChannel.sendMessage(STUDY_TIME_RECORD_MESSAGE + " 테슽흐").queue();
            textChannel.sendMessage(sheetsUrl).queue();
            log.info("공부시간 기록 알림 메시지 전송");
        }
    }
}
