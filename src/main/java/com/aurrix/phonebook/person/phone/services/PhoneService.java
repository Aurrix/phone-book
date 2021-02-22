package com.aurrix.phonebook.person.phone.services;

import com.aurrix.phonebook.person.phone.models.PhoneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {
  Page<PhoneModel> getAll(Pageable pageable);

  PhoneModel getOne(Long id);

  Long postOne(PhoneModel model);

  void putOne(PhoneModel model);

  void deleteOne(Long id);
}
