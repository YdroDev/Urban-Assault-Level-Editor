package LevelIO;

import UAstructures.*;
import main.*;

public class SingleplayerLevelSaver extends LevelSaver {

    void handleHeader(){
        mapSaver.println(";#*+ don't edit the magic runes");
        mapSaver.println();
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Original Header                                      ---");
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";");
    }
    void handleDescription(){
        mapSaver.println(";"+EditorState.description.replace("\n", "\n;"));
        mapSaver.println();
    }
    void handleLevelParameters() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Main Level Info                                      ---");
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println("begin_level");
        mapSaver.println("\tset = " + EditorState.set);
        mapSaver.println("\tsky = "+"objects/"+EditorState.sky+".base");
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
    }
    void handleBriefingMaps() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Mission Briefing Maps                                ---");
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println("begin_mbmap");
        if(EditorState.gameContent == 0) {
            if(EditorState.mbmap == "mb") mapSaver.println("\tname = "+EditorState.mbmap+".ilb");
            else mapSaver.println("\tname = "+EditorState.mbmap+".iff");
        }else if(EditorState.gameContent == 1) {
            mapSaver.println("\tname = "+EditorState.mbmap+".iff");
        }
        if(EditorState.mbMapSizeX > 0) mapSaver.println("\tsize_x = "+EditorState.mbMapSizeX);
        if(EditorState.mbMapSizeY > 0) mapSaver.println("\tsize_y = "+EditorState.mbMapSizeY);
        mapSaver.println("end");
        mapSaver.println("begin_dbmap");
        mapSaver.println(" \tname = "+EditorState.dbmap+".iff");
        if(EditorState.dbMapSizeX > 0) mapSaver.println("\tsize_x = "+EditorState.dbMapSizeX);
        if(EditorState.dbMapSizeY > 0) mapSaver.println("\tsize_y = "+EditorState.dbMapSizeY);
        mapSaver.println("end");
    }
    void handleBeamGates() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Beam Gates                                           ---");
        mapSaver.println(";------------------------------------------------------------");
        if(EditorState.beamGates.size() > 0) {
            for(BeamGate bg : EditorState.beamGates) {
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
                if(!bg.getVisibility()) mapSaver.println("\tmb_status = unknown");
                mapSaver.println("end");
            }
        }else mapSaver.println(";none");
    }
    void handleHostStations() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Robo Definitions                                     ---");
        mapSaver.println(";------------------------------------------------------------");
        Hoststation playerHoststation = (Hoststation)EditorState.hostStations.get(EditorState.playerSelected);
        if(playerHoststation != null) {
            mapSaver.println("begin_robo");
            mapSaver.println("\towner = "+playerHoststation.getOwner());
            if(EditorState.gameContent == 0 && playerHoststation.getVehicle() == 176)
                playerHoststation.setVehicle(59);
            else if(EditorState.gameContent == 0 && playerHoststation.getVehicle() == 177)
                playerHoststation.setVehicle(57);
            else if(EditorState.gameContent == 0 && playerHoststation.getVehicle() == 178)
                playerHoststation.setVehicle(60);
            if(EditorState.gameContent == 1 && playerHoststation.getVehicle() == 59)
                playerHoststation.setVehicle(176);
            else if(EditorState.gameContent == 1 && playerHoststation.getVehicle() == 57)
                playerHoststation.setVehicle(177);
            else if(EditorState.gameContent == 1 && playerHoststation.getVehicle() == 60)
                playerHoststation.setVehicle(178);

            mapSaver.println("\tvehicle = "+playerHoststation.getVehicle());
            double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
            // coords transfer formula: HoststationX * (1201 / mapSize)  the same for HoststationY
            mapSaver.println("\tpos_x = "+ (int)((playerHoststation.getUnitX() + (fullSectorSize * 0.5/2.0))*(1201.0 / fullSectorSize) ));
            mapSaver.println("\tpos_y = "+ -playerHoststation.getHeight());
            mapSaver.println("\tpos_z = "+ -(int)((playerHoststation.getUnitY() + (fullSectorSize * 0.5/2.0))*(1201.0 / fullSectorSize) ));
            mapSaver.println("\tenergy = "+ playerHoststation.getRawEnergy());
            if(playerHoststation.getReloadConst() > 0) mapSaver.println("\treload_const = "+ (int)Math.ceil(((double)(playerHoststation.getReloadConst())* (60000d/255d))));
            if(playerHoststation.getViewAngle() > 0) mapSaver.println("\tviewangle = "+ playerHoststation.getViewAngle());
            if(!playerHoststation.getVisibility()) mapSaver.println("\tmb_status = unknown");
            mapSaver.println("end");

            for(int i = 0;;i++) {
                Hoststation hoststation = (Hoststation)EditorState.hostStations.get(i);
                if(hoststation != null) {
                    if(i != EditorState.playerSelected) {
                        mapSaver.println("begin_robo");
                        mapSaver.println("\towner = "+hoststation.getOwner());
                        if(hoststation.getVehicle() == 176)
                            hoststation.setVehicle(59);
                        else if(hoststation.getVehicle() == 177)
                            hoststation.setVehicle(57);
                        else if(hoststation.getVehicle() == 178)
                            hoststation.setVehicle(60);
                        mapSaver.println("\tvehicle = "+hoststation.getVehicle());
                        // coords transfer formula: HoststationX * (1201 / mapSize)  the same for HoststationY
                        mapSaver.println("\tpos_x = "+ (int)((hoststation.getUnitX() + (fullSectorSize * 0.5/2.0))*(1201.0 / fullSectorSize)));
                        mapSaver.println("\tpos_y = "+ -hoststation.getHeight());
                        mapSaver.println("\tpos_z = "+ -(int)((hoststation.getUnitY() + (fullSectorSize * 0.5/2.0))*(1201.0 / fullSectorSize) ));
                        mapSaver.println("\tenergy = "+ hoststation.getRawEnergy());
                        if(hoststation.getReloadConst() > 0) mapSaver.println("\treload_const = "+ (int)Math.ceil(((double)(hoststation.getReloadConst())* (70000d/255d))));
                        if(hoststation.getViewAngle() > 0) mapSaver.println("\tviewangle = "+ hoststation.getViewAngle());
                        if(!hoststation.getVisibility()) mapSaver.println("\tmb_status = unknown");
                        mapSaver.println("\tcon_budget = "+hoststation.getConBudget());
                        mapSaver.println("\tcon_delay = "+hoststation.getRawConDelay());
                        mapSaver.println("\tdef_budget = "+hoststation.getDefBudget());
                        mapSaver.println("\tdef_delay = "+hoststation.getRawDefDelay());
                        mapSaver.println("\trec_budget = "+hoststation.getRecBudget());
                        mapSaver.println("\trec_delay = "+hoststation.getRawRecDelay());
                        mapSaver.println("\trob_budget = "+hoststation.getRobBudget());
                        mapSaver.println("\trob_delay = "+hoststation.getRawRobDelay());
                        mapSaver.println("\tpow_budget = "+hoststation.getPowBudget());
                        mapSaver.println("\tpow_delay = "+hoststation.getRawPowDelay());
                        mapSaver.println("\trad_budget = "+hoststation.getRadBudget());
                        mapSaver.println("\trad_delay = "+hoststation.getRawRadDelay());
                        mapSaver.println("\tsaf_budget = "+hoststation.getSafBudget());
                        mapSaver.println("\tsaf_delay = "+hoststation.getRawSafDelay());
                        mapSaver.println("\tcpl_budget = "+hoststation.getCplBudget());
                        mapSaver.println("\tcpl_delay = "+hoststation.getRawCplDelay());
                        mapSaver.println("end");
                    }
                }else break;
            }
        }else mapSaver.println(";none");
    }
    void handleBombs() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Superitem	                                          ---");
        mapSaver.println(";------------------------------------------------------------");
        if(EditorState.bombs.size() > 0) {
            for(StoudsonBomb sb : EditorState.bombs) {
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
    }
    void handlePredefinedSquads() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Predefined Squads                                    ---");
        mapSaver.println(";------------------------------------------------------------");
        if(EditorState.predefinedSquads.size() > 0) {
            for(int i = 0;;i++) {
                Squad squad = (Squad)EditorState.predefinedSquads.get(i);
                if(squad != null) {
                    mapSaver.println("begin_squad");
                    mapSaver.println("\towner = "+squad.getOwner());
                    mapSaver.println("\tvehicle = "+squad.getVehicle());
                    mapSaver.println("\tnum = "+squad.getQuantity());
                    if(squad.getUseable()) mapSaver.println("\tuseable");
                    double fullSectorSize = EditorState.sectorSize + EditorState.sectorIndent;
                    mapSaver.println("\tpos_x = "+ (int)((squad.getUnitX() + (fullSectorSize * 0.14/2.0))*(1201.0 / fullSectorSize) ));
                    mapSaver.println("\tpos_z = "+ -(int)((squad.getUnitY() + (fullSectorSize * 0.14/2.0))*(1201.0 / fullSectorSize) ));
                    if(!squad.getVisibility()) mapSaver.println("\tmb_status = unknown");
                    mapSaver.println("end");
                }else break;
            }
        }else mapSaver.println(";none");
    }
    void handleModifications() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Prototype Modifications                              ---");
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(EditorState.prototypeModifications);
    }
    void handlePrototypeEnabling() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Prototype Enabling                                   ---");
        mapSaver.println(";------------------------------------------------------------");
        if(EditorState.resistanceUnits.size() > 0 || EditorState.resistanceBuildings.size() > 0) {
            mapSaver.println("begin_enable 1");
            for(Integer u : EditorState.resistanceUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.resistanceBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.sulgogarUnits.size() > 0 || EditorState.sulgogarBuildings.size() > 0) {
            mapSaver.println("begin_enable 2");
            for(Integer u : EditorState.sulgogarUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.sulgogarBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.mykonianUnits.size() > 0 || EditorState.mykonianBuildings.size() > 0) {
            mapSaver.println("begin_enable 3");
            for(Integer u : EditorState.mykonianUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.mykonianBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.taerkastenUnits.size() > 0 || EditorState.taerkastenBuildings.size() > 0) {
            mapSaver.println("begin_enable 4");
            for(Integer u : EditorState.taerkastenUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.taerkastenBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.blackSectUnits.size() > 0 || EditorState.blackSectBuildings.size() > 0) {
            mapSaver.println("begin_enable 5");
            for(Integer u : EditorState.blackSectUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.blackSectBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.ghorkovUnits.size() > 0 || EditorState.ghorkovBuildings.size() > 0) {
            mapSaver.println("begin_enable 6");
            for(Integer u : EditorState.ghorkovUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.ghorkovBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.trainingUnits.size() > 0 || EditorState.trainingBuildings.size() > 0) {
            mapSaver.println("begin_enable 7");
            for(Integer u : EditorState.trainingUnits) mapSaver.println("\tvehicle = "+u);
            for(Integer b : EditorState.trainingBuildings) mapSaver.println("\tbuilding = "+b);
            mapSaver.println("end");
        }
        if(EditorState.resistanceUnits.size() == 0 && EditorState.sulgogarUnits.size() == 0 && EditorState.mykonianUnits.size() == 0 && EditorState.taerkastenUnits.size() == 0 && EditorState.blackSectUnits.size() == 0 && EditorState.ghorkovUnits.size() == 0 && EditorState.trainingUnits.size() == 0 && EditorState.resistanceBuildings.size() == 0 && EditorState.sulgogarBuildings.size() == 0 && EditorState.mykonianBuildings.size() == 0 && EditorState.taerkastenBuildings.size() == 0 && EditorState.blackSectBuildings.size() == 0 && EditorState.ghorkovBuildings.size() == 0 && EditorState.trainingBuildings.size() == 0) mapSaver.println(";none");
    }
    void handleTechUpgrades() {
        mapSaver.println(";------------------------------------------------------------");
        mapSaver.println(";--- Tech Upgrades                                        ---");
        mapSaver.println(";------------------------------------------------------------");

        if(EditorState.techUpgrades.size() > 0) {
            for(TechUpgrade tu : EditorState.techUpgrades) {
                mapSaver.println("begin_gem");
                mapSaver.println("\tsec_x = "+tu.getX());
                mapSaver.println("\tsec_y = "+tu.getY());
                mapSaver.println("\tbuilding = "+tu.getBuilding());
                if(tu.getType() != -1) mapSaver.println("\ttype = "+tu.getType());
                if(tu.getVehicleList().size() > 0 || tu.getWeaponList().size() > 0 || tu.getBuildingList().size() > 0) {
                    mapSaver.println("\tbegin_action");
                    for(int i = 0; i < tu.getVehicleList().size(); i++) {
                        if(tu.getVehicleList().get(i).getEnergy() != 0 || tu.getVehicleList().get(i).getShield() != 0 || tu.getVehicleList().get(i).getEnable(1) || tu.getVehicleList().get(i).getEnable(2) || tu.getVehicleList().get(i).getEnable(3) || tu.getVehicleList().get(i).getEnable(4) || tu.getVehicleList().get(i).getEnable(5) || tu.getVehicleList().get(i).getEnable(6) || tu.getVehicleList().get(i).getEnable(7) || tu.getVehicleList().get(i).getRadar() != 0 || tu.getVehicleList().get(i).getWeaponNum() != 0){
                            mapSaver.println("\t\tmodify_vehicle "+tu.getVehicleList().get(i).getVehicleID());
                            if(tu.getVehicleList().get(i).getEnable(1)) mapSaver.println("\t\t\tenable = 1");
                            if(tu.getVehicleList().get(i).getEnable(2)) mapSaver.println("\t\t\tenable = 2");
                            if(tu.getVehicleList().get(i).getEnable(3)) mapSaver.println("\t\t\tenable = 3");
                            if(tu.getVehicleList().get(i).getEnable(4)) mapSaver.println("\t\t\tenable = 4");
                            if(tu.getVehicleList().get(i).getEnable(5)) mapSaver.println("\t\t\tenable = 5");
                            if(tu.getVehicleList().get(i).getEnable(6)) mapSaver.println("\t\t\tenable = 6");
                            if(tu.getVehicleList().get(i).getEnable(7)) mapSaver.println("\t\t\tenable = 7");
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
                        if(tu.getBuildingList().get(i).getEnable(1) || tu.getBuildingList().get(i).getEnable(2) || tu.getBuildingList().get(i).getEnable(3) || tu.getBuildingList().get(i).getEnable(4) || tu.getBuildingList().get(i).getEnable(5) || tu.getBuildingList().get(i).getEnable(6) || tu.getBuildingList().get(i).getEnable(7)) {
                            mapSaver.println("\t\tmodify_building "+tu.getBuildingList().get(i).getBuildingID());
                            if(tu.getBuildingList().get(i).getEnable(1)) mapSaver.println("\t\t\tenable = 1");
                            if(tu.getBuildingList().get(i).getEnable(2)) mapSaver.println("\t\t\tenable = 2");
                            if(tu.getBuildingList().get(i).getEnable(3)) mapSaver.println("\t\t\tenable = 3");
                            if(tu.getBuildingList().get(i).getEnable(4)) mapSaver.println("\t\t\tenable = 4");
                            if(tu.getBuildingList().get(i).getEnable(5)) mapSaver.println("\t\t\tenable = 5");
                            if(tu.getBuildingList().get(i).getEnable(6)) mapSaver.println("\t\t\tenable = 6");
                            if(tu.getBuildingList().get(i).getEnable(7)) mapSaver.println("\t\t\tenable = 7");
                            mapSaver.println("\t\tend");
                        }
                    }
                    mapSaver.println("\tend_action");
                }
                if(!tu.getVisibility()) mapSaver.println("\tmb_status = unknown");
                mapSaver.println("end");
            }
        }else mapSaver.println(";none");
    }
    void handleTypMap() {
        mapSaver.println("    typ_map =");
        mapSaver.println("        "+(EditorState.horizontalSectors+2)+" "+(EditorState.verticalSectors+2));
        mapSaver.print("        f8 ");
        for(int i = 0; i < EditorState.horizontalSectors; i++) mapSaver.print("fc ");
        mapSaver.println("f9 ");
        for(int i = 0,j = 0;i < EditorState.verticalSectors; i++) {
            mapSaver.print("        ff ");
            for(int k = 0; k < EditorState.horizontalSectors; k++, j++) {
                if(EditorState.typ_map.get(j) < 16) mapSaver.print("0"+Integer.toHexString(EditorState.typ_map.get(j)) + " ");
                else mapSaver.print(Integer.toHexString(EditorState.typ_map.get(j)) + " ");
            }
            mapSaver.println("fd ");
        }
        mapSaver.print("        fb ");
        for(int i = 0; i < EditorState.horizontalSectors; i++) mapSaver.print("fe ");
        mapSaver.println("fa ");
    }
    void handleOwnMap() {
        mapSaver.println("    own_map =");
        mapSaver.println("        "+(EditorState.horizontalSectors+2)+" "+(EditorState.verticalSectors+2));
        mapSaver.print("        ");
        for(int i = 0; i < EditorState.horizontalSectors+1;i++) mapSaver.print("00 ");
        mapSaver.println("00 ");
        for(int i = 0, k = 0; i < EditorState.verticalSectors; i++) {
            mapSaver.print("        00 ");
            for(int j = 0; j < EditorState.horizontalSectors; j++, k++) {
                mapSaver.print("0"+EditorState.own_map.get(k)+" ");
            }
            mapSaver.println("00 ");
        }
        mapSaver.print("        ");
        for(int i = 0; i < EditorState.horizontalSectors+1;i++) mapSaver.print("00 ");
        mapSaver.println("00 ");
    }
    void handleHgtMap() {
        mapSaver.println("    hgt_map = ");
        mapSaver.println("        "+(EditorState.horizontalSectors+2)+" "+(EditorState.verticalSectors+2));
        for(int i = 0, k = 0; i < EditorState.verticalSectors+2; i++) {
            mapSaver.print("        ");
            for(int j = 0; j < EditorState.horizontalSectors+2; j++, k++) {
                if(EditorState.hgt_map.get(k) < 16) mapSaver.print("0"+Integer.toHexString(EditorState.hgt_map.get(k))+" ");
                else mapSaver.print(Integer.toHexString(EditorState.hgt_map.get(k))+" ");
            }
            mapSaver.println();
        }
    }
    void handleBlgMap() {
        mapSaver.println("    blg_map = ");
        mapSaver.println("        "+(EditorState.horizontalSectors+2)+" "+(EditorState.verticalSectors+2));
        mapSaver.print("        ");
        for(int i = 0; i < EditorState.horizontalSectors+2; i++) mapSaver.print("00 ");
        mapSaver.println();

        for(int i = 0, k = 0; i < EditorState.verticalSectors; i++) {
            mapSaver.print("        00 ");
            for(int j = 0; j < EditorState.horizontalSectors; j++, k++) {
                if(EditorState.blg_map.get(k) < 16) mapSaver.print("0"+Integer.toHexString(EditorState.blg_map.get(k))+" ");
                else mapSaver.print(Integer.toHexString(EditorState.blg_map.get(k))+" ");
            }
            mapSaver.println("00 ");
        }
        mapSaver.print("        ");
        for(int i = 0; i < EditorState.horizontalSectors+2; i++) mapSaver.print("00 ");
        mapSaver.println();
    }
}
