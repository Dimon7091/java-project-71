package hexlet.code;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) {
        var map1 = Parser.formJsonYaml(filepath1);
        var map2 = Parser.formJsonYaml(filepath2);
        String format = "stylish";
        var commonMap = new TreeMap<>(map2);
        commonMap.putAll(map1);
        var diffList = new LinkedList<Map<String, Object>>();

        for (Map.Entry<String, Object> entry : commonMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            var map2Value = map2.get(key);
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!value.equals(map2Value)) {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("type", "changed");
                    map.put("key", key);
                    map.put("value", value);
                    map.put("new value", map2Value);
                    diffList.add(map);
                } else {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("type", "unchanged");
                    map.put("key", key);
                    map.put("value", value);
                    diffList.add(map);
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("type", "deleted");
                map.put("key", key);
                map.put("value", value);
                diffList.add(map);
            } else {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("type", "added");
                map.put("key", key);
                map.put("value", map2Value);
                diffList.add(map);
            }
        }
        return Formater.choiceFormat(diffList, format);
    }
    public static String generate(String filepath1, String filepath2, String format) {
        var map1 = Parser.formJsonYaml(filepath1);
        var map2 = Parser.formJsonYaml(filepath2);

        var commonMap = new TreeMap<>(map2);
        commonMap.putAll(map1);
        var commonList = new LinkedList<Map<String, Object>>();

        for (Map.Entry<String, Object> entry : commonMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            var map2Value = map2.get(key);
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!value.equals(map2Value)) {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("type", "changed");
                    map.put("key", key);
                    map.put("value", value);
                    map.put("new value", map2Value);
                    commonList.add(map);
                } else {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("type", "unchanged");
                    map.put("key", key);
                    map.put("value", value);
                    commonList.add(map);
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("type", "deleted");
                map.put("key", key);
                map.put("value", value);
                commonList.add(map);
            } else {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("type", "added");
                map.put("key", key);
                map.put("value", map2Value);
                commonList.add(map);
            }
        }
        return Formater.choiceFormat(commonList, format);
    }
}
