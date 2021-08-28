package Dialogs;

import main.EditorState;
import main.MainWindow;
import main.UAdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameContent implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JRadioButton noneContent;
    private JRadioButton mdContent;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel contentList;

    public GameContent(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Additional game content", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);
    }
    public void render() {
        ButtonGroup contentGroup = new ButtonGroup();
        noneContent = new JRadioButton("none");
        mdContent = new JRadioButton("Metropolis Dawn");
        JPanel contentNote = new JPanel(new GridBagLayout());
        JPanel contentButtons = new JPanel(new GridBagLayout());
        saveButton = new JButton("Apply");
        cancelButton = new JButton("Cancel");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2,5,2,20);
        dialog.add(new JLabel("Select additional content for the game"), constraints);
        contentList = new JPanel(new GridBagLayout());
        contentList.setBorder(BorderFactory.createTitledBorder("Available game content"));
        constraints.anchor = GridBagConstraints.WEST;
        contentGroup.add(noneContent);
        contentGroup.add(mdContent);
        contentList.add(noneContent, constraints);
        constraints.gridy = 1;
        contentList.add(mdContent, constraints);
        if(EditorState.gameContent == 0) {
            noneContent.setSelected(true);
        } else if(EditorState.gameContent == 1){
            mdContent.setSelected(true);
        }

        constraints.insets = new Insets(2,2,2,2);
        constraints.gridy = 0;
        contentNote.setBorder(BorderFactory.createTitledBorder(""));
        contentNote.add(new JLabel("Note: In order to add Metropolis Dawn content properly,"), constraints);
        constraints.gridy = 1;
        contentNote.add(new JLabel("you have to decide which host station you want to play with "), constraints);
        constraints.gridy = 2;
        contentNote.add(new JLabel("then click on proper button in Options > Prototype Modifications."), constraints);
        constraints.gridy = 3;
        constraints.insets = new Insets(10,2,2,2);
        contentNote.add(new JLabel("For Ghorkov click \"Reset for metropolis dawn(Ghorkov)\""), constraints);
        constraints.gridy = 4;
        constraints.insets = new Insets(2,2,2,2);
        contentNote.add(new JLabel("or just change the first line to: include script:startupG.scr"), constraints);
        constraints.gridy = 5;
        constraints.insets = new Insets(10,2,2,2);
        contentNote.add(new JLabel("For Taerkasten click \"Reset for metropolis dawn(Taerkasten)\""), constraints);
        constraints.gridy = 6;
        constraints.insets = new Insets(2,2,2,2);
        contentNote.add(new JLabel("or just change the first line to: include script:startupT.scr"), constraints);

        constraints.gridy = 0;
        contentButtons.add(saveButton, constraints);
        saveButton.addActionListener(this);
        constraints.insets = new Insets(2,20,2,20);
        constraints.gridx = 1;
        contentButtons.add(cancelButton, constraints);
        cancelButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 1;
        constraints.insets = new Insets(15,1,1,1);
        dialog.add(contentList, constraints);
        constraints.gridy = 2;
        dialog.add(contentNote, constraints);
        constraints.gridy = 3;
        dialog.add(contentButtons, constraints);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton) {
            if(noneContent.isSelected()) {
                EditorState.gameContent = 0;
                UAdata.addOriginalData();
            }
            else if(mdContent.isSelected()) {
                EditorState.gameContent = 1;
                UAdata.addMetropolisDawnData();
            }
            this.window.updateGameContent();
            dialog.setVisible(false);
            this.window.makeUnsaved();
        }
        if(e.getSource() == cancelButton) {
            removeDialog();
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            removeDialog();
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
    public void removeDialog() {
        //TODO implement
    }
}
