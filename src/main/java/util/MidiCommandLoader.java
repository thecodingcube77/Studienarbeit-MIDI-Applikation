package util;

import org.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MidiCommandLoader {
    private static MidiCommandLoader instance = null;
    private String midiCommandConfigFilePath = "/home/bo/Documents/Studienarbeit/Studienarbeit-MIDI-Applikation/resources/config/MidiCommands.json";
    private JSONArray midiCommandConfig;
    private MidiCommandLoader() {
        loadJSONFile();
    }

    private void loadJSONFile() {
        StringBuilder jsonStringBuilder = new StringBuilder();
        File inputJSONFile = new File(midiCommandConfigFilePath);
        try (Scanner jsonFileReader = new Scanner(inputJSONFile)) {
            while (jsonFileReader.hasNextLine()) {
                jsonStringBuilder.append(jsonFileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read Midi Commands JSON File at:" + midiCommandConfigFilePath);
            e.printStackTrace();
        }
        midiCommandConfig = new JSONArray(jsonStringBuilder.toString());
    }

    public static synchronized MidiCommandLoader getInstance() {
        if (instance == null) {
            instance = new MidiCommandLoader();
        }
        return instance;
    }

    public String[] getCommandNames(String inputType) {
        ArrayList<String> commandNames = new ArrayList<>();
        // TODO for each element get the command name and append to array list above
        // TODO then return array list
        return new String[] {"noteOn", "noteOff"};
    }
}
