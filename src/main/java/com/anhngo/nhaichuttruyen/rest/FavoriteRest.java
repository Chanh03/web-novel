package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.FavoriteDTO;
import com.anhngo.nhaichuttruyen.service.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
public class FavoriteRest {

    private final FavoriteService favoriteService;

    public FavoriteRest(final FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<List<FavoriteDTO>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDTO> getFavorite(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(favoriteService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createFavorite(
            @RequestBody @Valid final FavoriteDTO favoriteDTO) {
        final Integer createdId = favoriteService.create(favoriteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateFavorite(@PathVariable(name = "id") final Integer id,
                                                  @RequestBody @Valid final FavoriteDTO favoriteDTO) {
        favoriteService.update(id, favoriteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable(name = "id") final Integer id) {
        favoriteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
