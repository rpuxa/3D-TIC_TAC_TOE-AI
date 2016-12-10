package util;

public class StringUtils {

    private static final String NEW_LINE = "\n";
    private static final String TAB = "\t";

    public static String tabulated(String line) {
        return TAB + line;
    }

    public static void appendLine(StringBuilder stringBuilder, String line) {
        stringBuilder.append(line);
        stringBuilder.append(NEW_LINE);
    }

    public static String toString(String[] lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            appendLine(stringBuilder, line);
        }

        return stringBuilder.toString();
    }
}
