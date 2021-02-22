package com.aurrix.phonebook.person.phone;

import com.aurrix.phonebook.person.Person;
import com.aurrix.phonebook.person.phone.enums.PhoneTypes;
import com.aurrix.phonebook.person.phone.models.PhoneModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "BIGINT(20)")
  private Long id;

  @Column(columnDefinition = "VARCHAR(13)")
  private String number;

  @Column(columnDefinition = "VARCHAR(6)")
  private PhoneTypes type;

  @ManyToOne private Person person;

  public final PhoneModel toModel() {
    return PhoneModel.builder()
        .id(this.id)
        .number(this.number)
        .type(this.type)
        .person(this.person.getId())
        .build();
  }
}
