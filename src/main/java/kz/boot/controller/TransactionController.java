package kz.boot.controller;

import kz.boot.model.Transaction;
import kz.boot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping("/getOperationList/{userId}")
    public Iterable<Transaction> getOperationList(@PathVariable Long userId,
                                                  @RequestParam(name = "startDate") Optional<String> startDate,
                                                  @RequestParam(name = "endDate") Optional<String> endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Optional<LocalDateTime> start = Optional.empty();
        Optional<LocalDateTime> end = Optional.empty();

        if (startDate.isPresent()) {
            Date sd = simpleDateFormat.parse(startDate.get());
            start = Optional.of(sd.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        if (endDate.isPresent()) {
            Date ed = simpleDateFormat.parse(endDate.get());
            end = Optional.of(ed.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return transactionService.getOperationList(userId, start, end);
    }

    @ExceptionHandler({ParseException.class})
    public BalanceResult badRequest() {
        return new BalanceResult(0, "Bad Request");
    }
}
