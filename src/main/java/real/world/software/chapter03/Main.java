package real.world.software.chapter03;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String JSON_RESOURCES = "src/main/resources/jsonExample.json";
    private static final String CSV_RESOURCES = "src/main/resources/example.csv";

    public static void main(String[] args) throws IOException {

        BankStatementCSVParser csvParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactionsB = csvParser.parseLines(CSV_RESOURCES);
        BankStatementProcessor csvProcessor = new BankStatementProcessor(bankTransactionsB);

        csvProcessor.findTransactions(bankTransaction -> bankTransaction.getAmount() >= 3000);
        csvProcessor.findTransactions(bankTransaction -> bankTransaction.getAmount() >= 300 && bankTransaction.getDate().isBefore(LocalDate.now()));
    }
}
