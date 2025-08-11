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
    Map<String, Object> parsedFile3recursiveJson = new LinkedHashMap<>();
    Map<String, Object> parsedFile4recursiveJson = new LinkedHashMap<>();
    Map<String, Object> parsedFile3recursiveYaml = new LinkedHashMap<>();
    Map<String, Object> parsedFile4recursiveYaml = new LinkedHashMap<>();

    String simpleFixture;
    String recursiveFixture;

    @BeforeEach
    public void preparationOfFixtures() throws Exception {
        parsedFile1.put("host", "hexlet.io");
        parsedFile1.put("timeout", 50);
        parsedFile1.put("proxy", "123.234.53.22");
        parsedFile1.put("follow", false);

        parsedFile2.put("timeout", 20);
        parsedFile2.put("verbose", true);
        parsedFile2.put("host", "hexlet.io");

        parsedFile3recursiveJson = Parser.formJsonYaml("./src/test/resources/fixtures/file3recursive.json");
        parsedFile4recursiveJson = Parser.formJsonYaml("./src/test/resources/fixtures/file4recursive.json");
        parsedFile3recursiveYaml = Parser.formJsonYaml("./src/test/resources/fixtures/file3recursive.yaml");
        parsedFile4recursiveYaml = Parser.formJsonYaml("./src/test/resources/fixtures/file4recursive.yaml");
        //Ожидающие результаты
        simpleFixture = Files.readString(Paths.get("src/test/resources/fixtures/simpleResult.txt"));
        recursiveFixture = Files.readString(Paths.get("src/test/resources/fixtures/recursiveResult.txt"));
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
    public void testDiffer() {
        var actual = Differ.generate(parsedFile1, parsedFile2);
        var expected = simpleFixture;
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferRecursiveJson() {
        var actual = Differ.generate(parsedFile3recursiveJson, parsedFile4recursiveJson);
        var expected  = recursiveFixture;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferRecursiveYaml() {
        var actual = Differ.generate(parsedFile3recursiveYaml, parsedFile4recursiveYaml);
        var expected  = recursiveFixture;
        assertEquals(expected, actual);
    }
}
