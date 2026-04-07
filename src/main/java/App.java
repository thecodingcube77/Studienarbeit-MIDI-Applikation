import java.io.IOException;

import configurator.control.*;
import configurator.model.*;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ConfiguratorModel configuratorModel = new ConfiguratorModel();
                MidiConfigModel midiConfigModel = new MidiConfigModel();
                new ConfiguratorControl(configuratorModel, midiConfigModel);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Fehler beim Starten: " + e.getMessage(),
                    "Kritischer Fehler", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}
