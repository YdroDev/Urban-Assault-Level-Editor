package main;

import java.util.ArrayList;

public class EditorState {

    public static String description;
    public static ArrayList<BeamGate> beamGates = new ArrayList<BeamGate>();
    public static ArrayList<StoudsonBomb> bombs = new ArrayList<StoudsonBomb>();
    public static ArrayList<TechUpgrade> techUpgrades = new ArrayList<TechUpgrade>();
    public static ArrayList<Unit> hostStations = new ArrayList<Unit>();
    public static ArrayList<Unit> predefinedSquads = new ArrayList<Unit>();
    public static int hostStationForPlayer = 0;

    public static int set = 1;
    public static String movie = "";
    public static int eventLoop = 0;
    public static String sky = "1998_01";
    public static int music = 0;
    public static int min_break = 0;
    public static int max_break = 0;

    public static String mbmap = "mb";
    public static int mbMapSizeX = 480;
    public static int mbMapSizeY = 480;
    public static String dbmap = "db_01";
    public static int dbMapSizeX = 480;
    public static int dbMapSizeY = 480;

    public static String prototypeModifications = "include data:scripts/startup2.scr";

    public static ArrayList<Integer> resistanceUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> ghorkovUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> taerkastenUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> mykonianUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> sulgogarUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> blackSectUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> trainingUnits = new ArrayList<Integer>();
    public static ArrayList<Integer> resistanceBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> ghorkovBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> taerkastenBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> mykonianBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> sulgogarBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> blackSectBuildings = new ArrayList<Integer>();
    public static ArrayList<Integer> trainingBuildings = new ArrayList<Integer>();

    public static int horizontalSectors = 0;
    public static int verticalSectors = 0;
    public static ArrayList<Integer> typ_map = new ArrayList<Integer>();
    public static ArrayList<Integer> own_map = new ArrayList<Integer>();
    public static ArrayList<Integer> hgt_map = new ArrayList<Integer>();
    public static ArrayList<Integer> blg_map = new ArrayList<Integer>();
}
