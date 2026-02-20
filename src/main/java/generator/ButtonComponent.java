package generator;

import org.json.JSONObject;

public class ButtonComponent extends CodeGeneratorComponent{
    private int channel;
    private String command;
    private int note;
    private int velocity;
    private int inputPin;

    public ButtonComponent(JSONObject buttonComponentInfo, int id) {
        this.channel = buttonComponentInfo.getInt("channel");
        this.command = buttonComponentInfo.getString("command");
        this.note = buttonComponentInfo.getInt("parameter1");
        this.velocity = buttonComponentInfo.getInt("parameter2");
        this.setComponentId(id);
        this.inputPin = id + 2;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, channel: %d, command: %s, note: %d, velocity: %d", this.getComponentId(), this.channel, this.command, this.note, this.velocity);
    }

    @Override
    public String getGlobalCode() {
        return String.format("""
                Button pushbutton%d {%d};
                const MIDIAddress noteAddress%d {%s, Channel_%d};
                const uint8_t velocity%d = %d;
                """,
                this.getComponentId(),
                this.inputPin,
                this.getComponentId(),
                CodeGeneratorUtilities.noteIntegerToNoteString(this.note),
                this.channel,
                this.getComponentId(),
                this.velocity);
    }

    @Override
    public String getSetupCode() {
        return String.format("""
                pushbutton%d.begin();
                """,
                this.getComponentId());
    }

    @Override
    public String getLoopCode(String midiInterfaceName) {
        return String.format("""
                pushbutton%d.update();
                if (pushbutton%d.getState() == Button::Falling) {
                    %s.sendNoteOn(noteAddress%d, velocity%d);
                }
                else if (pushbutton%d.getState() == Button::Rising) {
                    %s.sendNoteOff(noteAddress%d, velocity%d);
                }
                """,
                this.getComponentId(),
                this.getComponentId(),
                midiInterfaceName,
                this.getComponentId(),
                this.getComponentId(),
                this.getComponentId(),
                midiInterfaceName,
                this.getComponentId(),
                this.getComponentId());
    }
}
