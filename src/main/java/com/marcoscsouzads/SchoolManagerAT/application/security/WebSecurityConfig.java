package com.marcoscsouzads.SchoolManagerAT.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").authenticated() // Permitir acesso aos endpoints API após autenticação
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()); // Habilitar autenticação HTTP básica
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Definir um usuário em memória com papel "USER"
        var userDetailsManager = new InMemoryUserDetailsManager();
        var user = User.withUsername("teste")
                .password("{noop}12345") // {noop} para indicar que a senha não está criptografada
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        return userDetailsManager;
    }
}
