package com.shoppingcar.forMajo.product.encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery("select name, password, true from users where name=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select name, role from users  where name=?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->

                auth
                        .requestMatchers("/login-page").permitAll()
                        .requestMatchers("/start-page").permitAll()
                        .anyRequest().authenticated()
        )
                .formLogin(
                        formlogin ->
                                formlogin.loginPage("/login-page")
                                        .loginProcessingUrl("/authenticateTheUser")
                                        .defaultSuccessUrl("/shopping-homepage", true)

                )
                .csrf(csrf -> csrf.disable());


        return http.build();
    }
}
