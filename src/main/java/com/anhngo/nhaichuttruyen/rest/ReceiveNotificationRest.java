package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.ReceiveNotificationDTO;
import com.anhngo.nhaichuttruyen.service.ReceiveNotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/receiveNotifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceiveNotificationRest {

    private final ReceiveNotificationService receiveNotificationService;

    public ReceiveNotificationRest(
            final ReceiveNotificationService receiveNotificationService) {
        this.receiveNotificationService = receiveNotificationService;
    }

    @GetMapping
    public ResponseEntity<List<ReceiveNotificationDTO>> getAllReceiveNotifications() {
        return ResponseEntity.ok(receiveNotificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiveNotificationDTO> getReceiveNotification(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(receiveNotificationService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createReceiveNotification(
            @RequestBody @Valid final ReceiveNotificationDTO receiveNotificationDTO) {
        final Integer createdId = receiveNotificationService.create(receiveNotificationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateReceiveNotification(
            @PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ReceiveNotificationDTO receiveNotificationDTO) {
        receiveNotificationService.update(id, receiveNotificationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceiveNotification(
            @PathVariable(name = "id") final Integer id) {
        receiveNotificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
