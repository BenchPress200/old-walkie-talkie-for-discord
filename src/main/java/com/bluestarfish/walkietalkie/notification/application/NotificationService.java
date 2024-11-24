package com.bluestarfish.walkietalkie.notification.application;

import com.bluestarfish.walkietalkie.notification.domain.enumeration.Gif;
import com.bluestarfish.walkietalkie.notification.domain.enumeration.Quote;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
    private static final String WORKOUT_TIME_MESSAGE = "## 🧪 테스트 - 🏋🏻 오늘 운동 올려라 !";

    private final JDA jda;

    @Value("${discord.bot.channel-quote}")
    private String channelId;

    @Value("${discord.bot.channel-workout}")
    private String workoutChannelId;

    @Value("${sheets.url}")
    private String sheetsUrl;

    @Scheduled(cron = "0 0 9 * * *", zone = TIMEZONE)
    public void sendDailyQuote() {
        TextChannel textChannel = jda.getTextChannelById(channelId);
        if (textChannel != null) {
            textChannel.sendMessage(Gif.getRandomGif().getUrl()).queue();
            textChannel.sendMessage(Quote.getRandomQuote().getQuote()).queue();
            log.info("일간 메시지 전송");
        }
    }

    @Scheduled(cron = "0 59 23 * * *", zone = TIMEZONE)
    public void sendStudyRecordNotification() {
        TextChannel textChannel = jda.getTextChannelById(channelId);

        if (textChannel != null) {
            textChannel.sendMessage(STUDY_TIME_RECORD_MESSAGE).queue();
            textChannel.sendMessage(sheetsUrl).queue();
            log.info("공부시간 기록 알림 메시지 전송");
        }
    }

    @Scheduled(cron = "0 10 23 * * *", zone = TIMEZONE)
    public void sendWorkoutNotification() {
        TextChannel textChannel = jda.getTextChannelById(workoutChannelId);

        if (textChannel != null) {
            LocalDate today = LocalDate.now(ZoneId.of(TIMEZONE));
            String formattedDate = today.format(DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN));
            String messageWithDate = WORKOUT_TIME_MESSAGE;

            textChannel.sendMessage(messageWithDate).queue(message -> {
                message.createThreadChannel("\uD83D\uDCC5 " + formattedDate + " 운동 스레드").queue(thread -> {
                    thread.sendMessage("인증 ㄱㄱ").queue();
                });
            });
            log.info("운동 스레드 알림 메시지 전송: {}", formattedDate);
        }
    }
}
