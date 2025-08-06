import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest {
    Map<String, Object> parsedFile1 = null;
    Map<String, Object> parsedFile2 = null;
    ArrayList<String> expectedList = new ArrayList<>();

    @BeforeEach
    public void preparationOfFixtures() throws Exception {
        parsedFile1 = new LinkedHashMap<>();
        parsedFile2 = new LinkedHashMap<>();

        parsedFile1.put("host", "hexlet.io");
        parsedFile1.put("timeout", 50);
        parsedFile1.put("proxy", "123.234.53.22");
        parsedFile1.put("follow", false);

        parsedFile2.put("timeout", 20);
        parsedFile2.put("verbose", true);
        parsedFile2.put("host", "hexlet.io");

        expectedList.clear();
        expectedList.add("{");
        expectedList.add(" -follow: false");
        expectedList.add("  host: hexlet.io");
        expectedList.add(" -proxy: 123.234.53.22");
        expectedList.add(" -timeout: 50");
        expectedList.add(" +timeout: 20");
        expectedList.add(" +verbose: true");
        expectedList.add("}");
    }

    @Test
    public void testParserJson() {
        var actual = Parser.formJsonYaml("./src/test/resources/fixtures/file1.json");
        var expected = parsedFile1;
        assertEquals(expected, actual);
    }

    @Test
    public void testParsedYaml() {
        var actual = Parser.formJsonYaml("./src/test/resources/fixtures/file1.yaml");
        var expected = parsedFile1;
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffer() throws Exception {
        var actual = Differ.generate(parsedFile1, parsedFile2);
        var expected = expectedList;
        assertEquals(expected, actual, "Не совпадает с ожиданием");
    }
}
