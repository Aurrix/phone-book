package com.aurrix.phonebook.person.controller;

import com.aurrix.phonebook.person.models.PersonModel;
import com.aurrix.phonebook.person.services.PersonService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
@Api
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<Page<PersonModel>> getAll(Pageable pageable) {
    return ResponseEntity.ok(personService.getAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonModel> getOne(@PathVariable Long id) {
    return ResponseEntity.ok(personService.getOne(id));
  }

  @PostMapping
  public ResponseEntity<Void> postOne(@Valid @RequestBody PersonModel model) {
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(personService.postOne(model))
                .toUri())
        .build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putOne(@PathVariable Long id, @Valid @RequestBody PersonModel model) {
    model.setId(id);
    personService.putOne(model);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
    personService.deleteOne(id);
    return ResponseEntity.ok().build();
  }
}
