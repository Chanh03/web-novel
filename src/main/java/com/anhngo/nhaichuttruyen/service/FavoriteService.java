package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.FavoriteDTO;
import com.anhngo.nhaichuttruyen.entity.Favorite;
import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.repos.FavoriteRepository;
import com.anhngo.nhaichuttruyen.repos.NovelRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final NovelRepository novelRepository;

    public FavoriteService(final FavoriteRepository favoriteRepository,
                           final UserRepository userRepository, final NovelRepository novelRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.novelRepository = novelRepository;
    }

    public List<FavoriteDTO> findAll() {
        final List<Favorite> favorites = favoriteRepository.findAll(Sort.by("id"));
        return favorites.stream()
                .map(favorite -> mapToDTO(favorite, new FavoriteDTO()))
                .toList();
    }

    public FavoriteDTO get(final Integer id) {
        return favoriteRepository.findById(id)
                .map(favorite -> mapToDTO(favorite, new FavoriteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final FavoriteDTO favoriteDTO) {
        final Favorite favorite = new Favorite();
        mapToEntity(favoriteDTO, favorite);
        return favoriteRepository.save(favorite).getId();
    }

    public void update(final Integer id, final FavoriteDTO favoriteDTO) {
        final Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(favoriteDTO, favorite);
        favoriteRepository.save(favorite);
    }

    public void delete(final Integer id) {
        favoriteRepository.deleteById(id);
    }

    private FavoriteDTO mapToDTO(final Favorite favorite, final FavoriteDTO favoriteDTO) {
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setCreateDate(favorite.getCreateDate());
        favoriteDTO.setUser(favorite.getUser() == null ? null : favorite.getUser().getId());
        favoriteDTO.setNovel(favorite.getNovel() == null ? null : favorite.getNovel().getId());
        return favoriteDTO;
    }

    private Favorite mapToEntity(final FavoriteDTO favoriteDTO, final Favorite favorite) {
        favorite.setCreateDate(favoriteDTO.getCreateDate());
        final User user = favoriteDTO.getUser() == null ? null : userRepository.findById(favoriteDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        favorite.setUser(user);
        final Novel novel = favoriteDTO.getNovel() == null ? null : novelRepository.findById(favoriteDTO.getNovel())
                .orElseThrow(() -> new NotFoundException("novel not found"));
        favorite.setNovel(novel);
        return favorite;
    }

}
