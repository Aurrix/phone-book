package com.aurrix.phonebook.person.phone.respository;

import com.aurrix.phonebook.person.phone.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {}
