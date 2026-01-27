// Erstellt View mit Tabelle und addRowButton

package configurator.view;

import javax.swing.*;
import configurator.model.ConfiguratorTableModel;
import java.awt.*;

public class ConfiguratorView extends javax.swing.JFrame {

    private final JButton addRowButton;
    private final JTable table;

    public ConfiguratorView(TableView tableView) {
        super("Konfigurator");
        table = new JTable(tableView);
        addRowButton = new JButton("Neue Zeile");
        initView();
        System.out.println("Table-UI: " + table.getParent());
    };

    private void initView() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new java.awt.BorderLayout());
        this.add(new JScrollPane(table), java.awt.BorderLayout.CENTER);
        this.add(addRowButton, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public JTable getTable() {
        return table;
    }

    public AbstractButton getAddRowButton() {
        return addRowButton;
    }
}
