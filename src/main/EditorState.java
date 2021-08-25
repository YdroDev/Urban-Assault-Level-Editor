package main;

import UAstructures.BeamGate;
import UAstructures.StoudsonBomb;
import UAstructures.TechUpgrade;
import UAstructures.Unit;

import java.util.ArrayList;

public class EditorState {

    public static boolean isSaved;

    public static String description;
    public static ArrayList<BeamGate> beamGates;
    public static ArrayList<StoudsonBomb> bombs;
    public static ArrayList<TechUpgrade> techUpgrades;
    public static ArrayList<Unit> hostStations;
    public static ArrayList<Unit> predefinedSquads;
    public static int hostStationForPlayer;

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

    public static ArrayList<Integer> resistanceUnits;
    public static ArrayList<Integer> ghorkovUnits;
    public static ArrayList<Integer> taerkastenUnits;
    public static ArrayList<Integer> mykonianUnits;
    public static ArrayList<Integer> sulgogarUnits;
    public static ArrayList<Integer> blackSectUnits;
    public static ArrayList<Integer> trainingUnits;
    public static ArrayList<Integer> resistanceBuildings;
    public static ArrayList<Integer> ghorkovBuildings;
    public static ArrayList<Integer> taerkastenBuildings;
    public static ArrayList<Integer> mykonianBuildings;
    public static ArrayList<Integer> sulgogarBuildings;
    public static ArrayList<Integer> blackSectBuildings;
    public static ArrayList<Integer> trainingBuildings;

    public static int horizontalSectors = 0;
    public static int verticalSectors = 0;
    public static ArrayList<Integer> typ_map;
    public static ArrayList<Integer> own_map;
    public static ArrayList<Integer> hgt_map;
    public static ArrayList<Integer> blg_map;

    public static void resetState() {
        isSaved = true;

        description = "";
        beamGates = new ArrayList<BeamGate>();
        bombs = new ArrayList<StoudsonBomb>();
        techUpgrades = new ArrayList<TechUpgrade>();
        hostStations = new ArrayList<Unit>();
        predefinedSquads = new ArrayList<Unit>();
        hostStationForPlayer = 0;

        set = 1;
        movie = "";
        eventLoop = 0;
        sky = "1998_01";
        music = 0;
        min_break = 0;
        max_break = 0;

        mbmap = "mb";
        mbMapSizeX = 480;
        mbMapSizeY = 480;
        dbmap = "db_01";
        dbMapSizeX = 480;
        dbMapSizeY = 480;

        prototypeModifications = "include data:scripts/startup2.scr";

        resistanceUnits = new ArrayList<Integer>();
        ghorkovUnits = new ArrayList<Integer>();
        taerkastenUnits = new ArrayList<Integer>();
        mykonianUnits = new ArrayList<Integer>();
        sulgogarUnits = new ArrayList<Integer>();
        blackSectUnits = new ArrayList<Integer>();
        trainingUnits = new ArrayList<Integer>();
        resistanceBuildings = new ArrayList<Integer>();
        ghorkovBuildings = new ArrayList<Integer>();
        taerkastenBuildings = new ArrayList<Integer>();
        mykonianBuildings = new ArrayList<Integer>();
        sulgogarBuildings = new ArrayList<Integer>();
        blackSectBuildings = new ArrayList<Integer>();
        trainingBuildings = new ArrayList<Integer>();

        resistanceUnits.add(16);
        ghorkovUnits.add(24);
        taerkastenUnits.add(32);
        mykonianUnits.add(65);
        sulgogarUnits.add(73);
        trainingUnits.add(138);

        horizontalSectors = 0;
        verticalSectors = 0;
        typ_map = new ArrayList<Integer>();
        own_map = new ArrayList<Integer>();
        hgt_map = new ArrayList<Integer>();
        blg_map = new ArrayList<Integer>();
    }
}
