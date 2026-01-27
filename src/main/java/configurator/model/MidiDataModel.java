// Legt den Rahmen der Zeilen fest

package configurator.model;

public class MidiDataModel {
    String input;
    String function;
    String parameter1;
    String parameter2;

    public MidiDataModel(String input, String function, String parameter1, String parameter2) {
        this.input = input;
        this.function = function;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
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
}
