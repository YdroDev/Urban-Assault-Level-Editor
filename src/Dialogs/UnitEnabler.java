package Dialogs;

import UAstructures.CheckList;
import main.EditorState;
import main.MainWindow;
import main.UAdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class UnitEnabler implements WindowListener, ActionListener {
    private final MainWindow window;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private int enablerOwner;
    private CheckList resList;
    private CheckList ghorList;
    private CheckList taerList;
    private CheckList mykoList;
    private CheckList sulgList;
    private CheckList blackSectList;
    private CheckList trainingList;
    private CheckList specialList;
    private JCheckBox unlocker;
    private JButton resSaveEnabler;
    private JButton ghorSaveEnabler;
    private JButton taerSaveEnabler;
    private JButton mykoSaveEnabler;
    private JButton sulgSaveEnabler;
    private JButton blasecSaveEnabler;
    private JButton trainingSaveEnabler;
    private JButton cancelEnabler;

    public UnitEnabler(MainWindow window) {
        this.window = window;
        dialog = new JDialog(this.window, "Enabler", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);
    }

    public void render(int owner) {
        dialog.setSize(1200,800);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        initEnabler(owner);
        switch (owner) {
            case 1 -> dialog.setTitle("Resistance enabler");
            case 2 -> dialog.setTitle("Sulgogar enabler");
            case 3 -> dialog.setTitle("Mykonian enabler");
            case 4 -> dialog.setTitle("Taerkasten enabler");
            case 5 -> dialog.setTitle("Black sect enabler");
            case 6 -> dialog.setTitle("Ghorkov enabler");
            case 7 -> dialog.setTitle("Training enabler");
        }
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialog.add(unlocker,constraints);
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        dialog.add(resList.getGroup(), constraints);
        constraints.gridx = 1;
        dialog.add(ghorList.getGroup(), constraints);
        constraints.gridx = 2;
        dialog.add(taerList.getGroup(), constraints);
        constraints.gridx = 3;
        dialog.add(mykoList.getGroup(), constraints);
        constraints.gridx = 4;
        dialog.add(sulgList.getGroup(), constraints);
        constraints.gridx = 5;
        dialog.add(blackSectList.getGroup(), constraints);
        constraints.gridx = 6;
        dialog.add(trainingList.getGroup(), constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(80,0,0,0);
        dialog.add(specialList.getGroup(), constraints);
        constraints.insets = new Insets(20,0,0,0);

        switch (owner) {
            case 1 -> {
                resSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(resSaveEnabler, constraints);
                resSaveEnabler.addActionListener(this);
            }
            case 2 -> {
                sulgSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(sulgSaveEnabler, constraints);
                sulgSaveEnabler.addActionListener(this);
            }
            case 3 -> {
                mykoSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(mykoSaveEnabler, constraints);
                mykoSaveEnabler.addActionListener(this);
            }
            case 4 -> {
                taerSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(taerSaveEnabler, constraints);
                taerSaveEnabler.addActionListener(this);
            }
            case 5 -> {
                blasecSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(blasecSaveEnabler, constraints);
                blasecSaveEnabler.addActionListener(this);
            }
            case 6 -> {
                ghorSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(ghorSaveEnabler, constraints);
                ghorSaveEnabler.addActionListener(this);
            }
            case 7 -> {
                trainingSaveEnabler = new JButton("Save");
                constraints.gridy = 2;
                dialog.add(trainingSaveEnabler, constraints);
                trainingSaveEnabler.addActionListener(this);
            }
        }
        cancelEnabler = new JButton("Cancel");
        constraints.gridy = 3;
        constraints.insets = new Insets(20,0,0,0);
        dialog.add(cancelEnabler, constraints);
        cancelEnabler.addActionListener(this);
        constraints.insets = new Insets(0,0,0,0);

        dialog.setVisible(true);
    }
    void initEnabler(int owner) {
        resList = new CheckList("Resistance");
        ghorList = new CheckList("Ghorkov");
        taerList = new CheckList("Taerkasten");
        mykoList = new CheckList("Mykonian");
        sulgList = new CheckList("Sulgogar");
        blackSectList = new CheckList("Black Sect");
        trainingList = new CheckList("Training");
        specialList = new CheckList("Special");

        resList.addUnits(UAdata.resUnits);
        resList.addBuildings(UAdata.resBuildings);
        resList.makeList();

        ghorList.addUnits(UAdata.ghorUnits);
        ghorList.addBuildings(UAdata.ghorBuildings);
        ghorList.makeList();

        taerList.addUnits(UAdata.taerUnits);
        taerList.addBuildings(UAdata.taerBuildings);
        taerList.makeList();

        mykoList.addUnits(UAdata.mykoUnits);
        mykoList.addBuildings(UAdata.mykoBuildings);
        mykoList.makeList();

        sulgList.addUnits(UAdata.sulgUnits);
        sulgList.makeList();

        blackSectList.addBuildings(UAdata.blackSectBuildings);
        blackSectList.makeList();

        trainingList.addUnits(UAdata.trainingUnits);
        trainingList.makeList();

        specialList.addUnits(UAdata.specialUnits);
        specialList.makeList();

        unlocker = new JCheckBox("Unlock all");
        unlocker.addActionListener(this);

        switch (owner) {
            case 1 -> {
                checkUnits(EditorState.resistanceUnits);
                checkBuildings(EditorState.resistanceBuildings);
            }
            case 6 -> {
                checkUnits(EditorState.ghorkovUnits);
                checkBuildings(EditorState.ghorkovBuildings);
            }
            case 4 -> {
                checkUnits(EditorState.taerkastenUnits);
                checkBuildings(EditorState.taerkastenBuildings);
            }
            case 3 -> {
                checkUnits(EditorState.mykonianUnits);
                checkBuildings(EditorState.mykonianBuildings);
            }
            case 2 -> {
                checkUnits(EditorState.sulgogarUnits);
                checkBuildings(EditorState.sulgogarBuildings);
            }
            case 5 -> {
                checkUnits(EditorState.blackSectUnits);
                checkBuildings(EditorState.blackSectBuildings);
            }
            case 7 -> {
                checkUnits(EditorState.trainingUnits);
                checkBuildings(EditorState.trainingBuildings);
            }
        }

        this.enablerOwner = owner;
        constraints.gridx = 0;
        constraints.gridy = 0;
        lock();
    }

    void lock() {
        if(this.enablerOwner != 1) {
            resList.toggleUnitCheckBoxes(UAdata.resUnits, false);
            resList.toggleBuildingCheckBoxes(UAdata.resBuildings, false);
        }
        if(this.enablerOwner != 6) {
            ghorList.toggleUnitCheckBoxes(UAdata.ghorUnits, false);
            ghorList.toggleBuildingCheckBoxes(UAdata.ghorBuildings, false);
        }
        if(this.enablerOwner != 4) {
            taerList.toggleUnitCheckBoxes(UAdata.taerUnits, false);
            taerList.toggleBuildingCheckBoxes(UAdata.taerBuildings, false);
        }
        if(this.enablerOwner != 3) {
            mykoList.toggleUnitCheckBoxes(UAdata.mykoUnits, false);
            mykoList.toggleBuildingCheckBoxes(UAdata.mykoBuildings, false);
        }
        if(this.enablerOwner != 2) {
            sulgList.toggleUnitCheckBoxes(UAdata.sulgUnits, false);
        }
        if(this.enablerOwner != 5) {
            blackSectList.toggleBuildingCheckBoxes(UAdata.blackSectBuildings, false);
        }
        if(this.enablerOwner != 7) {
            trainingList.toggleUnitCheckBoxes(UAdata.trainingUnits, false);
        }
        specialList.toggleUnitCheckBoxes(UAdata.specialUnits, false);
    }


    void unlock() {
        if(this.enablerOwner != 1) {
            resList.toggleUnitCheckBoxes(UAdata.resUnits, true);
            resList.toggleBuildingCheckBoxes(UAdata.resBuildings, true);
        }
        if(this.enablerOwner != 6) {
            ghorList.toggleUnitCheckBoxes(UAdata.ghorUnits, true);
            ghorList.toggleBuildingCheckBoxes(UAdata.ghorBuildings, true);
        }
        if(this.enablerOwner != 4) {
            taerList.toggleUnitCheckBoxes(UAdata.taerUnits, true);
            taerList.toggleBuildingCheckBoxes(UAdata.taerBuildings, true);
        }
        if(this.enablerOwner != 3) {
            mykoList.toggleUnitCheckBoxes(UAdata.mykoUnits, true);
            mykoList.toggleBuildingCheckBoxes(UAdata.mykoBuildings, true);
        }
        if(this.enablerOwner != 2) {
            sulgList.toggleUnitCheckBoxes(UAdata.sulgUnits, true);
        }
        if(this.enablerOwner != 5) {
            blackSectList.toggleBuildingCheckBoxes(UAdata.blackSectBuildings, true);
        }
        if(this.enablerOwner != 7) {
            trainingList.toggleUnitCheckBoxes(UAdata.trainingUnits, true);
        }
        specialList.toggleUnitCheckBoxes(UAdata.specialUnits, true);
    }
    void checkUnits(ArrayList<Integer> hsUnits) {
        for(JCheckBox resUnitBox : resList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(resUnitBox.getText()))) {
                resUnitBox.setSelected(true);
            }
        }
        for(JCheckBox ghorUnitBox : ghorList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(ghorUnitBox.getText()))) {
                ghorUnitBox.setSelected(true);
            }
        }
        for(JCheckBox taerUnitBox : taerList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(taerUnitBox.getText()))) {
                taerUnitBox.setSelected(true);
            }
        }
        for(JCheckBox mykoUnitBox : mykoList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(mykoUnitBox.getText()))) {
                mykoUnitBox.setSelected(true);
            }
        }
        for(JCheckBox sulgUnitBox : sulgList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(sulgUnitBox.getText()))) {
                sulgUnitBox.setSelected(true);
            }
        }
        for(JCheckBox trainingUnitBox : trainingList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(trainingUnitBox.getText()))) {
                trainingUnitBox.setSelected(true);
            }
        }
        for(JCheckBox specialUnitBox : specialList.getUnitCheckBoxes()) {
            if(hsUnits.contains(UAdata.getUnitIDfromName(specialUnitBox.getText()))) {
                specialUnitBox.setSelected(true);
            }
        }
    }

    void checkBuildings(ArrayList<Integer> hsBuildings) {
        for(JCheckBox resBuildingBox : resList.getBuildingCheckBoxes()) {
            if(hsBuildings.contains(UAdata.getBuildingIDfromName(resBuildingBox.getText()))) {
                resBuildingBox.setSelected(true);
            }
        }
        for(JCheckBox ghorBuildingBox : ghorList.getBuildingCheckBoxes()) {
            if(hsBuildings.contains(UAdata.getBuildingIDfromName(ghorBuildingBox.getText()))) {
                ghorBuildingBox.setSelected(true);
            }
        }
        for(JCheckBox taerBuildingBox : taerList.getBuildingCheckBoxes()) {
            if(hsBuildings.contains(UAdata.getBuildingIDfromName(taerBuildingBox.getText()))) {
                taerBuildingBox.setSelected(true);
            }
        }
        for(JCheckBox mykoBuildingBox : mykoList.getBuildingCheckBoxes()) {
            if(hsBuildings.contains(UAdata.getBuildingIDfromName(mykoBuildingBox.getText()))) {
                mykoBuildingBox.setSelected(true);
            }
        }
        for(JCheckBox blackSectBuildingBox : blackSectList.getBuildingCheckBoxes()) {
            if(hsBuildings.contains(UAdata.getBuildingIDfromName(blackSectBuildingBox.getText()))) {
                blackSectBuildingBox.setSelected(true);
            }
        }
    }
    void enableUnits(ArrayList<Integer> hsUnits) {
        for(JCheckBox resUnitBox : resList.getUnitCheckBoxes()) {
            if(resUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(resUnitBox.getText()));
        }
        for(JCheckBox ghorUnitBox : ghorList.getUnitCheckBoxes()) {
            if(ghorUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(ghorUnitBox.getText()));
        }
        for(JCheckBox taerUnitBox : taerList.getUnitCheckBoxes()) {
            if(taerUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(taerUnitBox.getText()));
        }
        for(JCheckBox mykoUnitBox : mykoList.getUnitCheckBoxes()) {
            if(mykoUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(mykoUnitBox.getText()));
        }
        for(JCheckBox sulgUnitBox : sulgList.getUnitCheckBoxes()) {
            if(sulgUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(sulgUnitBox.getText()));
        }
        for(JCheckBox trainingUnitBox : trainingList.getUnitCheckBoxes()) {
            if(trainingUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(trainingUnitBox.getText()));
        }
        for(JCheckBox specialUnitBox : specialList.getUnitCheckBoxes()) {
            if(specialUnitBox.isSelected())
                hsUnits.add(UAdata.getUnitIDfromName(specialUnitBox.getText()));
        }
    }

    void enableBuildings(ArrayList<Integer> hsBuildings) {
        for(JCheckBox resBuildingBox : resList.getBuildingCheckBoxes()) {
            if(resBuildingBox.isSelected())
                hsBuildings.add(UAdata.getBuildingIDfromName(resBuildingBox.getText()));
        }
        for(JCheckBox ghorBuildingBox : ghorList.getBuildingCheckBoxes()) {
            if(ghorBuildingBox.isSelected())
                hsBuildings.add(UAdata.getBuildingIDfromName(ghorBuildingBox.getText()));
        }
        for(JCheckBox taerBuildingBox : taerList.getBuildingCheckBoxes()) {
            if(taerBuildingBox.isSelected())
                hsBuildings.add(UAdata.getBuildingIDfromName(taerBuildingBox.getText()));
        }
        for(JCheckBox mykoBuildingBox : mykoList.getBuildingCheckBoxes()) {
            if(mykoBuildingBox.isSelected())
                hsBuildings.add(UAdata.getBuildingIDfromName(mykoBuildingBox.getText()));
        }
        for(JCheckBox blackSectBuildingBox : blackSectList.getBuildingCheckBoxes()) {
            if(blackSectBuildingBox.isSelected())
                hsBuildings.add(UAdata.getBuildingIDfromName(blackSectBuildingBox.getText()));
        }
    }
    void removeEnablerDialog() {
        if(cancelEnabler != null) dialog.remove(cancelEnabler);
        if(trainingSaveEnabler != null) dialog.remove(trainingSaveEnabler);
        if(ghorSaveEnabler != null) dialog.remove(ghorSaveEnabler);
        if(blasecSaveEnabler != null) dialog.remove(blasecSaveEnabler);
        if(taerSaveEnabler != null) dialog.remove(taerSaveEnabler);
        if(mykoSaveEnabler != null) dialog.remove(mykoSaveEnabler);
        if(sulgSaveEnabler != null) dialog.remove(sulgSaveEnabler);
        if(resSaveEnabler != null) dialog.remove(resSaveEnabler);
        if(specialList.getGroup() != null) dialog.remove(specialList.getGroup());
        if(trainingList.getGroup() != null) dialog.remove(trainingList.getGroup());
        if(blackSectList.getGroup() != null) dialog.remove(blackSectList.getGroup());
        if(sulgList.getGroup() != null) dialog.remove(sulgList.getGroup());
        if(mykoList.getGroup() != null) dialog.remove(mykoList.getGroup());
        if(taerList.getGroup() != null) dialog.remove(taerList.getGroup());
        if(ghorList.getGroup() != null) dialog.remove(ghorList.getGroup());
        if(resList.getGroup() != null) dialog.remove(resList.getGroup());
        if(unlocker != null) dialog.remove(unlocker);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resSaveEnabler) {
            EditorState.resistanceUnits.clear();
            EditorState.resistanceBuildings.clear();

            enableUnits(EditorState.resistanceUnits);
            enableBuildings(EditorState.resistanceBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == ghorSaveEnabler) {
            EditorState.ghorkovUnits.clear();
            EditorState.ghorkovBuildings.clear();

            enableUnits(EditorState.ghorkovUnits);
            enableBuildings(EditorState.ghorkovBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == taerSaveEnabler) {
            EditorState.taerkastenUnits.clear();
            EditorState.taerkastenBuildings.clear();

            enableUnits(EditorState.taerkastenUnits);
            enableBuildings(EditorState.taerkastenBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == mykoSaveEnabler) {
            EditorState.mykonianUnits.clear();
            EditorState.mykonianBuildings.clear();

            enableUnits(EditorState.mykonianUnits);
            enableBuildings(EditorState.mykonianBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == sulgSaveEnabler) {
            EditorState.sulgogarUnits.clear();
            EditorState.sulgogarBuildings.clear();

            enableUnits(EditorState.sulgogarUnits);
            enableBuildings(EditorState.sulgogarBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == blasecSaveEnabler) {
            EditorState.blackSectUnits.clear();
            EditorState.blackSectBuildings.clear();

            enableUnits(EditorState.blackSectUnits);
            enableBuildings(EditorState.blackSectBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }
        if(e.getSource() == trainingSaveEnabler) {
            EditorState.trainingUnits.clear();
            EditorState.trainingBuildings.clear();

            enableUnits(EditorState.trainingUnits);
            enableBuildings(EditorState.trainingBuildings);

            removeEnablerDialog();
            dialog.setVisible(false);
            window.makeUnsaved();
        }

        if(e.getSource() == cancelEnabler) {
            removeEnablerDialog();
            dialog.setVisible(false);
        }
        if(e.getSource() == unlocker) {
            if(unlocker.isSelected()) unlock();
            else lock();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            removeEnablerDialog();
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
}
