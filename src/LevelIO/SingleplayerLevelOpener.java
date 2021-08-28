package LevelIO;

import UAstructures.*;
import main.EditorState;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class SingleplayerLevelOpener extends LevelOpener{

    void handleTypMap() {
        if(stringData.contains("typ_map")) {
            try{
                stringData = mapOpener.readLine();
                stringData = stringData.trim();
                for(int i = 0;i < stringData.length(); i++) {
                    if(stringData.charAt(i) == ' ') {
                        EditorState.horizontalSectors = Integer.parseInt(stringData.substring(0,i));
                        stringData = stringData.substring(i);
                        stringData = stringData.trim();

                        EditorState.verticalSectors = Integer.parseInt(stringData);
                    }
                }
                EditorState.horizontalSectors -= 2;
                EditorState.verticalSectors -= 2;
                mapOpener.readLine();

                int hex;
                for(int i = 0; i < EditorState.verticalSectors; i++) {
                    stringData = mapOpener.readLine();
                    stringData = stringData.trim();
                    stringData = stringData.substring(3);
                    for(int j = 0; j < EditorState.horizontalSectors; j++) {
                        try {
                            hex = Integer.parseInt(stringData.substring(0, 3).trim(), 16 );
                            EditorState.typ_map.add(hex);
                        }catch(NumberFormatException ex) {
                            EditorState.typ_map.add(0);
                        }
                        stringData = stringData.substring(3).trim();
                    }
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleOwnMap() {
        if(stringData.contains("own_map")) {
            try {
                stringData = mapOpener.readLine();
                stringData = mapOpener.readLine();
                for (int i = 0; i < EditorState.verticalSectors; i++) {
                    stringData = mapOpener.readLine();
                    stringData = stringData.trim();
                    stringData = stringData.substring(3);
                    for (int j = 0; j < EditorState.horizontalSectors; j++) {
                        EditorState.own_map.add(Integer.parseInt(stringData.substring(0, 3).trim(), 16));
                        stringData = stringData.substring(3).trim();
                    }
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleHgtMap() {
        if (stringData.contains("hgt_map")) {
            try {
                stringData = mapOpener.readLine();
                for (int i = 0; i < EditorState.verticalSectors + 2; i++) {
                    stringData = mapOpener.readLine();
                    stringData = stringData.trim();

                    for (int j = 0; j < EditorState.horizontalSectors + 1; j++) {
                        EditorState.hgt_map.add(Integer.parseInt(stringData.substring(0, 3).trim(), 16));
                        stringData = stringData.substring(3).trim();
                    }
                    EditorState.hgt_map.add(Integer.parseInt(stringData.substring(0, 2).trim(), 16));
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleBlgMap() {
        if(stringData.contains("blg_map")) {
            try {
                stringData = mapOpener.readLine();
                stringData = mapOpener.readLine();
                for (int i = 0; i < EditorState.verticalSectors; i++) {
                    stringData = mapOpener.readLine();
                    stringData = stringData.trim();
                    stringData = stringData.substring(3);
                    for (int j = 0; j < EditorState.horizontalSectors; j++) {
                        EditorState.blg_map.add(Integer.parseInt(stringData.substring(0, 3).trim(), 16));
                        stringData = stringData.substring(3).trim();
                    }
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleDescription() {
        try{
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            StringBuilder descString = new StringBuilder();
            while(stringData.length() > 0 && stringData.charAt(0) == ';') {
                descString.append(stringData.substring(1));
                descString.append("\n");
                stringData = mapOpener.readLine();
            }
            EditorState.description = descString.toString();
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void handleLevelParameters() {
        if(stringData.contains("begin_level")) {
            try {
                while (!stringData.trim().contentEquals("end")) {
                    stringData = mapOpener.readLine();

                    if (stringData.contains("set")) {
                        stringData = stringData.trim();
                        stringData = stringData.replace("set", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if (stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if ((!stringData.equals(""))) {
                            EditorState.set = Integer.parseInt(stringData);
                        }
                    }

                    if (stringData.contains("sky")) {
                        stringData = stringData.trim();
                        stringData = stringData.replace("sky", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if (stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if ((!stringData.equals(""))) {
                            stringData = stringData.replace("objects/", "");
                            stringData = stringData.replace(".base", "");
                            stringData = stringData.replace(".bas", "");
                            EditorState.sky = stringData;
                        }
                    }

                    if (stringData.contains("event_loop")) {
                        stringData = stringData.trim();
                        stringData = stringData.replace("event_loop", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if (stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if ((!stringData.equals(""))) {
                            EditorState.eventLoop = Integer.parseInt(stringData);
                        }
                    }

                    if (stringData.contains("ambiencetrack")) {
                        stringData = stringData.trim();
                        stringData = stringData.replace("ambiencetrack", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if (stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if ((!stringData.equals(""))) {
                            EditorState.music = Integer.parseInt(stringData.substring(0, 1));
                            if (stringData.length() > 1) {
                                stringData = stringData.substring(2);
                                EditorState.min_break = Integer.parseInt(stringData.substring(0, stringData.indexOf('_')));
                                stringData = stringData.substring(stringData.indexOf('_') + 1);
                                EditorState.max_break = Integer.parseInt(stringData);
                            }
                        }
                    }

                    if (stringData.contains("movie")) {
                        stringData = stringData.trim();
                        stringData = stringData.replace("movie", "");
                        stringData = stringData.trim();
                        stringData = stringData.substring(1);
                        stringData = stringData.trim();
                        if (stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        stringData = stringData.replace("mov:", "");
                        stringData = stringData.replace(".mpg", "");
                        EditorState.movie = stringData;
                    }
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleBriefingMaps() {
        try{
            if(stringData.contains("begin_mbmap")) {

                while(!stringData.trim().contentEquals("end")) {
                    if(stringData.contains("name")) {
                        stringData = stringData.replace("name", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        stringData = stringData.toLowerCase();
                        stringData = stringData.replace(".iff", "");
                        stringData = stringData.replace(".ilb", "");
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if((!stringData.equals(""))) {
                            EditorState.mbmap = stringData;
                        }
                    }
                    if(stringData.contains("size_x")) {
                        stringData = stringData.replace("size_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) EditorState.mbMapSizeX = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("size_y")) {
                        stringData = stringData.replace("size_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) EditorState.mbMapSizeY = Integer.parseInt(stringData);
                    }
                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
            }
            if(stringData.contains("begin_dbmap")) {
                while(!stringData.trim().contentEquals("end")) {
                    if(stringData.contains("name")) {
                        stringData = stringData.replace("name", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        stringData = stringData.toLowerCase();
                        stringData = stringData.replace(".iff", "");
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            EditorState.dbmap = stringData;
                        }

                    }
                    if(stringData.contains("size_x")) {
                        stringData = stringData.replace("size_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) EditorState.dbMapSizeX = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("size_y")) {
                        stringData = stringData.replace("size_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) EditorState.dbMapSizeY = Integer.parseInt(stringData);
                    }
                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
            }
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void handleBeamGates() {
        if(stringData.trim().contentEquals("begin_gate")) {
            try{
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(stringData.equals("")) return;
                int secX = 0;
                int secY = 0;
                int closedBG = 0;
                int openedBG = 0;
                ArrayList<Integer> keysecX = new ArrayList<>();
                ArrayList<Integer> keysecY = new ArrayList<>();
                ArrayList<Integer> targetLevels = new ArrayList<>();
                boolean mbStatus = true;

                while(!stringData.trim().contentEquals("end")) {

                    if(stringData.contains("keysec_x")) {
                        stringData = stringData.replace("keysec_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))keysecX.add(Integer.parseInt(stringData));
                    }
                    if(stringData.contains("keysec_y")) {
                        stringData = stringData.replace("keysec_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))keysecY.add(Integer.parseInt(stringData));
                    }
                    if(stringData.contains("sec_x")) {
                        stringData = stringData.replace("sec_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))secX = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("sec_y")) {
                        stringData = stringData.replace("sec_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))secY = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("closed_bp")) {
                        stringData = stringData.replace("closed_bp", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            if(Integer.parseInt(stringData) == 5) closedBG = 1;
                            else if(Integer.parseInt(stringData) == 25) closedBG = 2;
                        }
                    }
                    if(stringData.contains("opened_bp")) {
                        stringData = stringData.replace("opened_bp", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            if(Integer.parseInt(stringData) == 6) openedBG = 1;
                            else if(Integer.parseInt(stringData) == 26) openedBG = 2;
                        }
                    }
                    if(stringData.contains("target_level")) {
                        stringData = stringData.replace("target_level", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))targetLevels.add(Integer.parseInt(stringData));
                    }
                    if(stringData.contains("mb_status")) {
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals(""))mbStatus = false;
                    }

                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
                BeamGate newBeamGate = new BeamGate(secX, secY);
                newBeamGate.setClosedType(closedBG);
                newBeamGate.setOpenedType(openedBG);
                for(int i = 0; i < keysecX.size(); i++) newBeamGate.addKeysector(keysecX.get(i), keysecY.get(i));
                for (Integer targetLevel : targetLevels) newBeamGate.getTargetLevel().add(targetLevel);
                if(!mbStatus) newBeamGate.setVisibility(false);
                EditorState.beamGates.add(newBeamGate);
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleHostStations() {
        if(stringData.contains("begin_robo")) {
            try{
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(stringData.equals("")) return;
                int owner = 0;
                int vehicle = 0;
                int pos_x = 0;
                int pos_y = 0;
                int height = 0;
                int energy = 0;
                int reload_const = 0;
                int viewangle = 0;
                boolean mb_status = true;
                int con_budget = 0;
                int con_delay = 0;
                int def_budget = 0;
                int def_delay = 0;
                int rec_budget = 0;
                int rec_delay = 0;
                int rob_budget = 0;
                int rob_delay = 0;
                int pow_budget = 0;
                int pow_delay = 0;
                int rad_budget = 0;
                int rad_delay = 0;
                int saf_budget = 0;
                int saf_delay = 0;
                int cpl_budget = 0;
                int cpl_delay = 0;

                while(!stringData.trim().contentEquals("end")) {

                    if(stringData.contains("owner")) {
                        stringData = stringData.replace("owner", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) owner = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("vehicle")) {
                        stringData = stringData.replace("vehicle", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            vehicle = Integer.parseInt(stringData);
                            if(vehicle == 176 || vehicle == 177 || vehicle == 178) {
                                EditorState.gameContent = 1;
                            }
                        }
                    }
                    if(stringData.contains("pos_x")) {
                        stringData = stringData.replace("pos_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            pos_x = Integer.parseInt(stringData);
                            double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
                            //coords exchange formula: (HoststationX / 1201) * mapSize     same for HoststationY
                            pos_x = (int)( (((double)pos_x/1201) * fullSectorSize) - (fullSectorSize * 0.5/2.0)) +1;
                        }
                    }
                    if(stringData.contains("pos_y")) {
                        stringData = stringData.replace("pos_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) height = -Integer.parseInt(stringData);
                    }

                    if(stringData.contains("pos_z")) {
                        stringData = stringData.replace("pos_z", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            pos_y = Integer.parseInt(stringData);
                            pos_y = -pos_y;
                            double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
                            pos_y = (int)( (((double)pos_y/1201) * fullSectorSize) - (fullSectorSize * 0.5/2.0)) +1;
                        }
                    }

                    if(stringData.contains("energy")) {
                        stringData = stringData.replace("energy", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) energy = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("reload_const")) {
                        stringData = stringData.replace("reload_const", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            if(playerReloadConst) {
                                reload_const = (int)(Double.parseDouble(stringData) / (60000d / 255d));
                                playerReloadConst = false;
                            }else reload_const = (int)(Double.parseDouble(stringData) / (70000d / 255d));
                        }
                    }

                    if(stringData.contains("viewangle")) {
                        stringData = stringData.replace("viewangle", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) viewangle = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("mb_status")) {
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) mb_status = false;
                    }

                    if(stringData.contains("con_budget")) {
                        stringData = stringData.replace("con_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) con_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("con_delay")) {
                        stringData = stringData.replace("con_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) con_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("def_budget")) {
                        stringData = stringData.replace("def_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) def_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("def_delay")) {
                        stringData = stringData.replace("def_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) def_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rec_budget")) {
                        stringData = stringData.replace("rec_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rec_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rec_delay")) {
                        stringData = stringData.replace("rec_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rec_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rob_budget")) {
                        stringData = stringData.replace("rob_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rob_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rob_delay")) {
                        stringData = stringData.replace("rob_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rob_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("pow_budget")) {
                        stringData = stringData.replace("pow_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) pow_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("pow_delay")) {
                        stringData = stringData.replace("pow_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) pow_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rad_budget")) {
                        stringData = stringData.replace("rad_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rad_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("rad_delay")) {
                        stringData = stringData.replace("rad_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) rad_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("saf_budget")) {
                        stringData = stringData.replace("saf_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) saf_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("saf_delay")) {
                        stringData = stringData.replace("saf_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) saf_delay = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("cpl_budget")) {
                        stringData = stringData.replace("cpl_budget", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) cpl_budget = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("cpl_delay")) {
                        stringData = stringData.replace("cpl_delay", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) cpl_delay = Integer.parseInt(stringData);
                    }

                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
                Hoststation newHoststation = new Hoststation(pos_x, pos_y, owner, vehicle);
                newHoststation.setHeight(height);
                newHoststation.setRawEnergy(energy);
                if(reload_const > 0) newHoststation.setReloadConst(reload_const);
                if(viewangle > 0) newHoststation.setViewAngle(viewangle);
                if(!mb_status) newHoststation.setVisibility(false);
                newHoststation.setConBudget(con_budget);
                newHoststation.setRawConDelay(con_delay);
                newHoststation.setDefBudget(def_budget);
                newHoststation.setRawDefDelay(def_delay);
                newHoststation.setRecBudget(rec_budget);
                newHoststation.setRawRecDelay(rec_delay);
                newHoststation.setRobBudget(rob_budget);
                newHoststation.setRawRobDelay(rob_delay);
                newHoststation.setPowBudget(pow_budget);
                newHoststation.setRawPowDelay(pow_delay);
                newHoststation.setRadBudget(rad_budget);
                newHoststation.setRawRadDelay(rad_delay);
                newHoststation.setSafBudget(saf_budget);
                newHoststation.setRawSafDelay(saf_delay);
                newHoststation.setCplBudget(cpl_budget);
                newHoststation.setRawCplDelay(cpl_delay);

                EditorState.hostStations.add(newHoststation);
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleBombs() {
        if(stringData.contains("begin_item")) {
            try{
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(stringData.equals("")) return;
                int sec_x = 0;
                int sec_y = 0;
                int inactive_bp = 0;
                int active_bp = 0;
                int trigger_bp = 0;
                int countdown = 0;
                ArrayList<Integer> keysec_x = new ArrayList<>();
                ArrayList<Integer> keysec_y = new ArrayList<>();

                while(!stringData.trim().contentEquals("end")) {

                    if(stringData.contains("keysec_x")) {
                        stringData = stringData.replace("keysec_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) keysec_x.add(Integer.parseInt(stringData));
                    }
                    if(stringData.contains("keysec_y")) {
                        stringData = stringData.replace("keysec_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) keysec_y.add(Integer.parseInt(stringData));
                    }

                    if(stringData.contains("sec_x")) {
                        stringData = stringData.replace("sec_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) sec_x = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("sec_y")) {
                        stringData = stringData.replace("sec_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) sec_y = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("inactive_bp")) {
                        stringData = stringData.replace("inactive_bp", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) inactive_bp = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("active_bp")) {
                        stringData = stringData.replace("active_bp", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) active_bp = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("trigger_bp")) {
                        stringData = stringData.replace("trigger_bp", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) trigger_bp = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("countdown")) {
                        stringData = stringData.replace("countdown", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            countdown = Integer.parseInt(stringData);
                            if(countdown < 1) countdown = 1;
                        }
                    }

                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
                StoudsonBomb newBomb = new StoudsonBomb(sec_x, sec_y, countdown);
                newBomb.setInactiveType(inactive_bp);
                newBomb.setActiveType(active_bp);
                newBomb.setTriggerType(trigger_bp);
                for(int i = 0; i < keysec_x.size(); i++) newBomb.addReactor(keysec_x.get(i), keysec_y.get(i));

                EditorState.bombs.add(newBomb);
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handlePredefinedSquads() {
        if(stringData.contains("begin_squad")) {
            try{
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(stringData.equals("")) return;
                int owner = 0;
                int vehicle = 0;
                int num = 0;
                int pos_x = 0;
                int pos_y = 0;
                boolean useable = false;
                boolean mb_status = true;

                while(!stringData.trim().contentEquals("end")) {

                    if(stringData.contains("owner")) {
                        stringData = stringData.replace("owner", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) owner = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("vehicle")) {
                        stringData = stringData.replace("vehicle", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) vehicle = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("num")) {
                        stringData = stringData.replace("num", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) num = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("useable")) {
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) useable = true;
                    }

                    if(stringData.contains("pos_x")) {
                        stringData = stringData.replace("pos_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            pos_x = Integer.parseInt(stringData);
                            double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
                            pos_x = (int)( (((double)pos_x/1201.0) * fullSectorSize) - (fullSectorSize * 0.14/2.0)) +1;
                        }
                    }

                    if(stringData.contains("pos_z")) {
                        stringData = stringData.replace("pos_z", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            pos_y = Integer.parseInt(stringData);
                            pos_y = -pos_y;
                            double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
                            pos_y = (int)( (((double)pos_y/1201) * fullSectorSize) - (fullSectorSize * 0.14/2.0)) +1;
                        }
                    }

                    if(stringData.contains("mb_status")) {
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) mb_status = false;
                    }

                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
                Squad newSquad = new Squad(pos_x, pos_y, owner, vehicle);
                newSquad.setQuantity(num);
                if(useable) newSquad.setUseable(true);
                if(!mb_status) newSquad.setVisibility(false);

                EditorState.predefinedSquads.add(newSquad);
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleModifications() {
        if(stringData.contains("include")) {
            try{
                StringBuilder modsString = new StringBuilder();
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(!stringData.equals("")) {
                    modsString = new StringBuilder();
                    modsString.append(stringData);
                }
                stringData = mapOpener.readLine();
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                while(true) {
                    modsString.append("\n");
                    modsString.append(stringData);


                    stringData = mapOpener.readLine();
                    if(!stringData.isEmpty()) {
                        if(stringData.charAt(0) == ';') break;
                    }
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                }
                EditorState.prototypeModifications = modsString.toString();
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handlePrototypeEnabling() {
        if(stringData.contains("begin_enable")) {
            try{
                int enabler;
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(!stringData.equals("")) {
                    stringData = stringData.replace("begin_enable", "");
                    stringData = stringData.trim();
                    stringData = stringData.replace("=", "");
                    stringData = stringData.trim();

                    enabler = Integer.parseInt(stringData);

                    while(!stringData.trim().contentEquals("end")) {

                        if(stringData.contains("vehicle")) {
                            stringData = stringData.replace("vehicle", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if(!stringData.equals("")) {
                                switch (enabler) {
                                    case 1 -> EditorState.resistanceUnits.add(Integer.parseInt(stringData));
                                    case 2 -> EditorState.sulgogarUnits.add(Integer.parseInt(stringData));
                                    case 3 -> EditorState.mykonianUnits.add(Integer.parseInt(stringData));
                                    case 4 -> EditorState.taerkastenUnits.add(Integer.parseInt(stringData));
                                    case 5 -> EditorState.blackSectUnits.add(Integer.parseInt(stringData));
                                    case 6 -> EditorState.ghorkovUnits.add(Integer.parseInt(stringData));
                                    case 7 -> EditorState.trainingUnits.add(Integer.parseInt(stringData));
                                }
                            }
                        }

                        if(stringData.contains("building")) {
                            stringData = stringData.replace("building", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if(!stringData.equals("")) {
                                switch (enabler) {
                                    case 1 -> EditorState.resistanceBuildings.add(Integer.parseInt(stringData));
                                    case 2 -> EditorState.sulgogarBuildings.add(Integer.parseInt(stringData));
                                    case 3 -> EditorState.mykonianBuildings.add(Integer.parseInt(stringData));
                                    case 4 -> EditorState.taerkastenBuildings.add(Integer.parseInt(stringData));
                                    case 5 -> EditorState.blackSectBuildings.add(Integer.parseInt(stringData));
                                    case 6 -> EditorState.ghorkovBuildings.add(Integer.parseInt(stringData));
                                    case 7 -> EditorState.trainingBuildings.add(Integer.parseInt(stringData));
                                }
                            }
                        }

                        stringData = mapOpener.readLine();
                        stringData = stringData.toLowerCase().trim();
                    }
                }
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void handleTechUpgrades() {
        if(stringData.contains("begin_gem")) {
            try{
                if(stringData.contains(";")) {
                    stringData = stringData.substring(0, stringData.indexOf(';'));
                    stringData = stringData.trim();
                }
                if(stringData.equals("")) return;
                int sec_x = 0;
                int sec_y = 0;
                int building = 0;
                int type = 0;
                boolean mb_status = true;
                TechUpgrade newTechUpgrade = null;

                while(!stringData.trim().contentEquals("end")) {

                    if(stringData.contains("sec_x")) {
                        stringData = stringData.replace("sec_x", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) sec_x = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("sec_y")) {
                        stringData = stringData.replace("sec_y", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) sec_y = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("building")) {
                        stringData = stringData.replace("building", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) building = Integer.parseInt(stringData);
                    }

                    if(stringData.contains("type")) {
                        stringData = stringData.replace("type", "");
                        stringData = stringData.trim();
                        stringData = stringData.replace("=", "");
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) type = Integer.parseInt(stringData);
                    }
                    if(stringData.contains("begin_action")) {
                        newTechUpgrade = new TechUpgrade(sec_x, sec_y);
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) {
                            ArrayList<Integer> modifyVehicle = new ArrayList<>();
                            ArrayList<Integer> modifyBuilding = new ArrayList<>();

                            while(!stringData.trim().contentEquals("end_action")) {

                                if(stringData.contains("modify_vehicle")) {
                                    stringData = stringData.replace("modify_vehicle", "");
                                    stringData = stringData.trim();
                                    stringData = stringData.replace("=", "");
                                    stringData = stringData.trim();
                                    if(stringData.contains(";")) {
                                        stringData = stringData.substring(0, stringData.indexOf(';'));
                                        stringData = stringData.trim();
                                    }
                                    if(!stringData.equals("")) {
                                        if(!modifyVehicle.contains(Integer.parseInt(stringData)))
                                            modifyVehicle.add(Integer.parseInt(stringData));
                                    }else continue;
                                    int vehID = Integer.parseInt(stringData);
                                    int add_energy = 0;
                                    int add_shield = 0;
                                    int add_radar = 0;
                                    int num_weapons = 0;
                                    boolean resEnable = false;
                                    boolean ghorEnable = false;
                                    boolean taerEnable = false;
                                    boolean mykoEnable = false;
                                    boolean sulgEnable = false;
                                    boolean blasecEnable = false;
                                    boolean targetEnable = false;

                                    while(!stringData.trim().contentEquals("end")) {

                                        if(stringData.contains("enable")) {
                                            stringData = stringData.replace("enable", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) {
                                                if(Integer.parseInt(stringData) == 1) resEnable = true;
                                                else if(Integer.parseInt(stringData) == 2) sulgEnable = true;
                                                else if(Integer.parseInt(stringData) == 3) mykoEnable = true;
                                                else if(Integer.parseInt(stringData) == 4) taerEnable = true;
                                                else if(Integer.parseInt(stringData) == 5) blasecEnable = true;
                                                else if(Integer.parseInt(stringData) == 6) ghorEnable = true;
                                                else if(Integer.parseInt(stringData) == 7) targetEnable = true;
                                            }
                                        }

                                        if(stringData.contains("add_energy")) {
                                            stringData = stringData.replace("add_energy", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) add_energy = Integer.parseInt(stringData) / 100;
                                        }

                                        if(stringData.contains("add_shield")) {
                                            stringData = stringData.replace("add_shield", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) add_shield = Integer.parseInt(stringData);
                                        }

                                        if(stringData.contains("add_radar")) {
                                            stringData = stringData.replace("add_radar", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) add_radar = Integer.parseInt(stringData);
                                        }

                                        if(stringData.contains("num_weapons")) {
                                            stringData = stringData.replace("num_weapons", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) num_weapons = Integer.parseInt(stringData);
                                        }


                                        stringData = mapOpener.readLine();
                                        stringData = stringData.toLowerCase().trim();
                                    }
                                    if(resEnable) newTechUpgrade.enableVehicle(vehID, 1);
                                    if(ghorEnable) newTechUpgrade.enableVehicle(vehID, 6);
                                    if(taerEnable) newTechUpgrade.enableVehicle(vehID, 4);
                                    if(mykoEnable) newTechUpgrade.enableVehicle(vehID, 3);
                                    if(sulgEnable) newTechUpgrade.enableVehicle(vehID, 2);
                                    if(blasecEnable) newTechUpgrade.enableVehicle(vehID, 5);
                                    if(targetEnable) newTechUpgrade.enableVehicle(vehID, 7);
                                    if(add_energy != 0) newTechUpgrade.addEnergy(vehID, add_energy);
                                    if(add_shield != 0) newTechUpgrade.addShield(vehID, add_shield);
                                    if(add_radar != 0)newTechUpgrade.addRadar(vehID, add_radar);
                                    if(num_weapons != 0)newTechUpgrade.addWeapon(vehID, num_weapons);

                                }// end of modify_vehicle

                                if(stringData.contains("modify_weapon")) {
                                    stringData = stringData.replace("modify_weapon", "");
                                    stringData = stringData.trim();
                                    stringData = stringData.replace("=", "");
                                    stringData = stringData.trim();
                                    if(stringData.contains(";")) {
                                        stringData = stringData.substring(0, stringData.indexOf(';'));
                                        stringData = stringData.trim();
                                    }
                                    if(!stringData.equals("")) {
                                        if(!modifyVehicle.contains(Integer.parseInt(stringData)))
                                            modifyVehicle.add(Integer.parseInt(stringData));
                                    }else continue;
                                    int vehID = Integer.parseInt(stringData);
                                    int damage = 0;
                                    int shot_time = 0;
                                    int shot_time_user = 0;
                                    while(!stringData.trim().contentEquals("end")) {

                                        if(stringData.contains("add_energy")) {
                                            stringData = stringData.replace("add_energy", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) damage = Integer.parseInt(stringData) / 100;
                                        }
                                        if(stringData.contains("add_shot_time_user")) {
                                            stringData = stringData.replace("add_shot_time_user", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) shot_time_user = Integer.parseInt(stringData);
                                        }
                                        if(stringData.contains("add_shot_time")) {
                                            stringData = stringData.replace("add_shot_time", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) shot_time = Integer.parseInt(stringData);
                                        }


                                        stringData = mapOpener.readLine();
                                        stringData = stringData.toLowerCase().trim();
                                    }

                                    if(damage != 0) newTechUpgrade.addDamage(vehID, damage);
                                    if(shot_time != 0) newTechUpgrade.addShotTime(vehID, shot_time);
                                    if(shot_time_user != 0) newTechUpgrade.addShotTime(vehID, shot_time_user);

                                }//end of modify_weapon

                                if(stringData.contains("modify_building")) {
                                    stringData = stringData.replace("modify_building", "");
                                    stringData = stringData.trim();
                                    stringData = stringData.replace("=", "");
                                    stringData = stringData.trim();
                                    if(stringData.contains(";")) {
                                        stringData = stringData.substring(0, stringData.indexOf(';'));
                                        stringData = stringData.trim();
                                    }
                                    if(!stringData.equals("")) {
                                        if(!modifyBuilding.contains(Integer.parseInt(stringData)))
                                            modifyBuilding.add(Integer.parseInt(stringData));
                                    }else continue;
                                    int buiID = Integer.parseInt(stringData);
                                    boolean resEnable = false;
                                    boolean ghorEnable = false;
                                    boolean taerEnable = false;
                                    boolean mykoEnable = false;
                                    boolean sulgEnable = false;
                                    boolean blasecEnable = false;
                                    boolean targetEnable = false;

                                    while(!stringData.trim().contentEquals("end")) {

                                        if(stringData.contains("enable")) {
                                            stringData = stringData.replace("enable", "");
                                            stringData = stringData.trim();
                                            stringData = stringData.replace("=", "");
                                            stringData = stringData.trim();
                                            if(stringData.contains(";")) {
                                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                                stringData = stringData.trim();
                                            }
                                            if(!stringData.equals("")) {
                                                if(Integer.parseInt(stringData) == 1) resEnable = true;
                                                else if(Integer.parseInt(stringData) == 2) sulgEnable = true;
                                                else if(Integer.parseInt(stringData) == 3) mykoEnable = true;
                                                else if(Integer.parseInt(stringData) == 4) taerEnable = true;
                                                else if(Integer.parseInt(stringData) == 5) blasecEnable = true;
                                                else if(Integer.parseInt(stringData) == 6) ghorEnable = true;
                                                else if(Integer.parseInt(stringData) == 7) targetEnable = true;
                                            }
                                        }

                                        stringData = mapOpener.readLine();
                                        stringData = stringData.toLowerCase().trim();
                                    }
                                    if(resEnable) newTechUpgrade.enableBuilding(buiID, 1);
                                    if(ghorEnable) newTechUpgrade.enableBuilding(buiID, 6);
                                    if(taerEnable) newTechUpgrade.enableBuilding(buiID, 4);
                                    if(mykoEnable) newTechUpgrade.enableBuilding(buiID, 3);
                                    if(sulgEnable) newTechUpgrade.enableBuilding(buiID, 2);
                                    if(blasecEnable) newTechUpgrade.enableBuilding(buiID, 5);
                                    if(targetEnable) newTechUpgrade.enableBuilding(buiID, 7);
                                }//end of modify_building

                                if(stringData.contains("mb_status")) {
                                    stringData = stringData.trim();
                                    if(stringData.contains(";")) {
                                        stringData = stringData.substring(0, stringData.indexOf(';'));
                                        stringData = stringData.trim();
                                    }
                                    if(!stringData.equals("")) mb_status = false;
                                }


                                stringData = mapOpener.readLine();
                                stringData = stringData.toLowerCase().trim();
                            }

                        }
                    }// end of begin_action
                    if(stringData.contains("mb_status")) {
                        stringData = stringData.trim();
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                        if(!stringData.equals("")) mb_status = false;
                    }

                    stringData = mapOpener.readLine();
                    stringData = stringData.toLowerCase().trim();
                }
                if(newTechUpgrade == null) newTechUpgrade = new TechUpgrade(sec_x, sec_y);
                if(building > 0) newTechUpgrade.setBuilding(building);
                if(type > 0) newTechUpgrade.setType(type);
                if(!mb_status) newTechUpgrade.setVisibility(false);

                EditorState.techUpgrades.add(newTechUpgrade);
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"An I/O Error Occurred while opening the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
