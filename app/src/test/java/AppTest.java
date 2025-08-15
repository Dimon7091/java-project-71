import com.fasterxml.jackson.core.JsonProcessingException;
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
    private Map<String, Object> parsedFile1 = new LinkedHashMap<>();

    private String filePath1simple = "src/test/resources/fixtures/file1.json";
    private String filePath2simple = "src/test/resources/fixtures/file2.json";
    private String filePath3recursiveJson = "src/test/resources/fixtures/file3recursive.json";
    private String filePath4recursiveJson = "src/test/resources/fixtures/file4recursive.json";
    private String filePath3recursiveYaml = "src/test/resources/fixtures/file3recursive.yaml";
    private String filePath4recursiveYaml = "src/test/resources/fixtures/file4recursive.yaml";
    private String simpleResult;
    private String recursiveResult;
    private String plainResult;
    private String jsonResult;

    @BeforeEach
    public final void preparationOfFixtures() throws Exception {
        final int valueTimeout = 50;
        parsedFile1.put("host", "hexlet.io");
        parsedFile1.put("timeout", valueTimeout);
        parsedFile1.put("proxy", "123.234.53.22");
        parsedFile1.put("follow", false);

        //Ожидающие результаты
        simpleResult = Files.readString(Paths.get("src/test/resources/fixtures/simpleResult.txt"));
        recursiveResult = Files.readString(Paths.get("src/test/resources/fixtures/recursiveResult.txt"));
        plainResult = Files.readString(Paths.get("src/test/resources/fixtures/plainResult.txt"));
        jsonResult = Files.readString(Paths.get("src/test/resources/fixtures/jsonResult.txt"));
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
    public void testDiffer() throws JsonProcessingException {
        var actual = Differ.generate(filePath1simple, filePath2simple, "stylish");
        var expected = simpleResult;
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferRecursiveJson() throws JsonProcessingException {
        var actual = Differ.generate(filePath3recursiveJson, filePath4recursiveJson, "stylish");
        var expected  = recursiveResult;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferRecursiveYaml() throws JsonProcessingException {
        var actual = Differ.generate(filePath3recursiveYaml, filePath4recursiveYaml, "stylish");
        var expected  = recursiveResult;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferPlain() throws JsonProcessingException {
        var actual = Differ.generate(filePath3recursiveJson, filePath4recursiveJson, "plain");
        var expected = plainResult;
        assertEquals(expected, actual);
    }
    @Test
    public void testDifferToJson() throws JsonProcessingException {
        var actual = Differ.generate(filePath1simple, filePath2simple, "json");
        var expected = jsonResult;
        assertEquals(expected, actual);

    }
}
