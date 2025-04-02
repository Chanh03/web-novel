package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.PurchasDTO;
import com.anhngo.nhaichuttruyen.service.PurchasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/purchass", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseRest {

    private final PurchasService purchasService;

    public PurchaseRest(final PurchasService purchasService) {
        this.purchasService = purchasService;
    }

    @GetMapping
    public ResponseEntity<List<PurchasDTO>> getAllPurchass() {
        return ResponseEntity.ok(purchasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasDTO> getPurchas(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(purchasService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createPurchas(@RequestBody @Valid final PurchasDTO purchasDTO) {
        final Integer createdId = purchasService.create(purchasDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePurchas(@PathVariable(name = "id") final Integer id,
                                                 @RequestBody @Valid final PurchasDTO purchasDTO) {
        purchasService.update(id, purchasDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchas(@PathVariable(name = "id") final Integer id) {
        purchasService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
