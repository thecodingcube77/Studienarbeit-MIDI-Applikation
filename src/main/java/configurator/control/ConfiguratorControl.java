// Erstellt die JTable inklusive zusammenhängenden Zeilen

package configurator.control;
import javax.swing.*;

import configurator.model.ConfiguratorModel;
import configurator.model.DropdownModel;
import configurator.model.MidiDataModel;
import configurator.model.ConfiguratorTableModel;
import configurator.view.ConfiguratorView;
import configurator.view.TableView;

import java.awt.*;
import java.io.IOException;

public class ConfiguratorControl {
    private final ConfiguratorTableModel dataModel;
    private final DropdownModel dropdownModel;
    private final ConfiguratorView view;
    private final TableView tableView;

    public ConfiguratorControl(ConfiguratorTableModel dataModel, DropdownModel dropdownModel) {
        this.dataModel = dataModel;
        this.dropdownModel = dropdownModel;
        this.tableView = new TableView(dataModel);
        this.view = new ConfiguratorView(tableView);

        setupEditors();
        setupActions();

        view.setVisible(true);
    }

    private void setupEditors() {
        JTable table = view.getTable();

        JComboBox<String> comboBoxInput = new JComboBox<>(dropdownModel.getElement());
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBoxInput));

        JComboBox<String> comboBoxFunction = new JComboBox<>();
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxFunction));

        ParameterEditorControl parameterEditorControl1 = new ParameterEditorControl(dropdownModel);
        table.getColumnModel().getColumn(2).setCellEditor(parameterEditorControl1);
        table.getColumnModel().getColumn(2).setCellRenderer(new LabelRendererControl(dropdownModel));

        ParameterEditorControl parameterEditorControl2 = new ParameterEditorControl(dropdownModel);
        table.getColumnModel().getColumn(3).setCellEditor(parameterEditorControl2);
        table.getColumnModel().getColumn(3).setCellRenderer(new LabelRendererControl(dropdownModel));

        comboBoxInput.addActionListener(e -> {
            String selectedInput = (String) comboBoxInput.getSelectedItem();
            if (selectedInput == null) return;

            String[] functions = dropdownModel.getFunctions(selectedInput);
            comboBoxFunction.setModel(new DefaultComboBoxModel<>(functions));

            table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxFunction));

            int row = table.getSelectedRow();
            if (row >= 0 && functions.length > 0) {
                table.setValueAt(functions[0], row, 1);
                table.setValueAt(null, row, 2); // Parameter Spalte leeren
            }

            table.repaint();
        });

        comboBoxFunction.addActionListener(e -> {
            String selectedFunction = (String) comboBoxFunction.getSelectedItem();
            if (selectedFunction == null) return;

            String[] parameters = dropdownModel.getParameters(selectedFunction);

            parameterEditorControl1.setLabel(dropdownModel.getParameterNames(selectedFunction)[0]);
            parameterEditorControl2.setLabel(dropdownModel.getParameterNames(selectedFunction)[1]);

            int row = table.getSelectedRow();
            if (row >= 0 && parameters.length > 0) {
                table.setValueAt(parameters[0], row, 2);
            }

            tableView.setColumnEditable1(parameters[0] != null);
            tableView.setColumnEditable2(parameters[1] != null);

            table.repaint();
        });
    }

    private void setupActions() {
        view.getAddRowButton().addActionListener(e -> {
            dataModel.addRow(new MidiDataModel(null, null, null, null, dropdownModel ));
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
    }
}
