package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diffList) {

        StringBuilder diffString = new StringBuilder();

        for (var node : diffList) {
            var checker = false;
            var type = node.get("type").toString();
            var key = node.get("key");
            var value = node.get("value").toString();

            switch (type) {
                case "changed":
                    diffString.append("Property ").append("\'").append(key).append("\' ").append("was updated. ")
                            .append("From ").append(valueFormat(value)).append(" to ")
                            .append(valueFormat(node.get("new value").toString()));
                    checker = true;
                    break;
                case "unchanged":
                    break;
                case "added":
                    diffString.append("Property ").append("\'").append(key).append("\' ")
                            .append("was added with value: ").append(valueFormat(value));
                    checker = true;
                    break;
                case "deleted":
                    diffString.append("Property ").append("\'").append(key).append("\' ")
                            .append("was removed");
                    checker = true;
                    break;
                default:
                    throw new RuntimeException(type + " type not found");
            }
            if (checker) {
                diffString.append("\n");
            }
        }

        if (!diffString.isEmpty() && diffString.charAt(diffString.length() - 1) == '\n') {
            diffString.deleteCharAt(diffString.length() - 1);
        }
        return diffString.toString();
    }

    public static String valueFormat(String str) {
        String value = "";
        boolean onlyDigits = str.matches("\\d+");
        if (str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']'
                || str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') {
            value += "[complex value]";
        } else if (onlyDigits || str.equals("false") || str.equals("true") || str.equals("null")) {
            value += str;
        } else {
            value = "'" + str + "'";
        }
        return value;
    }
}
