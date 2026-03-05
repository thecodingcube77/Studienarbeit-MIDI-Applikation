package generator;

import org.json.JSONObject;

import java.util.Map;

public class ButtonComponent extends CodeGeneratorComponent{
    private JSONObject componentInfo;

    public ButtonComponent(JSONObject buttonComponentInfo, int id) {
        this.componentInfo = buttonComponentInfo;
    }

    @Override
    public String getGlobalCode() {
        return "";
        /*String command = componentInfo.getString("command");
        String template = CodeTemplateLoader.getInstance().getCodeTemplate("button", command, "global");
        return CodeGeneratorUtilities.fillTemplate(template, componentInfo);*/
    }


    @Override
    public String getSetupCode() {
        String command = componentInfo.getString("command");
        String template = CodeTemplateLoader.getInstance().getCodeTemplate("button", command, "setup");
        return CodeGeneratorUtilities.fillTemplate(template, componentInfo);
        /*return String.format("""
                pushbutton%d.begin();
                """,
                this.getComponentId());*/
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
