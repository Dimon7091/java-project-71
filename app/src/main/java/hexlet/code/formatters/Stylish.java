package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diffList) {

        StringBuilder diffString = new StringBuilder();
        diffString.append("{\n");

        for (var node : diffList) {
            var type = node.get("type").toString();
            var key = node.get("key");
            var value = node.get("value");
            switch (type) {
                case "changed":
                    diffString.append("  - ").append(key).append(": ").append(value).append("\n");
                    diffString.append("  + ").append(key).append(": ").append(node.get("new value"));
                    break;
                case "unchanged":
                    diffString.append("    ").append(key).append(": ").append(value);
                    break;
                case "added":
                    diffString.append("  + ").append(key).append(": ").append(value);
                    break;
                case "deleted":
                    diffString.append("  - ").append(key).append(": ").append(value);
                    break;
                default:
                    throw new RuntimeException(type + " type not found");
            }
            diffString.append("\n");
        }
        diffString.append("}");
        return diffString.toString();
    }
}
