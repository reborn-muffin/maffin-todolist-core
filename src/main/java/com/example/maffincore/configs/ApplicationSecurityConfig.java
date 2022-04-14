package com.example.maffincore.configs;

import com.example.maffincore.services.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImp userDetailsServiceImp;

    public ApplicationSecurityConfig(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "login", "register").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImp);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }
}
