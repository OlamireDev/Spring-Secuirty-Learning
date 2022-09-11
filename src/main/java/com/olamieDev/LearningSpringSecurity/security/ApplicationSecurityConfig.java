package com.olamieDev.LearningSpringSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.olamieDev.LearningSpringSecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","/css/*", "/js/*")//hey everything with this path doesn't need security
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers("/management/**").hasRole(TEACHER.name())
                .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails georgeOlamire = User.builder()
                .username("olamire")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();

        UserDetails annaSmith = User.builder()
                .username("anna")
                .password(passwordEncoder.encode("winter"))
                .roles(TEACHER.name()).build();

        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("winter"))
                .roles(TEACHER_TRAINEE.name()).build();

        return  new InMemoryUserDetailsManager(georgeOlamire, annaSmith, tom);
    }
}
