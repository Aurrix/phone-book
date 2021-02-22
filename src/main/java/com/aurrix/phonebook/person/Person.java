package com.aurrix.phonebook.person;

import com.aurrix.phonebook.person.models.PersonModel;
import com.aurrix.phonebook.person.phone.Phone;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "BIGINT(20)")
  private Long id;

  @Column(columnDefinition = "VARCHAR(20)")
  private String firstName;

  @Column(columnDefinition = "VARCHAR(20)")
  private String lastName;

  private Date birthDate;

  @OneToMany(mappedBy = "person",fetch = FetchType.EAGER) private List<Phone> phones;

  public final PersonModel toModel() {
    return PersonModel.builder()
        .id(this.id)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .birthDate(this.birthDate.toLocalDate())
        .phones(this.phones.stream().map(Phone::toModel).collect(Collectors.toList()))
        .build();
  }
}
