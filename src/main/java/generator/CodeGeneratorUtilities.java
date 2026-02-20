package generator;

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
}
