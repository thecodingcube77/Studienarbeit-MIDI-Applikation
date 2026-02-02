// Klasse, um das Label des Parameters hinzuzufügen

package configurator.control;

import configurator.model.DropdownModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.io.IOException;

public class ParameterEditorControl extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel = new JPanel(new BorderLayout());
    private JLabel label = new JLabel();
    private JTextField textField = new JTextField();
    private final DropdownModel dropdownModel;

    public ParameterEditorControl(DropdownModel dropdownModel) throws IOException {
        this.dropdownModel = new DropdownModel();
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }
    public void setCellEditorValue(String value) {
        textField.setText(value);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        String parameter = LabelRendererControl.getMidiParamNames(table, row, column, dropdownModel);

        label.setText(parameter + ": ");
        textField.setText(value != null ? value.toString() : "");

        return panel;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }
}
