package com.bluestarfish.walkietalkie.motivation.application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MotivationService extends ListenerAdapter {
    @Value("${discord.bot.channel-test}")
    private String channelId;

    private final String[] quotes = {
            "치지...치..치직..취지..취지직.....취직해",
            "지금 공부 안 하면 더울 때 더운 데서 일하고 추울 때 추운 데서 일한다.",
            "늦었다고 생각할 때가 늦었다",
            "어려운 길은 길이 아니다",
            "즐길 수 없으면 피하라",
            "성공은 1% 재능과 99%의 빽",
            "내일도 할 수 있는 일을 굳이 오늘 할 필요 없다 ",
            "가는 말이 고우면 얕본다",
            "고생 끝에 골병 난다",
            "참을 인 세 번이면 호구",
            "티끌 모아 티끌",
            "원수는 회사에서 만난다",
            "결혼은 좋은 짝을 만나는 게 아니라 좋은 짝이 되어주는 거다",
    };

    private String[] gifs = {
            "https://tenor.com/view/%EC%9E%A5%EC%B6%A9%EB%8F%99-%EC%99%95%EC%A1%B1%EB%B0%9C-%EB%B3%B4%EC%8C%88-%EC%9E%A5%EC%B6%A9%EB%8F%99%EC%99%95%EC%A1%B1%EB%B0%9C%EB%B3%B4%EC%8C%88-%EC%9D%B4%EA%B1%B0%EB%B3%B4%EC%84%B8%EC%9A%94-%EC%9D%B4%EA%B1%B0-%EB%B3%B4%EC%84%B8%EC%9A%94-%EC%9D%B4%EA%B1%B0-%EB%B3%B4%EC%84%B8%EC%9A%94%EC%98%A4-gif-15747888355259273683",
            "https://tenor.com/view/i-don%27t-want-to-infinite-challenge-%ED%95%98%EA%B8%B0%EC%8B%AB%EC%96%B4-%EB%B0%95%EB%AA%85%EC%88%98-%EB%AC%B4%ED%95%9C%EB%8F%84%EC%A0%84-gif-8464168590698275201",
            "https://tenor.com/view/seinfeld-cosmo-kramer-commuting-people-train-gif-4458792",
            "https://tenor.com/view/penguin-work-commute-walking-going-to-work-gif-5501512",
            "https://tenor.com/view/tom-and-jerry-tom-falling-asleep-too-sleepy-sleepy-gif-17722505",
            "https://tenor.com/view/tired-cats-kittens-cute-adorable-gif-16629513",
            "https://tenor.com/view/%EB%A1%9C%ED%8C%B8-%EB%B8%8C%EB%A1%9C%EC%BA%A1%ED%8B%B4-%EB%B8%8C%EB%A1%9C%EB%8C%80%EC%9E%A5-%E1%84%8F%E1%84%8F-%EA%B8%B0%EC%A0%88-gif-11857227398404477940",
            "https://tenor.com/view/train-subway-commuting-seat-annoyed-gif-4458667",
            "https://tenor.com/view/bruce-almighty-splits-traffic-gif-21757275",
            "https://tenor.com/view/%EB%A5%98%EC%8A%B9%EB%A3%A1-%EB%8B%AD%EA%B0%95%EC%A0%95-%EC%9D%B4%EC%A7%81-%EC%A0%95%EC%8B%9C%EC%B6%9C%EA%B7%BC-%ED%9A%8C%EC%82%AC-gif-13219601276400248484"
    };

    private int currentQuoteIndex = 0;
    private int currentGifIndex = 0;
    private TextChannel textChannel;

    private void sendQuote() {
        if (textChannel != null) {
            textChannel.sendMessage(gifs[currentGifIndex]).queue();
            currentGifIndex = (currentGifIndex + 1) % gifs.length;

            textChannel.sendMessage(quotes[currentQuoteIndex]).queue();
            currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
        }
    }

    @Override
    public void onReady(net.dv8tion.jda.api.events.session.ReadyEvent event) {
        textChannel = event.getJDA().getTextChannelById(channelId);

        if (textChannel != null) {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(this::sendQuote, 0, 5, TimeUnit.SECONDS);  // 3초마다 실행
        }
    }
}
