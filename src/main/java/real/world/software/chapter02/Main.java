package real.world.software.chapter02;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String JSON_RESOURCES = "src/main/resources/jsonExample.json";
    private static final String CSV_RESOURCES = "src/main/resources/example.csv";

    public static void main(String[] args) throws IOException {

        BankStatementJsonParser jsonParser = new BankStatementJsonParser();
        List<BankTransaction> bankTransactionsA = jsonParser.parseLines(JSON_RESOURCES);
        BankStatementProcessor jsonProcessor = new BankStatementProcessor(bankTransactionsA);
        System.out.println(jsonProcessor.calculateTotalAmount());
        System.out.println(jsonProcessor.calculateTotalAmountInMonth(LocalDate.of(2017, 2, 15)));

        BankStatementCSVParser csvParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactionsB = csvParser.parseLines(CSV_RESOURCES);
        BankStatementProcessor csvProcessor = new BankStatementProcessor(bankTransactionsB);
        System.out.println(csvProcessor.calculateTotalAmount());
        System.out.println(csvProcessor.calculateTotalAmountInMonth(LocalDate.of(2017, 2, 15)));
    }
}
