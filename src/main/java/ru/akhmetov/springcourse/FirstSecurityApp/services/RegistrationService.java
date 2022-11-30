package ru.akhmetov.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akhmetov.springcourse.FirstSecurityApp.models.Person;
import ru.akhmetov.springcourse.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

/**
 * @author Oleg Akhmetov on 30.11.2022
 */
@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }

    public Optional<Person> isUserCreated(Person person) {
        return peopleRepository.findByUsername(person.getUsername());
    }

}
