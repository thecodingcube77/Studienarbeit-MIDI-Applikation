package generator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CodeGeneratorUtilities {
    private static final String[] NoteNames = {
            "C",
            "Db",
            "D",
            "Eb",
            "E",
            "F",
            "Gb",
            "G",
            "Ab",
            "A",
            "Bb",
            "B"
    };

    public static String noteIntegerToNoteString(int noteInteger) {
        return String.format("MIDI_Notes::%s[%d]", NoteNames[noteInteger % 12], noteInteger / 12);
    }

    public static String fillTemplate(String template, JSONObject componentInfo) {
        String result = template;
        Pattern keywordsPatern = Pattern.compile("__([^ ]+)__");
        Matcher keywordsMatcher = keywordsPatern.matcher(result);
        List<String> keywords = new ArrayList<>();
        for (int index = 0; index < keywordsMatcher.groupCount(); index ++) {
            keywords.add(keywordsMatcher.group(index));
        }
        keywords.forEach(System.out::println);
        return result;
    }
}
