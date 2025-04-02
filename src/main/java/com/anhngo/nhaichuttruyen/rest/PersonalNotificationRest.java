package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.PersonalNotificationDTO;
import com.anhngo.nhaichuttruyen.service.PersonalNotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/personalNotifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonalNotificationRest {

    private final PersonalNotificationService personalNotificationService;

    public PersonalNotificationRest(
            final PersonalNotificationService personalNotificationService) {
        this.personalNotificationService = personalNotificationService;
    }

    @GetMapping
    public ResponseEntity<List<PersonalNotificationDTO>> getAllPersonalNotifications() {
        return ResponseEntity.ok(personalNotificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalNotificationDTO> getPersonalNotification(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(personalNotificationService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createPersonalNotification(
            @RequestBody @Valid final PersonalNotificationDTO personalNotificationDTO) {
        final Integer createdId = personalNotificationService.create(personalNotificationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePersonalNotification(
            @PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PersonalNotificationDTO personalNotificationDTO) {
        personalNotificationService.update(id, personalNotificationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalNotification(
            @PathVariable(name = "id") final Integer id) {
        personalNotificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
