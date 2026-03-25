package generator;

import org.json.JSONObject;

public interface CodeGeneratorComponent {
    public String getInputType();
    public String getInputName();
    public String getPinNumber();
    public String getNodeAddressName();
    public String getChannelName();
    public String getVelocityValue();
    public String getVelocityName();
    public String getCommand();
}
