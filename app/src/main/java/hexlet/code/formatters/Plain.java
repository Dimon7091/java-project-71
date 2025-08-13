package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public class Plain {
    public static String format(Map<String, Object> map1, Map<String, Object> map2) {

        var commonMap = new TreeMap<>(map2);
        commonMap.putAll(map1);
        StringBuilder string = new StringBuilder();
        int counter = 0;

        for (Map.Entry<String, Object> entry : commonMap.entrySet()) {
            var key = entry.getKey();

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!map1.get(key).equals(map2.get(key))) {
                    String value1 = map1.get(key).toString();
                    String newValue = map2.get(key).toString();
                    string.append("Property ").append("'").append(key).append("' ")
                            .append("was updated. From ").append(strFormat(value1)).append(" to ")
                            .append(strFormat(newValue));
                    if (counter < commonMap.size() - 1) {
                        string.append("\n");
                    }
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                string.append("Property ").append("'").append(key).append("' ").append("was removed");
                if (counter < commonMap.size() - 1) {
                    string.append("\n");
                }
            } else {
                String value = map2.get(key).toString();
                string.append("Property ").append("'").append(key).append("' ").append("was added with value: ")
                        .append(strFormat(value));
                if (counter < commonMap.size() - 1) {
                    string.append("\n");
                }
            }
            counter++;
        }
        System.out.println(string);
        return string.toString();
    }

    public static String strFormat(String str) {
        String editedString = "";
        int lastChar = str.length() - 1;
        boolean onlyDigits = str.matches("\\d+");
        if ((str.charAt(0) == '{') && (str.charAt(lastChar) == '}')
                || (str.charAt(0) == '[') && (str.charAt(lastChar) == ']')) {
            editedString += "[complex value]";
        } else if (str == "true" || str == "false" || str == "null" || onlyDigits) {
            editedString += str;
        } else {
            editedString += "'" + str + "'";
        }
        return editedString;
    }
}
