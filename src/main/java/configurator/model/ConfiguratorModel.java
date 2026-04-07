package configurator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguratorModel {
    private final List<MidiDataModel> rows = new ArrayList<MidiDataModel>();
    private final ConfiguratorDataWriterModel dataWriter = new ConfiguratorDataWriterModel();

    public void addRow(MidiDataModel data) {
        rows.add(data);
    }

    public List<MidiDataModel> getRows() {
        return rows;
    }

    public MidiDataModel getRow(int index) {
        return rows.get(index);
    }

    public int getRowCount() {
        return rows.size();
    }

    public void save() throws IOException {
        dataWriter.saveToFile(rows);
    }
}
