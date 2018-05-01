package org.chuck.app.chuck.config;


import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Elimane on Oct, 2017, at 21:55
 */
//@Configuration
public class ChuckConfiguration {

//        @Bean
        public ChuckNorrisQuotes getChuckQuotes()
        {
            return new ChuckNorrisQuotes();
        }


}
