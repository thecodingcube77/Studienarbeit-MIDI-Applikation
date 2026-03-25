package configurator.view;


import javax.swing.*;
import java.awt.*;

public class SettingsView extends JFrame {
    private final JComboBox<String> boardComboBox;
    private final JButton saveButton;
    private final JButton updateBoardsButton;

    public SettingsView() {
        this.boardComboBox = new JComboBox<>();
        this.saveButton = new JButton("Speichern");
        this.updateBoardsButton = new JButton("Boards aktualisieren");
        initView();
    }

    private void initView() {
        this.setLayout(new FlowLayout());
        JPanel boardSettingsPanel = new JPanel();
        boardSettingsPanel.add(boardComboBox);
        boardSettingsPanel.add(updateBoardsButton);
        this.add(boardSettingsPanel);
        this.add(saveButton);
        this.pack();
        this.setVisible(true);
    }

    public JComboBox<String> getBoardComboBox() {
        return boardComboBox;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getUpdateBoardsButton() {
        return updateBoardsButton;
    }
}
