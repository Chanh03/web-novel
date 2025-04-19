package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.GenreDTO;
import com.anhngo.nhaichuttruyen.DTO.NovelDTO;
import com.anhngo.nhaichuttruyen.entity.*;
import com.anhngo.nhaichuttruyen.repos.*;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class NovelService {

    private final NovelRepository novelRepository;
    private final UserRepository userRepository;
    private final GenreNovelRepository genreNovelRepository;
    private final ReceiveNotificationRepository receiveNotificationRepository;
    private final ChapterRepository chapterRepository;
    private final FavoriteRepository favoriteRepository;
    private final GenreRepository genreRepository;

    public NovelService(final NovelRepository novelRepository, final UserRepository userRepository,
                        final GenreNovelRepository genreNovelRepository,
                        final ReceiveNotificationRepository receiveNotificationRepository,
                        final ChapterRepository chapterRepository,
                        final FavoriteRepository favoriteRepository, GenreRepository genreRepository) {
        this.novelRepository = novelRepository;
        this.userRepository = userRepository;
        this.genreNovelRepository = genreNovelRepository;
        this.receiveNotificationRepository = receiveNotificationRepository;
        this.chapterRepository = chapterRepository;
        this.favoriteRepository = favoriteRepository;
        this.genreRepository = genreRepository;
    }

    public List<NovelDTO> findAll() {
        final List<Novel> novels = novelRepository.findAll(Sort.by("id"));
        return novels.stream()
                .map(novel -> mapToDTO(novel, new NovelDTO()))
                .toList();
    }

    public NovelDTO get(final Integer id) {
        return novelRepository.findById(id)
                .map(novel -> mapToDTO(novel, new NovelDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final NovelDTO novelDTO) {
        final Novel novel = new Novel();
        mapToEntity(novelDTO, novel);
        return novelRepository.save(novel).getId();
    }

    public void update(final Integer id, final NovelDTO novelDTO) {
        final Novel novel = novelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(novelDTO, novel);
        novelRepository.save(novel);
    }

    public void delete(final Integer id) {
        novelRepository.deleteById(id);
    }

    private NovelDTO mapToDTO(final Novel novel, final NovelDTO novelDTO) {
        novelDTO.setId(novel.getId());
        novelDTO.setTitle(novel.getTitle());
        novelDTO.setCreateDate(novel.getCreateDate());
        novelDTO.setIsDelete(novel.getIsDelete());
        novelDTO.setStatus(novel.getStatus());
        novelDTO.setUpdateDate(novel.getUpdateDate());
        novelDTO.setSummary(novel.getSummary());
        novelDTO.setViewCount(novel.getViewCount());
        novelDTO.setIsAvailable(novel.getIsAvailable());
        novelDTO.setThumbnail(novel.getThumbnail());
        novelDTO.setLikeCount(novel.getLikeCount());
        novelDTO.setSlug(novel.getSlug());
        List<GenreDTO> genreDTOs = novel.getNovelGenreNovels().stream().map(novelGenre -> {
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setId(novelGenre.getGenre().getId());
            genreDTO.setName(novelGenre.getGenre().getName());
            return genreDTO;
        }).collect(Collectors.toList());
        novelDTO.setGenres(genreDTOs);
        novelDTO.setAuthor(novel.getAuthor() == null ? null : novel.getAuthor().getId());
        return novelDTO;
    }

    private Novel mapToEntity(final NovelDTO novelDTO, final Novel novel) {
        novel.setTitle(novelDTO.getTitle());
        novel.setCreateDate(novelDTO.getCreateDate());
        novel.setIsDelete(novelDTO.getIsDelete());
        novel.setStatus(novelDTO.getStatus());
        novel.setUpdateDate(novelDTO.getUpdateDate());
        novel.setSummary(novelDTO.getSummary());
        novel.setViewCount(novelDTO.getViewCount());
        novel.setIsAvailable(novelDTO.getIsAvailable());
        novel.setThumbnail(novelDTO.getThumbnail());
        novel.setLikeCount(novelDTO.getLikeCount());
        novel.setSlug(novelDTO.getSlug());
        final User author = novelDTO.getAuthor() == null ? null : userRepository.findById(novelDTO.getAuthor())
                .orElseThrow(() -> new NotFoundException("author not found"));
        novel.setAuthor(author);
        return novel;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Novel novel = novelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final GenreNovel novelGenreNovel = genreNovelRepository.findFirstByNovel(novel);
        if (novelGenreNovel != null) {
            referencedWarning.setKey("novel.genreNovel.novel.referenced");
            referencedWarning.addParam(novelGenreNovel.getId());
            return referencedWarning;
        }
        final ReceiveNotification novelReceiveNotification = receiveNotificationRepository.findFirstByNovel(novel);
        if (novelReceiveNotification != null) {
            referencedWarning.setKey("novel.receiveNotification.novel.referenced");
            referencedWarning.addParam(novelReceiveNotification.getId());
            return referencedWarning;
        }
        final Chapter novelChapter = chapterRepository.findFirstByNovel(novel);
        if (novelChapter != null) {
            referencedWarning.setKey("novel.chapter.novel.referenced");
            referencedWarning.addParam(novelChapter.getId());
            return referencedWarning;
        }
        final Favorite novelFavorite = favoriteRepository.findFirstByNovel(novel);
        if (novelFavorite != null) {
            referencedWarning.setKey("novel.favorite.novel.referenced");
            referencedWarning.addParam(novelFavorite.getId());
            return referencedWarning;
        }
        return null;
    }

}
