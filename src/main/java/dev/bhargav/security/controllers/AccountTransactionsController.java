package dev.bhargav.security.controllers;

import dev.bhargav.security.exception.BadRequestException;
import dev.bhargav.security.exception.InsufficientAccountBalanceException;
import dev.bhargav.security.exception.ResourceNotFoundException;
import dev.bhargav.security.model.AccountDto;
import dev.bhargav.security.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Account transactions controller.
 */
@Tag(name = "Account Transactions", description = "Account Transactions APIs")
@RestController
@RequestMapping("/api/transaction")
public class AccountTransactionsController {

    @Autowired
    AccountService accountService;

    @Operation(summary = "Deposit to account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account credited",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid account number and/or amount supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content) })
    @PutMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public AccountDto deposit(
            @Parameter(description = "Account number to be credited")
            @RequestParam("accountNumber") String accountNumber,
            @Parameter(description = "Amount to be deposited")
            @RequestParam("amount") String depositAmount) throws ResourceNotFoundException, BadRequestException {
        return this.accountService.deposit(accountNumber, depositAmount);
    }

    @Operation(summary = "Withdraw from account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account debited",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid account number and/or amount supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content) })
    @PutMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public AccountDto withdraw(
            @Parameter(description = "Account number to be debited")
            @RequestParam("accountNumber") String accountNumber,
            @Parameter(description = "Account to be updated")
            @RequestParam("amount") String withdrawalAmount) throws ResourceNotFoundException, BadRequestException, InsufficientAccountBalanceException {
        return this.accountService.withdraw(accountNumber, withdrawalAmount);
    }
}
