package generator;

public abstract class CodeGeneratorComponent implements CodeGeneratorComponentInterface{
    private int componentId;

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int newId) {
        componentId = newId;
    }
}
