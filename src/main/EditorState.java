package main;

import UAstructures.BeamGate;
import UAstructures.StoudsonBomb;
import UAstructures.TechUpgrade;
import UAstructures.Unit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditorState {

    public static int sectorSize = 50;
    public static int sectorIndent = 2;

    public static boolean isSaved;
    public static int playerSelected;
    public static int gameContent; // 0 is original, 1 is Metropolis Dawn

    public static String description;
    public static ArrayList<BeamGate> beamGates = new ArrayList<>();
    public static ArrayList<StoudsonBomb> bombs = new ArrayList<>();
    public static ArrayList<TechUpgrade> techUpgrades = new ArrayList<>();
    public static ArrayList<Unit> hostStations = new ArrayList<>();
    public static ArrayList<Unit> predefinedSquads = new ArrayList<>();

    public static int set;
    public static String movie;
    public static int eventLoop;
    public static String sky;
    public static int music;
    public static int min_break;
    public static int max_break;

    public static String mbmap;
    public static int mbMapSizeX;
    public static int mbMapSizeY;
    public static String dbmap;
    public static int dbMapSizeX;
    public static int dbMapSizeY;

    public static String prototypeModifications;

    public static ArrayList<Integer> resistanceUnits = new ArrayList<>();
    public static ArrayList<Integer> ghorkovUnits = new ArrayList<>();
    public static ArrayList<Integer> taerkastenUnits = new ArrayList<>();
    public static ArrayList<Integer> mykonianUnits = new ArrayList<>();
    public static ArrayList<Integer> sulgogarUnits = new ArrayList<>();
    public static ArrayList<Integer> blackSectUnits = new ArrayList<>();
    public static ArrayList<Integer> trainingUnits = new ArrayList<>();
    public static ArrayList<Integer> resistanceBuildings = new ArrayList<>();
    public static ArrayList<Integer> ghorkovBuildings = new ArrayList<>();
    public static ArrayList<Integer> taerkastenBuildings = new ArrayList<>();
    public static ArrayList<Integer> mykonianBuildings = new ArrayList<>();
    public static ArrayList<Integer> sulgogarBuildings = new ArrayList<>();
    public static ArrayList<Integer> blackSectBuildings = new ArrayList<>();
    public static ArrayList<Integer> trainingBuildings = new ArrayList<>();

    public static int horizontalSectors;
    public static int verticalSectors;
    public static ArrayList<Integer> typ_map = new ArrayList<>();
    public static ArrayList<Integer> own_map = new ArrayList<>();
    public static ArrayList<Integer> hgt_map = new ArrayList<>();
    public static ArrayList<Integer> blg_map = new ArrayList<>();

    public static BeamGate getBeamGate(int x, int y) {
        for(BeamGate bg : beamGates) {
            if(bg.getX() == x && bg.getY() == y) return bg;
        }
        return null;
    }
    public static TechUpgrade getTechUpgrade(int x, int y) {
        for(TechUpgrade tu : techUpgrades) {
            if(tu.getX() == x && tu.getY() == y) return tu;
        }
        return null;
    }
    public static StoudsonBomb getStoudsonBomb(int x, int y) {
        for(StoudsonBomb bomb : bombs) {
            if(bomb.getX() == x && bomb.getY() == y) return bomb;
        }
        return null;
    }
    public static int getStoudsonBombNumber(int x, int y) {
        for(int i = 0; i < bombs.size(); i++){
            if(bombs.get(i).getX() == x && bombs.get(i).getY() == y) return i;
        }
        return -1;
    }

    public static void resetState() {
        isSaved = true;
        playerSelected = 0;
        gameContent = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        description = "------ Level name: \\n------ Created on: "+dtf.format(now)+" \\n------ Designed By: ";
        beamGates.clear();
        bombs.clear();
        techUpgrades.clear();
        hostStations.clear();
        predefinedSquads.clear();

        set = 1;
        movie = "";
        eventLoop = 0;
        sky = "1998_01";
        music = 0;
        min_break = 0;
        max_break = 0;

        mbmap = "mb.ilb";
        mbMapSizeX = 480;
        mbMapSizeY = 480;
        dbmap = "db_01.iff";
        dbMapSizeX = 480;
        dbMapSizeY = 480;

        prototypeModifications = "include data:scripts/startup2.scr";

        resistanceUnits.clear();
        ghorkovUnits.clear();
        taerkastenUnits.clear();
        mykonianUnits.clear();
        sulgogarUnits.clear();
        blackSectUnits.clear();
        trainingUnits.clear();
        resistanceBuildings.clear();
        ghorkovBuildings.clear();
        taerkastenBuildings.clear();
        mykonianBuildings.clear();
        sulgogarBuildings.clear();
        blackSectBuildings.clear();
        trainingBuildings.clear();

        resistanceUnits.add(16);
        ghorkovUnits.add(24);
        taerkastenUnits.add(32);
        mykonianUnits.add(65);
        sulgogarUnits.add(73);
        trainingUnits.add(138);

        horizontalSectors = 0;
        verticalSectors = 0;
        typ_map.clear();
        own_map.clear();
        hgt_map.clear();
        blg_map.clear();
    }
}
