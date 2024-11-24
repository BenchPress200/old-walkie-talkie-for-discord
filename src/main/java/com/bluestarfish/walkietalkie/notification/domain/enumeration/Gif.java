package com.bluestarfish.walkietalkie.notification.domain.enumeration;

import java.util.concurrent.ThreadLocalRandom;

// FIXME: 이후 데이터베이스로 이동
public enum Gif {
    GIF_10("https://tenor.com/view/%EB%A5%98%EC%8A%B9%EB%A3%A1-%EB%8B%AD%EA%B0%95%EC%A0%95-%EC%9D%B4%EC%A7%81-%EC%A0%95%EC%8B%9C%EC%B6%9C%EA%B7%BC-%ED%9A%8C%EC%82%AC-gif-13219601276400248484");

    private final String url;

    Gif(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public static Gif getRandomGif() {
        Gif[] gifs = values();
        int randomIndex = ThreadLocalRandom.current().nextInt(gifs.length);
        return gifs[randomIndex];
    }
}
