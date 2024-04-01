package org.ekal.ivd.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SESSIONLESS_URLS = {
            "/LoginController/**",
            "/public/**",
            "/index.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SESSIONLESS_URLS).permitAll()
                        .anyRequest().permitAll()
//                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
