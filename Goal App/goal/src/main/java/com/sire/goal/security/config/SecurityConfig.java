package com.sire.goal.security.config;


import com.sire.goal.security.filter.JwtAuthFilter;
import com.sire.goal.security.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImp userDetailsServiceImp;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(UserDetailsServiceImp UserDetailsServiceImp, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsServiceImp = UserDetailsServiceImp;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    req->req.requestMatchers("/auth/login/**",
                                    "/auth/register/**",
                                    "/v2/api-docs",
                                    "/swagger-resources",
                                    "/swagger-resources/**",
                                    "configuration/ui",
                                    "configuration/security",
                                    "/swagger-ui.html",
                                    "/webjars/**",
                                    "/v3/api-docs/**",
                                    "/swagger-ui/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
