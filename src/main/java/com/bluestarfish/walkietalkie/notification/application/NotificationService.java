package com.bluestarfish.walkietalkie.notification.application;

import com.bluestarfish.walkietalkie.notification.domain.enumeration.Gif;
import com.bluestarfish.walkietalkie.notification.domain.enumeration.Quote;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
        textChannel = event.getJDA().getTextChannelById(channelId);
    }

    @Scheduled(cron = "0 0 9 * * *", zone = TIMEZONE)
    public void sendDailyQuote() {
        if (textChannel != null) {
            textChannel.sendMessage(Gif.getRandomGif().getUrl()).queue();
            textChannel.sendMessage(Quote.getRandomQuote().getQuote()).queue();
        }
    }

    @Scheduled(cron = "0 59 23 * * *", zone = TIMEZONE)
    public void sendStudyRecordNotification() {
        if (textChannel != null) {
            textChannel.sendMessage(STUDY_TIME_RECORD_MESSAGE).queue();
            textChannel.sendMessage(sheetsUrl).queue();
        }
    }
}
