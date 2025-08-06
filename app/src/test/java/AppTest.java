import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AppTest {
    Map<String, Object> filemap1 = null;
    Map<String, Object> filemap2 = null;
    ArrayList<String> expected = new ArrayList<>();


    @BeforeEach
    public void preparationOfFixtures() throws Exception {
        File file1 = new File("./src/test/resources/fixtures/file1.json");
        File file2 = new File("./src/test/resources/fixtures/file2.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            filemap1 = mapper.readValue(file1,
                    new TypeReference<Map<String, Object>>() { });
            System.out.println(filemap1);
        } catch (IOException e) {
            System.err.println("Файл 1 не найден");
        }
        try {
            filemap2 = mapper.readValue(file2,
                    new TypeReference<Map<String, Object>>() { });
            System.out.println(filemap2);
        } catch (IOException e) {
            System.err.println("Файл 2 не найден");
        }

        expected.add("{");
        expected.add(" -follow: false");
        expected.add("  host: hexlet.io");
        expected.add(" -proxy: 123.234.53.22");
        expected.add(" -timeout: 50");
        expected.add(" +timeout: 20");
        expected.add(" +verbose: true");
        expected.add("}");

    }

    @Test
    public void testDiffer() throws Exception {
        var actual = Differ.generate(filemap1, filemap2);
        assertEquals(expected, actual, "Не совпадает с ожиданием");
    }
}
