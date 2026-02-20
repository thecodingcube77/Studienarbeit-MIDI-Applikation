// methoden für die Tabelle

package configurator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;

public class ConfiguratorTableModel {
    private final List<MidiDataModel> rows = new ArrayList<>();

    public int getRowCount() {
        return rows.size();
    }
    public MidiDataModel getRow(int index) {
        return rows.get(index);
    }
    public void addRow(MidiDataModel row) {
        rows.add(row);
    }
    public void saveTable() throws IOException {
        JSONArray midiData = new JSONArray();

        for (int i = 0; i < getRowCount(); i++) {
            JSONObject rowData = new JSONObject();
            rowData.put("input-type", rows.get(i).getInputType());
            rowData.put("command", rows.get(i).getCommand());
            rowData.put("channel", rows.get(i).getChannel());
            rowData.put("parameter1", rows.get(i).getParameter1());
            rowData.put("parameter2", rows.get(i).getParameter2());
            midiData.put(rowData);
        }
        try (FileWriter file = new FileWriter("ConfiguratorData.json")) {
            file.write(midiData.toString(2));
        }
    }

    public MidiDataModel getMidiDataModel(int index) {
        return rows.get(index);
    }

    public int getColumnCount() {
        return 5;
    }
}
