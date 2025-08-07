import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest {
    Map<String, Object> parsedFile1 = new LinkedHashMap<>();
    Map<String, Object> parsedFile2 = new LinkedHashMap<>();
    String simpleFixture;


    @BeforeEach
    public void preparationOfFixtures() throws Exception {
        parsedFile1.put("host", "hexlet.io");
        parsedFile1.put("timeout", 50);
        parsedFile1.put("proxy", "123.234.53.22");
        parsedFile1.put("follow", false);

        parsedFile2.put("timeout", 20);
        parsedFile2.put("verbose", true);
        parsedFile2.put("host", "hexlet.io");

        simpleFixture = Files.readString(Paths.get("src/test/resources/fixtures/simpleResult.txt"));
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
        var expected = simpleFixture;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferRecursive() {

    }
}
