package Dialogs;

import main.EditorState;
import main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LevelModifications implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JLabel modsInfo;
    private JTextArea modsData;
    private JScrollPane modsScroller;
    private JButton resetMods;
    private JButton resetGhorMods;
    private JButton resetTaerMods;
    private JButton saveButton;
    private JButton cancelButton;

    public LevelModifications(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Prototype Modifications", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);
    }
    public void render() {
        removeModsDialog();
        dialog.setSize(900, 720);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());

        modsInfo = new JLabel("This section allows you to modify any vehicle, weapon or building. \nThis is for advanced users so typing incorrect data may crash the game");
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.insets = new Insets (10,0,10,0);
        constraints.gridwidth = 6;
        dialog.add(modsInfo, constraints);
        modsData = new JTextArea(30,78);
        modsData.setText(EditorState.prototypeModifications);
        modsData.setLineWrap(true);
        modsData.setWrapStyleWord(true);
        modsScroller = new JScrollPane(modsData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        constraints.gridy = 1;
        dialog.add(modsScroller, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;
        constraints.insets = new Insets (2,0,2,0);
        constraints.gridx = 1;
        constraints.gridy = 2;
        resetMods = new JButton("Reset for original campaign");
        dialog.add(resetMods, constraints);
        resetMods.addActionListener(this);

        constraints.gridy = 3;
        resetGhorMods = new JButton("Reset for metropoils dawn(Ghorkov)");
        dialog.add(resetGhorMods, constraints);
        resetGhorMods.addActionListener(this);

        constraints.gridy = 4;
        resetTaerMods = new JButton("Reset for metropoils dawn(Taerkasten)");
        dialog.add(resetTaerMods, constraints);
        resetTaerMods.addActionListener(this);

        saveButton = new JButton("Save");
        constraints.gridx = 5;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridy = 2;
        dialog.add(saveButton, constraints);
        saveButton.addActionListener(this);

        constraints.insets = new Insets (10,0,10,0);
        cancelButton = new JButton("Cancel");
        constraints.gridy = 3;
        dialog.add(cancelButton, constraints);
        cancelButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;

        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton) {
            EditorState.prototypeModifications = modsData.getText();
            removeModsDialog();
            dialog.setVisible(false);
            this.window.makeUnsaved();
        }
        if(e.getSource() == resetMods) {
            modsData.setText("include data:scripts/startup2.scr");
        }
        if(e.getSource() == resetGhorMods) {
            modsData.setText("include script:startupG.scr");
        }
        if(e.getSource() == resetTaerMods) {
            modsData.setText("include script:startupT.scr");
        }
        if(e.getSource() == cancelButton) {
            removeModsDialog();
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            removeModsDialog();
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
    void removeModsDialog() {
        if(cancelButton != null) dialog.remove(cancelButton);
        if(resetMods != null) dialog.remove(resetMods);
        if(resetGhorMods != null) dialog.remove(resetGhorMods);
        if(resetTaerMods != null) dialog.remove(resetTaerMods);
        if(saveButton != null) dialog.remove(saveButton);
        if(modsData != null) dialog.remove(modsData);
        if(modsScroller != null) dialog.remove(modsScroller);
        if(modsInfo != null) dialog.remove(modsInfo);
    }
}
