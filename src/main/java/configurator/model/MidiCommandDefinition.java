package configurator.model;
import org.json.JSONObject;

public class MidiCommandDefinition {
    private final String inputType;
    private final boolean channelApplicable;
    private final String parameter1Name;
    private final String parameter2Name;

    public MidiCommandDefinition(String inputType, boolean channelApplicable, JSONObject parameter1, JSONObject parameter2){
        this.inputType = inputType;
        this.channelApplicable = channelApplicable;
        this.parameter1Name = (parameter1 != null) ? parameter1.optString("name", null) : null;
        this.parameter2Name = (parameter2 != null) ? parameter2.optString("name", null) : null;
    }

    public String[] getInputTypes() {
        return inputType.toLowerCase().split(",");
    }
    public boolean isChannelApplicable() {
        return channelApplicable;
    }
    public String getParameter1Name() {
        return parameter1Name;
    }
    public String getParameter2Name() {
        return parameter2Name;
    }
}