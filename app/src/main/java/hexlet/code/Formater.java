package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.Map;

public class Formater {
    public static String choiceFormat(Map<String, Object> map1, Map<String, Object> map2, String format) {
        switch (format) {
            case "stylish":
                return Stylish.format(map1, map2);
            case "plain":
                return Plain.format(map1, map2);
            case "json":

            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}
