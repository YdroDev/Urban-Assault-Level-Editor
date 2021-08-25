package LevelIO;

import main.*;

import javax.swing.*;
import java.io.*;

public class SingleplayerLevelSaver extends LevelSaver {

    void save(File f) {
        try {
            mapSaver = new PrintWriter(new BufferedWriter(new FileWriter(f)));

            mapSaver.println(";#*+ don't edit the magic runes");
            mapSaver.println();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Original Header                                      ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";");
            mapSaver.print(";");
            mapSaver.println(descString.replace("\n", "\n;"));
            mapSaver.println();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Main Level Info                                      ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println("begin_level");
            mapSaver.print("\t");
            mapSaver.print("set = ");
            mapSaver.println(Integer.toString(EditorState.set));
            mapSaver.print("\t");
            mapSaver.print("sky = ");
            mapSaver.println("objects/"+EditorState.sky+".base");
            mapSaver.println("\tslot0 = palette/standard.pal");
            mapSaver.println("\tslot1 = palette/red.pal");
            mapSaver.println("\tslot2 = palette/blau.pal");
            mapSaver.println("\tslot3 = palette/gruen.pal");
            mapSaver.println("\tslot4 = palette/inverse.pal");
            mapSaver.println("\tslot5 = palette/invdark.pal");
            mapSaver.println("\tslot6 = palette/sw.pal");
            mapSaver.println("\tslot7 = palette/invtuerk.pal");
            if(EditorState.eventLoop > 0) mapSaver.println("\tevent_loop = "+EditorState.eventLoop);
            if(EditorState.music > 0) {
                mapSaver.print("\tambiencetrack = "+(EditorState.music));
                if(EditorState.min_break < 10) mapSaver.print("_0"+EditorState.min_break);
                else mapSaver.print("_"+EditorState.min_break);
                if(EditorState.max_break < 10) mapSaver.println("_0"+EditorState.max_break);
                else mapSaver.println("_"+EditorState.max_break);
            }
            mapSaver.println("\tmovie = mov:"+EditorState.movie+".mpg");
            mapSaver.println("end");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Mission Briefing Maps                                ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println("begin_mbmap");
            if(savedContent == 0) {
                if(savedMB == 0) mapSaver.println("\tname = "+mbMaps[savedMB]+".ilb");
                else mapSaver.println("\tname = "+mbMaps[savedMB]+".iff");
            }else if(savedContent == 1) {
                mapSaver.println("\tname = "+mbMapsXp[savedMB]+".iff");
            }
            if(MBsizeX > 0) mapSaver.println("\tsize_x = "+MBsizeX);
            if(MBsizeY > 0) mapSaver.println("\tsize_y = "+MBsizeY);
            mapSaver.println("end");
            mapSaver.println("begin_dbmap");
            if(savedContent == 0)
                mapSaver.println(" \tname = "+dbMaps[savedDB]+".iff");
            else if(savedContent == 1)
                mapSaver.println(" \tname = "+dbMapsXp[savedDB]+".iff");
            if(MDsizeX > 0) mapSaver.println("\tsize_x = "+MDsizeX);
            if(MDsizeY > 0) mapSaver.println("\tsize_y = "+MDsizeY);
            mapSaver.println("end");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Beam Gates                                           ---");
            mapSaver.println(";------------------------------------------------------------");
            if(currentMap.getBeamGateList().size() > 0) {
                for(BeamGate bg : currentMap.getBeamGateList()) {
                    mapSaver.println("begin_gate");
                    mapSaver.println("\tsec_x = "+bg.getX());
                    mapSaver.println("\tsec_y = "+bg.getY());
                    if(bg.getClosedType() == 1) mapSaver.println("\tclosed_bp = 5");
                    else if(bg.getClosedType() == 2) mapSaver.println("\tclosed_bp = 25");
                    if(bg.getOpenedType() == 1) mapSaver.println("\topened_bp = 6");
                    else if(bg.getOpenedType() == 2) mapSaver.println("\topened_bp = 26");
                    for(Integer tl : bg.getTargetLevel()) {
                        mapSaver.println("\ttarget_level = "+tl);
                    }
                    for(SpecialBuilding sb : bg.getKeysectors()) {
                        mapSaver.println("\tkeysec_x = "+sb.getX());
                        mapSaver.println("\tkeysec_y = "+sb.getY());
                    }
                    if(bg.getVisibility() == false) mapSaver.println("\tmb_status = unknown");
                    mapSaver.println("end");
                }

            }else mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Robo Definitions                                     ---");
            mapSaver.println(";------------------------------------------------------------");
            if(currentMap.getHoststation(playerSelected) != null) {
                mapSaver.println("begin_robo");
                mapSaver.println("\towner = "+currentMap.getHoststation(playerSelected).getOwner());
                if(savedContent == 0 && currentMap.getHoststation(playerSelected).getVehicle() == 176)
                    currentMap.getHoststation(playerSelected).setVehicle(59);
                else if(savedContent == 0 && currentMap.getHoststation(playerSelected).getVehicle() == 177)
                    currentMap.getHoststation(playerSelected).setVehicle(57);
                else if(savedContent == 0 && currentMap.getHoststation(playerSelected).getVehicle() == 178)
                    currentMap.getHoststation(playerSelected).setVehicle(60);
                if(savedContent == 1 && currentMap.getHoststation(playerSelected).getVehicle() == 59)
                    currentMap.getHoststation(playerSelected).setVehicle(176);
                else if(savedContent == 1 && currentMap.getHoststation(playerSelected).getVehicle() == 57)
                    currentMap.getHoststation(playerSelected).setVehicle(177);
                else if(savedContent == 1 && currentMap.getHoststation(playerSelected).getVehicle() == 60)
                    currentMap.getHoststation(playerSelected).setVehicle(178);

                mapSaver.println("\tvehicle = "+currentMap.getHoststation(playerSelected).getVehicle());
                // coords transfer formula: HoststationX * (1201 / mapSize)  the same for HoststationY
                mapSaver.println("\tpos_x = "+ (int)((double)(currentMap.getHoststation(playerSelected).getUnitX() + ((double)currentMap.getMapSize() * 0.5/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                mapSaver.println("\tpos_y = "+ -currentMap.getHoststation(playerSelected).getHeight());
                mapSaver.println("\tpos_z = "+ -(int)((double)(currentMap.getHoststation(playerSelected).getUnitY() + ((double)currentMap.getMapSize() * 0.5/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                mapSaver.println("\tenergy = "+ currentMap.getHoststation(playerSelected).getRawEnergy());
                if(currentMap.getHoststation(playerSelected).getReloadConst() > 0) mapSaver.println("\treload_const = "+ (int)Math.ceil(((double)(currentMap.getHoststation(playerSelected).getReloadConst())* (60000d/255d))));
                if(currentMap.getHoststation(playerSelected).getViewAngle() > 0) mapSaver.println("\tviewangle = "+ currentMap.getHoststation(playerSelected).getViewAngle());
                if(currentMap.getHoststation(playerSelected).getVisibility() == false) mapSaver.println("\tmb_status = unknown");
                mapSaver.println("end");

                for(int i = 0;;i++) {
                    if(currentMap.getHoststation(i) != null) {
                        if(i != playerSelected) {
                            mapSaver.println("begin_robo");
                            mapSaver.println("\towner = "+currentMap.getHoststation(i).getOwner());
                            if(currentMap.getHoststation(i).getVehicle() == 176)
                                currentMap.getHoststation(i).setVehicle(59);
                            else if(currentMap.getHoststation(i).getVehicle() == 177)
                                currentMap.getHoststation(i).setVehicle(57);
                            else if(currentMap.getHoststation(i).getVehicle() == 178)
                                currentMap.getHoststation(i).setVehicle(60);
                            mapSaver.println("\tvehicle = "+currentMap.getHoststation(i).getVehicle());
                            // coords transfer formula: HoststationX * (1201 / mapSize)  the same for HoststationY
                            mapSaver.println("\tpos_x = "+ (int)((double)(currentMap.getHoststation(i).getUnitX() + ((double)currentMap.getMapSize() * 0.5/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                            mapSaver.println("\tpos_y = "+ -currentMap.getHoststation(i).getHeight());
                            mapSaver.println("\tpos_z = "+ -(int)((double)(currentMap.getHoststation(i).getUnitY() + ((double)currentMap.getMapSize() * 0.5/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                            mapSaver.println("\tenergy = "+ currentMap.getHoststation(i).getRawEnergy());
                            if(currentMap.getHoststation(i).getReloadConst() > 0) mapSaver.println("\treload_const = "+ (int)Math.ceil(((double)(currentMap.getHoststation(i).getReloadConst())* (70000d/255d))));
                            if(currentMap.getHoststation(i).getViewAngle() > 0) mapSaver.println("\tviewangle = "+ currentMap.getHoststation(i).getViewAngle());
                            if(currentMap.getHoststation(i).getVisibility() == false) mapSaver.println("\tmb_status = unknown");
                            mapSaver.println("\tcon_budget = "+currentMap.getHoststation(i).getConBudget());
                            mapSaver.println("\tcon_delay = "+currentMap.getHoststation(i).getRawConDelay());
                            mapSaver.println("\tdef_budget = "+currentMap.getHoststation(i).getDefBudget());
                            mapSaver.println("\tdef_delay = "+currentMap.getHoststation(i).getRawDefDelay());
                            mapSaver.println("\trec_budget = "+currentMap.getHoststation(i).getRecBudget());
                            mapSaver.println("\trec_delay = "+currentMap.getHoststation(i).getRawRecDelay());
                            mapSaver.println("\trob_budget = "+currentMap.getHoststation(i).getRobBudget());
                            mapSaver.println("\trob_delay = "+currentMap.getHoststation(i).getRawRobDelay());
                            mapSaver.println("\tpow_budget = "+currentMap.getHoststation(i).getPowBudget());
                            mapSaver.println("\tpow_delay = "+currentMap.getHoststation(i).getRawPowDelay());
                            mapSaver.println("\trad_budget = "+currentMap.getHoststation(i).getRadBudget());
                            mapSaver.println("\trad_delay = "+currentMap.getHoststation(i).getRawRadDelay());
                            mapSaver.println("\tsaf_budget = "+currentMap.getHoststation(i).getSafBudget());
                            mapSaver.println("\tsaf_delay = "+currentMap.getHoststation(i).getRawSafDelay());
                            mapSaver.println("\tcpl_budget = "+currentMap.getHoststation(i).getCplBudget());
                            mapSaver.println("\tcpl_delay = "+currentMap.getHoststation(i).getRawCplDelay());
                            mapSaver.println("end");
                        }
                    }else break;
                }
            }else mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Superitem	                                          ---");
            mapSaver.println(";------------------------------------------------------------");
            if(currentMap.getStoudsonBombList().size() > 0) {
                for(StoudsonBomb sb : currentMap.getStoudsonBombList()) {
                    mapSaver.println("begin_item");
                    mapSaver.println("\tsec_x = "+sb.getX());
                    mapSaver.println("\tsec_y = "+sb.getY());
                    mapSaver.println("\tinactive_bp = "+sb.getInactiveType());
                    mapSaver.println("\tactive_bp = "+sb.getActiveType());
                    mapSaver.println("\ttrigger_bp = "+sb.getTriggerType());
                    mapSaver.println("\ttype = 1");
                    mapSaver.println("\tcountdown = "+sb.getRawCountdown());
                    for(SpecialBuilding r : sb.getReactors()) {
                        mapSaver.println("\tkeysec_x = "+r.getX());
                        mapSaver.println("\tkeysec_y = "+r.getY());
                    }
                    mapSaver.println("end");
                }


            }else mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Predefined Squads                                    ---");
            mapSaver.println(";------------------------------------------------------------");
            if(currentMap.getSquad(0) != null) {
                for(int i = 0;;i++) {
                    if(currentMap.getSquad(i) != null) {
                        mapSaver.println("begin_squad");
                        mapSaver.println("\towner = "+currentMap.getSquad(i).getOwner());
                        mapSaver.println("\tvehicle = "+currentMap.getSquad(i).getVehicle());
                        mapSaver.println("\tnum = "+currentMap.getSquad(i).getQuantity());
                        if(currentMap.getSquad(i).getUseable() == true) mapSaver.println("\tuseable");
                        mapSaver.println("\tpos_x = "+ (int)((double)(currentMap.getSquad(i).getUnitX() + ((double)currentMap.getMapSize() * 0.14/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                        mapSaver.println("\tpos_z = "+ -(int)((double)(currentMap.getSquad(i).getUnitY() + ((double)currentMap.getMapSize() * 0.14/2.0))*(double)(1201.0 / (double)currentMap.getMapSize()) ));
                        if(currentMap.getSquad(i).getVisibility() == false) mapSaver.println("\tmb_status = unknown");
                        mapSaver.println("end");
                    }else break;
                }
            }else mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Prototype Modifications                              ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(modsString);
            //mapSaver.println();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Prototype Enabling                                   ---");
            mapSaver.println(";------------------------------------------------------------");
            if(resUnits.size() > 0 || resBuildings.size() > 0) {
                mapSaver.println("begin_enable 1");
                for(Integer u : resUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : resBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(sulgUnits.size() > 0 || sulgBuildings.size() > 0) {
                mapSaver.println("begin_enable 2");
                for(Integer u : sulgUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : sulgBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(mykoUnits.size() > 0 || mykoBuildings.size() > 0) {
                mapSaver.println("begin_enable 3");
                for(Integer u : mykoUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : mykoBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(taerUnits.size() > 0 || taerBuildings.size() > 0) {
                mapSaver.println("begin_enable 4");
                for(Integer u : taerUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : taerBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(blasecUnits.size() > 0 || blasecBuildings.size() > 0) {
                mapSaver.println("begin_enable 5");
                for(Integer u : blasecUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : blasecBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(ghorUnits.size() > 0 || ghorBuildings.size() > 0) {
                mapSaver.println("begin_enable 6");
                for(Integer u : ghorUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : ghorBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(trainingUnits.size() > 0 || trainingBuildings.size() > 0) {
                mapSaver.println("begin_enable 7");
                for(Integer u : trainingUnits) mapSaver.println("\tvehicle = "+u);
                for(Integer b : trainingBuildings) mapSaver.println("\tbuilding = "+b);
                mapSaver.println("end");
            }
            if(resUnits.size() == 0 && sulgUnits.size() == 0 && mykoUnits.size() == 0 && taerUnits.size() == 0 && blasecUnits.size() == 0 && ghorUnits.size() == 0 && trainingUnits.size() == 0 && resBuildings.size() == 0 && sulgBuildings.size() == 0 && mykoBuildings.size() == 0 && taerBuildings.size() == 0 && blasecBuildings.size() == 0 && ghorBuildings.size() == 0 && trainingBuildings.size() == 0) mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Tech Upgrades                                        ---");
            mapSaver.println(";------------------------------------------------------------");
            if(currentMap.getTechUpgradeList() != null) {
                for(TechUpgrade tu : currentMap.getTechUpgradeList()) {
                    mapSaver.println("begin_gem");
                    mapSaver.println("\tsec_x = "+tu.getX());
                    mapSaver.println("\tsec_y = "+tu.getY());
                    mapSaver.println("\tbuilding = "+tu.getBuilding());
                    if(tu.getType() != -1) mapSaver.println("\ttype = "+tu.getType());
                    if(tu.getVehicleList().size() > 0 || tu.getWeaponList().size() > 0 || tu.getBuildingList().size() > 0) {
                        mapSaver.println("\tbegin_action");
                        for(int i = 0; i < tu.getVehicleList().size(); i++) {
                            if(tu.getVehicleList().get(i).getEnergy() != 0 || tu.getVehicleList().get(i).getShield() != 0 || tu.getVehicleList().get(i).getEnable(1) == true || tu.getVehicleList().get(i).getEnable(2) == true || tu.getVehicleList().get(i).getEnable(3) == true || tu.getVehicleList().get(i).getEnable(4) == true || tu.getVehicleList().get(i).getEnable(5) == true || tu.getVehicleList().get(i).getEnable(6) == true  || tu.getVehicleList().get(i).getEnable(7) == true || tu.getVehicleList().get(i).getRadar() != 0 || tu.getVehicleList().get(i).getWeaponNum() != 0){
                                mapSaver.println("\t\tmodify_vehicle "+tu.getVehicleList().get(i).getVehicleID());
                                if(tu.getVehicleList().get(i).getEnable(1) == true) mapSaver.println("\t\t\tenable = 1");
                                if(tu.getVehicleList().get(i).getEnable(2) == true) mapSaver.println("\t\t\tenable = 2");
                                if(tu.getVehicleList().get(i).getEnable(3) == true) mapSaver.println("\t\t\tenable = 3");
                                if(tu.getVehicleList().get(i).getEnable(4) == true) mapSaver.println("\t\t\tenable = 4");
                                if(tu.getVehicleList().get(i).getEnable(5) == true) mapSaver.println("\t\t\tenable = 5");
                                if(tu.getVehicleList().get(i).getEnable(6) == true) mapSaver.println("\t\t\tenable = 6");
                                if(tu.getVehicleList().get(i).getEnable(7) == true) mapSaver.println("\t\t\tenable = 7");
                                if(tu.getVehicleList().get(i).getEnergy() != 0) mapSaver.println("\t\t\tadd_energy = "+tu.getVehicleList().get(i).getRawEnergy());
                                if(tu.getVehicleList().get(i).getShield() != 0) mapSaver.println("\t\t\tadd_shield = "+tu.getVehicleList().get(i).getShield());
                                if(tu.getVehicleList().get(i).getRadar() != 0) mapSaver.println("\t\t\tadd_radar = "+tu.getVehicleList().get(i).getRadar());
                                if(tu.getVehicleList().get(i).getWeaponNum() != 0) {
                                    mapSaver.println("\t\t\tnum_weapons = "+tu.getVehicleList().get(i).getWeaponNum());
                                    mapSaver.println("\t\t\tfire_x = "+tu.getVehicleList().get(i).getFireX());
                                    mapSaver.println("\t\t\tfire_y = "+tu.getVehicleList().get(i).getFireY());
                                    mapSaver.println("\t\t\tfire_z = "+tu.getVehicleList().get(i).getFireZ());
                                }
                                mapSaver.println("\t\tend");
                            }

                        }
                        for(int i = 0; i < tu.getWeaponList().size(); i++) {
                            if(tu.getWeaponList().get(i).getEnergy() != 0 || tu.getWeaponList().get(i).getShotTime() != 0 || tu.getWeaponList().get(i).getShotTimeUser() != 0){
                                mapSaver.println("\t\tmodify_weapon "+tu.getWeaponList().get(i).getWeaponID());
                                if(tu.getWeaponList().get(i).getEnergy() != 0) mapSaver.println("\t\t\tadd_energy = "+tu.getWeaponList().get(i).getRawEnergy());
                                if(tu.getWeaponList().get(i).getShotTime() != 0) mapSaver.println("\t\t\tadd_shot_time = "+tu.getWeaponList().get(i).getShotTime());
                                if(tu.getWeaponList().get(i).getShotTimeUser() != 0) mapSaver.println("\t\t\tadd_shot_time_user = "+tu.getWeaponList().get(i).getShotTimeUser());
                                mapSaver.println("\t\tend");
                            }
                        }
                        for(int i = 0; i < tu.getBuildingList().size(); i++) {
                            if(tu.getBuildingList().get(i).getEnable(1) == true || tu.getBuildingList().get(i).getEnable(2) == true || tu.getBuildingList().get(i).getEnable(3) == true || tu.getBuildingList().get(i).getEnable(4) == true || tu.getBuildingList().get(i).getEnable(5) == true || tu.getBuildingList().get(i).getEnable(6) == true || tu.getBuildingList().get(i).getEnable(7) == true) {
                                mapSaver.println("\t\tmodify_building "+tu.getBuildingList().get(i).getBuildingID());
                                if(tu.getBuildingList().get(i).getEnable(1) == true) mapSaver.println("\t\t\tenable = 1");
                                if(tu.getBuildingList().get(i).getEnable(2) == true) mapSaver.println("\t\t\tenable = 2");
                                if(tu.getBuildingList().get(i).getEnable(3) == true) mapSaver.println("\t\t\tenable = 3");
                                if(tu.getBuildingList().get(i).getEnable(4) == true) mapSaver.println("\t\t\tenable = 4");
                                if(tu.getBuildingList().get(i).getEnable(5) == true) mapSaver.println("\t\t\tenable = 5");
                                if(tu.getBuildingList().get(i).getEnable(6) == true) mapSaver.println("\t\t\tenable = 6");
                                if(tu.getBuildingList().get(i).getEnable(7) == true) mapSaver.println("\t\t\tenable = 7");
                                mapSaver.println("\t\tend");
                            }
                        }
                        mapSaver.println("\tend_action");
                    }
                    if(tu.getVisibility() == false) mapSaver.println("\tmb_status = unknown");
                    mapSaver.println("end");
                }
            }else mapSaver.println(";none");

            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- Map Dumps                                            ---");
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println("begin_maps");
            mapSaver.println("    typ_map =");
            mapSaver.println("        "+(currentMap.getHorizontalGrid()+2)+" "+(currentMap.getVerticalGrid()+2));
            mapSaver.print("        f8 ");
            for(int i = 0; i < currentMap.getHorizontalGrid(); i++) mapSaver.print("fc ");
            mapSaver.println("f9 ");
            for(int i = 0,j = 0;i < currentMap.getVerticalGrid(); i++) {
                mapSaver.print("        ff ");
                for(int k = 0; k < currentMap.getHorizontalGrid(); k++, j++) {
                    if(currentMap.getTypMap(j) < 16) mapSaver.print("0"+Integer.toHexString(currentMap.getTypMap(j)) + " ");
                    else mapSaver.print(Integer.toHexString(currentMap.getTypMap(j)) + " ");
                }
                mapSaver.println("fd ");
            }
            mapSaver.print("        fb ");
            for(int i = 0; i < currentMap.getHorizontalGrid(); i++) mapSaver.print("fe ");
            mapSaver.println("fa ");
            mapSaver.println("    own_map =");
            mapSaver.println("        "+(currentMap.getHorizontalGrid()+2)+" "+(currentMap.getVerticalGrid()+2));
            mapSaver.print("        ");
            for(int i = 0; i < currentMap.getHorizontalGrid()+1;i++) mapSaver.print("00 ");
            mapSaver.println("00 ");
            for(int i = 0, k = 0; i < currentMap.getVerticalGrid(); i++) {
                mapSaver.print("        00 ");
                for(int j = 0; j < currentMap.getHorizontalGrid(); j++, k++) {
                    mapSaver.print("0"+currentMap.getOwnMap(k)+" ");
                }
                mapSaver.println("00 ");
            }
            mapSaver.print("        ");
            for(int i = 0; i < currentMap.getHorizontalGrid()+1;i++) mapSaver.print("00 ");
            mapSaver.println("00 ");
            mapSaver.println("    hgt_map = ");
            mapSaver.println("        "+(currentMap.getHorizontalGrid()+2)+" "+(currentMap.getVerticalGrid()+2));
            for(int i = 0, k = 0; i < currentMap.getVerticalGrid()+2; i++) {
                mapSaver.print("        ");
                for(int j = 0; j < currentMap.getHorizontalGrid()+2; j++, k++) {
                    if(currentMap.getHgtMap(k) < 16) mapSaver.print("0"+Integer.toHexString(currentMap.getHgtMap(k))+" ");
                    else mapSaver.print(Integer.toHexString(currentMap.getHgtMap(k))+" ");
                }
                mapSaver.println();
            }
            mapSaver.println("    blg_map = ");
            mapSaver.println("        "+(currentMap.getHorizontalGrid()+2)+" "+(currentMap.getVerticalGrid()+2));
            mapSaver.print("        ");
            for(int i = 0; i < currentMap.getHorizontalGrid()+2; i++) mapSaver.print("00 ");
            mapSaver.println();

            for(int i = 0, k = 0; i < currentMap.getVerticalGrid(); i++) {
                mapSaver.print("        00 ");
                for(int j = 0; j < currentMap.getHorizontalGrid(); j++, k++) {
                    if(currentMap.getBlgMap(k) < 16) mapSaver.print("0"+Integer.toHexString(currentMap.getBlgMap(k))+" ");
                    else mapSaver.print(Integer.toHexString(currentMap.getBlgMap(k))+" ");
                }
                mapSaver.println("00 ");
            }
            mapSaver.print("        ");
            for(int i = 0; i < currentMap.getHorizontalGrid()+2; i++) mapSaver.print("00 ");
            mapSaver.println();
            mapSaver.println("; ------------------------ ");
            mapSaver.println(";--- map dumps end here ---");
            mapSaver.println("; ------------------------ ");
            mapSaver.println("end");
            mapSaver.println();
            mapSaver.println(";------------------------------------------------------------");
            mapSaver.println(";--- End Of File                                          ---");
            mapSaver.println(";------------------------------------------------------------");

            this.setTitle(f+" ("+currentMap.getHorizontalGrid()+"x"+currentMap.getVerticalGrid()+") - Urban Assault Level Editor");

            mapSaver.close();
            saved = true;
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(newMapDialog,"An I/O Error Occurred while saving the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
