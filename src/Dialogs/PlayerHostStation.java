package Dialogs;

import State.EditorState;
import main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class PlayerHostStation implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JLabel playerHSLabel;
    private JPanel hsListPanel;
    private final ArrayList<JRadioButton> availableHS;
    private JLabel noHSavailable;
    private JButton savePlayer;
    private JButton cancelPlayer;

    public PlayerHostStation(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Select Host Station for player", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

        availableHS = new ArrayList<>();
    }
    public void render() {
        removeDialog();
        dialog.setSize(300, 400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        playerHSLabel = new JLabel("Select Host Station you want to play with");
        constraints.gridwidth = 6;
        dialog.add(playerHSLabel, constraints);
        constraints.gridwidth = 1;
        hsListPanel = new JPanel(new GridBagLayout());
        ButtonGroup playerHSgroup = new ButtonGroup();
        constraints.insets = new Insets(10,1,10,1);
        int playerGridy = 1;
        constraints.gridy = playerGridy;
        if(EditorState.hostStations.isEmpty()) {
            noHSavailable = new JLabel("No Host Station available");
            hsListPanel.add(noHSavailable, constraints);
        }else {
            constraints.insets = new Insets(5,1,5,1);
            constraints.anchor = GridBagConstraints.WEST;
            hsListPanel.setBorder(BorderFactory.createTitledBorder("Available Host Stations"));

            for(int i = 0;;i++) {
                if(EditorState.hostStations.get(i) == null) break;

                switch (EditorState.hostStations.get(i).getOwner()) {
                    case 1 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Resistance"));
                    case 2 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Sulgogar"));
                    case 3 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Mykonian"));
                    case 4 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Taerkasten"));
                    case 5 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Black Sect"));
                    case 6 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Ghorkov"));
                    case 7 -> availableHS.add(new JRadioButton("Host Station " + (i + 1) + ": Target Host Station"));
                }
                playerHSgroup.add(availableHS.get(availableHS.size()-1));
                hsListPanel.add(availableHS.get(availableHS.size()-1),  constraints);
                playerGridy++;
                constraints.gridy = playerGridy;
            }
            if(availableHS.size() <= EditorState.playerSelected) EditorState.playerSelected = availableHS.size()-1;
            availableHS.get(EditorState.playerSelected).setSelected(true);
        }
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy = 1;
        constraints.gridwidth = 6;
        constraints.gridx = 1;
        dialog.add(hsListPanel, constraints);
        constraints.gridwidth = 2;
        constraints.gridx = 3;
        savePlayer = new JButton("Save");
        constraints.gridy = 2;
        constraints.insets = new Insets(5,60,5,1);
        dialog.add(savePlayer, constraints);
        constraints.gridwidth = 1;
        savePlayer.addActionListener(this);
        constraints.gridx = 5;
        constraints.insets = new Insets(5,1,5,30);
        cancelPlayer = new JButton("Cancel");
        dialog.add(cancelPlayer, constraints);
        cancelPlayer.addActionListener(this);
        constraints.insets = new Insets(1,1,1,1);

        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == savePlayer) {
            for(int i = 0; i < availableHS.size(); i++) {
                if(availableHS.get(i).isSelected())
                    EditorState.playerSelected = i;
            }
            removeDialog();
            dialog.setVisible(false);
            this.window.makeUnsaved();
        }

        if(e.getSource() == cancelPlayer) {
            removeDialog();
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            removeDialog();
            dialog.setVisible(false);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    void removeDialog() {
        if(cancelPlayer != null) dialog.remove(cancelPlayer);
        if(savePlayer != null) dialog.remove(savePlayer);
        if(noHSavailable != null) dialog.remove(noHSavailable);
        if(hsListPanel != null) dialog.remove(hsListPanel);
        availableHS.clear();
        hsListPanel.setBorder(null);
        if(playerHSLabel != null) dialog.remove(playerHSLabel);
    }
}
