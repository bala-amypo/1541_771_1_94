package com.example.demo.config;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // JwtTokenProvider BEAN (needed by tests + AuthController)
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider("VerySecretKeyForJwtDemo1234567890");
    }

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
        .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
        )
        .httpBasic(config -> config.disable());   // 👈 popup disabled

    return http.build();
}

}
