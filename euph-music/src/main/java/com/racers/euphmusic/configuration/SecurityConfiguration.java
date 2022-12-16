package com.racers.euphmusic.configuration;

import com.racers.euphmusic.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    PersonRepo personRepo;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(urlConfig -> urlConfig
                        .antMatchers("/login", "/registration").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
