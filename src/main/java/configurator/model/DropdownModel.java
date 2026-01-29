// initialisiert die Auswahlmöglichkeiten der Dropdown-Felder

package configurator.model;

import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;

public class DropdownModel {

    String midiCommands = Files.readString(Path.of("resources", "config", "MidiCommands.json"));
    JSONObject midiObject = new JSONObject(midiCommands);
    Map<String, ArrayList<String>> midiCommandMap = new HashMap<>();

    public DropdownModel() throws IOException {

        for(String key : midiObject.keySet()) {
            String inputType = midiObject.getJSONObject(key).getString("input-type");
            String channelApplicable = String.valueOf(midiObject.getJSONObject(key).getBoolean("channel-applicable"));
            String midiParameter1Name = midiObject.getJSONObject(key).getJSONObject("parameter1").getString("name");
            String midiParameter1Req = String.valueOf(midiObject.getJSONObject(key).getJSONObject("parameter1").getBoolean("required"));
            String midiParameter2Name = midiObject.getJSONObject(key).getJSONObject("parameter2").getString("name");
            String midiParameter2Req = String.valueOf(midiObject.getJSONObject(key).getJSONObject("parameter2").getBoolean("required"));

            ArrayList<String> midiCommandInfo = new ArrayList<>();
            midiCommandInfo.add(inputType);
            midiCommandInfo.add(channelApplicable);
            midiCommandInfo.add(midiParameter1Name);
            midiCommandInfo.add(midiParameter1Req);
            midiCommandInfo.add(midiParameter2Name);
            midiCommandInfo.add(midiParameter2Req);

            midiCommandMap.put(key, midiCommandInfo);
        }
    }

    public String[] getMidiCommands() {
        return midiCommandMap.keySet().toArray(new String[0]);
    }
    public String getInputType(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(0);
    }
    public boolean getChannelApplicable(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(1).equals("true");
    }
    public String getMidiParameter1Name(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(2);
    }
    public boolean getMidiParameter1Req(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(3).equals("true");
    }
    public String getMidiParameter2Name(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(4);
    }
    public boolean getMidiParameter2Req(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(5).equals("true");
    }
}
