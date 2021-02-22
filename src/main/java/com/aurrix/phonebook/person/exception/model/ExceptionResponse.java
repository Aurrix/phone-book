package com.aurrix.phonebook.person.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
  private List<String> exceptions;
}
