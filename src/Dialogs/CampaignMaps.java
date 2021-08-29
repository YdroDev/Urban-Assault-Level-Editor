package Dialogs;

import main.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CampaignMaps implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private BufferedImage originalImg, MDghorImg, MDtaerImg;
    private JLabel originalLabel, MDghorLabel, MDtaerLabel;
    private JPanel originalMap, MDghorMap, MDtaerMap;
    private JTabbedPane mapsTabs;
    private JPanel mapsPanel;
    private JButton mapsClose;

    public CampaignMaps(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Campaign maps");
        dialog.addWindowListener(this);
    }
    public void render() {
        removeDialog();
        dialog.setSize(670, 580);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);

        originalLabel = new JLabel();
        MDghorLabel = new JLabel();
        MDtaerLabel = new JLabel();
        originalMap = new JPanel();
        MDghorMap = new JPanel();
        MDtaerMap = new JPanel();
        mapsTabs = new JTabbedPane();
        mapsPanel = new JPanel(new GridBagLayout());
        mapsClose = new JButton("Close");
        try {
            originalImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/originalMap.jpg"));
            originalLabel.setIcon(new ImageIcon(originalImg));
            MDghorImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/MDGhorMap.jpg"));
            MDghorLabel.setIcon(new ImageIcon(MDghorImg));
            MDtaerImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/MDTaerMap.jpg"));
            MDtaerLabel.setIcon(new ImageIcon(MDtaerImg));
        }catch(IOException ex) {
            System.out.println("campaign map could not load");
        }

        mapsClose.addActionListener(this);
        originalMap.add(originalLabel);
        MDghorMap.add(MDghorLabel);
        MDtaerMap.add(MDtaerLabel);
        mapsTabs.addTab("Original levels", originalMap);
        mapsTabs.addTab("Metropolis Dawn levels(Ghorkov)", MDghorMap);
        mapsTabs.addTab("Metropolis Dawn levels(Taerkasten)", MDtaerMap);
        constraints.gridx = 0;
        constraints.gridy = 0;
        mapsPanel.add(mapsTabs, constraints);
        constraints.gridy = 1;
        mapsPanel.add(mapsClose, constraints);

        dialog.add(mapsPanel);
        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mapsClose) {
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
        if(mapsClose != null) dialog.remove(mapsClose);
    }
}
