// initialisiert die Auswahlmöglichkeiten der Dropdown-Felder

package configurator.model;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DropdownModel {

    String midiCommands = null;
    JSONObject midiObject = null;
    Map<String, ArrayList<String>> midiCommandMap = new HashMap<>();

    public DropdownModel() {
        try {
            midiCommands = Files.readString(Path.of("resources", "config", "MidiCommands.json"));
            midiObject = new JSONObject(midiCommands);
        }
        catch (IOException e) {
            System.out.println("Probleme beim laden der \"MidiCommands.json\"");
        }
        for(String key : midiObject.keySet()) {
            String inputType = null;
            String channelApplicable = null;
            String midiParameter1Name = null;
            String midiParameter1Req = null;
            String midiParameter2Name = null;
            String midiParameter2Req = null;

            try {
                inputType = midiObject.getJSONObject(key).getString("input-type");
                channelApplicable = String.valueOf(midiObject.getJSONObject(key).getBoolean("channel-applicable"));

                if (midiObject.getJSONObject(key).has("parameter1")) {
                    midiParameter1Name = midiObject.getJSONObject(key).getJSONObject("parameter1").getString("name");
                    midiParameter1Req = String.valueOf(midiObject.getJSONObject(key).getJSONObject("parameter1").getBoolean("required"));
                }
                if (midiObject.getJSONObject(key).has("parameter2")) {
                    midiParameter2Name = midiObject.getJSONObject(key).getJSONObject("parameter2").getString("name");
                    midiParameter2Req = String.valueOf(midiObject.getJSONObject(key).getJSONObject("parameter2").getBoolean("required"));
                }
            }  catch (JSONException e) {
                System.out.println("Es gab einen Fehler beim Lesen der MidiConfig");
            }

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
    public String[] getInputType(String key) {
        ArrayList<String> a = midiCommandMap.get(key);
        return a.get(0).toLowerCase().split(",");
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
