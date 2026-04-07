package configurator.view;

import configurator.model.MidiCommandDefinition;
import configurator.model.MidiConfigModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ParameterEditorView extends AbstractCellEditor implements TableCellEditor {
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel label = new JLabel();
    private final JTextField textField = new JTextField();
    private final MidiConfigModel configModel;

    public ParameterEditorView(final MidiConfigModel configModel) {
        this.configModel = configModel;
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Object command = table.getValueAt(row, 0);
        String prefix = "";
        if (command != null) {
            MidiCommandDefinition def = configModel.getDefinition(command.toString());
            if (def != null) {
                prefix = (column == 2) ? def.getParameter1Name() : def.getParameter2Name();
            }
        }
        label.setText(prefix != null && !prefix.isEmpty() ? prefix + ": " : "");
        textField.setText(value != null ? value.toString() : "");
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }
}
