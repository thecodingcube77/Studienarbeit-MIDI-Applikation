package configurator;

import configurator.model.ConfiguratorModel;
import configurator.model.MidiDataModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceZeileTest {
    @Test
    public void test() {
        ConfiguratorModel model = new ConfiguratorModel();
        int tests = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            model.addRow(new MidiDataModel("", "", "0", "0", "1"));
        }
        long end = System.currentTimeMillis();
        long durchschnitt = (end - start) / tests;
        assertTrue(durchschnitt < 200);
    }
}
