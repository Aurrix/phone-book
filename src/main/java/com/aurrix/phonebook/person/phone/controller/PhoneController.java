package com.aurrix.phonebook.person.phone.controller;

import com.aurrix.phonebook.person.phone.models.PhoneModel;
import com.aurrix.phonebook.person.phone.services.PhoneService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/phone")
@Api
public class PhoneController {

  private final PhoneService phoneService;

  public PhoneController(PhoneService phoneService) {
    this.phoneService = phoneService;
  }

  @GetMapping
  public ResponseEntity<Page<PhoneModel>> getAll(Pageable pageable) {
    return ResponseEntity.ok(phoneService.getAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PhoneModel> getOne(@PathVariable Long id) {
    return ResponseEntity.ok(phoneService.getOne(id));
  }

  @PostMapping
  public ResponseEntity<Void> postOne(@Valid @RequestBody PhoneModel model) {
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneService.postOne(model))
                .toUri())
        .build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putOne(@PathVariable Long id, @Valid @RequestBody PhoneModel model) {
    model.setId(id);
    phoneService.putOne(model);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
    phoneService.deleteOne(id);
    return ResponseEntity.ok().build();
  }
}
