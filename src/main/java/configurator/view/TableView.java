package configurator.view;

import configurator.model.MidiCommandDefinition;
import configurator.model.MidiConfigModel;
import configurator.model.MidiDataModel;
import configurator.model.ConfiguratorModel;
import javax.swing.table.AbstractTableModel;

public class TableView extends AbstractTableModel {

    private final ConfiguratorModel model;
    private final MidiConfigModel configModel;

    public TableView(ConfiguratorModel model, MidiConfigModel configModel) {
        this.model = model;
        this.configModel = configModel;
    }

    @Override
    public int getRowCount() {
        return model.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Funktion";
            case 1 -> "Input";
            case 2 -> "Parameter1";
            case 3 -> "Parameter2";
            case 4 -> "Channel";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= model.getRowCount()) {
            return "";
        }
        MidiDataModel row = model.getRow(rowIndex);
        return switch(columnIndex){
            case 0 -> row.getCommand();
            case 1 -> row.getInputType();
            case 2 -> row.getParameter1();
            case 3 -> row.getParameter2();
            case 4 -> row.getChannel();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value == null) return;

        MidiDataModel data = model.getRow(row);
        String valString = value.toString();

        switch (col) {
            case 0 -> data.setCommand(valString);
            case 1 -> data.setInputType(valString);
            case 2 -> data.setParameter1(valString);
            case 3 -> data.setParameter2(valString);
            case 4 -> data.setChannel(valString);
        }
        fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) return true; // Command immer editierbar

        MidiDataModel data = model.getRow(row);
        MidiCommandDefinition def = configModel.getDefinition(data.getCommand());

        if (def == null) return col == 0;

        return switch(col) {
            case 2 -> def.getParameter1Name() != null;
            case 3 -> def.getParameter2Name() != null;
            case 4 -> def.isChannelApplicable();
            default -> true;
        };
    }
}
