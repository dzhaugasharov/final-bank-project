package kz.boot.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user_balances")
@AllArgsConstructor
public class Balance {
    @Id
    @Column(name = "user_id")
    @JsonProperty("userId")
    @JsonAlias("user_id")
    private Long userId;
    private BigDecimal balance;

    public Balance() {

    }
}
