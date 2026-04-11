package configurator;

import configurator.model.ConfiguratorDataWriterModel;
import configurator.model.ConfiguratorModel;
import configurator.model.MidiDataModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceSpeichernTest {
    @Test
    public void test() throws IOException {
        ConfiguratorModel model = new ConfiguratorModel();
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        int tests = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            model.save();
        }
        long end = System.currentTimeMillis();
        long durchschnitt = (end - start) / tests;
        assertTrue(durchschnitt < 200);
    }
}
