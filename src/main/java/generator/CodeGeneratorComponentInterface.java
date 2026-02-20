package generator;

public interface CodeGeneratorComponentInterface {
    public String getGlobalCode();
    public String getSetupCode();
    public String getLoopCode(String midiInterfaceName);
    public int getComponentId();
    public void setComponentId(int newId);
}
