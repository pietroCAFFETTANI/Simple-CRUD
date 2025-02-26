package com.REST_Project.API.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean //Spring instaciar a classe
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Statless (REST)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.DELETE, "/books/{id}/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/{id}/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/add").hasRole("ADMIN")
                        .anyRequest().authenticated())//Quais são as requisições HTTP que serão authorizadas dependedo do Role
                .build();
    }

}
