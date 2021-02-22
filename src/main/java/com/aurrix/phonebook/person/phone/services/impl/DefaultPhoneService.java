package com.aurrix.phonebook.person.phone.services.impl;

import com.aurrix.phonebook.person.exception.definition.PersonNotFoundException;
import com.aurrix.phonebook.person.phone.Phone;
import com.aurrix.phonebook.person.phone.exceptions.definition.PhoneNotFoundException;
import com.aurrix.phonebook.person.phone.models.PhoneModel;
import com.aurrix.phonebook.person.phone.respository.PhoneRepository;
import com.aurrix.phonebook.person.phone.services.PhoneService;
import com.aurrix.phonebook.person.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DefaultPhoneService implements PhoneService {
  private final PhoneRepository phoneRepository;
  private final PersonRepository personRepository;

  public DefaultPhoneService(PhoneRepository phoneRepository, PersonRepository personRepository) {
    this.phoneRepository = phoneRepository;
    this.personRepository = personRepository;
  }

  @Override
  public Page<PhoneModel> getAll(Pageable pageable) {
    return new PageImpl<>(
        this.phoneRepository.findAll(pageable).stream()
            .map(Phone::toModel)
            .collect(Collectors.toList()),
        pageable,
        phoneRepository.count());
  }

  @Override
  public PhoneModel getOne(Long id) {
    return phoneRepository
        .findById(id)
        .orElseThrow(() -> new PhoneNotFoundException("Cannot find phone with id: " + id))
        .toModel();
  }

  @Override
  public Long postOne(PhoneModel model) {
    personRepository
        .findById(model.getPerson())
        .orElseThrow(
            () -> new PersonNotFoundException("Cannot find person with id: " + model.getPerson()));
    return phoneRepository.save(model.toEntity()).getId();
  }

  @Override
  public void putOne(PhoneModel model) {
    personRepository
        .findById(model.getPerson())
        .orElseThrow(
            () -> new PersonNotFoundException("Cannot find person with id: " + model.getPerson()));
    phoneRepository
        .findById(model.getId())
        .orElseThrow(
            () -> new PhoneNotFoundException("Cannot find phone with id: " + model.getId()));
    phoneRepository.save(model.toEntity());
  }

  @Override
  public void deleteOne(Long id) {
    phoneRepository
        .findById(id)
        .orElseThrow(() -> new PhoneNotFoundException("Cannot find phone with id: " + id));
    phoneRepository.deleteById(id);
  }
}
