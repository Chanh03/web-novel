package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.GenreDTO;
import com.anhngo.nhaichuttruyen.service.GenreService;
import com.anhngo.nhaichuttruyen.util.ReferencedException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/genres", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreRest {

    private final GenreService genreService;

    public GenreRest(final GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(genreService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createGenre(@RequestBody @Valid final GenreDTO genreDTO) {
        final Integer createdId = genreService.create(genreDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateGenre(@PathVariable(name = "id") final Integer id,
                                               @RequestBody @Valid final GenreDTO genreDTO) {
        genreService.update(id, genreDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = genreService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
