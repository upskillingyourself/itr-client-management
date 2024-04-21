package com.toratax.securityconfig;

import com.toratax.filters.CsrfCookieFilter;
import com.toratax.filters.JWTTokenGeneratorFilter;
import com.toratax.filters.JWTTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();
        csrfHandler.setCsrfRequestAttributeName("_csrf");




        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfiguration -> corsConfiguration.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfig = new CorsConfiguration();
                        corsConfig.setAllowedMethods(Collections.singletonList("*"));
                        corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsConfig.setAllowCredentials(true);
                        corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
                        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfig.setMaxAge(3600L);

                        return corsConfig;
                    }
                }))
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfHandler).ignoringRequestMatchers("/toratax/rest/v1.0/register")
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/toratax/rest/v1.0/register").permitAll())
                .authorizeHttpRequests(requests->requests.requestMatchers("/toratax/rest/v1.0/users").hasAuthority("ADMIN"))
                .authorizeHttpRequests(requests->requests.anyRequest().authenticated())

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder endCodePassword() {
        return new BCryptPasswordEncoder();
    }
}
