package com.restdemo.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class RESTDemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //add users for inMemory authentication
        User.UserBuilder users = User.withDefaultPasswordEncoder();

//        auth.jdbcAuthentication().dataSource(dataSource);

        auth.inMemoryAuthentication()
                .withUser(users.username("feky").password("feky").roles("NORMAL_USER"))
                .withUser(users.username("fekyAdmin").password("fekyAdmin").roles("NORMAL_USER","ADMIN"))
                .withUser(users.username("fekyManager").password("fekyManager").roles("NORMAL_USER","MANAGER"));
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
//            "/",
//            "/newUser",
//            "/forgetPassword",
            "/login",
            "/fonts/**",
            "/images/**",
            "/fontawesome/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/api/").hasRole("NORMAL_USER")
                .antMatchers("/api/users").hasAnyRole("MANAGER","ADMIN")
                .and()
                .formLogin().loginPage("/loginPage")
//                .loginProcessingUrl("/auth")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

        http.authorizeRequests().
                antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
    }



}
