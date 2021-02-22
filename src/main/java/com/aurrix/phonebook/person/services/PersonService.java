package com.aurrix.phonebook.person.services;

import com.aurrix.phonebook.person.models.PersonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
  Page<PersonModel> getAll(Pageable pageable);

  PersonModel getOne(Long id);

  Long postOne(PersonModel model);

  void putOne(PersonModel model);

  void deleteOne(Long id);
}
