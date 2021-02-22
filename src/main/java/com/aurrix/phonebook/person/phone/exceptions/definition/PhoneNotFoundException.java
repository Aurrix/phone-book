package com.aurrix.phonebook.person.phone.exceptions.definition;

public class PhoneNotFoundException extends RuntimeException {
  public PhoneNotFoundException(String message) {
    super(message);
  }
}
