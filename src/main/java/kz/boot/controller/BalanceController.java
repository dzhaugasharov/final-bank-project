package kz.boot.controller;

import kz.boot.repository.BalanceRepository;
import kz.boot.model.Balance;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class BalanceController {

    private final BalanceRepository balanceJpa;

    public BalanceController(BalanceRepository balanceJpa) {
        this.balanceJpa = balanceJpa;
    }

    @RequestMapping("/getMoney/{userId}")
    Balance getMoney(@PathVariable Long userId) {
        Optional<Balance> balance = balanceJpa.findById(userId);
        if (!balance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "{\"error\": \"User Not Found\"}");
        return balance.get();
    }

    @Data
    public static class Sum {
        private BigDecimal sum;
    }

    @PutMapping(value = "/putMoney/{userId}")
    BalanceResult putMoney(@PathVariable Long userId, @RequestBody Sum sum) {
        Optional<Balance> optionalBalance = balanceJpa.findById(userId);
        if (!optionalBalance.isPresent())
            return new BalanceResult(0, "User not found");
        Balance balance = optionalBalance.get();
        BigDecimal result = balance.getBalance().add(sum.sum);
        balance.setBalance(result);
        balanceJpa.save(balance);

        return new BalanceResult(1, "Success");
    }

    @PutMapping("/takeMoney/{userId}")
    BalanceResult takeMoney(@PathVariable Long userId, @RequestBody Sum sum) {
        Optional<Balance> optionalBalance = balanceJpa.findById(userId);
        if (!optionalBalance.isPresent())
            return new BalanceResult(0, "User not found");
        Balance balance = optionalBalance.get();
        if (balance.getBalance().compareTo(sum.sum) < 0) {
            return new BalanceResult(0, "Not enough money");
        }

        BigDecimal result = balance.getBalance().subtract(sum.sum);
        balance.setBalance(result);
        balanceJpa.save(balance);

        return new BalanceResult(1, "Success");
    }

    @ExceptionHandler({NullPointerException.class, HttpMessageNotReadableException.class})
    public BalanceResult badRequest() {
        return new BalanceResult(0, "Bad Request");
    }
}
