package configurator;

import configurator.control.ConfiguratorControl;
import configurator.model.ConfiguratorModel;
import configurator.model.MidiConfigModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidierungTest {
    @Test
    public void testValidateInputs(){
        ConfiguratorModel configuratorModel = new ConfiguratorModel();
        MidiConfigModel midiConfigModel = new MidiConfigModel();
        ConfiguratorControl configuratorControl = new ConfiguratorControl(configuratorModel,midiConfigModel);

        String ergebnis = configuratorControl.validateRange("1");
        assertEquals("1", ergebnis, "sfd");

        String ergebnis2 = configuratorControl.validateRange("128");
        assertEquals("127", ergebnis2);

        String ergebnis3 = configuratorControl.validateRange("-2");
        assertEquals("0", ergebnis3);

        String ergebnis4 = configuratorControl.validateRange("test");
        assertEquals("0", ergebnis4);

        String ergebnis5 = configuratorControl.validateRange("");
        assertEquals("0", ergebnis5);
    }
}
