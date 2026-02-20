// Erstellt die JTable inklusive zusammenhängenden Zeilen

package configurator.control;
import javax.swing.*;

import configurator.model.DropdownModel;
import configurator.model.MidiDataModel;
import configurator.model.ConfiguratorTableModel;
import configurator.view.ConfiguratorView;
import configurator.view.TableView;
import generator.CodeGenerator;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ConfiguratorControl {
    private final ConfiguratorTableModel dataModel;
    private final DropdownModel dropdownModel;
    private final ConfiguratorView view;
    private final TableView tableView;

    public ConfiguratorControl(ConfiguratorTableModel dataModel, DropdownModel dropdownModel) throws IOException {
        this.dataModel = dataModel;
        this.dropdownModel = dropdownModel;
        this.tableView = new TableView(dataModel);
        this.view = new ConfiguratorView(tableView);

        setupEditors();
        setupActions();

        view.setVisible(true);
    }

    private void setupEditors() throws IOException {
        JTable table = view.getTable();

        JComboBox<String> comboBoxCommand = new JComboBox<>(dropdownModel.getMidiCommands());
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBoxCommand));

        JComboBox<String> comboBoxInputType = new JComboBox<>();
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxInputType));

        ParameterEditorControl parameterEditorControl1 = new ParameterEditorControl(dropdownModel);
        table.getColumnModel().getColumn(2).setCellEditor(parameterEditorControl1);
        table.getColumnModel().getColumn(2).setCellRenderer(new LabelRendererControl(dropdownModel));

        ParameterEditorControl parameterEditorControl2 = new ParameterEditorControl(dropdownModel);
        table.getColumnModel().getColumn(3).setCellEditor(parameterEditorControl2);
        table.getColumnModel().getColumn(3).setCellRenderer(new LabelRendererControl(dropdownModel));

        String[] channels = getChannels();
        JComboBox<String> comboBoxChannel = new JComboBox<>(channels);
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBoxChannel));

        comboBoxCommand.addActionListener(e -> {
            String selectedCommand = (String) comboBoxCommand.getSelectedItem();
            if (selectedCommand == null) return;
            int row = table.getSelectedRow();
            if (row == -1) return;

            String[] inputType = dropdownModel.getInputType(selectedCommand);
            comboBoxInputType.setModel(new DefaultComboBoxModel<>(inputType));
            table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxInputType));
            table.setValueAt(inputType[0], row, 1);

            String parameter1 = dropdownModel.getMidiParameter1Name(selectedCommand);
            String parameter2 = dropdownModel.getMidiParameter2Name(selectedCommand);

            parameterEditorControl1.setLabel(dropdownModel.getMidiParameter1Name(selectedCommand));
            parameterEditorControl2.setLabel(dropdownModel.getMidiParameter2Name(selectedCommand));

            table.setValueAt(parameter1, row, 2);

            dataModel.getMidiDataModel(row).setColumnEditableInputType(inputType.length > 1);
            dataModel.getMidiDataModel(row).setColumnEditable1(parameter1 != null);
            dataModel.getMidiDataModel(row).setColumnEditable2(parameter2 != null);
            dataModel.getMidiDataModel(row).setColumnEditableChannel(dropdownModel.getChannelApplicable(selectedCommand));

            tableView.fireTableDataChanged();
        });
    }

    private String[] getChannels() {
        ArrayList<String> channels = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            channels.add(String.valueOf(i));
        }
        return channels.toArray(new String[16]);
    }

    private void setupActions() {
        view.getAddRowButton().addActionListener(e -> {
            dataModel.addRow(new MidiDataModel(null, null, null, null,null, dropdownModel ));
            tableView.fireTableDataChanged();
        });
        view.getSaveButton().addActionListener(e -> {
            JTable table = view.getTable();
            try {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                dataModel.saveTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getGenerateButton().addActionListener(e -> {
            CodeGenerator generator = new CodeGenerator();
        });
    }
}
