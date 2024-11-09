package com.esprit.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class ResourceServerConfig {
    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()

                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                .anyRequest().authenticated() // OR .access("authenticated AND hasRole('product_read')")

                .and()

                .oauth2ResourceServer()
                ;
        return http.build();

    }



}
