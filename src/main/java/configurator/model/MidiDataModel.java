// Legt den Rahmen der Zeilen fest

package configurator.model;

public class MidiDataModel {
    String command;
    String inputType;
    String parameter1;
    String parameter2;
    String channel;

    public MidiDataModel(String command, String inputType, String parameter1, String parameter2, String channel) {
        this.command = command;
        this.inputType = inputType;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.channel = channel;
    }

    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getInputType() {
        return inputType;
    }
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
    public String getParameter1() {
        return parameter1;
    }
    public void setParameter1(String value) {
        this.parameter1 = value;
    }
    public String getParameter2() {
        return parameter2;
    }
    public void setParameter2(String value) {
        this.parameter2 = value;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
}
