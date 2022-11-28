package ru.akhmetov.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akhmetov.springcourse.FirstSecurityApp.models.Person;

import java.util.Optional;

/**
 * @author Oleg Akhmetov on 28.11.2022
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
