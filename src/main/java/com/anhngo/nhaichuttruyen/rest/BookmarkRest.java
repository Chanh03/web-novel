package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.BookmarkDTO;
import com.anhngo.nhaichuttruyen.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/bookmarks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookmarkRest {

    private final BookmarkService bookmarkService;

    public BookmarkRest(final BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public ResponseEntity<List<BookmarkDTO>> getAllBookmarks() {
        return ResponseEntity.ok(bookmarkService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkDTO> getBookmark(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(bookmarkService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createBookmark(
            @RequestBody @Valid final BookmarkDTO bookmarkDTO) {
        final Integer createdId = bookmarkService.create(bookmarkDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateBookmark(@PathVariable(name = "id") final Integer id,
                                                  @RequestBody @Valid final BookmarkDTO bookmarkDTO) {
        bookmarkService.update(id, bookmarkDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable(name = "id") final Integer id) {
        bookmarkService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
