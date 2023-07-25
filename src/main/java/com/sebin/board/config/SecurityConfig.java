package com.sebin.board.config;

import com.sebin.board.jwt.JwtAccessDeniedHandler;
import com.sebin.board.jwt.JwtAuthenticationEntryPoint;
import com.sebin.board.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class SecurityConfig implements WebMvcConfigurer {

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final TokenProvider tokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000")
        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .securityMatcher("/**").authorizeHttpRequests(auth -> {
          auth.requestMatchers("/api/auth/**").permitAll();
          auth.anyRequest().authenticated();
        })
        .exceptionHandling((exceptionHandling) -> {
          exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
          exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler);
        })
        .apply(new JwtSecurityConfig(tokenProvider));

    return http.build();
  }
}
