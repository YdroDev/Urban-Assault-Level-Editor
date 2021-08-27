package Dialogs;

import main.EditorState;
import main.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NewLevel implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private int savedMap;
    private JFileChooser selectSaveFile;
    private FileNameExtensionFilter ldfFilter;
    private JLabel newMapLabel;
    private JLabel horizontalLabel;
    private JLabel verticalLabel;
    private JTextField horizontalNum;
    private JTextField verticalNum;
    private JLabel borderInfo;
    private JButton confirmBut;
    private JButton cancelBut;

    public NewLevel(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Create a new map", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

        savedMap = JFileChooser.CANCEL_OPTION;
        selectSaveFile = new JFileChooser();
        ldfFilter = new FileNameExtensionFilter("Urban Assault level file (.ldf)", "ldf");
        selectSaveFile.setFileFilter(ldfFilter);
        selectSaveFile.addActionListener(this);
    }

    public void render() {
        if(!EditorState.isSaved) {
            if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
                if(savedMap == JFileChooser.APPROVE_OPTION) this.window.saveLevel(selectSaveFile.getSelectedFile());
            }
        }
        dialog.setSize(400,250);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        horizontalNum = new JTextField("", 3);
        verticalNum = new JTextField("", 3);
        borderInfo = new JLabel("(Borders will be added automatically to these values)");
        confirmBut = new JButton("OK");
        cancelBut = new JButton("Cancel");
        newMapLabel = new JLabel("Enter number of sectors for horizontal and vertical space:");
//        newMapLabel.setFont(mainFont);
        horizontalLabel = new JLabel("Horizontal sectors: ");
        verticalLabel = new JLabel("Vertical sectors: ");

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 5;
        constraints.insets = new Insets(1,0,20,0);
        dialog.add(newMapLabel, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        dialog.add(horizontalLabel,constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        dialog.add(horizontalNum,constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0,13,20,0);
        dialog.add(verticalLabel,constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(1,0,20,0);
        dialog.add(verticalNum,constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        dialog.add(borderInfo,constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        constraints.gridx = 1;
        constraints.insets = new Insets(20,1,20,10);
        confirmBut.addActionListener(this);
        dialog.add(confirmBut,constraints);
        constraints.gridwidth = 2;
        constraints.gridx = 2;
        constraints.insets = new Insets(20,1,20,0);
        cancelBut.addActionListener(this);
        dialog.add(cancelBut,constraints);


        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBut) {
            String hText = horizontalNum.getText();
            String vText = verticalNum.getText();
            int warn = JOptionPane.YES_OPTION;

            try {
                EditorState.horizontalSectors = Integer.parseInt(hText);
                EditorState.verticalSectors = Integer.parseInt(vText);
                if(EditorState.horizontalSectors > 0 && EditorState.verticalSectors > 0) {
                    if(EditorState.horizontalSectors > 64 || EditorState.verticalSectors > 64) {
                        warn = JOptionPane.showConfirmDialog(null,"Playing on level with number of sectors greater than 64 may be unstable. Do you want to proceed?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    }
                    if(warn == JOptionPane.YES_OPTION) {
                        EditorState.resetState();
                        this.window.createNewMap();
                        removeNewMapDialog();
                        dialog.setVisible(false);
                        savedMap = JFileChooser.CANCEL_OPTION;
                        this.window.makeUnsaved();
                    }
                }else {
                    JOptionPane.showMessageDialog(dialog,"Both numbers should be greater than 0", "Wrong value", JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,"Both fields must be a number", "Wrong value", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancelBut) {
            removeNewMapDialog();
            dialog.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        removeNewMapDialog();
        dialog.setVisible(false);
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
    void removeNewMapDialog() {
        dialog.remove(cancelBut);
        dialog.remove(confirmBut);
        dialog.remove(borderInfo);
        dialog.remove(verticalNum);
        dialog.remove(verticalLabel);
        dialog.remove(horizontalNum);
        dialog.remove(horizontalLabel);
        dialog.remove(newMapLabel);
    }
}
