package Dialogs;

import main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class KeyboardShortcuts implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JButton closeButton;

    public KeyboardShortcuts(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Available shortcuts", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);
    }
    public void render() {
        dialog.setSize(450,490);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,1,5,1);
        constraints.anchor = GridBagConstraints.WEST;

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);

        dialog.add(new JLabel("'1' - set selected sector owner to resistance"), constraints);
        constraints.gridy = 1;
        dialog.add(new JLabel("'2' - set selected sector owner to sulgogar"), constraints);
        constraints.gridy = 2;
        dialog.add(new JLabel("'3' - set selected sector owner to mykonian"), constraints);
        constraints.gridy = 3;
        dialog.add(new JLabel("'4' - set selected sector owner to taerkasten"), constraints);
        constraints.gridy = 4;
        dialog.add(new JLabel("'5' - set selected sector owner to black sect"), constraints);
        constraints.gridy = 5;
        dialog.add(new JLabel("'6' - set selected sector owner to ghorkov"), constraints);
        constraints.gridy = 6;
        dialog.add(new JLabel("'7' - set selected sector owner to neutral(for buildings)"), constraints);
        constraints.gridy = 7;
        dialog.add(new JLabel("'0' - set selected sector owner to neutral"), constraints);
        constraints.gridy = 8;
        dialog.add(new JLabel("'+' - increase selected sector height by 1"), constraints);
        constraints.gridy = 9;
        dialog.add(new JLabel("'-' - decrease selected sector height by 1"), constraints);
        constraints.gridy = 10;
        dialog.add(new JLabel("'.' - change selected sector appearance(typ_map) to next one"), constraints);
        constraints.gridy = 11;
        dialog.add(new JLabel("',' - change selected sector appearance(typ_map) to previous one"), constraints);
        constraints.gridy = 12;
        dialog.add(new JLabel("'H' - change selected sector/sectors height"), constraints);
        constraints.gridy = 13;
        dialog.add(new JLabel("'T' - change selected sector appearance(typ_map)"), constraints);
        constraints.gridy = 14;
        dialog.add(new JLabel("'Del' - clear selected sector"), constraints);
        constraints.gridy = 15;
        dialog.add(new JLabel("'Ctrl + S' - save current level"), constraints);
        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        dialog.add(closeButton, constraints);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton) {
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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
}
