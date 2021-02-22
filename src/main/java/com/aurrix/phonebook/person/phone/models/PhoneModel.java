package com.aurrix.phonebook.person.phone.models;

import com.aurrix.phonebook.person.Person;
import com.aurrix.phonebook.person.phone.Phone;
import com.aurrix.phonebook.person.phone.enums.PhoneTypes;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneModel {

  private Long id;

  @NotEmpty
  @Pattern(
      regexp = "\\+371 [0-9]{8}",
      message = "Invalid Phone number format. Should be: +371 XXXXXXXX")
  private String number;

  @NotNull private PhoneTypes type;
  @NotNull private Long person;

  public final Phone toEntity() {
    return Phone.builder()
        .id(this.id)
        .number(this.number)
        .type(this.type)
        .person(Person.builder().id(person).build())
        .build();
  }
}
