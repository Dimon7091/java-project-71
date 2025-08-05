package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.",
        version = "1.0")

public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;
    @Override
    public void run() {
        File file1 = new File(filepath1);
        File file2 = new File(filepath2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> filemap1 = null;
        Map<String, Object> filemap2 = null;
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
        Differ.generate(filemap1, filemap2);

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
