package com.web.spring.rest.mobile.app.security;

import com.web.spring.rest.mobile.app.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Elimane on Sep, 2018, at 13:50
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    public static final String USER_POST_SIGN_UP = "/users";
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //Configuring web security based on specific requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Only authenticated requests will be accepted
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, USER_POST_SIGN_UP)
                .permitAll().anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
