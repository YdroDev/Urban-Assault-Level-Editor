package LevelIO;

import main.EditorState;

import javax.swing.*;
import java.io.*;

public abstract class LevelOpener {
    protected BufferedReader mapOpener;
    protected String stringData;
    protected boolean playerReloadConst = true;

    public void open(File f) {
        try{
            mapOpener = new BufferedReader(new FileReader(f));
            stringData = "";

            do {
                checkGameContentBasedOnHSvehicleID();
                handleTypMap();
                handleOwnMap();
                handleHgtMap();
                handleBlgMap();
                stringData = mapOpener.readLine();
            }while(stringData != null);

            mapOpener = new BufferedReader(new FileReader(f));
            handleDescription();
            while(stringData != null) {
                handleLevelParameters();
                handleBriefingMaps();
                handleBeamGates();
                handleHostStations();
                handleBombs();
                handlePredefinedSquads();
                handleModifications();
                handlePrototypeEnabling();
                handleTechUpgrades();
                stringData = mapOpener.readLine();
            }
            mapOpener.close();
            EditorState.isSaved = true;
        }catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Provided file doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"An unknown error occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    abstract void checkGameContentBasedOnHSvehicleID();
    abstract void handleTypMap();
    abstract void handleOwnMap();
    abstract void handleHgtMap();
    abstract void handleBlgMap();
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
}
