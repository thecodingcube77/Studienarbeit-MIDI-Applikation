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
    private final List<MidiDataModel> columns = new ArrayList<>();

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
            rowData.put("input-type", rows.get(i).getInput());
            rowData.put("command", rows.get(i).getFunction());
            rowData.put("channel", "1");
            rowData.put("parameter1", rows.get(i).getParameter1());
            rowData.put("parameter2", rows.get(i).getParameter2());
            midiData.put(rowData);
        }
        try (FileWriter file = new FileWriter("ConfiguratorData.json")) {
            file.write(midiData.toString(2));
        }
    }

    public void updateInput(int index, String value) {
        rows.get(index).setInput(value);
    }
    public void updateFunction(int index, String value) {
        rows.get(index).setFunction(value);
    }
    public void updateParameter1(int index, String value) {
        rows.get(index).setParameter1(value);
    }
    public void updateParameter2(int index, String value) {
        rows.get(index).setParameter2(value);
    }

    public int getColumnCount() {
        return 4;
    }
}
