package com.bluestarfish.walkietalkie.motivation.domain.enumeration;

import java.util.concurrent.ThreadLocalRandom;

// FIXME: 이후 데이터베이스로 이동
public enum Quote {
    QUOTE_1("## 치지...치..치직..취지..취지직.....취직해.....  :pepe_dumb:"),
    QUOTE_2("## 지금 공부 안 하면 더울 때 더운 데서 일하고 추울 때 추운 데서 일한다 :hot_face:  :cold_face: "),
    QUOTE_3("## 늦었다고 생각할 때가 늦었다  :tired_face:"),
    QUOTE_4("## 어려운 길은 길이 아니다  :face_vomiting:"),
    QUOTE_5("## 즐길 수 없으면 피하라  :neutral_face:"),
    QUOTE_7("## 고생 끝에 골병 난다  :ghost:"),
    QUOTE_8("## 티끌 모아 티끌  :spider_web:");

    private final String quote;

    Quote(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return this.quote;
    }

    public static Quote getRandomQuote() {
        Quote[] quotes = values();
        int randomIndex = ThreadLocalRandom.current().nextInt(quotes.length);
        return quotes[randomIndex];
    }
}
