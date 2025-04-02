package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.UserRankDTO;
import com.anhngo.nhaichuttruyen.service.UserRankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/userRanks", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRankRest {

    private final UserRankService userRankService;

    public UserRankRest(final UserRankService userRankService) {
        this.userRankService = userRankService;
    }

    @GetMapping
    public ResponseEntity<List<UserRankDTO>> getAllUserRanks() {
        return ResponseEntity.ok(userRankService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRankDTO> getUserRank(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(userRankService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createUserRank(
            @RequestBody @Valid final UserRankDTO userRankDTO) {
        final Integer createdId = userRankService.create(userRankDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUserRank(@PathVariable(name = "id") final Integer id,
                                                  @RequestBody @Valid final UserRankDTO userRankDTO) {
        userRankService.update(id, userRankDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRank(@PathVariable(name = "id") final Integer id) {
        userRankService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
