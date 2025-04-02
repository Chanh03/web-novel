package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.GenreNovelDTO;
import com.anhngo.nhaichuttruyen.entity.Genre;
import com.anhngo.nhaichuttruyen.entity.GenreNovel;
import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.repos.GenreNovelRepository;
import com.anhngo.nhaichuttruyen.repos.GenreRepository;
import com.anhngo.nhaichuttruyen.repos.NovelRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreNovelService {

    private final GenreNovelRepository genreNovelRepository;
    private final GenreRepository genreRepository;
    private final NovelRepository novelRepository;

    public GenreNovelService(final GenreNovelRepository genreNovelRepository,
                             final GenreRepository genreRepository, final NovelRepository novelRepository) {
        this.genreNovelRepository = genreNovelRepository;
        this.genreRepository = genreRepository;
        this.novelRepository = novelRepository;
    }

    public List<GenreNovelDTO> findAll() {
        final List<GenreNovel> genreNovels = genreNovelRepository.findAll(Sort.by("id"));
        return genreNovels.stream()
                .map(genreNovel -> mapToDTO(genreNovel, new GenreNovelDTO()))
                .toList();
    }

    public GenreNovelDTO get(final Integer id) {
        return genreNovelRepository.findById(id)
                .map(genreNovel -> mapToDTO(genreNovel, new GenreNovelDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final GenreNovelDTO genreNovelDTO) {
        final GenreNovel genreNovel = new GenreNovel();
        mapToEntity(genreNovelDTO, genreNovel);
        return genreNovelRepository.save(genreNovel).getId();
    }

    public void update(final Integer id, final GenreNovelDTO genreNovelDTO) {
        final GenreNovel genreNovel = genreNovelRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(genreNovelDTO, genreNovel);
        genreNovelRepository.save(genreNovel);
    }

    public void delete(final Integer id) {
        genreNovelRepository.deleteById(id);
    }

    private GenreNovelDTO mapToDTO(final GenreNovel genreNovel, final GenreNovelDTO genreNovelDTO) {
        genreNovelDTO.setId(genreNovel.getId());
        genreNovelDTO.setGenre(genreNovel.getGenre() == null ? null : genreNovel.getGenre().getId());
        genreNovelDTO.setNovel(genreNovel.getNovel() == null ? null : genreNovel.getNovel().getId());
        return genreNovelDTO;
    }

    private GenreNovel mapToEntity(final GenreNovelDTO genreNovelDTO, final GenreNovel genreNovel) {
        final Genre genre = genreNovelDTO.getGenre() == null ? null : genreRepository.findById(genreNovelDTO.getGenre())
                .orElseThrow(() -> new NotFoundException("genre not found"));
        genreNovel.setGenre(genre);
        final Novel novel = genreNovelDTO.getNovel() == null ? null : novelRepository.findById(genreNovelDTO.getNovel())
                .orElseThrow(() -> new NotFoundException("novel not found"));
        genreNovel.setNovel(novel);
        return genreNovel;
    }

}
