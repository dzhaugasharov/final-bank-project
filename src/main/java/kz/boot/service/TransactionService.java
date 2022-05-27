package kz.boot.service;

import kz.boot.model.Transaction;
import kz.boot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Iterable<Transaction> getOperationList(Long userId, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
        if (startDate.isPresent() && endDate.isPresent())
            return transactionRepository.findAllByUserIdEqualsAndCreatedAtIsBetween(userId, startDate.get(), endDate.get());
        if (startDate.isPresent())
            return transactionRepository.findAllByUserIdEqualsAndCreatedAtAfter(userId, startDate.get());
        if (endDate.isPresent())
            return transactionRepository.findAllByUserIdEqualsAndCreatedAtBefore(userId, endDate.get());
        return transactionRepository.findAllByUserIdEquals(userId);
    }

}
