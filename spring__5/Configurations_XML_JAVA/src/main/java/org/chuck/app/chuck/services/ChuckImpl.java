package org.chuck.app.chuck.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

/**
 * Created by Elimane on Oct, 2017, at 18:35
 * @java.util.concurrent  to generate random message by using ThreadLocalRandom class
 */
@Service
public class ChuckImpl implements ChuckInterface {

    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public ChuckImpl(ChuckNorrisQuotes chuckNorrisQuotes) {
        this.chuckNorrisQuotes = chuckNorrisQuotes;
    }


//    public ChuckImpl() {
//
//        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
//    }

    @Override
    public String getQuote() {

        return chuckNorrisQuotes.getRandomQuote();

    }
}
