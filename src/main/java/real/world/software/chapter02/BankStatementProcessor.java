package real.world.software.chapter02;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = Collections.unmodifiableList(bankTransactions);
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream()
                .map(BankTransaction::getAmount)
                .mapToDouble(d -> d)
                .sum();
    }

    public double calculateTotalAmountInMonth(LocalDate month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().isEqual(month))
                .map(BankTransaction::getAmount)
                .mapToDouble(d -> d)
                .sum();
    }
}
