// Erstellt die View der Tabelle (ggf zu viel, teilweise eher Model oder Control?)

package configurator.view;

import configurator.model.MidiDataModel;

import javax.swing.table.AbstractTableModel;
import configurator.model.ConfiguratorTableModel;

public class TableView extends AbstractTableModel {
    private final ConfiguratorTableModel model;
    private boolean editable1 = true;
    private boolean editable2 = true;

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
            case 0 -> "Input";
            case 1 -> "Funktion";
            case 2 -> "Parameter1";
            case 3 -> "Parameter2";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MidiDataModel row = model.getRow(rowIndex);
        return switch(columnIndex){
            case 0 -> row.getInput();
            case 1 -> row.getFunction();
            case 2 -> row.getParameter1();
            case 3 -> row.getParameter2();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if(value == null) return;
        MidiDataModel data = model.getRow(row);
        switch(col) {
            case 0 -> data.setInput((String) value);
            case 1 -> data.setFunction((String) value);
            case 2 -> data.setParameter1((String) checkRange((String) value));
            case 3 -> data.setParameter2((String) checkRange((String) value));
        }
        fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2) return editable1;
        if (columnIndex == 3) return editable2;
        return true;
    }

    public void setColumnEditable1(boolean editable1) {
        this.editable1 = editable1;
        fireTableDataChanged();
    }
    public void setColumnEditable2(boolean editable2) {
        this.editable2 = editable2;
        fireTableDataChanged();
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
            ;
            if (Integer.parseInt(String.valueOf(value)) < 0) {
                return String.valueOf(0);
            }
            return String.valueOf(value);
        }
        catch (NumberFormatException e) {
            return String.valueOf(0);
        }
    }
}
