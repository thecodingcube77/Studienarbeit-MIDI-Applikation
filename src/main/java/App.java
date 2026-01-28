import java.io.IOException;
import java.util.Arrays;

import configurator.control.*;
import configurator.model.*;
import util.MidiCommandLoader;

class Main {
    public static void main(String[] args) throws IOException {
        ConfiguratorTableModel configuratorTableModel = new ConfiguratorTableModel();
        DropdownModel dropdownModel = new DropdownModel();
        new ConfiguratorControl(configuratorTableModel, dropdownModel);
    }
}
