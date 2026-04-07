// Erstellt View mit Tabelle und addRowButton

package configurator.view;

import javax.swing.*;
import java.awt.*;

public class ConfiguratorView extends javax.swing.JFrame {

    private JButton addRowButton;
    private JButton saveButton;
    private JButton generateButton;
    private JButton openSettingsButton;
    private JButton uploadProgramButton;
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
        initLayout();
    }

    private void initLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(openSettingsButton);
        toolBar.add(uploadProgramButton);
        add(toolBar, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addRowButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(generateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
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
