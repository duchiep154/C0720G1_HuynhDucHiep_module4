package com.codegym.config;

import com.codegym.service.login.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf
        http.csrf().disable();

        // login
        http.formLogin()
                .loginProcessingUrl("/checkLogin")
                .loginPage("/login")
                // login successful
                .defaultSuccessUrl("/customer")
                // login failed
                .failureUrl("/login/wrong-password")
                // setting username, password
                .usernameParameter("userName")
                .passwordParameter("password")
                // logout
                .and().logout().logoutUrl("/logout")
                // logout successful
                .logoutSuccessUrl("/login");

        // authorization
        // guest
        //        http.authorizeRequests().antMatchers("/login").permitAll();

        //        http.authorizeRequests().antMatchers("/", "/student", "/student/detail").hasRole("USER");
        //        http.authorizeRequests().antMatchers("/", "/student", "/student/detail")
        //                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
        //        http.authorizeRequests().antMatchers("/employee")
        //                .hasAnyAuthority("Admin");

        //        http.authorizeRequests().antMatchers("/customer/**").hasAnyRole("ADMIN", "MEMBER");

        //        http.authorizeRequests().antMatchers("/customer", "/service", "/contract", "/contractDetail")
        //                .hasAnyAuthority("Member");

        // no permission
        http.exceptionHandling().accessDeniedPage("/login/no-accessing");

        // remember me
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberMeCookie")
                .tokenValiditySeconds(60 * 60 * 24 * 365 * 25);
    }
}
