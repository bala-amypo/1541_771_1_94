package com.example.demo.config;

import com.example.demo.security.JwtAuthFilter;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // one JwtTokenProvider bean, shared everywhere
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider("VerySecretKeyForJwtDemo1234567890");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthFilter jwtAuthFilter) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                    sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/",                     // portal home page
                            "/api/auth/**",          // auth endpoints
                            "/swagger-ui/**",        // swagger ui
                            "/swagger-ui.html",
                            "/v3/api-docs/**",       // openapi docs
                            "/webjars/**",
                            "/static/**",
                            "/css/**",
                            "/js/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(custom -> custom.disable())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
