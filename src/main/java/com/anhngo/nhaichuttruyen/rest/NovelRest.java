package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.NovelDTO;
import com.anhngo.nhaichuttruyen.service.NovelService;
import com.anhngo.nhaichuttruyen.util.ReferencedException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/novels", produces = MediaType.APPLICATION_JSON_VALUE)
public class NovelRest {

    private final NovelService novelService;

    public NovelRest(final NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping
    public ResponseEntity<List<NovelDTO>> getAllNovels() {
        return ResponseEntity.ok(novelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovelDTO> getNovel(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(novelService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createNovel(@RequestBody @Valid final NovelDTO novelDTO) {
        final Integer createdId = novelService.create(novelDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateNovel(@PathVariable(name = "id") final Integer id,
                                               @RequestBody @Valid final NovelDTO novelDTO) {
        novelService.update(id, novelDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovel(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = novelService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        novelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
