package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.BookmarkDTO;
import com.anhngo.nhaichuttruyen.entity.Bookmark;
import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.repos.BookmarkRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<BookmarkDTO> findAll() {
        return bookmarkRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BookmarkDTO get(Integer id) {
        return bookmarkRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Bookmark not found"));
    }


    public void update(Integer id, BookmarkDTO bookmarkDTO) {
        final Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookmark not found"));
        mapToEntity(bookmarkDTO, bookmark);
        bookmarkRepository.save(bookmark);
    }

    public void delete(Integer id) {
        bookmarkRepository.deleteById(id);
    }

    private BookmarkDTO mapToDTO(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setUserId(bookmark.getUser().getId());
        dto.setChapterId(bookmark.getChapter().getId());
        return dto;
    }

    private void mapToEntity(BookmarkDTO dto, Bookmark bookmark) {
        User user = new User();
        user.setId(dto.getUserId());
        bookmark.setUser(user);
        Chapter chapter = new Chapter();
        chapter.setId(dto.getChapterId());
        bookmark.setChapter(chapter);
    }

    public Integer create(@Valid BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = new Bookmark();
        mapToEntity(bookmarkDTO, bookmark);
        return bookmarkRepository.save(bookmark).getId();
    }
}