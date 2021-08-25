package LevelIO;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class SingleplayerLevelOpener extends LevelOpener{

    void open(File f) {

        currentMap.closeMap();
        boolean playerReloadConst = true;
        playerSelected = 0;
        savedContent = 0;
        noneContent.setSelected(true);
        MBsizeX = 0;
        MBsizeY = 0;
        MDsizeX = 0;
        MDsizeY = 0;
        selectedMB = 0;
        selectedDB = 0;
        selectedSet = 0;
        selectedSky = 0;
        selectedMovie = 0;
        selectedEventLoop = 0;
        selectedMusic = 0;
        selectedMinBreak = 0;
        selectedMaxBreak = 0;
        savedMB = 0;
        savedDB = 0;
        savedSet = 0;
        savedSky = 0;
        savedMovie = 0;
        savedEventLoop = 0;
        savedMusic = 0;
        savedMinBreak = 0;
        savedMaxBreak = 0;
        resUnits.clear();
        resBuildings.clear();
        ghorUnits.clear();
        ghorBuildings.clear();
        taerUnits.clear();
        taerBuildings.clear();
        mykoUnits.clear();
        mykoBuildings.clear();
        sulgUnits.clear();
        sulgBuildings.clear();
        blasecUnits.clear();
        blasecBuildings.clear();
        trainingUnits.clear();
        trainingBuildings.clear();

        try {
            mapOpener = new BufferedReader(new FileReader(f));
            String stringData = "";

            do {
                if(stringData.contains("begin_robo")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
                    int vehicle = 0;

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
                                vehicle = Integer.parseInt(stringData);
                                if(vehicle == 176 || vehicle == 177 || vehicle == 178) {
                                    savedContent = 1;
                                    mdContent.setSelected(true);
                                }
                            }
                        }
                        stringData = mapOpener.readLine();
                        stringData = stringData.toLowerCase().trim();
                    }
                }


                if(stringData.contains("typ_map")) {
                    stringData = mapOpener.readLine();
                    stringData = stringData.trim();
                    for(int i = 0;i < stringData.length(); i++) {
                        if(stringData.charAt(i) == ' ') {
                            horizontalSectors = Integer.parseInt(stringData.substring(0,i));
                            stringData = stringData.substring(i);
                            stringData = stringData.trim();

                            verticalSectors = Integer.parseInt(stringData);

                        }

                    }
                    horizontalSectors -= 2;
                    verticalSectors -= 2;
                    mapOpener.readLine();

                    int hex;
                    for(int i = 0; i < verticalSectors; i++) {
                        stringData = mapOpener.readLine();
                        stringData = stringData.trim();
                        stringData = stringData.substring(3);
                        for(int j = 0; j < horizontalSectors; j++) {
                            try {
                                hex = Integer.parseInt(stringData.substring(0, 3).trim(), 16 );
                                currentMap.addTypMap(hex);
                            }catch(NumberFormatException ex) {
                                currentMap.addTypMap(0);
                            }
                            stringData = stringData.substring(3).trim();
                        }
                    }
                }

                if(stringData.contains("own_map")) {
                    stringData = mapOpener.readLine();
                    stringData = mapOpener.readLine();
                    for(int i = 0; i < verticalSectors; i++) {
                        stringData = mapOpener.readLine();
                        stringData = stringData.trim();
                        stringData = stringData.substring(3);
                        for(int j = 0; j < horizontalSectors; j++) {
                            currentMap.addOwnMap(Integer.parseInt(stringData.substring(0, 3).trim(), 16 ));
                            stringData = stringData.substring(3).trim();
                        }
                    }
                }

                if(stringData.contains("hgt_map")) {
                    stringData = mapOpener.readLine();
                    for(int i = 0; i < verticalSectors+2; i++) {
                        stringData = mapOpener.readLine();
                        stringData = stringData.trim();

                        for(int j = 0; j < horizontalSectors+1; j++) {
                            currentMap.addHgtMap(Integer.parseInt(stringData.substring(0, 3).trim(), 16 ));
                            stringData = stringData.substring(3).trim();
                        }
                        currentMap.addHgtMap(Integer.parseInt(stringData.substring(0, 2).trim(), 16 ));
                    }
                }

                if(stringData.contains("blg_map")) {
                    stringData = mapOpener.readLine();
                    stringData = mapOpener.readLine();
                    for(int i = 0; i < verticalSectors; i++) {
                        stringData = mapOpener.readLine();
                        stringData = stringData.trim();
                        stringData = stringData.substring(3);
                        for(int j = 0; j < horizontalSectors; j++) {
                            currentMap.addBlgMap(Integer.parseInt(stringData.substring(0, 3).trim(), 16 ));
                            stringData = stringData.substring(3).trim();
                        }
                    }
                }

                stringData = mapOpener.readLine();
            }
            while(stringData != null);

            mapOpener = new BufferedReader(new FileReader(f));
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            stringData = mapOpener.readLine();
            descString = "";
            while(stringData.length() > 0 && stringData.charAt(0) == ';') {
                descString += stringData.substring(1);
                descString += "\n";
                stringData = mapOpener.readLine();
            }

            while(stringData != null) {
                if(stringData.contains("begin_level")) {
                    while(!stringData.trim().contentEquals("end")) {
                        stringData = mapOpener.readLine();

                        if(stringData.contains("set")) {
                            stringData = stringData.trim();
                            stringData = stringData.replace("set", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if((!stringData.equals(""))) {
                                savedSet = Integer.parseInt(stringData)-1;
                                selectedSet = Integer.parseInt(stringData)-1;
                            }
                        }

                        if(stringData.contains("sky")) {
                            stringData = stringData.trim();
                            stringData = stringData.replace("sky", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if((!stringData.equals(""))) {
                                stringData = stringData.replace("objects/", "");
                                stringData = stringData.replace(".base", "");
                                stringData = stringData.replace(".bas", "");
                                for(int i = 0;;i++) {
                                    if(stringData.toLowerCase().equals(skies[i].toLowerCase())) {
                                        savedSky = i;
                                        selectedSky = i;
                                        break;
                                    }
                                }
                            }
                        }

                        if(stringData.contains("event_loop")) {
                            stringData = stringData.trim();
                            stringData = stringData.replace("event_loop", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if((!stringData.equals(""))) {
                                savedEventLoop = Integer.parseInt(stringData);
                                selectedEventLoop = Integer.parseInt(stringData);
                            }
                        }

                        if(stringData.contains("ambiencetrack")) {
                            stringData = stringData.trim();
                            stringData = stringData.replace("ambiencetrack", "");
                            stringData = stringData.trim();
                            stringData = stringData.replace("=", "");
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if((!stringData.equals(""))) {
                                savedMusic = Integer.parseInt(stringData.substring(0, 1))-1;
                                selectedMusic = Integer.parseInt(stringData.substring(0, 1))-1;
                                if(stringData.length() > 1) {
                                    stringData = stringData.substring(2);
                                    savedMinBreak = Integer.parseInt(stringData.substring(0,stringData.indexOf('_')));
                                    selectedMinBreak = Integer.parseInt(stringData.substring(0,stringData.indexOf('_')));
                                    stringData = stringData.substring(stringData.indexOf('_')+1);
                                    savedMaxBreak = Integer.parseInt(stringData);
                                    selectedMaxBreak = Integer.parseInt(stringData);
                                }
                            }
                        }

                        if(stringData.contains("movie")) {
                            stringData = stringData.trim();
                            stringData = stringData.replace("movie", "");
                            stringData = stringData.trim();
                            stringData = stringData.substring(1);
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            stringData = stringData.replace("mov:", "");

                            if(stringData.equals("intro.mpg")) {
                                savedMovie = 1;
                                selectedMovie = 1;
                            }else if(stringData.equals("tut1.mpg")) {
                                savedMovie = 2;
                                selectedMovie = 2;
                            }else if(stringData.equals("tut2.mpg")) {
                                savedMovie = 3;
                                selectedMovie = 3;
                            }else if(stringData.equals("tut3.mpg")) {
                                savedMovie = 4;
                                selectedMovie = 4;
                            }else if(stringData.equals("kyt.mpg")) {
                                savedMovie = 5;
                                selectedMovie = 5;
                            }else if(stringData.equals("taer.mpg")) {
                                savedMovie = 6;
                                selectedMovie = 6;
                            }else if(stringData.equals("myk.mpg")) {
                                savedMovie = 7;
                                selectedMovie = 7;
                            }else if(stringData.equals("sulg.mpg")) {
                                savedMovie = 8;
                                selectedMovie = 8;
                            }else if(stringData.equals("black.mpg")) {
                                savedMovie = 9;
                                selectedMovie = 9;
                            }else if(stringData.equals("lose.mpg")) {
                                savedMovie = 10;
                                selectedMovie = 10;
                            }else if(stringData.equals("win.mpg")) {
                                savedMovie = 11;
                                selectedMovie = 11;
                            }

                        }

                    }
                }

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

                                if(savedContent == 0) {
                                    for(int i = 0; i < 74; i++) {
                                        if(stringData.equals(mbMaps[i])) {
                                            savedMB = i;
                                            selectedMB = i;
                                            break;
                                        }
                                    }
                                }else if(savedContent == 1) {

                                    for(int i = 0; i < 31; i++) {
                                        if(stringData.equals(mbMapsXp[i])) {
                                            savedMB = i;
                                            selectedMB = i;
                                            break;
                                        }
                                    }
                                }
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
                            if(!stringData.equals("")) MBsizeX = Integer.parseInt(stringData);
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
                            if(!stringData.equals("")) MBsizeY = Integer.parseInt(stringData);
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
                                if(savedContent == 0) {
                                    for(int i = 0; i < 114; i++) {
                                        if(stringData.contains(dbMaps[i])) {
                                            savedDB = i;
                                            selectedDB = i;
                                            break;
                                        }
                                    }
                                } else if(savedContent == 1) {
                                    for(int i = 0; i < 71; i++) {
                                        if(stringData.contains(dbMapsXp[i])) {
                                            savedDB = i;
                                            selectedDB = i;
                                            break;
                                        }
                                    }
                                }
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
                            if(!stringData.equals("")) MDsizeX = Integer.parseInt(stringData);
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
                            if(!stringData.equals("")) MDsizeY = Integer.parseInt(stringData);
                        }


                        stringData = mapOpener.readLine();
                        stringData = stringData.toLowerCase().trim();
                    }
                }

                if(stringData.trim().contentEquals("begin_gate")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
                    int secX = 0;
                    int secY = 0;
                    int closedBG = 0;
                    int openedBG = 0;
                    ArrayList<Integer> keysecX = new ArrayList<Integer>();
                    ArrayList<Integer> keysecY = new ArrayList<Integer>();
                    ArrayList<Integer> targetLevels = new ArrayList<Integer>();
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
                    currentMap.addBeamGate(secX, secY);
                    currentMap.getBeamGate(secX, secY).setClosedType(closedBG);
                    currentMap.getBeamGate(secX, secY).setOpenedType(openedBG);
                    for(int i = 0; i < keysecX.size(); i++) currentMap.getBeamGate(secX, secY).addKeysector(keysecX.get(i), keysecY.get(i));
                    for(int i = 0; i < targetLevels.size(); i++) currentMap.getBeamGate(secX, secY).getTargetLevel().add(targetLevels.get(i));
                    if(mbStatus == false) currentMap.getBeamGate(secX, secY).setVisibility(false);
                    secX = 0;
                    secY = 0;
                    closedBG = 0;
                    openedBG = 0;
                    keysecX.clear();
                    keysecY.clear();
                    targetLevels.clear();
                    mbStatus = true;
                }

                if(stringData.contains("begin_robo")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
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
                                    savedContent = 1;
                                    mdContent.setSelected(true);
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
                                //coords exchange formula: (HoststationX / 1201) * mapSize     same for HoststationY
                                pos_x = (int)( (((double)pos_x/1201) * (double)currentMap.getMapSize()) - ((double)currentMap.getMapSize() * 0.5/2.0)) +1;
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
                                pos_y = (int)( (((double)pos_y/1201) * (double)currentMap.getMapSize()) - ((double)currentMap.getMapSize() * 0.5/2.0)) +1;
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
                    currentMap.addHoststation(pos_x, pos_y, owner, vehicle);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setHeight(height);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawEnergy(energy);
                    if(reload_const > 0) currentMap.getHoststation(currentMap.getHoststationSize()-1).setReloadConst(reload_const);
                    if(viewangle > 0) currentMap.getHoststation(currentMap.getHoststationSize()-1).setViewAngle(viewangle);
                    if(mb_status == false) currentMap.getHoststation(currentMap.getHoststationSize()-1).setVisibility(false);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setConBudget(con_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawConDelay(con_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setDefBudget(def_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawDefDelay(def_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRecBudget(rec_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawRecDelay(rec_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRobBudget(rob_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawRobDelay(rob_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setPowBudget(pow_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawPowDelay(pow_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRadBudget(rad_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawRadDelay(rad_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setSafBudget(saf_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawSafDelay(saf_delay);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setCplBudget(cpl_budget);
                    currentMap.getHoststation(currentMap.getHoststationSize()-1).setRawCplDelay(cpl_delay);
                }

                if(stringData.contains("begin_item")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
                    int sec_x = 0;
                    int sec_y = 0;
                    int inactive_bp = 0;
                    int active_bp = 0;
                    int trigger_bp = 0;
                    int countdown = 0;
                    ArrayList<Integer> keysec_x = new ArrayList<Integer>();
                    ArrayList<Integer> keysec_y = new ArrayList<Integer>();

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
                    currentMap.addStoudsonBomb(sec_x, sec_y, countdown);
                    currentMap.getStoudsonBomb(sec_x, sec_y).setInactiveType(inactive_bp);
                    currentMap.getStoudsonBomb(sec_x, sec_y).setActiveType(active_bp);
                    currentMap.getStoudsonBomb(sec_x, sec_y).setTriggerType(trigger_bp);
                    for(int i = 0; i < keysec_x.size(); i++) currentMap.getStoudsonBomb(sec_x, sec_y).addReactor(keysec_x.get(i), keysec_y.get(i));
                }

                if(stringData.contains("begin_squad")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
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
                                pos_x = (int)( (((double)pos_x/1201.0) * (double)currentMap.getMapSize()) - (double)((double)currentMap.getMapSize() * 0.14/2.0)) +1;
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
                                pos_y = (int)( (((double)pos_y/1201) * (double)currentMap.getMapSize()) - ((double)currentMap.getMapSize() * 0.14/2.0)) +1;
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
                    currentMap.addSquad(pos_x, pos_y, owner, vehicle);
                    currentMap.getSquad(currentMap.getSquadSize()-1).setQuantity(num);
                    if(useable == true) currentMap.getSquad(currentMap.getSquadSize()-1).setUseable(true);
                    if(mb_status == false) currentMap.getSquad(currentMap.getSquadSize()-1).setVisibility(false);
                }

                if(stringData.contains("include")) {

                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(!stringData.equals("")) {
                        modsString = "";
                        modsString += stringData;
                    }
                    stringData = mapOpener.readLine();
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    while(true) {
                        modsString += "\n";
                        modsString += stringData;


                        stringData = mapOpener.readLine();
                        if(!stringData.isEmpty()) {
                            if(stringData.charAt(0) == ';') break;
                        }
                        if(stringData.contains(";")) {
                            stringData = stringData.substring(0, stringData.indexOf(';'));
                            stringData = stringData.trim();
                        }
                    }

                }

                if(stringData.contains("begin_enable")) {
                    int enabler = 0;
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
                                    switch(enabler) {
                                        case 1:
                                            resUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 2:
                                            sulgUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 3:
                                            mykoUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 4:
                                            taerUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 5:
                                            blasecUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 6:
                                            ghorUnits.add(Integer.parseInt(stringData));
                                            break;
                                        case 7:
                                            trainingUnits.add(Integer.parseInt(stringData));
                                            break;
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
                                    switch(enabler) {
                                        case 1:
                                            resBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 2:
                                            sulgBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 3:
                                            mykoBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 4:
                                            taerBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 5:
                                            blasecBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 6:
                                            ghorBuildings.add(Integer.parseInt(stringData));
                                            break;
                                        case 7:
                                            trainingBuildings.add(Integer.parseInt(stringData));
                                            break;
                                    }
                                }
                            }

                            stringData = mapOpener.readLine();
                            stringData = stringData.toLowerCase().trim();
                        }
                    }
                }

                if(stringData.contains("begin_gem")) {
                    if(stringData.contains(";")) {
                        stringData = stringData.substring(0, stringData.indexOf(';'));
                        stringData = stringData.trim();
                    }
                    if(stringData.equals("")) continue;
                    int sec_x = 0;
                    int sec_y = 0;
                    int building = 0;
                    int type = 0;
                    boolean mb_status = true;

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
                            currentMap.addTechUpgrade(sec_x, sec_y);
                            stringData = stringData.trim();
                            if(stringData.contains(";")) {
                                stringData = stringData.substring(0, stringData.indexOf(';'));
                                stringData = stringData.trim();
                            }
                            if(!stringData.equals("")) {
                                ArrayList<Integer> modifyVehicle = new ArrayList<Integer>();
                                ArrayList<Integer> modifyBuilding = new ArrayList<Integer>();

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
                                        if(resEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 1);
                                        if(ghorEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 6);
                                        if(taerEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 4);
                                        if(mykoEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 3);
                                        if(sulgEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 2);
                                        if(blasecEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 5);
                                        if(targetEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableVehicle(vehID, 7);
                                        if(add_energy != 0) currentMap.getTechUpgrade(sec_x, sec_y).addEnergy(vehID, add_energy);
                                        if(add_shield != 0) currentMap.getTechUpgrade(sec_x, sec_y).addShield(vehID, add_shield);
                                        if(add_radar != 0)currentMap.getTechUpgrade(sec_x, sec_y).addRadar(vehID, add_radar);
                                        if(num_weapons != 0)currentMap.getTechUpgrade(sec_x, sec_y).addWeapon(vehID, num_weapons);

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

                                        if(damage != 0) currentMap.getTechUpgrade(sec_x, sec_y).addDamage(vehID, damage);
                                        if(shot_time != 0) currentMap.getTechUpgrade(sec_x, sec_y).addShotTime(vehID, shot_time);
                                        if(shot_time_user != 0) currentMap.getTechUpgrade(sec_x, sec_y).addShotTime(vehID, shot_time_user);

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
                                        if(resEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 1);
                                        if(ghorEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 6);
                                        if(taerEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 4);
                                        if(mykoEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 3);
                                        if(sulgEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 2);
                                        if(blasecEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 5);
                                        if(targetEnable == true) currentMap.getTechUpgrade(sec_x, sec_y).enableBuilding(buiID, 7);
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
                    if(currentMap.getTechUpgrade(sec_x, sec_y) == null) currentMap.addTechUpgrade(sec_x, sec_y);
                    if(building > 0) currentMap.getTechUpgrade(sec_x, sec_y).setBuilding(building);
                    if(type > 0) currentMap.getTechUpgrade(sec_x, sec_y).setType(type);
                    if(mb_status == false)currentMap.getTechUpgrade(sec_x, sec_y).setVisibility(false);
                }

                stringData = mapOpener.readLine();
            }

            mapOpener.close();
            currentMap.removeAddUnits();
            currentMap.initAddUnits();
            currentMap.removeSpecialBuildings();
            currentMap.initAddBuildings();
            currentMap.openMap(horizontalSectors, verticalSectors);
            mapscroller.revalidate();
            setTitle(f +" ("+currentMap.getHorizontalGrid()+"x"+currentMap.getVerticalGrid()+") - Urban Assault Level Editor");
            saved = true;
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
