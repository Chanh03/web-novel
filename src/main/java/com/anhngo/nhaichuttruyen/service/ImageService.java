package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.ImageDTO;
import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Image;
import com.anhngo.nhaichuttruyen.repos.ChapterRepository;
import com.anhngo.nhaichuttruyen.repos.ImageRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ChapterRepository chapterRepository;

    public ImageService(final ImageRepository imageRepository,
                        final ChapterRepository chapterRepository) {
        this.imageRepository = imageRepository;
        this.chapterRepository = chapterRepository;
    }

    public List<ImageDTO> findAll() {
        final List<Image> images = imageRepository.findAll(Sort.by("id"));
        return images.stream()
                .map(image -> mapToDTO(image, new ImageDTO()))
                .toList();
    }

    public ImageDTO get(final Integer id) {
        return imageRepository.findById(id)
                .map(image -> mapToDTO(image, new ImageDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ImageDTO imageDTO) {
        final Image image = new Image();
        mapToEntity(imageDTO, image);
        return imageRepository.save(image).getId();
    }

    public void update(final Integer id, final ImageDTO imageDTO) {
        final Image image = imageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(imageDTO, image);
        imageRepository.save(image);
    }

    public void delete(final Integer id) {
        imageRepository.deleteById(id);
    }

    private ImageDTO mapToDTO(final Image image, final ImageDTO imageDTO) {
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setOrderNum(image.getOrderNum());
        imageDTO.setCreateDate(image.getCreateDate());
        imageDTO.setChapter(image.getChapter() == null ? null : image.getChapter().getId());
        return imageDTO;
    }

    private Image mapToEntity(final ImageDTO imageDTO, final Image image) {
        image.setUrl(imageDTO.getUrl());
        image.setOrderNum(imageDTO.getOrderNum());
        image.setCreateDate(imageDTO.getCreateDate());
        final Chapter chapter = imageDTO.getChapter() == null ? null : chapterRepository.findById(imageDTO.getChapter())
                .orElseThrow(() -> new NotFoundException("chapter not found"));
        image.setChapter(chapter);
        return image;
    }

}
