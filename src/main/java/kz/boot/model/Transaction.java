package kz.boot.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @JsonProperty("userId")
    @JsonAlias("user_id")
    private Long userId;
    private Types type;
    private BigDecimal sum;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Transaction() {

    }

    public Transaction(Long userId, Types type, BigDecimal sum) {
        this.userId = userId;
        this.type = type;
        this.sum = sum;
        this.createdAt = LocalDateTime.now();
    }

    public enum Types {
        PUT,
        GET
    }
}
