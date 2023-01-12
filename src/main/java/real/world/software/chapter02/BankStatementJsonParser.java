package real.world.software.chapter02;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BankStatementJsonParser implements BankStatementParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<BankTransaction> parseLines(String resources) throws IOException  {
        JsonNode jsonNode = objectMapper.readTree(new File(resources));
        return StreamSupport.stream(jsonNode.spliterator(), false)
                .map(String::valueOf)
                .map(this::parse)
                .collect(Collectors.toList());
    }

    @Override
    public BankTransaction parse(String line)  {
        try {
            JsonNode jsonNode = objectMapper.readTree(line);
            return new BankTransaction(LocalDate.parse(jsonNode.get("date").asText(), FORMATTER), jsonNode.get("amount").asDouble());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
