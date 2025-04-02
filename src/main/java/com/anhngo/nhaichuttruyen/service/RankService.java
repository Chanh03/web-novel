package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.RankDTO;
import com.anhngo.nhaichuttruyen.entity.Rank;
import com.anhngo.nhaichuttruyen.entity.UserRank;
import com.anhngo.nhaichuttruyen.repos.RankRepository;
import com.anhngo.nhaichuttruyen.repos.UserRankRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RankService {

    private final RankRepository rankRepository;
    private final UserRankRepository userRankRepository;

    public RankService(final RankRepository rankRepository,
                       final UserRankRepository userRankRepository) {
        this.rankRepository = rankRepository;
        this.userRankRepository = userRankRepository;
    }

    public List<RankDTO> findAll() {
        final List<Rank> ranks = rankRepository.findAll(Sort.by("id"));
        return ranks.stream()
                .map(rank -> mapToDTO(rank, new RankDTO()))
                .toList();
    }

    public RankDTO get(final Integer id) {
        return rankRepository.findById(id)
                .map(rank -> mapToDTO(rank, new RankDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RankDTO rankDTO) {
        final Rank rank = new Rank();
        mapToEntity(rankDTO, rank);
        return rankRepository.save(rank).getId();
    }

    public void update(final Integer id, final RankDTO rankDTO) {
        final Rank rank = rankRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(rankDTO, rank);
        rankRepository.save(rank);
    }

    public void delete(final Integer id) {
        rankRepository.deleteById(id);
    }

    private RankDTO mapToDTO(final Rank rank, final RankDTO rankDTO) {
        rankDTO.setId(rank.getId());
        rankDTO.setName(rank.getName());
        rankDTO.setIsAvailable(rank.getIsAvailable());
        rankDTO.setCreateDate(rank.getCreateDate());
        return rankDTO;
    }

    private Rank mapToEntity(final RankDTO rankDTO, final Rank rank) {
        rank.setName(rankDTO.getName());
        rank.setIsAvailable(rankDTO.getIsAvailable());
        rank.setCreateDate(rankDTO.getCreateDate());
        return rank;
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Rank rank = rankRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserRank rankUserRank = userRankRepository.findFirstByRank(rank);
        if (rankUserRank != null) {
            referencedWarning.setKey("rank.userRank.rank.referenced");
            referencedWarning.addParam(rankUserRank.getId());
            return referencedWarning;
        }
        return null;
    }

}
