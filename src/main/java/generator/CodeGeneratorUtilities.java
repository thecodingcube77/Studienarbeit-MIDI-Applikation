package generator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGeneratorUtilities {
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

    public String noteIntegerToNoteString(int noteInteger) {
        return String.format("MIDI_Notes::%s[%d]", NoteNames[noteInteger % 12], noteInteger / 12);
    }

    public String fillTemplate(String template, JSONObject componentInfo) {
        String result = template;
        Matcher keywordsMatcher = Pattern.compile("(__[^_ ]+__)").matcher(template);
        List<String> matches = new ArrayList<>();
        while (keywordsMatcher.find()) {
            matches.add(keywordsMatcher.group(0));
        }
        Set<String> keywords = new HashSet<>(matches);
        for (String keyword : keywords) {
            System.out.println(keyword);
        }
        return result;
    }
}
