package hexlet.code;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        var parsedMap1 = Parser.formJsonYaml(filepath1);
        var parsedMap2 = Parser.formJsonYaml(filepath2);
        return Formater.choiceFormat(parsedMap1, parsedMap2, format);
    }
}
