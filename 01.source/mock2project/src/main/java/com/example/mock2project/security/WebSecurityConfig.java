package com.example.mock2project.security;

import com.example.mock2project.filter.CustomAuthenticationFilter;
import com.example.mock2project.filter.CustomAuthorizationFilter;
import com.example.mock2project.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/signup","/token/refresh/**", "/confirm", "/product").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/user/profile/**").hasAnyAuthority("ROLE_ACTIVE_USER",
                        "ROLE_SYSTEM_ADMIN", "ROLE_SALE_ADMIN");
        http
                .addFilter(new CustomAuthenticationFilter(authenticationManager()));
        http
                .addFilterBefore(new CustomAuthorizationFilter() , UsernamePasswordAuthenticationFilter.class);
        http.logout();

    }
}
