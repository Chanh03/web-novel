package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.RankDTO;
import com.anhngo.nhaichuttruyen.service.RankService;
import com.anhngo.nhaichuttruyen.util.ReferencedException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/ranks", produces = MediaType.APPLICATION_JSON_VALUE)
public class RankRest {

    private final RankService rankService;

    public RankRest(final RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping
    public ResponseEntity<List<RankDTO>> getAllRanks() {
        return ResponseEntity.ok(rankService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankDTO> getRank(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(rankService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createRank(@RequestBody @Valid final RankDTO rankDTO) {
        final Integer createdId = rankService.create(rankDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateRank(@PathVariable(name = "id") final Integer id,
                                              @RequestBody @Valid final RankDTO rankDTO) {
        rankService.update(id, rankDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRank(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = rankService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        rankService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
