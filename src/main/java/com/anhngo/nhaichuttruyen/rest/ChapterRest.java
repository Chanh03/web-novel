package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.ChapterDTO;
import com.anhngo.nhaichuttruyen.service.ChapterService;
import com.anhngo.nhaichuttruyen.util.ReferencedException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/chapters", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChapterRest {

    private final ChapterService chapterService;

    public ChapterRest(final ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getAllChapters() {
        return ResponseEntity.ok(chapterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDTO> getChapter(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(chapterService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createChapter(@RequestBody @Valid final ChapterDTO chapterDTO) {
        final Integer createdId = chapterService.create(chapterDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateChapter(@PathVariable(name = "id") final Integer id,
                                                 @RequestBody @Valid final ChapterDTO chapterDTO) {
        chapterService.update(id, chapterDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = chapterService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        chapterService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
