package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.ChapterDTO;
import com.anhngo.nhaichuttruyen.entity.*;
import com.anhngo.nhaichuttruyen.repos.*;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final NovelRepository novelRepository;
    private final ImageRepository imageRepository;
    private final BookmarkRepository bookmarkRepository;
    private final RatingRepository ratingRepository;
    private final PurchasRepository purchasRepository;

    public ChapterService(final ChapterRepository chapterRepository,
                          final NovelRepository novelRepository, final ImageRepository imageRepository,
                          final BookmarkRepository bookmarkRepository, final RatingRepository ratingRepository,
                          final PurchasRepository purchasRepository) {
        this.chapterRepository = chapterRepository;
        this.novelRepository = novelRepository;
        this.imageRepository = imageRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.ratingRepository = ratingRepository;
        this.purchasRepository = purchasRepository;
    }

    public List<ChapterDTO> findAll() {
        final List<Chapter> chapters = chapterRepository.findAll(Sort.by("id"));
        return chapters.stream()
                .map(chapter -> mapToDTO(chapter, new ChapterDTO()))
                .toList();
    }

    public ChapterDTO get(final Integer id) {
        return chapterRepository.findById(id)
                .map(chapter -> mapToDTO(chapter, new ChapterDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ChapterDTO chapterDTO) {
        final Chapter chapter = new Chapter();
        mapToEntity(chapterDTO, chapter);
        return chapterRepository.save(chapter).getId();
    }

    public void update(final Integer id, final ChapterDTO chapterDTO) {
        final Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(chapterDTO, chapter);
        chapterRepository.save(chapter);
    }

    public void delete(final Integer id) {
        chapterRepository.deleteById(id);
    }

    private ChapterDTO mapToDTO(final Chapter chapter, final ChapterDTO chapterDTO) {
        chapterDTO.setId(chapter.getId());
        chapterDTO.setTitle(chapter.getTitle());
        chapterDTO.setCreateDate(chapter.getCreateDate());
        chapterDTO.setViewCount(chapter.getViewCount());
        chapterDTO.setDiscount(chapter.getDiscount());
        chapterDTO.setPrice(chapter.getPrice());
        chapterDTO.setNovel(chapter.getNovel() == null ? null : chapter.getNovel().getId());
        return chapterDTO;
    }

    private Chapter mapToEntity(final ChapterDTO chapterDTO, final Chapter chapter) {
        chapter.setTitle(chapterDTO.getTitle());
        chapter.setCreateDate(chapterDTO.getCreateDate());
        chapter.setViewCount(chapterDTO.getViewCount());
        chapter.setDiscount(chapterDTO.getDiscount());
        chapter.setPrice(chapterDTO.getPrice());
        final Novel novel = chapterDTO.getNovel() == null ? null : novelRepository.findById(chapterDTO.getNovel())
                .orElseThrow(() -> new NotFoundException("novel not found"));
        chapter.setNovel(novel);
        return chapter;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Image chapterImage = imageRepository.findFirstByChapter(chapter);
        if (chapterImage != null) {
            referencedWarning.setKey("chapter.image.chapter.referenced");
            referencedWarning.addParam(chapterImage.getId());
            return referencedWarning;
        }
        final Bookmark chapterBookmark = bookmarkRepository.findFirstByChapter(chapter);
        if (chapterBookmark != null) {
            referencedWarning.setKey("chapter.bookmark.chapter.referenced");
            referencedWarning.addParam(chapterBookmark.getId());
            return referencedWarning;
        }
        final Rating chapterRating = ratingRepository.findFirstByChapter(chapter);
        if (chapterRating != null) {
            referencedWarning.setKey("chapter.rating.chapter.referenced");
            referencedWarning.addParam(chapterRating.getId());
            return referencedWarning;
        }
        final Purchase chapterPurchase = purchasRepository.findFirstByChapter(chapter);
        if (chapterPurchase != null) {
            referencedWarning.setKey("chapter.purchas.chapter.referenced");
            referencedWarning.addParam(chapterPurchase.getId());
            return referencedWarning;
        }
        return null;
    }

}
