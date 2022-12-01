package ru.akhmetov.springcourse.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.akhmetov.springcourse.FirstSecurityApp.services.PersonDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Oleg Akhmetov on 28.11.2022
 */

//@EnableWebSecurity
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;


    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService)  {
        this.personDetailsService = personDetailsService;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
}
/*
@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
    provider.setUserDetailsService(personDetailsService);
    provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    return provider;
}
*/

/*@Bean
public AuthenticationManager authManager(HttpSecurity http, PersonDetailsService personDetailsService)
        throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(personDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .and()
            .build();
}*/




}
