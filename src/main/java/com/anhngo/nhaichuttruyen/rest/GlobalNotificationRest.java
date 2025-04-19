package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.GlobalNotificationDTO;
import com.anhngo.nhaichuttruyen.service.GlobalNotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/globalNotifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalNotificationRest {

    private final GlobalNotificationService globalNotificationService;

    public GlobalNotificationRest(final GlobalNotificationService globalNotificationService) {
        this.globalNotificationService = globalNotificationService;
    }

    @GetMapping
    public ResponseEntity<List<GlobalNotificationDTO>> getAllGlobalNotifications() {
        return ResponseEntity.ok(globalNotificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalNotificationDTO> getGlobalNotification(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(globalNotificationService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createGlobalNotification(
            @RequestBody @Valid final GlobalNotificationDTO globalNotificationDTO) {
        final Integer createdId = globalNotificationService.create(globalNotificationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateGlobalNotification(
            @PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final GlobalNotificationDTO globalNotificationDTO) {
        globalNotificationService.update(id, globalNotificationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlobalNotification(
            @PathVariable(name = "id") final Integer id) {
        globalNotificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
