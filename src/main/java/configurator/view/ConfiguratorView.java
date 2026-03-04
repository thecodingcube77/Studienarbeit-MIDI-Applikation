// Erstellt View mit Tabelle und addRowButton

package configurator.view;

import javax.swing.*;
import java.awt.*;

public class ConfiguratorView extends javax.swing.JFrame {

    private final JButton addRowButton;
    private final JButton saveButton;
    private final JButton generateButton;
    private final JButton openSettingsButton;
    private final JButton uploadProgramButton;
    private final JTable table;

    public ConfiguratorView(TableView tableView) {
        super("Konfigurator");
        table = new JTable(tableView);
        addRowButton = new JButton("Neue Zeile");
        saveButton = new JButton("Speichern");
        generateButton = new JButton("Code generieren");
        openSettingsButton = new JButton("Einstellungen");
        uploadProgramButton = new JButton("Programm hochladen");
        table.getTableHeader().setReorderingAllowed(false);
        initView();
    }

    private void initView() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new java.awt.BorderLayout());
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(openSettingsButton);
        toolBar.add(uploadProgramButton);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(new JScrollPane(table), java.awt.BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addRowButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(generateButton);
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

    public JButton getOpenSettingsButton() {
        return openSettingsButton;
    }

    public JButton getUploadProgramButton() {
        return uploadProgramButton;
    }
}
