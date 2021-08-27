package LevelIO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LevelOpener {
    private BufferedReader mapOpener;

    public void open(File f) {
        try{

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
}
