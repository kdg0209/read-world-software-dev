package real.world.software.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class BankTransaction {

    private LocalDate date;
    private double amount;
}
