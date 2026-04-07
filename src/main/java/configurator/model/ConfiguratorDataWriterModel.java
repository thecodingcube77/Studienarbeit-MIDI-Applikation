package configurator.model;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ConfiguratorDataWriterModel {
    private final static Path FilePath = Path.of("resources", "ConfiguratorData.json");

    public void saveToFile(List<MidiDataModel> data) throws IOException {
        JSONArray midiData = new JSONArray();

        for (MidiDataModel dataModel : data) {
            JSONObject rowData = new JSONObject();
            rowData.put("command", dataModel.getCommand());
            rowData.put("input-type", dataModel.getInputType());
            rowData.put("parameter1", dataModel.getParameter1());
            rowData.put("parameter2", dataModel.getParameter2());
            rowData.put("channel", dataModel.getChannel());
            midiData.put(rowData);
        }

        try (FileWriter fileWriter = new FileWriter(FilePath.toFile())) {
            fileWriter.write(midiData.toString(2));
            System.out.println("Datei wird gespeichert unter: " + FilePath.toAbsolutePath());
            fileWriter.flush();
        }
    }
}
