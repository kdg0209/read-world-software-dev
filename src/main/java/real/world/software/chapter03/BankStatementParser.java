package real.world.software.chapter03;

import java.io.IOException;
import java.util.List;

public interface BankStatementParser {

    List<BankTransaction> parseLines(String resources) throws IOException;
    BankTransaction parse(String line);
}
