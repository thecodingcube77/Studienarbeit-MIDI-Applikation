package configurator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        checkRows();
        dataWriter.saveToFile(rows);
    }

    private void checkRows() throws IOException {
        for (MidiDataModel data : rows) {
            if(data.getCommand().isEmpty() || data.getCommand() == null) {
                throw new IOException("Command empty");
            }
            if(data.getInputType().isEmpty() || data.getInputType() == null) {
                throw new IOException("Input type empty");
            }
            if(data.getChannel().isEmpty() || data.getChannel() == null) {
                throw new IOException("Channel empty");
            }
            if(Integer.parseInt(data.getParameter1()) < 0 || Integer.parseInt(data.getParameter1()) > 127){
                throw new IOException("Parameter 1 out of bounds");
            }
            if(Integer.parseInt(data.getParameter2()) < 0 || Integer.parseInt(data.getParameter2()) > 127){
                throw new IOException("Parameter 2 out of bounds");
            }
        }
    }
}
