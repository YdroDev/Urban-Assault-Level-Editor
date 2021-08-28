package Dialogs;

import main.EditorState;
import main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LevelDescription implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JLabel descInfo;
    private JTextArea descData;
    private JScrollPane descScroller;
    private JButton saveButton;
    private JButton cancelButton;

    public LevelDescription(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Set level description", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

    }
    public void render() {
        removeDialog();
        dialog.setSize(900, 880);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());

        descInfo = new JLabel("You can write description to this level here which will be added at the beginning of ldf file");
        descData = new JTextArea(42, 80);

        descData.setText(EditorState.description);
        descScroller = new JScrollPane(descData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descScroller.setPreferredSize(new Dimension(890, 700));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(3, 2, 2, 2);
        dialog.add(descInfo, constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(20, 2, 2, 2);
        dialog.add(descScroller, constraints);
        constraints.gridy = 2;
        dialog.add(saveButton, constraints);
        saveButton.addActionListener(this);
        constraints.gridy = 3;
        dialog.add(cancelButton, constraints);
        cancelButton.addActionListener(this);

        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton) {
            EditorState.description = descData.getText();
            removeDialog();
            dialog.setVisible(false);
            this.window.makeUnsaved();
        }
        if(e.getSource() == cancelButton) {
            removeDialog();
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            removeDialog();
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    void removeDialog() {
        if(cancelButton != null) dialog.remove(cancelButton);
        if(saveButton != null) dialog.remove(saveButton);
        if(descScroller != null) dialog.remove(descScroller);
        if(descInfo != null) dialog.remove(descInfo);
    }
}
