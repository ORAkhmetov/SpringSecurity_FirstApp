package ru.akhmetov.springcourse.FirstSecurityApp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import ru.akhmetov.springcourse.FirstSecurityApp.security.AuthProviderImpl;

/**
 * @author Oleg Akhmetov on 28.11.2022
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    //Настраивает аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}
