// initialisiert die Auswahlmöglichkeiten der Dropdown-Felder

package configurator.model;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MidiConfigModel {

    private final Map<String, MidiCommandDefinition> commands = new HashMap<>();

    public MidiConfigModel() {
        loadConfig();
    }

    private void loadConfig() {
        try {
            String midiConfig = Files.readString(Path.of("resources", "config", "MidiCommands.json"));
            JSONObject json = new JSONObject(midiConfig);

            for (String key : json.keySet()) {
                JSONObject obj = json.getJSONObject(key);
                commands.put(key, new MidiCommandDefinition(
                    obj.getString("input-type"),
                    obj.getBoolean("channel-applicable"),
                    obj.optJSONObject("parameter1"),
                    obj.optJSONObject("parameter2")
                ));
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Midi-Konfiguration");
        }
    }

    public String[] getMidiCommands() {
        return commands.keySet().toArray(new String[0]);
    }

    public MidiCommandDefinition getDefinition(String command) {
        return commands.get(command);
    }
}

