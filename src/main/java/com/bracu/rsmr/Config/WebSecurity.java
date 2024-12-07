package com.bracu.rsmr.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests((authz) -> 
        {
            authz.requestMatchers("/api/**");
            authz.anyRequest().authenticated();
        }
        );

        return http.build();
    }    
}
