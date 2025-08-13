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

    String filePath1simple = "src/test/resources/fixtures/file1.json";
    String filePath2simple = "src/test/resources/fixtures/file2.json";
    String filePath3recursiveJson = "src/test/resources/fixtures/file3recursive.json";
    String filePath4recursiveJson = "src/test/resources/fixtures/file4recursive.json";
    String filePath3recursiveYaml = "src/test/resources/fixtures/file3recursive.yaml";
    String filePath4recursiveYaml = "src/test/resources/fixtures/file4recursive.yaml";
    String simpleResult;
    String recursiveResult;
    String plainResult;

    @BeforeEach
    public void preparationOfFixtures() throws Exception {
        parsedFile1.put("host", "hexlet.io");
        parsedFile1.put("timeout", 50);
        parsedFile1.put("proxy", "123.234.53.22");
        parsedFile1.put("follow", false);

        parsedFile2.put("timeout", 20);
        parsedFile2.put("verbose", true);
        parsedFile2.put("host", "hexlet.io");

        //Ожидающие результаты
        simpleResult = Files.readString(Paths.get("src/test/resources/fixtures/simpleResult.txt"));
        recursiveResult = Files.readString(Paths.get("src/test/resources/fixtures/recursiveResult.txt"));
        plainResult = Files.readString(Paths.get("src/test/resources/fixtures/plainResult.txt"));
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
        var actual = Differ.generate(filePath1simple, filePath2simple, "stylish");
        var expected = simpleResult;
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferRecursiveJson() {
        var actual = Differ.generate(filePath3recursiveJson, filePath4recursiveJson, "stylish");
        var expected  = recursiveResult;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferRecursiveYaml() {
        var actual = Differ.generate(filePath3recursiveYaml, filePath4recursiveYaml, "stylish");
        var expected  = recursiveResult;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferPlain() {
        var actual = Differ.generate(filePath3recursiveJson, filePath4recursiveJson, "plain");
        var expected = plainResult;
        assertEquals(expected, actual);
    }
}
