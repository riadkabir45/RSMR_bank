package com.bracu.rsmr.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bracu.rsmr.User.UserLogService;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    private UserLogService userLogService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http
        .httpBasic(httpmgr -> {
            httpmgr.init(http);
        })
        .userDetailsService(userLogService)
        .csrf(csrfmgr -> {
            csrfmgr.disable();
        })
        .authorizeHttpRequests((authz) -> 
        {
            authz.requestMatchers("/login").permitAll();
            authz.anyRequest().authenticated();
        }
        ).formLogin(logmgr -> {
            logmgr.permitAll();
            logmgr.defaultSuccessUrl("/");
        });

        return http.build();
    }    
}
