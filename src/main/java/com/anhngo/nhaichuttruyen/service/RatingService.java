package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.RatingDTO;
import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Rating;
import com.anhngo.nhaichuttruyen.repos.ChapterRepository;
import com.anhngo.nhaichuttruyen.repos.RatingRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final ChapterRepository chapterRepository;

    public RatingService(final RatingRepository ratingRepository,
                         final ChapterRepository chapterRepository) {
        this.ratingRepository = ratingRepository;
        this.chapterRepository = chapterRepository;
    }

    public List<RatingDTO> findAll() {
        final List<Rating> ratings = ratingRepository.findAll(Sort.by("id"));
        return ratings.stream()
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .toList();
    }

    public RatingDTO get(final Integer id) {
        return ratingRepository.findById(id)
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RatingDTO ratingDTO) {
        final Rating rating = new Rating();
        mapToEntity(ratingDTO, rating);
        return ratingRepository.save(rating).getId();
    }

    public void update(final Integer id, final RatingDTO ratingDTO) {
        final Rating rating = ratingRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ratingDTO, rating);
        ratingRepository.save(rating);
    }

    public void delete(final Integer id) {
        ratingRepository.deleteById(id);
    }

    private RatingDTO mapToDTO(final Rating rating, final RatingDTO ratingDTO) {
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setComment(rating.getComment());
        ratingDTO.setCreateDate(rating.getCreateDate());
        ratingDTO.setUpdateDate(rating.getUpdateDate());
        ratingDTO.setChapter(rating.getChapter() == null ? null : rating.getChapter().getId());
        return ratingDTO;
    }

    private Rating mapToEntity(final RatingDTO ratingDTO, final Rating rating) {
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        rating.setCreateDate(ratingDTO.getCreateDate());
        rating.setUpdateDate(ratingDTO.getUpdateDate());
        final Chapter chapter = ratingDTO.getChapter() == null ? null : chapterRepository.findById(ratingDTO.getChapter())
                .orElseThrow(() -> new NotFoundException("chapter not found"));
        rating.setChapter(chapter);
        return rating;
    }

}
