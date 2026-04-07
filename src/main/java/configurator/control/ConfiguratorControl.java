package configurator.control;

import configurator.model.*;
import configurator.view.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.io.IOException;

public class ConfiguratorControl {
    private final ConfiguratorModel model;
    private final MidiConfigModel configModel;
    private final ConfiguratorView view;
    private final TableView tableView;

    public ConfiguratorControl(ConfiguratorModel model, MidiConfigModel configModel) {
        this.model = model;
        this.configModel = configModel;
        this.tableView = new TableView(model, configModel);
        this.view = new ConfiguratorView(tableView);

        setupTableEditors();
        setupButtonActions();

        view.setVisible(true);
    }
    private void setupTableEditors() {
        JTable table = view.getTable();

        LabelRendererView labelRendererView = new LabelRendererView(configModel);
        ParameterEditorView parameterEditorView = new ParameterEditorView(configModel);

        table.getColumnModel().getColumn(2).setCellRenderer(labelRendererView);
        table.getColumnModel().getColumn(2).setCellEditor(parameterEditorView);

        table.getColumnModel().getColumn(3).setCellRenderer(labelRendererView);
        table.getColumnModel().getColumn(3).setCellEditor(parameterEditorView);

        TableColumn commandColumn = table.getColumnModel().getColumn(0);
        JComboBox<String> commandComboBox = new JComboBox<>(configModel.getMidiCommands());
        commandColumn.setCellEditor(new DefaultCellEditor(commandComboBox));

        TableColumn channelColumn = table.getColumnModel().getColumn(4);
        String[] channels = new String[16];
        for (int i = 0; i < 16; i++) {
            channels[i] = String.valueOf(i+1);
        }
        JComboBox<String> channelComboBox = new JComboBox<>(channels);
        channelColumn.setCellEditor(new DefaultCellEditor(channelComboBox));

        table.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();
                handleTableUpdate(row, col);
            }
        });
    }
    private void handleTableUpdate(int row, int col) {
        MidiDataModel data = model.getRow(row);

        switch (col) {
            case 0 -> setCommand(data, row);
            case 1 -> {}
            case 2 -> setParameter1(data);
            case 3 -> setParameter2(data);
        }
        view.getTable().repaint();
    }

    private void setCommand(MidiDataModel data, int row){
        MidiCommandDefinition def = configModel.getDefinition(data.getCommand());
        if (def != null ){
            data.setInputType(def.getInputTypes()[0]);
            updateInputEditor(row, def.getInputTypes());
            if (def.getParameter1Name() == null) data.setParameter1("0");
            if (def.getParameter2Name() == null) data.setParameter2("0");
            tableView.fireTableRowsUpdated(row, row);
        }
    }

    private void updateInputEditor(int row, String[] inputTypes) {
        JComboBox<String> inputTypeComboBox = new JComboBox<>(inputTypes);
        TableColumn inputType = view.getTable().getColumnModel().getColumn(1);
        inputType.setCellEditor(new DefaultCellEditor(inputTypeComboBox));
    }

    private void setParameter1(MidiDataModel data){
        data.setParameter1(validateRange(data.getParameter1()));
    }

    private void setParameter2(MidiDataModel data){
        data.setParameter2(validateRange(data.getParameter2()));
    }

    private String validateRange(Object value){
        try {
            int i = Integer.parseInt(String.valueOf(value));
            return String.valueOf(Math.max(0, Math.min(127,i)));
        } catch (NumberFormatException e){
            return "0";
        }
    }

    private void setupButtonActions() {
        view.getAddRowButton().addActionListener(e -> {
            model.addRow(new MidiDataModel("", "", "0", "0", "1"));
            tableView.fireTableRowsInserted(model.getRowCount() - 1, model.getRowCount() - 1);
        });

        view.getSaveButton().addActionListener(e -> {
            try {
                if (view.getTable().isEditing()){
                    view.getTable().getCellEditor().stopCellEditing();
                }
                model.save();
                JOptionPane.showMessageDialog(view, "Erfolgreich gespeichert!");
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(view, "Fehler beim speichern: " + exception.getMessage());
            }
        });

        view.getOpenSettingsButton().addActionListener(e -> {
            SettingsView sView = new SettingsView();
            new SettingsControl(sView);
        });

        view.getGenerateButton().addActionListener(e -> {
            generator.CodeGenerator generator = new generator.CodeGenerator();
            System.out.println(codeuploader.CodeUploader.getConnectedBoards());
        });

        view.getUploadProgramButton().addActionListener(e -> {
            if (!util.ApplicationSettings.hasSelectedBoard()) {
                JOptionPane.showMessageDialog(view,"Es muss ein Board ausgewählt sein", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!util.ApplicationSettings.hasPortAddress()) {
                JOptionPane.showMessageDialog(view,"Es ist kein Port gesetzt", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
            codeuploader.CodeUploader.uploadCode();
        });
    }
}