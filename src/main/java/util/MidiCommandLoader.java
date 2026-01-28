package util;

import org.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MidiCommandLoader {
    private static MidiCommandLoader instance = null;
    private String midiCommandConfigFilePath = "./resources/config/MidiCommands.json";
    private JSONObject midiCommandConfig;
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
        midiCommandConfig = new JSONObject(jsonStringBuilder.toString());
    }

    public static synchronized MidiCommandLoader getInstance() {
        if (instance == null) {
            instance = new MidiCommandLoader();
        }
        return instance;
    }

    public String[] getCommandNames() {
        ArrayList<String> commandNames = new ArrayList<>();
        Iterator<String> commandNamesIterator = midiCommandConfig.keys();
        while (commandNamesIterator.hasNext()) {
            commandNames.add(commandNamesIterator.next());
        }
        return commandNames.toArray(new String[0]);
    }

    public String getMidiCommandInputType(String commandName) {
        JSONObject commandInfo = midiCommandConfig.getJSONObject(commandName);
        return commandInfo.getString("input-type");
    }

    /**
     * Get the names of both parameters of a MIDI command
     * @param commandName Name of the command
     * @return String Array with {parameter1Name, parameter2Name}
     */
    public String[] getMidiCommandParameterNames(String commandName) {
        // TODO What if the command does not require both or even one parameter?
        JSONObject commandInfo = midiCommandConfig.getJSONObject(commandName);
        String parameter1Name = commandInfo.getJSONObject("parameter1").getString("name");
        String parameter2Name = commandInfo.getJSONObject("parameter2").getString("name");
        return new String[]{parameter1Name, parameter2Name};
    }
}
