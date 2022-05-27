package kz.boot.repository;

import kz.boot.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Iterable<Transaction> findAllByUserIdEquals(Long userId);

    Iterable<Transaction> findAllByUserIdEqualsAndCreatedAtAfter(Long userId, LocalDateTime startDate);

    Iterable<Transaction> findAllByUserIdEqualsAndCreatedAtBefore(Long userId, LocalDateTime endDate);

    Iterable<Transaction> findAllByUserIdEqualsAndCreatedAtIsBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
