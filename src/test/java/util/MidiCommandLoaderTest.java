package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class MidiCommandLoaderTest {
    @Test
    void commandNames() {
        final String[] ACTUAL = MidiCommandLoader.getInstance().getCommandNames();
        final String[] EXPECTED = new String[]{"note-on", "note-off"};
        assertThat(ACTUAL).isEqualTo(EXPECTED);
    }

    @Test
    void inputTypeNoteOn() {
        final String ACTUAL = MidiCommandLoader.getInstance().getMidiCommandInputType("note-on");
        final String EXPECTED = "button";
        assertEquals(EXPECTED, ACTUAL);
    }

    @Test
    void inputTypeNoteOff() {
        final String ACTUAL = MidiCommandLoader.getInstance().getMidiCommandInputType("note-off");
        final String EXPECTED = "button";
        assertEquals(EXPECTED, ACTUAL);
    }

    @Test
    void parameterNamesNoteOn() {
        final String[] ACTUAL = MidiCommandLoader.getInstance().getMidiCommandParameterNames("note-on");
        final String[] EXPECTED = new String[]{"pitch", "velocity"};
        assertThat(ACTUAL).isEqualTo(EXPECTED);
    }

    @Test
    void parameterNamesNoteOff() {
        final String[] ACTUAL = MidiCommandLoader.getInstance().getMidiCommandParameterNames("note-off");
        final String[] EXPECTED = new String[]{"pitch", "velocity"};
        assertThat(ACTUAL).isEqualTo(EXPECTED);
    }
}
