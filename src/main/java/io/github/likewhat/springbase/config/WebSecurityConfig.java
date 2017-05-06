package io.github.likewhat.springbase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import io.github.likewhat.springbase.security.CustomAuthenticationSuccessHandler;
import io.github.likewhat.springbase.security.CustomLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    private final CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public WebSecurityConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler,
                             CustomLogoutSuccessHandler logoutSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/index",
                        "/about",
                        "/account/login",
                        "/account/reset-password",
                        "/account/register",
                        "/api/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}