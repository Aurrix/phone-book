package com.aurrix.phonebook.person.validators;

import com.aurrix.phonebook.person.validators.constraints.BeforeTodayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = BeforeTodayValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeforeToday {
    String message() default "Date is past today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
