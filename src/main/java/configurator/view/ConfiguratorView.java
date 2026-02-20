// Erstellt View mit Tabelle und addRowButton

package configurator.view;

import javax.swing.*;
import java.awt.*;

public class ConfiguratorView extends javax.swing.JFrame {

    private final JButton addRowButton;
    private final JButton saveButton;
    private final JButton generateButton;
    private final JTable table;

    public ConfiguratorView(TableView tableView) {
        super("Konfigurator");
        table = new JTable(tableView);
        addRowButton = new JButton("Neue Zeile");
        saveButton = new JButton("Speichern");
        generateButton = new JButton("Code generieren");
        table.getTableHeader().setReorderingAllowed(false);
        initView();
    }

    private void initView() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new java.awt.BorderLayout());
        this.add(new JScrollPane(table), java.awt.BorderLayout.NORTH);
        this.add(addRowButton, BorderLayout.WEST);
        this.add(saveButton, BorderLayout.EAST);
        this.add(generateButton, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public JTable getTable() {
        return table;
    }

    public AbstractButton getAddRowButton() {
        return addRowButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }
}
