package kz.boot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceResult {
    private int code;
    private String message;
}
