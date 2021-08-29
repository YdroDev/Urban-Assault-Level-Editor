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

    private JLabel label;
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
        label = new JLabel("<html><p>'1' - set selected sector owner to resistance</p>" +
                "<p>'2' - set selected sector owner to sulgogar</p>" +
                "<p>'3' - set selected sector owner to mykonian</p>" +
                "<p>'4' - set selected sector owner to taerkasten</p>" +
                "<p>'5' - set selected sector owner to black sect</p>" +
                "<p>'6' - set selected sector owner to ghorkov</p>" +
                "<p>'7' - set selected sector owner to neutral(for buildings)</p>" +
                "<p>'0' - set selected sector owner to neutral</p>" +
                "<p>'+' - increase selected sector height by 1</p>" +
                "<p>'-' - decrease selected sector height by 1</p>" +
                "<p>'.' - change selected sector appearance(typ_map) to next one</p>" +
                "<p>',' - change selected sector appearance(typ_map) to previous one</p>" +
                "<p>'H' - change selected sector/sectors height</p>" +
                "<p>'T' - change selected sector appearance(typ_map)</p>" +
                "<p>'Del' - clear selected sector</p>" +
                "<p>'Ctrl + S' - save current level</p></html>");
        constraints.gridy = 0;
        dialog.add(label, constraints);
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        dialog.add(closeButton, constraints);
        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton) {
            dialog.setVisible(false);
            removeDialog();
        }
    }
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            dialog.setVisible(false);
            removeDialog();
        }
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    void removeDialog() {
        if(closeButton != null) dialog.remove(closeButton);
        if(label != null) dialog.remove(label);
    }
}
