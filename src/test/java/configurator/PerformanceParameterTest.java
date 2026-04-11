package configurator;

import configurator.control.ConfiguratorControl;
import configurator.model.ConfiguratorDataWriterModel;
import configurator.model.ConfiguratorModel;
import configurator.model.MidiConfigModel;
import configurator.model.MidiDataModel;
import configurator.view.ConfiguratorView;
import configurator.view.TableView;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceParameterTest {
    @Test
    public void test() throws IOException {
        ConfiguratorModel model = new ConfiguratorModel();
        MidiConfigModel configModel = new MidiConfigModel();
        TableView tableView = new TableView(model, configModel);
        ConfiguratorView view = new ConfiguratorView(tableView);
        ConfiguratorControl control = new ConfiguratorControl(model, configModel);
        model.addRow(new MidiDataModel("note-on", "button", "0", "0", "1"));
        int tests = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            tableView.setValueAt(i, 0, 3);
        }
        long end = System.currentTimeMillis();
        long durchschnitt = (end - start) / tests;
        assertTrue(durchschnitt < 200);
    }
}
