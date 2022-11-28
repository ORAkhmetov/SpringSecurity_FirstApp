package ru.akhmetov.springcourse.FirstSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.akhmetov.springcourse.FirstSecurityApp.services.PersonDetailsServices;

import java.util.Collections;

/**
 * @author Oleg Akhmetov on 28.11.2022
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsServices personDetailsServices;

    @Autowired
    public AuthProviderImpl(PersonDetailsServices personDetailsServices) {
        this.personDetailsServices = personDetailsServices;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails personDetails = personDetailsServices.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if(!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(personDetails, password,
                Collections.emptyList());


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
