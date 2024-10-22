package com.bluestarfish.walkietalkie.motivation.domain.enumeration;

import java.util.concurrent.ThreadLocalRandom;

// FIXME: 이후 데이터베이스로 이동
public enum Gif {
    GIF_1("https://tenor.com/view/%EC%9E%A5%EC%B6%A9%EB%8F%99-%EC%99%95%EC%A1%B1%EB%B0%9C-%EB%B3%B4%EC%8C%88-%EC%9E%A5%EC%B6%A9%EB%8F%99%EC%99%95%EC%A1%B1%EB%B0%9C%EB%B3%B4%EC%8C%88-%EC%9D%B4%EA%B1%B0%EB%B3%B4%EC%84%B8%EC%9A%94-%EC%9D%B4%EA%B1%B0-%EB%B3%B4%EC%84%B8%EC%9A%94-%EC%9D%B4%EA%B1%B0-%EB%B3%B4%EC%84%B8%EC%9A%94%EC%98%A4-gif-15747888355259273683"),
    GIF_2("https://tenor.com/view/i-don%27t-want-to-infinite-challenge-%ED%95%98%EA%B8%B0%EC%8B%AB%EC%96%B4-%EB%B0%95%EB%AA%85%EC%88%98-%EB%AC%B4%ED%95%9C%EB%8F%84%EC%A0%84-gif-8464168590698275201"),
    GIF_3("https://tenor.com/view/seinfeld-cosmo-kramer-commuting-people-train-gif-4458792"),
    GIF_4("https://tenor.com/view/penguin-work-commute-walking-going-to-work-gif-5501512"),
    GIF_5("https://tenor.com/view/tom-and-jerry-tom-falling-asleep-too-sleepy-sleepy-gif-17722505"),
    GIF_6("https://tenor.com/view/tired-cats-kittens-cute-adorable-gif-16629513"),
    GIF_7("https://tenor.com/view/%EB%A1%9C%ED%8C%B8-%EB%B8%8C%EB%A1%9C%EC%BA%A1%ED%8B%B4-%EB%B8%8C%EB%A1%9C%EB%8C%80%EC%9E%A5-%E1%84%8F%E1%84%8F-%EA%B8%B0%EC%A0%88-gif-11857227398404477940"),
    GIF_8("https://tenor.com/view/train-subway-commuting-seat-annoyed-gif-4458667"),
    GIF_9("https://tenor.com/view/bruce-almighty-splits-traffic-gif-21757275"),
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
