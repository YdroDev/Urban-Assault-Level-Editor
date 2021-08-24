import java.util.ArrayList;

public class UAstate {

    static String description = "";
    static ArrayList<BeamGate> beamGates = new ArrayList<BeamGate>();
    static ArrayList<StoudsonBomb> bombs = new ArrayList<StoudsonBomb>();
    static ArrayList<TechUpgrade> techUpgrades = new ArrayList<TechUpgrade>();
    static ArrayList<Unit> hostStations = new ArrayList<Unit>();
    static ArrayList<Unit> predefinedSquads = new ArrayList<Unit>();
    static int hostStationForPlayer = 0;

    static int set = 1;
    static String movie = "";
    static int eventLoop = 0;
    static String sky = "1998_01";
    static int music = 0;
    static int min_break = 0;
    static int max_break = 0;

    static String mbmap = "mb";
    static int mbMapSizeX = 480;
    static int mbMapSizeY = 480;
    static String dbmap = "db_01";
    static int dbMapSizeX = 480;
    static int dbMapSizeY = 480;

    static String prototypeModifications = "include data:scripts/startup2.scr";

    static ArrayList<Integer> resistanceUnits = new ArrayList<Integer>();
    static ArrayList<Integer> ghorkovUnits = new ArrayList<Integer>();
    static ArrayList<Integer> taerkastenUnits = new ArrayList<Integer>();
    static ArrayList<Integer> mykonianUnits = new ArrayList<Integer>();
    static ArrayList<Integer> sulgogarUnits = new ArrayList<Integer>();
    static ArrayList<Integer> blackSectUnits = new ArrayList<Integer>();
    static ArrayList<Integer> trainingUnits = new ArrayList<Integer>();
    static ArrayList<Integer> resistanceBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> ghorkovBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> taerkastenBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> mykonianBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> sulgogarBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> blackSectBuildings = new ArrayList<Integer>();
    static ArrayList<Integer> trainingBuildings = new ArrayList<Integer>();

    static int horizontalSectors = 0;
    static int verticalSectors = 0;
    static ArrayList<Integer> typ_map = new ArrayList<Integer>();
    static ArrayList<Integer> own_map = new ArrayList<Integer>();
    static ArrayList<Integer> hgt_map = new ArrayList<Integer>();
    static ArrayList<Integer> blg_map = new ArrayList<Integer>();
}
