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
        Object selectedFunction = table.getValueAt(row, 1);

        String parameter = "";
        if (selectedFunction != null) {
            parameter = switch (column) {
                case 2 -> dropdownModel.getParameterNames(selectedFunction.toString())[0];
                case 3 -> dropdownModel.getParameterNames(selectedFunction.toString())[1];
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
