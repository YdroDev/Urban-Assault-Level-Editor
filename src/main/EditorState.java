package main;

import UAstructures.BeamGate;
import UAstructures.StoudsonBomb;
import UAstructures.TechUpgrade;
import UAstructures.Unit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditorState {

    public static boolean isSaved;
    public static int playerSelected;

    public static String description;
    public static ArrayList<BeamGate> beamGates = new ArrayList<BeamGate>();;
    public static ArrayList<StoudsonBomb> bombs = new ArrayList<StoudsonBomb>();
    public static ArrayList<TechUpgrade> techUpgrades = new ArrayList<TechUpgrade>();
    public static ArrayList<Unit> hostStations = new ArrayList<Unit>();
    public static ArrayList<Unit> predefinedSquads = new ArrayList<Unit>();
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

    public static int horizontalSectors = 0;
    public static int verticalSectors = 0;
    public static ArrayList<Integer> typ_map = new ArrayList<>();
    public static ArrayList<Integer> own_map = new ArrayList<>();
    public static ArrayList<Integer> hgt_map = new ArrayList<>();
    public static ArrayList<Integer> blg_map = new ArrayList<>();

    public static void resetState() {
        isSaved = true;
        playerSelected = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        description = "------ Level name: \\n------ Created on: \"+dtf.format(now)+\" \\n------ Designed By: ";
        beamGates.clear();
        bombs.clear();
        techUpgrades.clear();
        hostStations.clear();
        predefinedSquads.clear();
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
