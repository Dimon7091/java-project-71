package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> formJsonYaml(String filepath) {
        String format = filepath.substring(filepath.length() - 4);
        Map<String, Object> filemap = null;
        if (format.equals("json")) {
            File file1 = new File(filepath);
            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                filemap = jsonMapper.readValue(file1,
                        new TypeReference<Map<String, Object>>() { });
            } catch (IOException e) {
                System.err.println("Файл 1 не найден");
            }
        } else if (format.equals("yaml") || format.equals(".yml")) {
            File file1 = new File(filepath);
            ObjectMapper yamlMapper = new YAMLMapper();
            try {
                filemap = yamlMapper.readValue(file1,
                        new TypeReference<Map<String, Object>>() { });
            } catch (IOException e) {
                System.err.println("Файл 1 не найден");
            }
        }
        assert filemap != null;
        fixNull(filemap);
        return filemap;
    }
    public static void fixNull(Map<String, Object> map) {
        for (String key : map.keySet()) {
            if (map.get(key) == null) {
                map.put(key, "null");
            }
        }
    }
}
