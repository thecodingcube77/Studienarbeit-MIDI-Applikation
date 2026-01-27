// Klasse, um das Label des Parameters hinzuzufügen

package configurator.control;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ParameterEditorControl extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel = new JPanel(new BorderLayout());
    private JLabel label = new JLabel();
    private JTextField textField = new JTextField();

    public ParameterEditorControl() {
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
        textField.setText((String) value);
        return panel;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }
}
