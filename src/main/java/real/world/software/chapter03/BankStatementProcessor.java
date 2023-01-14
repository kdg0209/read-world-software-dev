package real.world.software.chapter03;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<BankTransaction> findTransactionsGreaterThanEqual(int amount) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactionsInMonth(LocalDate month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().isEqual(month))
                .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactionsInMonthAndGreater(LocalDate month, int amount) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().isEqual(month))
                .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> predicate) {
        return bankTransactions.stream()
                .filter(bankTransaction -> predicate.test(bankTransaction))
                .collect(Collectors.toList());
    }
}
