import java.io.IOException;
import java.util.Arrays;

import configurator.control.*;
import configurator.model.*;
import generator.CodeTemplateLoader;
import util.MidiCommandLoader;

class Main {
    public static void main(String[] args) throws IOException {
        CodeTemplateLoader.getInstance();
        ConfiguratorTableModel configuratorTableModel = new ConfiguratorTableModel();
        DropdownModel dropdownModel = new DropdownModel();
        new ConfiguratorControl(configuratorTableModel, dropdownModel);
    }
}
