package com.aldairgc.budget_dev.controller;

import com.aldairgc.budget_dev.controller.dto.in.TransactionDtoIn;
import com.aldairgc.budget_dev.controller.dto.out.TransactionDtoOut;
import com.aldairgc.budget_dev.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
@Tag(name = "Transaction", description = "Transaction RESTFUL API")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get all user transactions", description = "Retrieves a list of transactions by user id")
    @ApiResponse(responseCode = "200", description = "List of transactions")
    public ResponseEntity<List<TransactionDtoOut>> findAllByUserId(@PathVariable Long userId) {
        var transactions = transactionService.findAllByUserId(userId);
        var transactionsDto = transactions.stream().map(TransactionDtoOut::fromDomain).toList();
        return ResponseEntity.ok(transactionsDto);
    }

    @GetMapping("/{userId}/{id}")
    @Operation(summary = "Get transaction by id", description = "Retrieves a transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access")
    })
    public ResponseEntity<TransactionDtoOut> findById(@PathVariable Long userId, @PathVariable Long id) {
        var transaction = transactionService.findById(id);
        if (transaction.getUser().getId().equals(userId)) {
            return ResponseEntity.ok(TransactionDtoOut.fromDomain(transaction));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping
    @Operation(summary = "Create transaction", description = "Creates a new transaction and returns its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created"),
            @ApiResponse(responseCode = "422", description = "Invalid data")
    })
    public ResponseEntity<TransactionDtoOut> create(@RequestBody TransactionDtoIn transactionDtoIn) {
        var transaction = transactionService.create(TransactionDtoIn.toDomain(transactionDtoIn));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(TransactionDtoOut.fromDomain(transaction));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transaction", description = "Updates a transaction by id and returns its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction updated"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data")
    })
    public ResponseEntity<TransactionDtoOut> update(@PathVariable Long id, @RequestBody TransactionDtoIn transactionDtoIn) {
        var transaction = transactionService.update(id, TransactionDtoIn.toDomain(transactionDtoIn));
        return ResponseEntity.ok(TransactionDtoOut.fromDomain(transaction));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction", description = "Deletes a transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction deleted"),
            @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
