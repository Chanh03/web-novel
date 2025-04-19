package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.GenreNovelDTO;
import com.anhngo.nhaichuttruyen.service.GenreNovelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/genreNovels", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreNovelRest {

    private final GenreNovelService genreNovelService;

    public GenreNovelRest(final GenreNovelService genreNovelService) {
        this.genreNovelService = genreNovelService;
    }

    @GetMapping
    public ResponseEntity<List<GenreNovelDTO>> getAllGenreNovels() {
        return ResponseEntity.ok(genreNovelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreNovelDTO> getGenreNovel(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(genreNovelService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createGenreNovel(
            @RequestBody @Valid final GenreNovelDTO genreNovelDTO) {
        final Integer createdId = genreNovelService.create(genreNovelDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateGenreNovel(@PathVariable(name = "id") final Integer id,
                                                    @RequestBody @Valid final GenreNovelDTO genreNovelDTO) {
        genreNovelService.update(id, genreNovelDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreNovel(@PathVariable(name = "id") final Integer id) {
        genreNovelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
