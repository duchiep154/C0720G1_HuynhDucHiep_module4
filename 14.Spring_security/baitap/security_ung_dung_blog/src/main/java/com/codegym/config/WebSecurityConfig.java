package com.codegym.config;

import com.codegym.service.UserService;
import com.codegym.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
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
                .defaultSuccessUrl("/category")

                // login failed
                .failureUrl("/login?error=true")

                // setting username, password
                .usernameParameter("username")
                .passwordParameter("password")

                // logout
                .and().logout().logoutUrl("/logout")

                // logout successful
                .logoutSuccessUrl("/login");


        // ----------------------- Authorization ---------------------------------
        // guest
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();


        http.authorizeRequests().antMatchers("/category", "/category/{id}/view", "/blog", "/blog/{id}/view")
                .access("hasAnyRole('USER','ADMIN')");

        // admin
        http.authorizeRequests().antMatchers("/category/create", "/category/{id}/edit", "/category/{id}/delete",
                                                            "/blog/create", "/blog/{id}/edit", "/blog/{id}/delete")
                .access("hasRole('ADMIN')");

        // no permission
        http.exceptionHandling().accessDeniedPage("/403");

        // remember me
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberMeCookie")
                .tokenValiditySeconds(30)
                .tokenRepository(this.persistentTokenRepository());
    }

    // Token stored in Table - persistent_logins
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
