package generator;

import org.json.JSONObject;

public class ButtonComponent implements CodeGeneratorComponent {
    private JSONObject componentInfo;
    private CodeGeneratorUtilities utilities;

    public ButtonComponent(CodeGeneratorUtilities utilities, JSONObject buttonComponentInfo) {
        this.componentInfo = buttonComponentInfo;
        this.utilities = utilities;
    }

    @Override
    public String getInputType() {
        return componentInfo.getString("input-type");
    }

    @Override
    public String getInputName() {
        return componentInfo.getString("input-type") + this.getPinNumber();
    }

    @Override
    public String getPinNumber() {
        return "x";
    }

    @Override
    public String getNodeAddressName() {
        return utilities.noteIntegerToNoteString(componentInfo.getInt("parameter1"));
    }

    @Override
    public String getChannelName() {
        return "Channel_" + componentInfo.getString("channel");
    }

    @Override
    public String getVelocityValue() {
        return componentInfo.getString("parameter2");
    }

    @Override
    public String getVelocityName() {
        return "velocity" + this.getPinNumber();
    }

    @Override
    public String getCommand() {
        return componentInfo.getString("command");
    }
}
