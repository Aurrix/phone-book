package com.aurrix.phonebook.person.repositories;

import com.aurrix.phonebook.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
