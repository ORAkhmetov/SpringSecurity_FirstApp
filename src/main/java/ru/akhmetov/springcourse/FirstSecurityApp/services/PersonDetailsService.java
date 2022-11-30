package ru.akhmetov.springcourse.FirstSecurityApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akhmetov.springcourse.FirstSecurityApp.models.Person;
import ru.akhmetov.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.akhmetov.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

/**
 * @author Oleg Akhmetov on 28.11.2022
 */
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;


    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new PersonDetails(person.get());
    }
}
