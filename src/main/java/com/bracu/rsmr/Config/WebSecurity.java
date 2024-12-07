package com.bracu.rsmr.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http
        .csrf(csrfmgr -> {
            csrfmgr.disable();
        })
        .authorizeHttpRequests((authz) -> 
        {
            authz.anyRequest().permitAll();
        }
        );

        return http.build();
    }    
}
