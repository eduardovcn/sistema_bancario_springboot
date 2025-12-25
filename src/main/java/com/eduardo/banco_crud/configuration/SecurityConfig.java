package com.eduardo.banco_crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura as regras de segurança HTTP.
       Desabilita CSRF (para permitir POST via Postman).
       Exige autenticação para qualquer requisição.
       Habilita o login via HTTP Basic (aba Authorization do Postman).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
       Cria um usuário em memória para testes.
       Substitui a configuração do application.properties.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // Apenas para desenvolvimento
                .username("admin")
                .password("12345")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}