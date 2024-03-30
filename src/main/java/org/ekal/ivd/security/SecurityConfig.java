package org.ekal.ivd.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SESSIONLESS_URLS = {
            "/LoginController/**",
            "/LoginController/validateOTP",
            "/public/**",
            "/index.html"
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(Arrays.toString(SESSIONLESS_URLS));
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SESSIONLESS_URLS).permitAll()
                        .anyRequest().authenticated()
                );
//        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
