package com.bluestarfish.walkietalkie.notification.domain.enumeration;

import java.util.concurrent.ThreadLocalRandom;

// FIXME: 이후 데이터베이스로 이동
public enum Quote {
    QUOTE_1("## 치지...치..치직..취지..취지직.....취직해.....  :pepe_dumb:");

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
