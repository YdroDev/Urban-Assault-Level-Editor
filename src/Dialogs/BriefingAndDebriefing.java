package Dialogs;

import main.EditorState;
import main.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BriefingAndDebriefing implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private Border MBborder, MDborder;
    private JPanel MBpanel, MDpanel;
    private JTextField MBsizeXField;
    private JTextField MBsizeYField;
    private JTextField MDsizeXField;
    private JTextField MDsizeYField;
    private JLabel MBlabelX;
    private JLabel MBlabelY;
    private JLabel MDlabelX;
    private JLabel MDlabelY;
    private JComboBox<String> mbList;
    private JComboBox<String> dbList;
    private final String[] mbMaps = new String[74];;
    private final String[] dbMaps = new String[114];
    private final String[] mbMapsXp = new String[31];
    private final String[] dbMapsXp = new String[71];
    private final BufferedImage[] mbMap = new BufferedImage[74];
    private final BufferedImage[] mbMapXp = new BufferedImage[31];
    private final BufferedImage[] dbMap = new BufferedImage[114];
    private final BufferedImage[] dbMapXp = new BufferedImage[71];
    private final JLabel[] mbMapframe = new JLabel[74];
    private final JLabel[] dbMapframe = new JLabel[114];
    private final JLabel[] mbMapframeXp = new JLabel[31];
    private final JLabel[] dbMapframeXp = new JLabel[71];

    private int selectedMBIndex = 0;
    private int selectedDBIndex = 0;
    private int savedMBIndex = 0;
    private int savedDBIndex = 0;

    private JButton saveBriefing;
    private JButton cancelBriefing;

    public BriefingAndDebriefing(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Set briefing/debriefing map", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

        for(int i = 0, j= 0; i < 74; i++, j++) {
            if(j == 0) {
                mbMaps[i] = "mb";
            }
            if(j > 0 && j < 9) {
                mbMaps[i] = "mb_0" + j;
            }
            if(j == 9) j = 10;
            if(j > 9 && j < 24) {
                mbMaps[i] = "mb_" + j;
            }
            if(j == 24) j = 25;
            if(j > 24 && j < 27) {
                mbMaps[i] = "mb_" + j;
            }
            if(j == 27) j = 28;
            if(j > 27 && j < 49) {
                mbMaps[i] = "mb_" + j;
            }
            if(j == 49)j = 50;
            if(j > 49 && j < 59) {
                mbMaps[i] = "mb_" + j;
            }
            if(j == 59) j = 60;
            if(j > 59 && j < 76) {
                mbMaps[i] = "mb_" + j;
            }
            if(j == 76) j = 77;
            if(j > 76) {
                mbMaps[i] = "mb_" + j;
            }
        }

        for(int i = 0, j = 1; i < 114; i++, j++) {
            if(j < 6) {
                dbMaps[i] = "db_0" + j;
            }
            if(j == 6) j = 10;
            if(j > 9 && j < 13) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 13) j = 15;
            if(j == 15) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 16) j = 20;
            if(j > 19 && j < 24) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 24) j = 25;
            if(j > 24 && j < 27) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 27) j = 30;
            if(j > 29 && j < 35) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 35) j = 40;
            if(j > 39 && j < 45) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 45) j = 50;
            if(j > 49 && j < 55) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 55) j = 60;
            if(j > 59 && j < 65) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 65) j = 66;
            if(j == 66) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 67) j = 70;
            if(j > 69 && j < 76) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 76) j = 80;
            if(j > 79 && j < 97) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 97) j = 100;
            if(j > 99 && j < 136) {
                dbMaps[i] = "db_" + j;
            }
            if(j == 136) j = 140;
            if(j > 139) {
                dbMaps[i] = "db_" + j;
            }
        }

        for(int i = 0, j = 0; i < 31; i++, j++) {
            if(j == 0) j = 6;
            if(j > 5 && j < 9)
                mbMapsXp[i] = "mb_0"+j;
            if(j == 9) j = 13;
            if(j > 12 && j < 15)
                mbMapsXp[i] = "mb_"+j;
            if(j == 15) j = 16;
            if(j > 15 && j < 20)
                mbMapsXp[i] = "mb_"+j;
            if(j == 20) j = 28;
            if(j > 27 && j < 30)
                mbMapsXp[i] = "mb_"+j;
            if(j == 30) j = 35;
            if(j > 34 && j < 40)
                mbMapsXp[i] = "mb_"+j;
            if(j == 40) j = 45;
            if(j > 44 && j < 49)
                mbMapsXp[i] = "mb_"+j;
            if(j == 49) j = 55;
            if(j > 54 && j < 59)
                mbMapsXp[i] = "mb_"+j;
            if(j == 59) j = 65;
            if(j == 65)
                mbMapsXp[i] = "mb_"+j;
            if(j == 66) j = 67;
            if(j > 66 && j < 70)
                mbMapsXp[i] = "mb_"+j;
            if(j == 70) j = 77;
            if(j > 76)
                mbMapsXp[i] = "mb_"+j;
        }

        for(int i = 0, j = 0; i < 71; i++, j++) {
            if(j == 0) j = 80;
            if(j > 79 && j < 97)
                dbMapsXp[i] = "db_"+j;
            if(j == 97) j = 100;
            if(j > 99 && j < 121)
                dbMapsXp[i] = "db_"+j;
            if(j == 121) j = 122;
            if(j > 121 && j < 136)
                dbMapsXp[i] = "db_"+j;
            if(j == 136) j = 140;
            if(j > 139)
                dbMapsXp[i] = "db_"+j;
        }
    }

    public void render() {
        removeBriefingMapDialog();

        dialog.setSize(500,880);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        MBborder = BorderFactory.createTitledBorder("Select briefing map");
        MDborder = BorderFactory.createTitledBorder("Select debriefing map");
        MBpanel = new JPanel(new GridBagLayout());
        MDpanel = new JPanel(new GridBagLayout());

        if(EditorState.gameContent == 0) {
            mbList = new JComboBox<String>(mbMaps);
            mbList.setSelectedIndex(selectedMBIndex);
            mbList.addActionListener(this);
            dbList = new JComboBox<String>(dbMaps);
            dbList.setSelectedIndex(selectedDBIndex);
            dbList.addActionListener(this);
        }else if(EditorState.gameContent == 1) {
            mbList = new JComboBox<String>(mbMapsXp);
            mbList.setSelectedIndex(selectedMBIndex);
            mbList.addActionListener(this);
            dbList = new JComboBox<String>(dbMapsXp);
            dbList.setSelectedIndex(selectedDBIndex);
            dbList.addActionListener(this);
        }
        MBlabelX = new JLabel("Size x:");
        MBlabelY = new JLabel("Size y:");
        MDlabelX = new JLabel("Size x:");
        MDlabelY = new JLabel("Size y:");
        MBsizeXField = new JTextField(4);
        MBsizeYField = new JTextField(4);
        MDsizeXField = new JTextField(4);
        MDsizeYField = new JTextField(4);
        saveBriefing = new JButton("Save");
        saveBriefing.addActionListener(this);
        cancelBriefing = new JButton("Cancel");
        cancelBriefing.addActionListener(this);
        MBsizeXField.setText(Integer.toString(EditorState.mbMapSizeX));
        MBsizeYField.setText(Integer.toString(EditorState.mbMapSizeY));
        MDsizeXField.setText(Integer.toString(EditorState.dbMapSizeX));
        MDsizeYField.setText(Integer.toString(EditorState.dbMapSizeY));
        constraints.gridx = 0;
        constraints.gridy = 0;

        MBpanel.setBorder(MBborder);
        //MBpanel.setSize(700, 300);
        MDpanel.setBorder(MDborder);
        //MDpanel.setSize(500, 300);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10,2,4,2);
        MBpanel.add(mbList, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        MBpanel.add(MBlabelX, constraints);
        constraints.gridx = 1;
        MBpanel.add(MBsizeXField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        MBpanel.add(MBlabelY, constraints);
        constraints.gridx = 1;
        MBpanel.add(MBsizeYField, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 4;
        try {
            if(EditorState.gameContent == 0) {
                mbMap[selectedMBIndex] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+mbMaps[selectedMBIndex]+".png"));
                if(mbMap[selectedMBIndex].getWidth() > 400 || mbMap[selectedMBIndex].getHeight() > 400)
                    mbMap[selectedMBIndex] = resizeMap((int)(mbMap[selectedMBIndex].getWidth() / 1.5), (int)(mbMap[selectedMBIndex].getHeight() / 1.5), mbMap[selectedMBIndex]);

                mbMapframe[selectedMBIndex] = new JLabel(new ImageIcon(mbMap[selectedMBIndex]));
                MBpanel.add(mbMapframe[selectedMBIndex], constraints);
            }else if(EditorState.gameContent == 1) {
                mbMapXp[selectedMBIndex] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+mbMapsXp[selectedMBIndex]+".png"));
                if(mbMapXp[selectedMBIndex].getWidth() > 400 || mbMapXp[selectedMBIndex].getHeight() > 400)
                    mbMapXp[selectedMBIndex] = resizeMap((int)(mbMapXp[selectedMBIndex].getWidth() / 1.5), (int)(mbMapXp[selectedMBIndex].getHeight() / 1.5), mbMapXp[selectedMBIndex]);

                mbMapframeXp[selectedMBIndex] = new JLabel(new ImageIcon(mbMapXp[selectedMBIndex]));
                MBpanel.add(mbMapframeXp[selectedMBIndex], constraints);
            }
        }catch(IOException ex) {
            System.out.println("An error occured while loading a briefing map");
        }
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        dialog.add(MBpanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        MDpanel.add(dbList, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        MDpanel.add(MDlabelX, constraints);
        constraints.gridx = 1;
        MDpanel.add(MDsizeXField, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;
        MDpanel.add(MDlabelY, constraints);
        constraints.gridx = 1;
        MDpanel.add(MDsizeYField, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 4;
        try {
            if(EditorState.gameContent == 0) {
                dbMap[selectedDBIndex] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+dbMaps[selectedDBIndex]+".png"));
                if(dbMap[selectedDBIndex].getWidth() > 400 || dbMap[selectedDBIndex].getHeight() > 400) {
                    dbMap[selectedDBIndex] = resizeMap((int)(dbMap[selectedDBIndex].getWidth() / 1.5), (int)(dbMap[selectedDBIndex].getHeight() / 1.5), dbMap[selectedDBIndex]);
                }
                dbMapframe[selectedDBIndex] = new JLabel(new ImageIcon(dbMap[selectedDBIndex]));
                MDpanel.add(dbMapframe[selectedDBIndex], constraints);
            }else if(EditorState.gameContent == 1) {
                dbMapXp[selectedDBIndex] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+dbMapsXp[selectedDBIndex]+".png"));
                if(dbMapXp[selectedDBIndex].getWidth() > 400 || dbMapXp[selectedDBIndex].getHeight() > 400) {
                    dbMapXp[selectedDBIndex] = resizeMap((int)(dbMapXp[selectedDBIndex].getWidth() / 1.5), (int)(dbMapXp[selectedDBIndex].getHeight() / 1.5), dbMapXp[selectedDBIndex]);
                }
                dbMapframeXp[selectedDBIndex] = new JLabel(new ImageIcon(dbMapXp[selectedDBIndex]));
                MDpanel.add(dbMapframeXp[selectedDBIndex], constraints);
            }

        }catch(IOException ex) {
            System.out.println("An error occured while loading a debriefing map");
        }
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        dialog.add(MDpanel, constraints);
        constraints.gridy = 3;
        dialog.add(saveBriefing, constraints);
        constraints.gridy = 4;
        dialog.add(cancelBriefing, constraints);

        dialog.setVisible(true);
    }
    public void reset() {
        savedMBIndex = 0;
        savedDBIndex = 0;
        selectedMBIndex = 0;
        selectedDBIndex = 0;
        if(EditorState.gameContent == 0){
            EditorState.mbmap = mbMaps[0];
            EditorState.dbmap = dbMaps[0];
        } else if (EditorState.gameContent == 1) {
            EditorState.mbmap = mbMapsXp[0];
            EditorState.dbmap = dbMapsXp[0];
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mbList) {
            selectedMBIndex = mbList.getSelectedIndex();
            render();
        }
        if(e.getSource() == dbList) {
            selectedDBIndex = dbList.getSelectedIndex();
            render();
        }
        if(e.getSource() == saveBriefing) {
            try {
                EditorState.mbMapSizeX = Integer.parseInt(MBsizeXField.getText());
                EditorState.mbMapSizeY = Integer.parseInt(MBsizeYField.getText());
                EditorState.dbMapSizeX = Integer.parseInt(MDsizeXField.getText());
                EditorState.dbMapSizeY = Integer.parseInt(MDsizeYField.getText());
                removeBriefingMapDialog();
                savedMBIndex = selectedMBIndex;
                savedDBIndex = selectedDBIndex;
                dialog.setVisible(false);
                this.window.makeUnsaved();
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancelBriefing) {
            removeBriefingMapDialog();
            selectedMBIndex = savedMBIndex;
            selectedDBIndex = savedDBIndex;
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        removeBriefingMapDialog();
        selectedMBIndex = savedMBIndex;
        selectedDBIndex = savedDBIndex;
        dialog.setVisible(false);
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
    void removeBriefingMapDialog() {
        if(cancelBriefing != null) dialog.remove(cancelBriefing);
        if(saveBriefing != null) dialog.remove(saveBriefing);
        if(MDpanel != null) dialog.remove(MDpanel);
        if(MBpanel != null) dialog.remove(MBpanel);
    }

    public BufferedImage resizeMap(int newW, int newH, BufferedImage img) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        img = dimg;
        return img;
    }
}
