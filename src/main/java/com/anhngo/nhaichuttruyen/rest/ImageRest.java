package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.ImageDTO;
import com.anhngo.nhaichuttruyen.service.CloudinaryService;
import com.anhngo.nhaichuttruyen.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(value = "/api/images", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageRest {

    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    public ImageRest(final ImageService imageService, CloudinaryService cloudinaryService) {
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        return ResponseEntity.ok(imageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getImage(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(imageService.get(id));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createImages(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("chapter") int chapter) {
        if (files.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không có file nào để tải lên");
        }

        for (MultipartFile file : files) {
            if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Một trong các file không hợp lệ");
            }

            String url = cloudinaryService.uploadFile(file);

            int orderNum = imageService.findAll().isEmpty() ? 1 : imageService.findAll().size() + 1;

            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setUrl(url);
            imageDTO.setOrderNum(orderNum);
            imageDTO.setChapter(chapter);
            imageService.create(imageDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Tải lên thành công");
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
