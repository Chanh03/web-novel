package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.UserRankDTO;
import com.anhngo.nhaichuttruyen.entity.Rank;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRank;
import com.anhngo.nhaichuttruyen.repos.RankRepository;
import com.anhngo.nhaichuttruyen.repos.UserRankRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRankService {

    private final UserRankRepository userRankRepository;
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public UserRankService(final UserRankRepository userRankRepository,
                           final RankRepository rankRepository, final UserRepository userRepository) {
        this.userRankRepository = userRankRepository;
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }

    public List<UserRankDTO> findAll() {
        final List<UserRank> userRanks = userRankRepository.findAll(Sort.by("id"));
        return userRanks.stream()
                .map(userRank -> mapToDTO(userRank, new UserRankDTO()))
                .toList();
    }

    public UserRankDTO get(final Integer id) {
        return userRankRepository.findById(id)
                .map(userRank -> mapToDTO(userRank, new UserRankDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UserRankDTO userRankDTO) {
        final UserRank userRank = new UserRank();
        mapToEntity(userRankDTO, userRank);
        return userRankRepository.save(userRank).getId();
    }

    public void update(final Integer id, final UserRankDTO userRankDTO) {
        final UserRank userRank = userRankRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userRankDTO, userRank);
        userRankRepository.save(userRank);
    }

    public void delete(final Integer id) {
        userRankRepository.deleteById(id);
    }

    private UserRankDTO mapToDTO(final UserRank userRank, final UserRankDTO userRankDTO) {
        userRankDTO.setId(userRank.getId());
        userRankDTO.setStartDate(userRank.getStartDate());
        userRankDTO.setEndDate(userRank.getEndDate());
        userRankDTO.setRank(userRank.getRank() == null ? null : userRank.getRank().getId());
        userRankDTO.setUser(userRank.getUser() == null ? null : userRank.getUser().getId());
        return userRankDTO;
    }

    private UserRank mapToEntity(final UserRankDTO userRankDTO, final UserRank userRank) {
        userRank.setStartDate(userRankDTO.getStartDate());
        userRank.setEndDate(userRankDTO.getEndDate());
        final Rank rank = userRankDTO.getRank() == null ? null : rankRepository.findById(userRankDTO.getRank())
                .orElseThrow(() -> new NotFoundException("rank not found"));
        userRank.setRank(rank);
        final User user = userRankDTO.getUser() == null ? null : userRepository.findById(userRankDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        userRank.setUser(user);
        return userRank;
    }

}
