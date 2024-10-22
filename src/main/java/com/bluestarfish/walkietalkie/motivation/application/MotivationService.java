package com.bluestarfish.walkietalkie.motivation.application;

import com.bluestarfish.walkietalkie.motivation.domain.enumeration.Gif;
import com.bluestarfish.walkietalkie.motivation.domain.enumeration.Quote;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MotivationService extends ListenerAdapter {
    @Value("${discord.bot.channel-quote}")
    private String channelId;
    private TextChannel textChannel;

    @Override
    public void onReady(ReadyEvent event) {
        textChannel = event.getJDA().getTextChannelById(channelId);
    }

    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    public void sendDailyQuote() {
        if (textChannel != null) {
            textChannel.sendMessage(Gif.getRandomGif().getUrl()).queue();
            textChannel.sendMessage(Quote.getRandomQuote().getQuote()).queue();
        }
    }
}
