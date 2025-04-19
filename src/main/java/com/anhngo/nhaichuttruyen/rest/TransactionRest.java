package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.TransactionDTO;
import com.anhngo.nhaichuttruyen.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRest {

    private final TransactionService transactionService;

    public TransactionRest(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(transactionService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createTransaction(
            @RequestBody @Valid final TransactionDTO transactionDTO) {
        final Integer createdId = transactionService.create(transactionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateTransaction(@PathVariable(name = "id") final Integer id,
                                                     @RequestBody @Valid final TransactionDTO transactionDTO) {
        transactionService.update(id, transactionDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(name = "id") final Integer id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
