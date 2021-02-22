package com.aurrix.phonebook.person.models;

import com.aurrix.phonebook.person.Person;
import com.aurrix.phonebook.person.phone.models.PhoneModel;
import com.aurrix.phonebook.person.validators.BeforeToday;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonModel {
  private Long id;
  @NotEmpty private String firstName;
  @NotEmpty private String lastName;

  @BeforeToday
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  private List<PhoneModel> phones;

  public final Person toEntity() {
    return Person.builder()
        .id(this.id)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .birthDate(java.sql.Date.valueOf(this.birthDate))
        .build();
  }
}
