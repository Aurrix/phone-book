package com.aurrix.phonebook;

import com.aurrix.phonebook.person.models.PersonModel;
import com.aurrix.phonebook.person.phone.models.PhoneModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PhoneBookApplicationTests {

  @Autowired TestRestTemplate restTemplate;

  @LocalServerPort int port;

  public String rootUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void crud() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic dXNlcjp1c2Vy");
    headers.add("Content-Type", "application/json");
    String person =
        "{\n"
            + "    \"firstName\":\"Given\",\n"
            + "    \"lastName\":\"Family\",\n"
            + "    \"birthDate\":\"1997-04-31\"\n"
            + "}";
    String phone =
        "{\n"
            + "    \"number\":\"+371 24444688\",\n"
            + "    \"type\":\"mobile\",\n"
            + "    \"person\":1\n"
            + "}";

    String personUpdated =
        "{\n"
            + "    \"id\":1,\n"
            + "    \"firstName\":\"GivenUpdated\",\n"
            + "    \"lastName\":\"FamilyUpdated\",\n"
            + "    \"birthDate\":\"1990-09-09\"\n"
            + "}";
    String phoneUpdated =
        "{\n"
            + "    \"id\":1,\n"
            + "    \"number\":\"+371 99999999\",\n"
            + "    \"type\":\"work\",\n"
            + "    \"person\":1\n"
            + "}";

    // Create
    ResponseEntity<Void> forPostPerson =
        restTemplate.exchange(
            rootUrl() + "/person", HttpMethod.POST, new HttpEntity<>(person, headers), Void.class);
    assertEquals(HttpStatus.CREATED, forPostPerson.getStatusCode());
    assertEquals(rootUrl() + "/person/1", forPostPerson.getHeaders().getFirst("Location"));
    ResponseEntity<Void> forPostPhone =
        restTemplate.exchange(
            rootUrl() + "/phone", HttpMethod.POST, new HttpEntity<>(phone, headers), Void.class);
    assertEquals(HttpStatus.CREATED, forPostPhone.getStatusCode());
    assertEquals(rootUrl() + "/phone/1", forPostPhone.getHeaders().getFirst("Location"));

    // Update
    ResponseEntity<Void> forPutPerson =
        restTemplate.exchange(
            rootUrl() + "/person/1",
            HttpMethod.PUT,
            new HttpEntity<>(personUpdated, headers),
            Void.class);
    assertEquals(HttpStatus.OK, forPutPerson.getStatusCode());

    ResponseEntity<PhoneModel> forPutPhone =
        restTemplate.exchange(
            rootUrl() + "/phone/1",
            HttpMethod.PUT,
            new HttpEntity<>(phoneUpdated, headers),
            PhoneModel.class);
    assertEquals(HttpStatus.OK, forPutPhone.getStatusCode());

    // Get

    ResponseEntity<PersonModel> forGetPerson =
        restTemplate.exchange(
            rootUrl() + "/person/1",
            HttpMethod.GET,
            new HttpEntity<>("", headers),
            PersonModel.class);

    assertEquals(HttpStatus.OK, forPutPerson.getStatusCode());
    assertEquals("GivenUpdated", forGetPerson.getBody().getFirstName());
    assertEquals("FamilyUpdated", forGetPerson.getBody().getLastName());
    assertEquals(
        "1990-09-09",
        forGetPerson.getBody().getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    ResponseEntity<PhoneModel> forGetPhone =
        restTemplate.exchange(
            rootUrl() + "/phone/1",
            HttpMethod.GET,
            new HttpEntity<>(phoneUpdated, headers),
            PhoneModel.class);
    assertEquals(HttpStatus.OK, forPutPhone.getStatusCode());

    assertEquals("+371 99999999", forGetPhone.getBody().getNumber());
    assertEquals("work", forGetPhone.getBody().getType().toString());

    // Get all

    ResponseEntity<String> forGetAllPerson =
        restTemplate.exchange(
            rootUrl() + "/person",
            HttpMethod.GET,
            new HttpEntity<>("", headers),
            String.class);
    assertEquals(HttpStatus.OK, forPutPerson.getStatusCode());
    assertTrue(forGetAllPerson.getBody().contains("GivenUpdated"));
    assertTrue(forGetAllPerson.getBody().contains("FamilyUpdated"));
    assertTrue(forGetAllPerson.getBody().contains("1990-09-09"));
    assertTrue(forGetAllPerson.getBody().contains("+371 99999999"));
    ResponseEntity<String> forGetAllPhone =
        restTemplate.exchange(
            rootUrl() + "/phone",
            HttpMethod.GET,
            new HttpEntity<>(phoneUpdated, headers),
            String.class);

    assertEquals(HttpStatus.OK, forGetAllPhone.getStatusCode());
    assertTrue(forGetAllPhone.getBody().contains("+371 99999999"));
    assertTrue(forGetAllPhone.getBody().contains("work"));
  }
}
