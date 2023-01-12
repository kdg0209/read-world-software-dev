package real.world.software.chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<BankTransaction> parseLines(String resources) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(resources));
        return lines.stream()
                .map(this::parse)
                .collect(Collectors.toList());
    }

    @Override
    public BankTransaction parse(String line) {
        String[] split = line.split(",");
        LocalDate date = LocalDate.parse(split[0], FORMATTER);
        double amount = Double.parseDouble(split[1]);
        return new BankTransaction(date, amount);
    }
}
