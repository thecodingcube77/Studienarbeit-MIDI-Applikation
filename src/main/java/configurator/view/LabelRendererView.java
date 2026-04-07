package configurator.view;

import configurator.model.MidiCommandDefinition;
import configurator.model.MidiConfigModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class LabelRendererView extends JPanel implements TableCellRenderer {
    private final JLabel label = new JLabel();
    private final MidiConfigModel configModel;

    public LabelRendererView(MidiConfigModel configModel) {
        this.configModel = configModel;
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (table == null || row < 0 || row >= table.getRowCount()) {
            label.setText("");
            return this;
        }

        Object command = table.getValueAt(row, 0);
        String prefix = "";

        if (command != null) {
            MidiCommandDefinition def = configModel.getDefinition(command.toString());
            if (def != null) {
                prefix = (column == 2) ? def.getParameter1Name() : def.getParameter2Name();
            }
        }
        String text = (value != null) ? value.toString() : "";
        label.setText((prefix != null && !prefix.isEmpty()) ? prefix + ": " + text : "");
        return this;
    }
}
