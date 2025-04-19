package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.ImageDTO;
import com.anhngo.nhaichuttruyen.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/images", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageRest {

    private final ImageService imageService;

    public ImageRest(final ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        return ResponseEntity.ok(imageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getImage(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(imageService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createImage(@RequestBody @Valid final ImageDTO imageDTO) {
        final Integer createdId = imageService.create(imageDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateImage(@PathVariable(name = "id") final Integer id,
                                               @RequestBody @Valid final ImageDTO imageDTO) {
        imageService.update(id, imageDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable(name = "id") final Integer id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
