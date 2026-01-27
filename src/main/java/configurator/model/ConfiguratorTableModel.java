// methoden für die Tabelle

package configurator.model;

import java.util.ArrayList;
import java.util.List;

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
