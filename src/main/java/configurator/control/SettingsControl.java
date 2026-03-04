package configurator.control;

import codeuploader.CodeUploader;
import configurator.view.SettingsView;
import util.ApplicationSettings;

import javax.swing.*;
import java.awt.desktop.AppForegroundListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class SettingsControl {
    private final SettingsView view;
    public SettingsControl(SettingsView settingsView) {
        this.view = settingsView;
        setAvailableBoards();
        setupActionListeners();
    }

    private void setAvailableBoards() {
        Map<String, Map<String, String>> boards = CodeUploader.getConnectedBoards();
        view.getBoardComboBox().removeAllItems();
        boards.forEach((fqbn, details) -> {
            String name = details.get("name");
            String port = details.get("port");
            view.getBoardComboBox().addItem(name + " auf " + port);
        });
    }

    private void setupActionListeners() {
        view.getUpdateBoardsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAvailableBoards();
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comboBoxValue = view.getBoardComboBox().getSelectedItem().toString();
                String port = comboBoxValue.split(" auf ")[1];
                final String[] fqbn = {null};
                String boardName = comboBoxValue.split(" auf ")[0];
                Map<String, Map<String, String>> boards = CodeUploader.getConnectedBoards();
                boards.forEach((key, detail) -> {
                    if (detail.get("name").equals(boardName)) {
                        fqbn[0] = key;
                    }
                });
                if (fqbn[0] == null) {
                    JOptionPane.showMessageDialog(view,
                            "Das ausgewählte Board ist nicht mehr verfügbar",
                            "Ausgewähltes Board nicht mehr da",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ApplicationSettings.setPortAddress(port);
                ApplicationSettings.setSelectedBoard(fqbn[0]);

            }
        });
    }
}
