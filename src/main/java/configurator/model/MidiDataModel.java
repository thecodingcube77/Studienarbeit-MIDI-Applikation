// Legt den Rahmen der Zeilen fest

package configurator.model;

public class MidiDataModel {
    String command;
    String inputType;
    String parameter1;
    String parameter2;
    String channel;
    DropdownModel dropdownModel;
    boolean editable1 = false;
    boolean editable2 = false;
    boolean editableChannel = false;
    boolean editableInputType = false;

    public MidiDataModel(String command, String inputType, String parameter1, String parameter2, String channel, DropdownModel dropdownModel) {
        this.command = command;
        this.inputType = inputType;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.channel = channel;
        this.dropdownModel = dropdownModel;
    }

    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
        this.setInputType(dropdownModel.getInputType(getCommand())[0]);
    }
    public String getInputType() {
        return inputType;
    }
    public void setInputType(String inputType) {
        this.inputType = inputType;
        this.setParameter1(String.valueOf(0));
        this.setParameter2(String.valueOf(0));
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

    public void setColumnEditable1(boolean editable1) {
        this.editable1 = editable1;
    }
    public void setColumnEditable2(boolean editable2) {
        this.editable2 = editable2;
    }
    public void setColumnEditableChannel(boolean editableChannel) {
        this.editableChannel = editableChannel;
    }
    public void setColumnEditableInputType(boolean editableInputType) {
        this.editableInputType = editableInputType;
    }
    public boolean isEditable1() {
        return editable1;
    }
    public boolean isEditable2() {
        return editable2;
    }
    public boolean isEditableChannel() {
        return editableChannel;
    }
    public boolean isEditableInputType() {
        return editableInputType;
    }
}
