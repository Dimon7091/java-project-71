package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<String, Object>> diffList) {

        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{\n");
        var counter = 0;

        for (var node : diffList) {
            var type = node.get("type").toString();
            var key = node.get("key");
            var value = node.get("value");
            switch (type) {
                case "changed":
                    jsonString.append("  \"- ").append(key).append("\": \"").append(value).append("\"").append(",\n");
                    jsonString.append("  \"+ ").append(key).append("\": \"").append(node.get("new value")).append("\"");
                    break;
                case "unchanged":
                    jsonString.append("  \"").append(key).append("\": \"").append(value).append("\"");
                    break;
                case "added":
                    jsonString.append("  \"+ ").append(key).append("\": \"").append(value).append("\"");
                    break;
                case "deleted":
                    jsonString.append("  \"- ").append(key).append("\": \"").append(value).append("\"");
                    break;
                default:
                    throw new RuntimeException(type + " type not found");
            }
            counter++;
            if (counter < diffList.size()) {
                jsonString.append(",");
            }
            jsonString.append("\n");
        }
        jsonString.append("}");
        return jsonString.toString();
    }
}
