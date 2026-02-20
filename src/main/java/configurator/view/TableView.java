// Erstellt die View der Tabelle (ggf zu viel, teilweise eher Model oder Control?)

package configurator.view;

import configurator.model.MidiDataModel;

import javax.swing.table.AbstractTableModel;
import configurator.model.ConfiguratorTableModel;

public class TableView extends AbstractTableModel {

    private final ConfiguratorTableModel model;

    public TableView(ConfiguratorTableModel model) {
        this.model = model;
    }

    @Override
    public int getRowCount() {
        return model.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return model.getColumnCount();
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
        if(value == null) return;
        MidiDataModel data = model.getRow(row);
        switch(col) {
            case 0 -> data.setCommand((String) value);
            case 1 -> data.setInputType((String) value);
            case 2 -> data.setParameter1(checkRange(value));
            case 3 -> data.setParameter2(checkRange(value));
            case 4 -> data.setChannel((String) value);
        }
        fireTableRowsUpdated(row, row);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        MidiDataModel row = model.getRow(rowIndex);
        if (columnIndex == 1) return row.isEditableInputType();
        if (columnIndex == 2) return row.isEditable1();
        if (columnIndex == 3) return row.isEditable2();
        if (columnIndex == 4) return row.isEditableChannel();
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    private String checkRange(Object value) {
        try {
            if (Integer.parseInt(String.valueOf(value)) > 127) {
                return String.valueOf(127);
            }
            if (Integer.parseInt(String.valueOf(value)) < 0) {
                return String.valueOf(0);
            }
            return String.valueOf(Integer.parseInt(String.valueOf(value)));
        }
        catch (NumberFormatException e) {
            return String.valueOf(0);
        }
    }
}
