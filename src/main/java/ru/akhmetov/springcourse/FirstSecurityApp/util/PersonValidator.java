package ru.akhmetov.springcourse.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.akhmetov.springcourse.FirstSecurityApp.models.Person;
import ru.akhmetov.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.akhmetov.springcourse.FirstSecurityApp.services.RegistrationService;

import java.util.Optional;

/**
 * @author Oleg Akhmetov on 30.11.2022
 */
@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;
    private final RegistrationService registrationService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService, RegistrationService registrationService) {
        this.personDetailsService = personDetailsService;
        this.registrationService = registrationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Optional<Person> person = registrationService.isUserCreated((Person) target);
        if (person.isPresent()) {
            errors.rejectValue("username", "", "Человек с таким именем уже существует");
        }

        /*try {
            personDetailsService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }*/

    }
}
