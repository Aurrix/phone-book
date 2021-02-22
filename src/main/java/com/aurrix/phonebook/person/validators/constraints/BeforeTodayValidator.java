package com.aurrix.phonebook.person.validators.constraints;

import com.aurrix.phonebook.person.validators.BeforeToday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BeforeTodayValidator implements ConstraintValidator<BeforeToday, LocalDate> {

  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
    return date.isBefore(LocalDate.now());
  }
}
