import java.io.IOException;
import java.util.Arrays;

import configurator.control.*;
import configurator.model.*;
import util.MidiCommandLoader;

class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(MidiCommandLoader.getInstance().getCommandNames("button")));
        ConfiguratorTableModel configuratorTableModel = new ConfiguratorTableModel();
        DropdownModel dropdownModel = new DropdownModel();
        new ConfiguratorControl(configuratorTableModel, dropdownModel);
    }
}
