import java.io.IOException;

import configurator.control.*;
import configurator.model.*;

class Main {
    public static void main(String[] args) throws IOException {
        ConfiguratorTableModel configuratorTableModel = new ConfiguratorTableModel();
        DropdownModel dropdownModel = new DropdownModel();
        new ConfiguratorControl(configuratorTableModel, dropdownModel);
    }
}
