package configurator;

import configurator.model.ConfiguratorModel;
import configurator.model.MidiDataModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SicherheitTest {
    @Test
    public void sicherheitTest() throws IOException {
        ConfiguratorModel model = new ConfiguratorModel();
        model.addRow(new MidiDataModel("note-on", "slider", "0", "999", "1"));
        assertThrows(Exception.class, model::save);
    }
}
