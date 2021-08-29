package LevelIO;

import State.EditorState;

import javax.swing.*;
import java.io.*;

public abstract class LevelSaver {
    protected PrintWriter mapSaver;

    public void save(File f) {
        try {
            mapSaver = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            handleHeader();
            handleDescription();
            handleLevelParameters();
            handleBriefingMaps();
            handleBeamGates();
            handleHostStations();
            handleBombs();
            handlePredefinedSquads();
            handleModifications();
            handlePrototypeEnabling();
            handleTechUpgrades();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Map Dumps                                            ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println("begin_maps");
            handleTypMap();
            handleOwnMap();
            handleHgtMap();
            handleBlgMap();
            mapSaver.println("; ------------------------ ");
            mapSaver.println(";--- map dumps end here ---");
            mapSaver.println("; ------------------------ ");
            mapSaver.println("end");
            mapSaver.println();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- End Of File                                          ---");
            mapSaver.println(";------------------------------------------------------------");

            mapSaver.close();
            EditorState.isSaved = true;
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,"An I/O Error Occurred while saving the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    abstract void handleHeader();
    abstract void handleDescription();
    abstract void handleLevelParameters();
    abstract void handleBriefingMaps();
    abstract void handleBeamGates();
    abstract void handleHostStations();
    abstract void handleBombs();
    abstract void handlePredefinedSquads();
    abstract void handleModifications();
    abstract void handlePrototypeEnabling();
    abstract void handleTechUpgrades();
    abstract void handleTypMap();
    abstract void handleOwnMap();
    abstract void handleHgtMap();
    abstract void handleBlgMap();
}
