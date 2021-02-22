package com.aurrix.phonebook.person.services.impl;

import com.aurrix.phonebook.person.Person;
import com.aurrix.phonebook.person.exception.definition.PersonNotFoundException;
import com.aurrix.phonebook.person.models.PersonModel;
import com.aurrix.phonebook.person.repositories.PersonRepository;
import com.aurrix.phonebook.person.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DefaultPersonService implements PersonService {
  private final PersonRepository personRepository;

  public DefaultPersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Page<PersonModel> getAll(Pageable pageable) {
    return new PageImpl<>(
        this.personRepository.findAll(pageable).stream()
            .map(Person::toModel)
            .collect(Collectors.toList()),
        pageable,
        personRepository.count());
  }

  @Override
  public PersonModel getOne(Long id) {
    return personRepository
        .findById(id)
        .orElseThrow(() -> new PersonNotFoundException("Cannot find person with id : " + id))
        .toModel();
  }

  @Override
  public Long postOne(PersonModel model) {
    return personRepository.save(model.toEntity()).getId();
  }

  @Override
  public void putOne(PersonModel model) {
    personRepository
        .findById(model.getId())
        .orElseThrow(
            () -> new PersonNotFoundException("Cannot find person with id : " + model.getId()));
    personRepository.save(model.toEntity());
  }

  @Override
  public void deleteOne(Long id) {
    personRepository
        .findById(id)
        .orElseThrow(() -> new PersonNotFoundException("Cannot find person with id : " + id));
    personRepository.deleteById(id);
  }
}
