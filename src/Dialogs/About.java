package Dialogs;

import main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class About implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JButton aboutClose;

    public About(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Urban Assault Level Editor", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);
    }
    public void render() {
        removeDialog();
        dialog.setSize(400,260);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        JLabel header = new JLabel("Urban Assault Level Editor");
        header.setFont(header.getFont().deriveFont (22f));
        dialog.add(header, constraints);
        aboutClose = new JButton("Close");
        aboutClose.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(30,1,1,1);
        constraints.gridy = 1;
        dialog.add(new JLabel("Version: 1.4.0a"), constraints);
        constraints.gridwidth = 2; // TODO update about
        constraints.insets = new Insets(10,1,1,1);
        constraints.gridy = 2;
        dialog.add(new JLabel("Compile Date: 2020-11-14"), constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 3;
        dialog.add(new JLabel("Compiled with: JRE 1.8.0_271 "), constraints);
        constraints.gridy = 4;
        constraints.gridwidth = 5;
        constraints.insets = new Insets(20,1,1,1);
        dialog.add(aboutClose, constraints);
        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == aboutClose) {
            removeDialog();
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
    public void removeDialog() {
    //TODO implement
    }
}
