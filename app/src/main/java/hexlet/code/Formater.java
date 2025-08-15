package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formater {
    public static String choiceFormat(List<Map<String, Object>> commonList, String format) {
        switch (format) {
            case "stylish":
                return Stylish.format(commonList);
            case "plain":
                return Plain.format(commonList);
            case "json":
                return Json.format(commonList);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

}
