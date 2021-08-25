package Dialogs;

import main.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class BriefingAndDebriefing implements WindowListener {
    private final MainWindow window;

    public BriefingAndDebriefing(MainWindow window) {
        this.window = window;
    }

    public void render() {


        briefingDialog.setSize(500,880);
        briefingDialog.setLocationRelativeTo(null);
        briefingDialog.setResizable(false);
        briefingDialog.setLayout(new GridBagLayout());
        MBborder = BorderFactory.createTitledBorder("Select briefing map");
        MDborder = BorderFactory.createTitledBorder("Select debriefing map");
        MBpanel = new JPanel(new GridBagLayout());
        MDpanel = new JPanel(new GridBagLayout());

        if(savedContent == 0) {
            mbList = new JComboBox<String>(mbMaps);
            mbList.setSelectedIndex(selectedMB);
            mbList.addActionListener(this);
            dbList = new JComboBox<String>(dbMaps);
            dbList.setSelectedIndex(selectedDB);
            dbList.addActionListener(this);
        }else if(savedContent == 1) {
            mbList = new JComboBox<String>(mbMapsXp);
            mbList.setSelectedIndex(selectedMB);
            mbList.addActionListener(this);
            dbList = new JComboBox<String>(dbMapsXp);
            dbList.setSelectedIndex(selectedDB);
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
        MBsizeXField.setText(Integer.toString(MBsizeX));
        MBsizeYField.setText(Integer.toString(MBsizeY));
        MDsizeXField.setText(Integer.toString(MDsizeX));
        MDsizeYField.setText(Integer.toString(MDsizeY));
        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 0;

        MBpanel.setBorder(MBborder);
        //MBpanel.setSize(700, 300);
        MDpanel.setBorder(MDborder);
        //MDpanel.setSize(500, 300);

        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 0;
        briefingConstraints.gridwidth = 2;
        briefingConstraints.insets = new Insets(10,2,4,2);
        MBpanel.add(mbList, briefingConstraints);
        briefingConstraints.gridwidth = 1;
        briefingConstraints.gridy = 1;
        MBpanel.add(MBlabelX, briefingConstraints);
        briefingConstraints.gridx = 1;
        MBpanel.add(MBsizeXField, briefingConstraints);
        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 2;
        MBpanel.add(MBlabelY, briefingConstraints);
        briefingConstraints.gridx = 1;
        MBpanel.add(MBsizeYField, briefingConstraints);
        briefingConstraints.gridx = 2;
        briefingConstraints.gridy = 0;
        briefingConstraints.gridheight = 4;
        try {
            if(savedContent == 0) {
                mbMap[selectedMB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+mbMaps[selectedMB]+".png"));
                if(mbMap[selectedMB].getWidth() > 400 || mbMap[selectedMB].getHeight() > 400)
                    mbMap[selectedMB] = resizeMap((int)(mbMap[selectedMB].getWidth() / 1.5), (int)(mbMap[selectedMB].getHeight() / 1.5), mbMap[selectedMB]);

                mbMapframe[selectedMB] = new JLabel(new ImageIcon(mbMap[selectedMB]));
                MBpanel.add(mbMapframe[selectedMB], briefingConstraints);
            }else if(savedContent == 1) {
                mbMapXp[selectedMB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+mbMapsXp[selectedMB]+".png"));
                if(mbMapXp[selectedMB].getWidth() > 400 || mbMapXp[selectedMB].getHeight() > 400)
                    mbMapXp[selectedMB] = resizeMap((int)(mbMapXp[selectedMB].getWidth() / 1.5), (int)(mbMapXp[selectedMB].getHeight() / 1.5), mbMapXp[selectedMB]);

                mbMapframeXp[selectedMB] = new JLabel(new ImageIcon(mbMapXp[selectedMB]));
                MBpanel.add(mbMapframeXp[selectedMB], briefingConstraints);
            }
        }catch(IOException ex) {
            System.out.println("An error occured while loading a briefing map");
        }
        briefingConstraints.gridheight = 1;
        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 1;
        briefingDialog.add(MBpanel, briefingConstraints);

        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 0;
        briefingConstraints.gridwidth = 2;
        MDpanel.add(dbList, briefingConstraints);
        briefingConstraints.gridwidth = 1;
        briefingConstraints.gridy = 1;
        MDpanel.add(MDlabelX, briefingConstraints);
        briefingConstraints.gridx = 1;
        MDpanel.add(MDsizeXField, briefingConstraints);
        briefingConstraints.gridy = 2;
        briefingConstraints.gridx = 0;
        MDpanel.add(MDlabelY, briefingConstraints);
        briefingConstraints.gridx = 1;
        MDpanel.add(MDsizeYField, briefingConstraints);
        briefingConstraints.gridx = 2;
        briefingConstraints.gridy = 0;
        briefingConstraints.gridheight = 4;
        try {
            if(savedContent == 0) {
                dbMap[selectedDB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+dbMaps[selectedDB]+".png"));
                if(dbMap[selectedDB].getWidth() > 400 || dbMap[selectedDB].getHeight() > 400) {
                    dbMap[selectedDB] = resizeMap((int)(dbMap[selectedDB].getWidth() / 1.5), (int)(dbMap[selectedDB].getHeight() / 1.5), dbMap[selectedDB]);
                }
                dbMapframe[selectedDB] = new JLabel(new ImageIcon(dbMap[selectedDB]));
                MDpanel.add(dbMapframe[selectedDB], briefingConstraints);
            }else if(savedContent == 1) {
                dbMapXp[selectedDB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+dbMapsXp[selectedDB]+".png"));
                if(dbMapXp[selectedDB].getWidth() > 400 || dbMapXp[selectedDB].getHeight() > 400) {
                    dbMapXp[selectedDB] = resizeMap((int)(dbMapXp[selectedDB].getWidth() / 1.5), (int)(dbMapXp[selectedDB].getHeight() / 1.5), dbMapXp[selectedDB]);
                }
                dbMapframeXp[selectedDB] = new JLabel(new ImageIcon(dbMapXp[selectedDB]));
                MDpanel.add(dbMapframeXp[selectedDB], briefingConstraints);
            }

        }catch(IOException ex) {
            System.out.println("An error occured while loading a debriefing map");
        }
        briefingConstraints.gridheight = 1;
        briefingConstraints.gridx = 0;
        briefingConstraints.gridy = 2;
        briefingDialog.add(MDpanel, briefingConstraints);
        briefingConstraints.gridy = 3;
        briefingDialog.add(saveBriefing, briefingConstraints);
        briefingConstraints.gridy = 4;
        briefingDialog.add(cancelBriefing, briefingConstraints);

        briefingDialog.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        removeBriefingMapDialog();
        selectedMB = savedMB;
        selectedDB = savedDB;
        briefingDialog.setVisible(false);
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
    void removeBriefingMapDialog() {
        if(cancelBriefing != null) briefingDialog.remove(cancelBriefing);
        if(saveBriefing != null) briefingDialog.remove(saveBriefing);
        if(MDpanel != null) briefingDialog.remove(MDpanel);
        if(MBpanel != null) briefingDialog.remove(MBpanel);
    }
}
