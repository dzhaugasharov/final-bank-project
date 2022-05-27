package kz.boot.service;

import kz.boot.controller.BalanceResult;
import kz.boot.model.Balance;
import kz.boot.model.Transaction;
import kz.boot.repository.BalanceRepository;
import kz.boot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceJpa;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public BalanceResult putMoney(Long userId, BigDecimal sum) {
        Optional<Balance> optionalBalance = balanceJpa.findById(userId);
        if (!optionalBalance.isPresent())
            return new BalanceResult(0, "User not found");
        Balance balance = optionalBalance.get();
        BigDecimal result = balance.getBalance().add(sum);
        balance.setBalance(result);
        balanceJpa.save(balance);

        Transaction transaction = new Transaction(userId, Transaction.Types.PUT, sum);
        transactionRepository.save(transaction);
        System.out.println(transaction);
        return new BalanceResult(1, "Success");
    }

    @Transactional
    public BalanceResult takeMoney(Long userId, BigDecimal sum) {
        Optional<Balance> optionalBalance = balanceJpa.findById(userId);
        if (!optionalBalance.isPresent())
            return new BalanceResult(0, "User not found");
        Balance balance = optionalBalance.get();
        if (balance.getBalance().compareTo(sum) < 0) {
            return new BalanceResult(0, "Not enough money");
        }

        BigDecimal result = balance.getBalance().subtract(sum);
        balance.setBalance(result);
        balanceJpa.save(balance);

        Transaction transaction = new Transaction(userId, Transaction.Types.GET, sum);
        transactionRepository.save(transaction);
        System.out.println(transaction);

        return new BalanceResult(1, "Success");
    }
}
