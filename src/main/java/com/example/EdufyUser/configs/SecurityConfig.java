package com.example.EdufyUser.configs;

import com.example.EdufyUser.convertes.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//ED-47-SJ
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //ED-223-AA
    private JwtAuthConverter jwtAuthConverter;

    @Autowired
    public void setJwtAuthConverter(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(fo -> fo.disable()))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2
                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }
}
