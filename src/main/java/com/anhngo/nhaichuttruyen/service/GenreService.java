package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.GenreDTO;
import com.anhngo.nhaichuttruyen.entity.Genre;
import com.anhngo.nhaichuttruyen.entity.GenreNovel;
import com.anhngo.nhaichuttruyen.repos.GenreNovelRepository;
import com.anhngo.nhaichuttruyen.repos.GenreRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreNovelRepository genreNovelRepository;

    public GenreService(final GenreRepository genreRepository,
                        final GenreNovelRepository genreNovelRepository) {
        this.genreRepository = genreRepository;
        this.genreNovelRepository = genreNovelRepository;
    }

    public List<GenreDTO> findAll() {
        final List<Genre> genres = genreRepository.findAll(Sort.by("id"));
        return genres.stream()
                .map(genre -> mapToDTO(genre, new GenreDTO()))
                .toList();
    }

    public GenreDTO get(final Integer id) {
        return genreRepository.findById(id)
                .map(genre -> mapToDTO(genre, new GenreDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final GenreDTO genreDTO) {
        final Genre genre = new Genre();
        mapToEntity(genreDTO, genre);
        return genreRepository.save(genre).getId();
    }

    public void update(final Integer id, final GenreDTO genreDTO) {
        final Genre genre = genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(genreDTO, genre);
        genreRepository.save(genre);
    }

    public void delete(final Integer id) {
        genreRepository.deleteById(id);
    }

    private GenreDTO mapToDTO(final Genre genre, final GenreDTO genreDTO) {
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    private Genre mapToEntity(final GenreDTO genreDTO, final Genre genre) {
        genre.setName(genreDTO.getName());
        return genre;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Genre genre = genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final GenreNovel genreGenreNovel = genreNovelRepository.findFirstByGenre(genre);
        if (genreGenreNovel != null) {
            referencedWarning.setKey("genre.genreNovel.genre.referenced");
            referencedWarning.addParam(genreGenreNovel.getId());
            return referencedWarning;
        }
        return null;
    }

}
