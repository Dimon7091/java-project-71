package hexlet.code;

import java.util.*;

public class Differ {
    public static void generate(Map<String, Object> map1, Map<String, Object> map2) {

        var preparedList = new ArrayList<>();
        for (Map.Entry<String, Object> entry1: map1.entrySet()) {
            var key1 = entry1.getKey();
            var value1 = entry1.getValue();
            // Если ключи есть в обоих файлах
            if (map2.containsKey(key1)) {
                // Если значения равны
                if (value1.equals(map2.get(key1))){
                    preparedList.add(key1 + ": " + value1);
                    // Если на равны
                } else {
                    preparedList.add("-" + key1 + ": " + value1);
                    preparedList.add("+" + key1 + ": " + map2.get(key1));
                }
            }
            // Если ключа нет во втором файле
            if (!map2.containsKey(key1)) {
                preparedList.add("-" + key1 + ": " + value1);
            }
        }
        // Если ключи есть только во втором файле
        for (Map.Entry<String, Object> entry2: map2.entrySet()) {
            var key2 = entry2.getKey();
            var value2 = entry2.getValue();
            if (!map1.containsKey(key2)) {
                preparedList.add("+" + key2 + ": " + value2);
            }
        }
        // Сортировка списка по алфавиту
        preparedList.sort((s1, s2) -> {
            char c1 = s1.toString().charAt(1);
            char c2 = s2.toString().charAt(1);
            return Character.compare(c1, c2);
        });
        preparedList.add(0, "{");
        preparedList.add(preparedList.size(), "}");
        for (var element : preparedList) {
            System.out.println(element);

        }
    }
}
