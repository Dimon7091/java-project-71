package hexlet.code.formatters;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Stylish {
    public static String format(Map<String, Object> map1, Map<String, Object> map2) {

        var commonMap = new TreeMap<>(map2);
        commonMap.putAll(map1);
        var commonList = new LinkedList<>();

        for (Map.Entry<String, Object> entry : commonMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (map1.containsKey(key) && map2.containsKey(key)) {
                var map2Value = map2.get(key);
                if (!value.equals(map2Value)) {
                    commonList.add("  - " + key + ": " + value);
                    commonList.add("  + " + key + ": " + map2Value);
                } else {
                    commonList.add("    " + key + ": " + value);
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                commonList.add("  - " + key + ": " + value);
            } else {
                commonList.add("  + " + key + ": " + value);
            }
        }
        commonList.addFirst("{");
        commonList.addLast("}");

        StringBuilder result = new StringBuilder();
        for (var i = 0; i < commonList.size(); i++)  {
            var lastLineIdx = commonList.size() - 1;
            result.append(commonList.get(i));
            if (!(lastLineIdx == i)) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
