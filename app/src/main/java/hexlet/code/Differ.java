package hexlet.code;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {

        var commonMap = new TreeMap<>(map2);
        commonMap.putAll(map1);
        var commonList = new LinkedList<>();

        for (Map.Entry<String, Object> entry : commonMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (map1.containsKey(key) && map2.containsKey(key)) {
                var map2Value = map2.get(key);
                // Если значение null
                if (value == null) {
                    if (map2Value == null) {
                        commonList.add("    " + key + ": " + value);
                    } else {
                        commonList.add("  - " + key + ": " + value);
                        commonList.add("  + " + key + ": " + map2Value);
                    }
                } else {
                    if (!value.equals(map2Value)) {
                        commonList.add("  - " + key + ": " + value);
                        commonList.add("  + " + key + ": " + map2Value);
                    } else {
                        commonList.add("    " + key + ": " + value);
                    }
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                commonList.add("  - " + key + ": " + value);
            } else {
                commonList.add("  + " + key + ": " + value);
            }
        }
        commonList.add(0, "{");
        var lastIdx = commonList.size();
        commonList.add(lastIdx, "}");

        String result = "";
        for (var i = 0; i < commonList.size(); i++)  {
            var lastLineIdx = commonList.size() - 1;
            result += commonList.get(i);
            if (!(lastLineIdx == i)) {
                result += "\n";
            }
        }
        System.out.println(result);
        return result;
    }
}
