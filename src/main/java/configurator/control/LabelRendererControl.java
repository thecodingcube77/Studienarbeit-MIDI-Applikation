//sorgt dafür, dass das Label durchgehend sichtbar ist und nicht nur bei der Bearbeitung

package configurator.control;

import configurator.model.DropdownModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class LabelRendererControl extends javax.swing.JPanel implements TableCellRenderer {

    private final JLabel label = new JLabel();
    private final DropdownModel dropdownModel;

    public LabelRendererControl(DropdownModel dropdownModel) {
        this.dropdownModel = dropdownModel;
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Object selectedCommand = table.getValueAt(row, 0);

        String parameter = "";
        if (selectedCommand != null) {
            parameter = switch (column) {
                case 2 -> dropdownModel.getMidiParameter1Name(selectedCommand.toString());
                case 3 -> dropdownModel.getMidiParameter2Name(selectedCommand.toString());
                default -> "";
            };

        }
        String number = "";
        if (value != null) {
            number = value.toString();
        }

        if (parameter != null){
            label.setText(parameter + ": " + number);
        }
        else {
            label.setText("");
        }

        return this;
    }
}
