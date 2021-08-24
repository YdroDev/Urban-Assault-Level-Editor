import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
public class LevelManager extends JFrame{

	private GameMap map;
	private ListenerClass listener;
	private JScrollPane unitScroller;
	private JScrollPane sectorScroller;
	private JTabbedPane tabs;
	private JPanel unitWindow;
	private JPanel sectorWindow;
	private JPanel managerUnitWindow;
	private JPanel managerSectorWindow;
	private int currentIndex;
	private int selectedSector;
	
	private JLabel unitImg;
	private JLabel unitName;
	private JLabel sectorInfo;
	private ArrayList<JLabel> sectorsInfo;
	private JLabel energyLabel;
	private JLabel viewAngleLabel;
	private JLabel reloadConstLabel;
	private JLabel xPosLabel;
	private JLabel yPosLabel;
	private JLabel zPosLabel;
	private JLabel preStatsLabel;
	private JLabel conBudgetLabel;
	private JLabel conDelayLabel;
	private JLabel defBudgetLabel;
	private JLabel defDelayLabel;
	private JLabel recBudgetLabel;
	private JLabel recDelayLabel;
	private JLabel robBudgetLabel;
	private JLabel robDelayLabel;
	private JLabel powBudgetLabel;
	private JLabel powDelayLabel;
	private JLabel radBudgetLabel;
	private JLabel radDelayLabel;
	private JLabel safBudgetLabel;
	private JLabel safDelayLabel;
	private JLabel cplBudgetLabel;
	private JLabel cplDelayLabel;
	private JLabel[] percentLabel;
	private JLabel[] secondLabel;
	
	private JCheckBox mbStatus;
	
	private JTextField energyField;
	private JTextField viewAngleField;
	private JTextField reloadConstField;
	private JTextField xField;
	private JTextField yField;
	private JTextField zField;
	private JTextField conBudgetField;
	private JTextField conDelayField;
	private JTextField defBudgetField;
	private JTextField defDelayField;
	private JTextField recBudgetField;
	private JTextField recDelayField;
	private JTextField robBudgetField;
	private JTextField robDelayField;
	private JTextField powBudgetField;
	private JTextField powDelayField;
	private JTextField radBudgetField;
	private JTextField radDelayField;
	private JTextField safBudgetField;
	private JTextField safDelayField;
	private JTextField cplBudgetField;
	private JTextField cplDelayField;
	
	private JSlider conBudgetSlider;
	private JSlider conDelaySlider;
	private JSlider defBudgetSlider;
	private JSlider defDelaySlider;
	private JSlider recBudgetSlider;
	private JSlider recDelaySlider;
	private JSlider robBudgetSlider;
	private JSlider robDelaySlider;
	private JSlider powBudgetSlider;
	private JSlider powDelaySlider;
	private JSlider radBudgetSlider;
	private JSlider radDelaySlider;
	private JSlider safBudgetSlider;
	private JSlider safDelaySlider;
	private JSlider cplBudgetSlider;
	private JSlider cplDelaySlider;
	
	private JComboBox<String> preStats;
	private String[] preStatsString;
	private int hsTypeSelected;
	
	private JComboBox<String> hsType;
	private String[] hsTypes;
	
	private JSeparator[] sep;
	private GridBagConstraints managerConstraints;
	private JPanel namePanel;
	private JPanel energyPanel;
	private JPanel viewAnglePanel;
	private JPanel reloadConstPanel;
	private JPanel positionPanel;
	private JPanel statsPanel;
	private JPanel imgPanel;
	private JPanel mbStatusPanel;
	private JPanel applyPanel;
	private JPanel jsonPanel;
	private JPanel applySquadPanel;
	
	private JPanel infoPanel; // infoPanel for sectors, namePanel for units/hoststations
	private JPanel multiInfoPanel;
	private JPanel borderPanel;
	private JPanel wrapPanel;
	private JPanel ownMapPanel;
	private JPanel blgMapPanel;
	private JPanel typMapPanel;
	private JPanel beamGatePanel;
	private JPanel keySectorPanel;
	private JPanel techUpgradePanel;
	private JPanel stoudsonBombPanel;
	
	private JLabel borderLabel;
	private JLabel ownMapLabel;
	private JLabel blgMapLabel;
	private JLabel typMapLabel;
	private BufferedImage typMapImg;
	private JLabel techUpgradeLabel;
	private JLabel techUpgradeTypeLabel;
	private JLabel modifyLabel;
	
	private JComboBox<String> selectModifier;
	
	private int tuGridy = 4;
	private int multiGridy;
	private int bgGridy;
	
	private JLabel beamGateLabel;
	private JLabel bgKeysectorInfo;
	private ArrayList<JLabel> keysectorList;
	private JLabel openedBgLabel;
	private JComboBox<String> openedBgList;
	private String[] bgString;
	private JLabel closedBgLabel;
	private JComboBox<String> closedBgList;
	private JLabel levelTargetLabel;
	private JComboBox<String> levelTargetList;
	private String[] levelNames;
	private int[] levelIDs;
	private String[] mdLevelNames;
	private int[] mdLevelIDs;
	private JButton addlevelTarget;
	private JButton applyBg;
	private JSeparator bgSep;
	private ArrayList<JLabel> levelTargets;
	private ArrayList<JButton> removeLevelTarget;
	private JLabel errorBg;
	
	private JLabel keysectorLabel;
	
	private JCheckBox tuMbStatus;
	private JCheckBox bgMbStatus;
	
	private String[] techUpgradeBuildings;
	private JComboBox<String> techUpgradeBuilding;
	private String[] techUpgradeTypes;
	private JComboBox<String> techUpgradeType;
	
	private JButton addModifier;
	private JButton saveModifiers;
	private ArrayList<JPanel> vehicleAdded;
	private ArrayList<Integer> vehicleIDs;
	private ArrayList<JCheckBox> resEnabler;
	private ArrayList<JCheckBox> ghorEnabler;
	private ArrayList<JCheckBox> taerEnabler;
	private ArrayList<JCheckBox> mykoEnabler;
	private ArrayList<JCheckBox> sulgEnabler;
	private ArrayList<JCheckBox> blasecEnabler;
	private ArrayList<JCheckBox> targetHSEnabler;
	private ArrayList<JLabel> addEnergyLabel; 
	private ArrayList<JTextField> addEnergyField; 
	private ArrayList<JLabel> addShieldLabel; 
	private ArrayList<JTextField> addShieldField; 
	private ArrayList<JLabel> addWeaponLabel; 
	private ArrayList<JTextField> addWeaponField;
	private ArrayList<JLabel> addRadarLabel; 
	private ArrayList<JTextField> addRadarField;
	private ArrayList<JLabel> addDamageLabel; 
	private ArrayList<JTextField> addDamageField;
	private ArrayList<JLabel> addShotTimeLabel; 
	private ArrayList<JTextField> addShotTimeField;
	private ArrayList<JLabel> addShotTimeUserLabel; 
	private ArrayList<JTextField> addShotTimeUserField;
	private ArrayList<JButton> removeVehicle;
	
	private ArrayList<JPanel> buildingAdded;
	private ArrayList<Integer> buildingIDs;
	private ArrayList<JCheckBox> resBuildingEnabler;
	private ArrayList<JCheckBox> ghorBuildingEnabler;
	private ArrayList<JCheckBox> taerBuildingEnabler;
	private ArrayList<JCheckBox> mykoBuildingEnabler;
	private ArrayList<JCheckBox> sulgBuildingEnabler;
	private ArrayList<JCheckBox> blasecBuildingEnabler;
	private ArrayList<JCheckBox> targetHSBuildingEnabler;
	private ArrayList<JButton> removeBuilding;
	
	private int sbGridy;
	
	private JLabel bombLabel;
	private JComboBox<String> bombList;
	private String[] bombString;
	private JPanel countdownPanel;
	private JLabel countdownLabel;
	private JTextField cdHours;
	private JLabel cdHoursLabel;
	private JTextField cdMinutes;
	private JLabel cdMinutesLabel;
	private JTextField cdSeconds;
	private JLabel cdSecondsLabel;
	private JButton applyBomb;
	private JLabel rectorInfoLabel;
	private ArrayList<JLabel> reactorList;
	
	private JPanel reactorPanel;
	private JLabel reactorLabel;
	private JLabel reactorTypmapLabel;
	private JComboBox<String> reactorTypmapList;
	private String[] normalReactor;
	private String[] parasiteReactor;
	private JButton applyReactor;
	
	private int selectedSectorX, selectedSectorY;
	private boolean isBorder;
	
	private JButton applyUnit;
	private JButton loadJSON, saveJSON;
	private JButton applySquad;
	private JFileChooser selectSaveJSON;
	private FileNameExtensionFilter jsonFilter;
	private PrintWriter statSaver;
	private JFileChooser selectOpenJSON;
	
	private JLabel imgLabel;
	private BufferedImage hsVehicle;
	
	private JPanel numPanel;
	private JPanel ownPanel;
	private JPanel locationPanel; // locationPanel for units, positionPanel for hoststations
	private JPanel useablePanel;
	private JPanel unitMbStatusPanel;
	
	private JLabel numLabel;
	private JLabel ownLabel;
	private JLabel xUnitLabel;
	private JLabel yUnitLabel;
	
	private JTextField numField;
	private JComboBox<String> ownList;
	private String[] hsStrings;
	private JTextField xUnitField;
	private JTextField yUnitField;
	private JCheckBox useable;
	
	LevelManager(GameMap m){
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("/img/icon.png")));
		}catch(IOException ex) {
			System.out.println("Couldn't load main icon for the level manager");
		}
		this.map = m;
		this.listener = new ListenerClass();
		this.unitWindow = new JPanel(new BorderLayout());
		this.sectorWindow = new JPanel(new BorderLayout());
		this.tabs = new JTabbedPane();
		this.unitScroller = new JScrollPane();
		this.sectorScroller = new JScrollPane();
		this.managerUnitWindow = new JPanel(new GridBagLayout());
		this.managerSectorWindow = new JPanel(new GridBagLayout());
		this.managerConstraints = new GridBagConstraints();
		this.namePanel = new JPanel(new GridBagLayout());
		this.energyPanel = new JPanel(new GridBagLayout());
		this.positionPanel = new JPanel();
		this.viewAnglePanel = new JPanel(new GridBagLayout());
		this.reloadConstPanel = new JPanel(new GridBagLayout());
		this.statsPanel = new JPanel(new GridBagLayout());
		this.imgPanel = new JPanel(new GridBagLayout());
		this.mbStatusPanel = new JPanel(new GridBagLayout());
		this.applyPanel = new JPanel(new GridBagLayout());
		this.jsonPanel = new JPanel(new GridBagLayout());
		
		this.numPanel = new JPanel(new GridBagLayout());
		this.ownPanel = new JPanel(new GridBagLayout());
		this.locationPanel = new JPanel(new GridBagLayout());
		this.useablePanel = new JPanel(new GridBagLayout());
		this.unitMbStatusPanel = new JPanel(new GridBagLayout());
		this.applySquadPanel = new JPanel(new GridBagLayout());
		
		this.infoPanel = new JPanel(new GridBagLayout());
		this.multiInfoPanel = new JPanel(new GridBagLayout());
		this.borderPanel = new JPanel(new GridBagLayout());
		this.wrapPanel = new JPanel(new GridBagLayout());
		this.ownMapPanel = new JPanel();
		this.blgMapPanel = new JPanel(new GridBagLayout());
		this.typMapPanel = new JPanel(new GridBagLayout());
		this.beamGatePanel = new JPanel(new GridBagLayout());
		this.keySectorPanel = new JPanel(new GridBagLayout());
		this.techUpgradePanel = new JPanel(new GridBagLayout());
		this.stoudsonBombPanel = new JPanel(new GridBagLayout());
		
		this.imgLabel = new JLabel();
		this.hsTypes = new String[8];
		this.sep = new JSeparator[8]; 
		this.percentLabel = new JLabel[8];
		this.secondLabel = new JLabel[8];
		for(int i = 0; i < 8; i++) {
			this.sep[i] = new JSeparator();
			this.sep[i].setPreferredSize(new Dimension(400,2));
			this.percentLabel[i] = new JLabel("%");
			this.secondLabel[i] = new JLabel("sec");
		}
		this.preStatsString = new String[6];
		this.preStatsString[0] = "Attack--Easy"; this.preStatsString[1] = "Attack--Medium"; this.preStatsString[2] = "Attack--Hard"; this.preStatsString[3] = "Attack--Expert"; this.preStatsString[4] = "Defend"; this.preStatsString[5] = "Custom";  
		this.setSize(469, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Level manager");
		this.unitImg = new JLabel();
		this.unitName = new JLabel("No unit selected");
		this.energyLabel = new JLabel("Energy:");
		this.energyField = new JTextField(5);
		this.viewAngleLabel = new JLabel("View angle:");
		this.viewAngleField = new JTextField(3);
		this.reloadConstLabel = new JLabel("Reload const:");
		this.reloadConstField = new JTextField(5);
		this.xPosLabel = new JLabel("X: ");
		this.yPosLabel = new JLabel("Y: ");
		this.zPosLabel = new JLabel("Z: ");
		this.xField = new JTextField(4);
		this.yField = new JTextField(4);
		this.zField = new JTextField(4);
		this.applyUnit = new JButton("Apply changes");
		this.applyUnit.addActionListener(listener);
		this.applySquad = new JButton("Apply changes");
		this.applySquad.addActionListener(listener);
		this.selectSaveJSON = new JFileChooser();
		this.jsonFilter = new FileNameExtensionFilter("JavaScript Object Notation (.json)", "json");
		this.selectSaveJSON.setFileFilter(this.jsonFilter);
		this.selectOpenJSON = new JFileChooser();
		this.selectOpenJSON.setFileFilter(this.jsonFilter);
		this.preStatsLabel = new JLabel("Predefined behavior: ");
		this.preStats = new JComboBox<String>(this.preStatsString);
		this.preStats.addActionListener(this.listener);
		this.conBudgetLabel = new JLabel("Conquering sectors: ");
		this.conBudgetSlider = new JSlider(0, 100);
		this.conBudgetSlider.addChangeListener(this.listener);
		this.conBudgetField = new JTextField(3);
		this.conDelayLabel = new JLabel("Delay: ");
		this.conDelaySlider = new JSlider(0, 9000);
		this.conDelayField = new JTextField(3);
		this.conDelaySlider.addChangeListener(this.listener);
		this.defBudgetLabel = new JLabel("Defense: ");
		this.defBudgetSlider = new JSlider(0, 100);
		this.defBudgetField = new JTextField(3);
		this.defBudgetSlider.addChangeListener(this.listener);
		this.defDelayLabel = new JLabel("Delay: ");
		this.defDelaySlider = new JSlider(0, 9000);
		this.defDelayField = new JTextField(3);
		this.defDelaySlider.addChangeListener(this.listener);
		this.recBudgetLabel = new JLabel("Reconnaissance: ");
		this.recBudgetSlider = new JSlider(0, 100);
		this.recBudgetField = new JTextField(3);
		this.recBudgetSlider.addChangeListener(this.listener);
		this.recDelayLabel = new JLabel("Delay: ");
		this.recDelaySlider = new JSlider(0, 9000);
		this.recDelayField = new JTextField(3);
		this.recDelaySlider.addChangeListener(this.listener);
		this.robBudgetLabel = new JLabel("Attacking: ");
		this.robBudgetSlider = new JSlider(0, 100);
		this.robBudgetField = new JTextField(3);
		this.robBudgetSlider.addChangeListener(this.listener);
		this.robDelayLabel = new JLabel("Delay: ");
		this.robDelaySlider = new JSlider(0, 9000);
		this.robDelayField = new JTextField(3);
		this.robDelaySlider.addChangeListener(this.listener);
		this.powBudgetLabel = new JLabel("Power-building: ");
		this.powBudgetSlider = new JSlider(0, 100);
		this.powBudgetField = new JTextField(3);
		this.powBudgetSlider.addChangeListener(this.listener);
		this.powDelayLabel = new JLabel("Delay: ");
		this.powDelaySlider = new JSlider(0, 9000);
		this.powDelayField = new JTextField(3);
		this.powDelaySlider.addChangeListener(this.listener);
		this.radBudgetLabel = new JLabel("Radar-building: ");
		this.radBudgetSlider = new JSlider(0, 100);
		this.radBudgetField = new JTextField(3);
		this.radBudgetSlider.addChangeListener(this.listener);
		this.radDelayLabel = new JLabel("Delay: ");
		this.radDelaySlider = new JSlider(0, 9000);
		this.radDelayField = new JTextField(3);
		this.radDelaySlider.addChangeListener(this.listener);
		this.safBudgetLabel = new JLabel("Flak-building: ");
		this.safBudgetSlider = new JSlider(0, 100);
		this.safBudgetField = new JTextField(3);
		this.safBudgetSlider.addChangeListener(this.listener);
		this.safDelayLabel = new JLabel("Delay: ");
		this.safDelaySlider = new JSlider(0, 9000);
		this.safDelayField = new JTextField(3);
		this.safDelaySlider.addChangeListener(this.listener);
		this.cplBudgetLabel = new JLabel("Moving station: ");
		this.cplBudgetSlider = new JSlider(0, 100);
		this.cplBudgetField = new JTextField(3);
		this.cplBudgetSlider.addChangeListener(this.listener);
		this.cplDelayLabel = new JLabel("Delay: ");
		this.cplDelaySlider = new JSlider(0, 9000);
		this.cplDelayField = new JTextField(3);
		this.cplDelaySlider.addChangeListener(this.listener);
		
		this.sectorInfo = new JLabel("No sector selected");
		this.sectorsInfo = new ArrayList<JLabel>();
		
		this.hsTypes[0] = "Resistance Host Station"; this.hsTypes[1] = "Turantul I"; this.hsTypes[2] = "Turantul II"; this.hsTypes[3] = "Taerkasten Flying Fortness"; this.hsTypes[4] = "Myko Station"; this.hsTypes[5] = "Sulgogar Queen"; this.hsTypes[6] = "Black Sect Station"; this.hsTypes[7] = "Target Host Station"; 
		this.hsType = new JComboBox<String>(this.hsTypes);
		this.mbStatus = new JCheckBox("");
		this.hsType.addActionListener(this.listener);
		this.loadJSON = new JButton("Load behavior from file");
		this.loadJSON.addActionListener(listener);
		this.saveJSON = new JButton("Save behavior to file");
		this.saveJSON.addActionListener(listener);
		
		this.numLabel = new JLabel("Number of units: ");
		this.numField = new JTextField(2);
		this.ownLabel = new JLabel("Unit faction: ");
		this.hsStrings = new String[7];
		this.hsStrings[0] = "Resistance"; this.hsStrings[1] = "Ghorkov"; this.hsStrings[2] = "Taerkasten"; this.hsStrings[3] = "Mykonian"; this.hsStrings[4] = "Sulgogar"; this.hsStrings[5] = "Black Sect"; this.hsStrings[6] = "Target faction";
		this.ownList = new JComboBox<String>(hsStrings);
		this.xUnitLabel = new JLabel("X: ");
		this.xUnitField = new JTextField(3);
		this.yUnitLabel = new JLabel("Y: ");
		this.yUnitField = new JTextField(3);
		this.useable = new JCheckBox("AI may use this squad as normal units");
		
		this.borderLabel = new JLabel();
		this.selectedSectorX = 0;
		this.selectedSectorY = 0;
		this.isBorder = false;
		this.ownMapLabel = new JLabel();
		this.blgMapLabel = new JLabel();
		this.typMapLabel = new JLabel();
		this.beamGateLabel = new JLabel();
		this.bgKeysectorInfo = new JLabel();
		this.keysectorList = new ArrayList<JLabel>();
		this.openedBgLabel = new JLabel();
		this.bgString = new String[2];
		this.bgString[0] = "With road"; this.bgString[1] = "Without road";
		this.openedBgList = new JComboBox<String>(this.bgString);
		this.closedBgLabel = new JLabel();
		this.closedBgList = new JComboBox<String>(this.bgString);
		this.bgMbStatus = new JCheckBox("");
		this.levelTargetLabel = new JLabel();
		this.levelTargetLabel.setText("Unlock new level: ");
		this.levelNames = new String[44];
		this.levelNames[0] = "L0101"; this.levelNames[1] = "L0202"; this.levelNames[2] = "L0303"; this.levelNames[3] = "L0404"; this.levelNames[4] = "L0505"; this.levelNames[5] = "L1010"; this.levelNames[6] = "L1111"; this.levelNames[7] = "L1212"; this.levelNames[8] = "L1515"; this.levelNames[9] = "L2020"; this.levelNames[10] = "L2121"; this.levelNames[11] = "L2222"; this.levelNames[12] = "L2323"; this.levelNames[13] = "L2525"; this.levelNames[14] = "L2626"; this.levelNames[15] = "L3030"; this.levelNames[16] = "L3131"; this.levelNames[17] = "L3232"; this.levelNames[18] = "L3333"; this.levelNames[19] = "L3434"; this.levelNames[20] = "L4040"; this.levelNames[21] = "L4141"; this.levelNames[22] = "L4242"; this.levelNames[23] = "L4343"; this.levelNames[24] = "L4444"; this.levelNames[25] = "L5050"; this.levelNames[26] = "L5151"; this.levelNames[27] = "L5252"; this.levelNames[28] = "L5353"; this.levelNames[29] = "L5454"; this.levelNames[30] = "L6060"; this.levelNames[31] = "L6161"; this.levelNames[32] = "L6262"; this.levelNames[33] = "L6363"; this.levelNames[34] = "L6464"; this.levelNames[35] = "L6666"; this.levelNames[36] = "L7070"; this.levelNames[37] = "L7171"; this.levelNames[38] = "L7272"; this.levelNames[39] = "L7373"; this.levelNames[40] = "L7474"; this.levelNames[41] = "L7575"; this.levelNames[42] = "L9898"; this.levelNames[43] = "L9999";
		this.levelIDs = new int[44];
		this.levelIDs[0] = 1; this.levelIDs[1] = 2; this.levelIDs[2] = 3; this.levelIDs[3] = 4; this.levelIDs[4] = 5; this.levelIDs[5] = 10; this.levelIDs[6] = 11; this.levelIDs[7] = 12; this.levelIDs[8] = 15; this.levelIDs[9] = 20; this.levelIDs[10] = 21; this.levelIDs[11] = 22; this.levelIDs[12] = 23; this.levelIDs[13] = 25; this.levelIDs[14] = 26; this.levelIDs[15] = 30; this.levelIDs[16] = 31; this.levelIDs[17] = 32; this.levelIDs[18] = 33; this.levelIDs[19] = 34; this.levelIDs[20] = 40; this.levelIDs[21] = 41; this.levelIDs[22] = 42; this.levelIDs[23] = 43; this.levelIDs[24] = 44; this.levelIDs[25] = 50; this.levelIDs[26] = 51; this.levelIDs[27] = 52; this.levelIDs[28] = 53; this.levelIDs[29] = 54; this.levelIDs[30] = 60; this.levelIDs[31] = 61; this.levelIDs[32] = 62; this.levelIDs[33] = 63; this.levelIDs[34] = 64; this.levelIDs[35] = 66; this.levelIDs[36] = 70; this.levelIDs[37] = 71; this.levelIDs[38] = 72; this.levelIDs[39] = 73; this.levelIDs[40] = 74; this.levelIDs[41] = 75; this.levelIDs[42] = 98; this.levelIDs[43] = 99;
		this.mdLevelNames = new String[31];
		this.mdLevelNames[0] = "L0606"; this.mdLevelNames[1] = "L0707"; this.mdLevelNames[2] = "L0808"; this.mdLevelNames[3] = "L1313"; this.mdLevelNames[4] = "L1414"; this.mdLevelNames[5] = "L1616"; this.mdLevelNames[6] = "L1717"; this.mdLevelNames[7] = "L1818"; this.mdLevelNames[8] = "L1919"; this.mdLevelNames[9] = "L2828"; this.mdLevelNames[10] = "L2929"; this.mdLevelNames[11] = "L3535"; this.mdLevelNames[12] = "L3636"; this.mdLevelNames[13] = "L3737"; this.mdLevelNames[14] = "L3838"; this.mdLevelNames[15] = "L3939"; this.mdLevelNames[16] = "L4545"; this.mdLevelNames[17] = "L4646"; this.mdLevelNames[18] = "L4747"; this.mdLevelNames[19] = "L4848"; this.mdLevelNames[20] = "L5555"; this.mdLevelNames[21] = "L5656"; this.mdLevelNames[22] = "L5757"; this.mdLevelNames[23] = "L5858"; this.mdLevelNames[24] = "L6565"; this.mdLevelNames[25] = "L6767"; this.mdLevelNames[26] = "L6868"; this.mdLevelNames[27] = "L6969"; this.mdLevelNames[28] = "L7777"; this.mdLevelNames[29] = "L7878"; this.mdLevelNames[30] = "L7979";
		this.mdLevelIDs = new int[31];
		this.mdLevelIDs[0] = 6; this.mdLevelIDs[1] = 7; this.mdLevelIDs[2] = 8; this.mdLevelIDs[3] = 13; this.mdLevelIDs[4] = 14; this.mdLevelIDs[5] = 16; this.mdLevelIDs[6] = 17; this.mdLevelIDs[7] = 18; this.mdLevelIDs[8] = 19; this.mdLevelIDs[9] = 28; this.mdLevelIDs[10] = 29; this.mdLevelIDs[11] = 35; this.mdLevelIDs[12] = 36; this.mdLevelIDs[13] = 37; this.mdLevelIDs[14] = 38; this.mdLevelIDs[15] = 39; this.mdLevelIDs[16] = 45; this.mdLevelIDs[17] = 46; this.mdLevelIDs[18] = 47; this.mdLevelIDs[19] = 48; this.mdLevelIDs[20] = 55; this.mdLevelIDs[21] = 56; this.mdLevelIDs[22] = 57; this.mdLevelIDs[23] = 58; this.mdLevelIDs[24] = 65; this.mdLevelIDs[25] = 67; this.mdLevelIDs[26] = 68; this.mdLevelIDs[27] = 69; this.mdLevelIDs[28] = 77; this.mdLevelIDs[29] = 78; this.mdLevelIDs[30] = 79; 
		this.addlevelTarget = new JButton("Add");
		this.addlevelTarget.addActionListener(listener);
		this.applyBg = new JButton("APPLY CHANGES");
		this.applyBg.addActionListener(listener);
		this.bgSep = new JSeparator();
		this.bgSep.setPreferredSize(new Dimension(320,5));
		this.levelTargets = new ArrayList<JLabel>();
		this.removeLevelTarget = new ArrayList<JButton>();
		this.keysectorLabel = new JLabel();
		this.errorBg = new JLabel("Unlock at least one level to avoid game crash");
		this.errorBg.setForeground(Color.RED);
		this.techUpgradeLabel = new JLabel();
		this.techUpgradeBuildings = new String[9];
		this.techUpgradeBuildings[0] = "Sphinx(set 5 only!)"; this.techUpgradeBuildings[1] = "Wall left"; this.techUpgradeBuildings[2] = "Gate and street"; this.techUpgradeBuildings[3] = "Gate with Flak and street"; this.techUpgradeBuildings[4] = "Cross"; this.techUpgradeBuildings[5] = "Wall at the top with tower"; this.techUpgradeBuildings[6] = "4 towers"; this.techUpgradeBuildings[7] = "Square"; this.techUpgradeBuildings[8] = "1 tower";
		this.techUpgradeBuilding = new JComboBox<String>(this.techUpgradeBuildings);
		this.techUpgradeTypeLabel = new JLabel();
		this.techUpgradeTypes = new String[6];
		this.techUpgradeTypes[0] = "Weapon upgrade"; this.techUpgradeTypes[1] = "Shield upgrade"; this.techUpgradeTypes[2] = "New vehicle"; this.techUpgradeTypes[3] = "New building"; this.techUpgradeTypes[4] = "Radarstation expansion for the Scout"; this.techUpgradeTypes[5] = "none";
		this.techUpgradeType = new JComboBox<String>(this.techUpgradeTypes); 
		this.tuMbStatus = new JCheckBox("");
		this.addModifier = new JButton("+");
		this.addModifier.addActionListener(listener);
		this.saveModifiers = new JButton("APPLY CHANGES");
		this.saveModifiers.addActionListener(listener);
		this.selectModifier = new JComboBox<String>();

	
		this.modifyLabel = new JLabel("Modify: ");

		for(UAitem unitName : UAdata.allUnits){
			this.selectModifier.addItem(unitName.getName());
		}
		for(UAitem unitName : UAdata.fallbackUnits){
			this.selectModifier.addItem(unitName.getName());
		}
		for(UAitem unitName : UAdata.techUpgradeItems){
			this.selectModifier.addItem(unitName.getName());
		}

		for(UAitem buildingName : UAdata.allBuildings){
			this.selectModifier.addItem(buildingName.getName());
		}
		for(UAitem buildingName : UAdata.fallbackBuildings){
			this.selectModifier.addItem(buildingName.getName());
		}
		
		this.vehicleAdded = new ArrayList<JPanel>();
		this.vehicleIDs = new ArrayList<Integer>();
		this.resEnabler = new ArrayList<JCheckBox>();
		this.ghorEnabler = new ArrayList<JCheckBox>();
		this.taerEnabler = new ArrayList<JCheckBox>();
		this.mykoEnabler = new ArrayList<JCheckBox>();
		this.sulgEnabler = new ArrayList<JCheckBox>();
		this.blasecEnabler = new ArrayList<JCheckBox>();
		this.targetHSEnabler = new ArrayList<JCheckBox>();
		this.addEnergyLabel = new ArrayList<JLabel>();
		this.addEnergyField = new ArrayList<JTextField>();
		this.addShieldLabel = new ArrayList<JLabel>();
		this.addShieldField = new ArrayList<JTextField>();
		this.addWeaponLabel = new ArrayList<JLabel>();
		this.addWeaponField = new ArrayList<JTextField>();
		this.addRadarLabel = new ArrayList<JLabel>();
		this.addRadarField = new ArrayList<JTextField>();
		this.addDamageLabel = new ArrayList<JLabel>();
		this.addDamageField = new ArrayList<JTextField>();
		this.addShotTimeLabel = new ArrayList<JLabel>();
		this.addShotTimeField = new ArrayList<JTextField>();
		this.addShotTimeUserLabel = new ArrayList<JLabel>();
		this.addShotTimeUserField = new ArrayList<JTextField>();
		this.removeVehicle = new ArrayList<JButton>();
		
		this.buildingAdded = new ArrayList<JPanel>();
		this.buildingIDs = new ArrayList<Integer>();
		this.removeBuilding = new ArrayList<JButton>();
		this.resBuildingEnabler = new ArrayList<JCheckBox>();
		this.ghorBuildingEnabler = new ArrayList<JCheckBox>();
		this.taerBuildingEnabler = new ArrayList<JCheckBox>();
		this.mykoBuildingEnabler = new ArrayList<JCheckBox>();
		this.sulgBuildingEnabler = new ArrayList<JCheckBox>();
		this.blasecBuildingEnabler = new ArrayList<JCheckBox>();
		this.targetHSBuildingEnabler = new ArrayList<JCheckBox>();

		this.bombLabel = new JLabel();
		this.bombString = new String[2];
		this.bombString[0] = "Normal"; this.bombString[1] = "Parasite (set 6 only!)";
		this.bombList = new JComboBox<String>(this.bombString);
		this.countdownPanel = new JPanel(new GridBagLayout());
		this.countdownLabel = new JLabel();
		this.cdHours = new JTextField(2);
		this.cdHoursLabel = new JLabel();
		this.cdMinutes = new JTextField(2);
		this.cdMinutesLabel = new JLabel();
		this.cdSeconds = new JTextField(2);
		this.cdSecondsLabel = new JLabel();
		this.applyBomb = new JButton("APPLY CHANGES");
		this.applyBomb.addActionListener(listener);
		this.sbGridy = 7;
		this.rectorInfoLabel = new JLabel();
		this.reactorList = new ArrayList<JLabel>();
		this.reactorPanel = new JPanel(new GridBagLayout());
		this.reactorLabel = new JLabel();
		this.reactorTypmapLabel = new JLabel();
		this.normalReactor = new String[3];
		this.normalReactor[0] = "Variant 1"; this.normalReactor[1] = "Variant 2"; this.normalReactor[2] = "Other";
		this.parasiteReactor = new String[7];
		this.parasiteReactor[0] = "Top-Left corner"; this.parasiteReactor[1] = "Top-Right corner"; this.parasiteReactor[2] = "Bottom-Left corner"; this.parasiteReactor[3] = "Bottom-Right corner"; this.parasiteReactor[4] = "Variant 1"; this.parasiteReactor[5] = "Variant 2"; this.parasiteReactor[6] = "Other";
		this.applyReactor = new JButton("APPLY");
		this.applyReactor.addActionListener(listener);

		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;

		this.managerConstraints.insets = new Insets(5,5,5,5);
		this.namePanel.add(this.unitImg, this.managerConstraints);
		this.managerConstraints.gridx = 1;
		this.namePanel.add(this.unitName, this.managerConstraints);
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridwidth = 8;
		this.managerUnitWindow.add(this.namePanel, this.managerConstraints);
		this.managerConstraints.gridy = 1;
		this.managerConstraints.gridwidth = 2;
		this.managerUnitWindow.add(this.energyPanel, this.managerConstraints);
		this.managerConstraints.gridx = 2;
		this.managerConstraints.gridwidth = 1;
		this.managerUnitWindow.add(this.viewAnglePanel, this.managerConstraints);
		this.managerConstraints.gridx = 3;
		this.managerUnitWindow.add(this.reloadConstPanel, this.managerConstraints);
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 2;
		this.managerConstraints.gridwidth = 4;
		this.managerUnitWindow.add(this.positionPanel, this.managerConstraints);
		this.managerConstraints.gridy = 3;
		this.managerConstraints.gridwidth = 4;
		this.managerUnitWindow.add(this.statsPanel, this.managerConstraints);
		this.managerConstraints.gridwidth = 4;
		this.managerConstraints.gridy = 4;
		this.managerUnitWindow.add(this.mbStatusPanel, this.managerConstraints);
		this.managerConstraints.gridwidth = 3;
		this.managerConstraints.gridy = 5;
		this.managerUnitWindow.add(this.imgPanel, this.managerConstraints);
		this.managerConstraints.gridwidth = 3;
		this.managerConstraints.gridx = 3;
		this.managerUnitWindow.add(this.applyPanel, this.managerConstraints);
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridwidth = 4;
		this.managerConstraints.gridy = 6;
		this.managerUnitWindow.add(this.jsonPanel, this.managerConstraints);
		
		this.managerConstraints.gridx = 0;
		this.managerConstraints.insets = new Insets(3,3,3,3);
		this.managerConstraints.gridy = 1;
		this.managerConstraints.gridwidth = 1;
		this.managerUnitWindow.add(this.numPanel, this.managerConstraints);
		this.managerConstraints.gridx = 4;
		this.managerUnitWindow.add(this.ownPanel, this.managerConstraints);
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 2;
		this.managerConstraints.gridwidth = 5;
		this.managerUnitWindow.add(this.locationPanel, this.managerConstraints);
		this.managerConstraints.gridy = 3;
		this.managerUnitWindow.add(this.useablePanel, this.managerConstraints);
		this.managerConstraints.gridy = 4;
		this.managerUnitWindow.add(this.unitMbStatusPanel, this.managerConstraints);
		this.managerConstraints.gridy = 5;
		this.managerUnitWindow.add(this.applySquadPanel, this.managerConstraints);
		
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;
		this.infoPanel.add(this.sectorInfo);
		this.managerConstraints.gridwidth = 10;
		this.managerConstraints.gridx = 6;
		this.managerSectorWindow.add(this.infoPanel, this.managerConstraints);
		this.managerSectorWindow.add(this.multiInfoPanel, this.managerConstraints);
		this.managerConstraints.gridy = 1;
		this.managerSectorWindow.add(this.borderPanel, this.managerConstraints);
		this.managerConstraints.gridwidth = 8;
		this.managerConstraints.gridx = 3;
		this.wrapPanel.add(this.ownMapPanel);
		this.wrapPanel.add(this.blgMapPanel);
		this.managerSectorWindow.add(this.wrapPanel, this.managerConstraints);
		this.managerConstraints.gridwidth = 10;
		this.managerConstraints.gridy = 2;
		this.managerConstraints.gridx = 1;
		this.managerSectorWindow.add(this.typMapPanel, this.managerConstraints);
		this.managerConstraints.gridy = 3;
		this.managerSectorWindow.add(this.beamGatePanel, this.managerConstraints);
		this.managerConstraints.gridy = 4;
		this.managerSectorWindow.add(this.keySectorPanel, this.managerConstraints);
		this.managerConstraints.gridy = 5;
		this.managerSectorWindow.add(this.techUpgradePanel, this.managerConstraints);
		this.managerConstraints.gridy = 6;
		this.managerSectorWindow.add(this.stoudsonBombPanel, this.managerConstraints);
		this.managerConstraints.gridy = 7;
		this.managerSectorWindow.add(this.reactorPanel, this.managerConstraints);
		
		
		this.managerConstraints.gridwidth = 1;
		this.unitScroller.setBorder(null);
		this.sectorScroller.setBorder(null);
		this.unitWindow.add(this.managerUnitWindow);
		this.sectorWindow.add(this.managerSectorWindow);
		this.unitScroller = new JScrollPane(this.unitWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.unitScroller.setPreferredSize(new Dimension(300,300));
		this.sectorScroller = new JScrollPane(this.sectorWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.sectorScroller.setPreferredSize(new Dimension(300,300));
		this.tabs.addTab("Host Station / Squad", this.unitScroller);
		this.tabs.addTab("Sector", this.sectorScroller);
		
		this.add(this.tabs);
		this.addKeyListener(listener);
		this.setFocusable(true);
	}
	
	public void showHoststationOptions(int own, int index) {
		if(index > -1) {
			index++;
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 0;
			this.managerConstraints.insets = new Insets(1,1,1,1);
			switch(own) {
			case 1:
				this.unitName.setText("Hoststation "+index+": Resistance");
				break;
			case 2:
				this.unitName.setText("Hoststation "+index+": Sulgogar");
				break;
			case 3:
				this.unitName.setText("Hoststation "+index+": Mykonian");
				break;
			case 4:
				this.unitName.setText("Hoststation "+index+": Taerkasten");
				break;
			case 5:
				this.unitName.setText("Hoststation "+index+": Black sect");
				break;
			case 6:
				this.unitName.setText("Hoststation "+index+": Ghorkov");
				break;
			case 7:
				this.unitName.setText("Hoststation "+index+": Target Host Station");
				break;
			}
			index--;

			try {
				if(this.map.getHoststation(index).getVehicle() == 56) {
					this.hsTypeSelected = 0;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/resistanceHS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 59 || this.map.getHoststation(index).getVehicle() == 176) {
					this.hsTypeSelected = 1;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/ghorkov1HS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 57 || this.map.getHoststation(index).getVehicle() == 177) {
					this.hsTypeSelected = 2;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/ghorkov2HS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 60 || this.map.getHoststation(index).getVehicle() == 178) {
					this.hsTypeSelected = 3;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/taerkastenHS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 58) {
					this.hsTypeSelected = 4;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/mykonianHS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 61) {
					this.hsTypeSelected = 5;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/sulgogarHS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 62) {
					this.hsTypeSelected = 6;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/blackSectHS.png"));
				}else if(this.map.getHoststation(index).getVehicle() == 132) {
					this.hsTypeSelected = 7;
					this.hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/targetHS.png"));
				}
			}catch(IOException ex) {
				System.out.println("An error occured while loading hoststation image");
			}

			this.energyPanel.add(this.energyLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.energyField.setText(Integer.toString(this.map.getHoststation(index).getEnergy()) );
			this.energyPanel.add(this.energyField, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.viewAnglePanel.add(this.viewAngleLabel, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.viewAngleField.setText(Integer.toString(this.map.getHoststation(index).getViewAngle()) );
			this.viewAnglePanel.add(this.viewAngleField, this.managerConstraints);
			this.reloadConstPanel.add(this.reloadConstLabel, this.managerConstraints);
			this.managerConstraints.gridx = 4;
			this.reloadConstField.setText(Integer.toString(this.map.getHoststation(index).getReloadConst()) );
			this.reloadConstPanel.add(this.reloadConstField, this.managerConstraints);
			
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 0;
			this.positionPanel.add(this.xPosLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.xField.setText(Integer.toString(this.map.getHoststation(index).getUnitX()+ (int)(this.map.getMapSize() * 0.5/ 2)));
			this.positionPanel.add(this.xField, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.positionPanel.add(this.yPosLabel, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.yField.setText(Integer.toString(this.map.getHoststation(index).getUnitY()+ (int)(this.map.getMapSize() * 0.5/ 2)));
			this.positionPanel.add(this.yField, this.managerConstraints);
			this.managerConstraints.gridx = 4;
			this.positionPanel.add(this.zPosLabel, this.managerConstraints);
			this.managerConstraints.gridx = 5;
			this.zField.setText(Integer.toString(this.map.getHoststation(index).getHeight()));
			this.positionPanel.add(this.zField, this.managerConstraints);
			
			this.statsPanel.setBorder(BorderFactory.createTitledBorder("Enemy behavior (AI only)"));
			this.managerConstraints.gridy = 1;
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[0], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 2;
			this.statsPanel.add(this.conBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.conBudgetSlider.setValue(this.map.getHoststation(index).getConBudget());
			this.statsPanel.add(this.conBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.conBudgetField.setText(Integer.toString(this.map.getHoststation(index).getConBudget()));
			this.statsPanel.add(this.conBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.anchor = GridBagConstraints.WEST;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[0], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 3;
			this.statsPanel.add(this.conDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.conDelaySlider.setValue(this.map.getHoststation(index).getConDelay());
			this.statsPanel.add(this.conDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.conDelayField.setText(Integer.toString(this.map.getHoststation(index).getConDelay()));
			this.statsPanel.add(this.conDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[0], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridy = 4;
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[1], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 5;
			this.statsPanel.add(this.defBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.defBudgetSlider.setValue(this.map.getHoststation(index).getDefBudget());
			this.statsPanel.add(this.defBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.defBudgetField.setText(Integer.toString(this.map.getHoststation(index).getDefBudget()));
			this.statsPanel.add(this.defBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[1], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 6;
			this.statsPanel.add(this.defDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.defDelaySlider.setValue(this.map.getHoststation(index).getDefDelay());
			this.statsPanel.add(this.defDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.defDelayField.setText(Integer.toString(this.map.getHoststation(index).getDefDelay()));
			this.statsPanel.add(this.defDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[1], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 7;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[2], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 8;
			this.statsPanel.add(this.recBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.recBudgetSlider.setValue(this.map.getHoststation(index).getRecBudget());
			this.statsPanel.add(this.recBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.recBudgetField.setText(Integer.toString(this.map.getHoststation(index).getRecBudget()));
			this.statsPanel.add(this.recBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[2], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 9;
			this.statsPanel.add(this.recDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.recDelaySlider.setValue(this.map.getHoststation(index).getRecDelay());
			this.statsPanel.add(this.recDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.recDelayField.setText(Integer.toString(this.map.getHoststation(index).getRecDelay()));
			this.statsPanel.add(this.recDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[2], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 10;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[3], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 11;
			this.statsPanel.add(this.robBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.robBudgetSlider.setValue(this.map.getHoststation(index).getRobBudget());
			this.statsPanel.add(this.robBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.robBudgetField.setText(Integer.toString(this.map.getHoststation(index).getRobBudget()));
			this.statsPanel.add(this.robBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[3], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 12;
			this.statsPanel.add(this.robDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.robDelaySlider.setValue(this.map.getHoststation(index).getRobDelay());
			this.statsPanel.add(this.robDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.robDelayField.setText(Integer.toString(this.map.getHoststation(index).getRobDelay()));
			this.statsPanel.add(this.robDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[3], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 13;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[4], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 14;
			this.statsPanel.add(this.powBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.powBudgetSlider.setValue(this.map.getHoststation(index).getPowBudget());
			this.statsPanel.add(this.powBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.powBudgetField.setText(Integer.toString(this.map.getHoststation(index).getPowBudget()));
			this.statsPanel.add(this.powBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[4], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 15;
			this.statsPanel.add(this.powDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.powDelaySlider.setValue(this.map.getHoststation(index).getPowDelay());
			this.statsPanel.add(this.powDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.powDelayField.setText(Integer.toString(this.map.getHoststation(index).getPowDelay()));
			this.statsPanel.add(this.powDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[4], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 16;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[5], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 17;
			this.statsPanel.add(this.radBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.radBudgetSlider.setValue(this.map.getHoststation(index).getRadBudget());
			this.statsPanel.add(this.radBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.radBudgetField.setText(Integer.toString(this.map.getHoststation(index).getRadBudget()));
			this.statsPanel.add(this.radBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[5], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 18;
			this.statsPanel.add(this.radDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.radDelaySlider.setValue(this.map.getHoststation(index).getRadDelay());
			this.statsPanel.add(this.radDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.radDelayField.setText(Integer.toString(this.map.getHoststation(index).getRadDelay()));
			this.statsPanel.add(this.radDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[5], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 19;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[6], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 20;
			this.statsPanel.add(this.safBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.safBudgetSlider.setValue(this.map.getHoststation(index).getSafBudget());
			this.statsPanel.add(this.safBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.safBudgetField.setText(Integer.toString(this.map.getHoststation(index).getSafBudget()));
			this.statsPanel.add(this.safBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[6], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 21;
			this.statsPanel.add(this.safDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.safDelaySlider.setValue(this.map.getHoststation(index).getSafDelay());
			this.statsPanel.add(this.safDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.safDelayField.setText(Integer.toString(this.map.getHoststation(index).getSafDelay()));
			this.statsPanel.add(this.safDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[6], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 22;
			this.managerConstraints.gridwidth = 4;
			this.statsPanel.add(this.sep[7], this.managerConstraints);
			this.managerConstraints.gridwidth = 1;
			this.managerConstraints.gridy = 23;
			this.statsPanel.add(this.cplBudgetLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.cplBudgetSlider.setValue(this.map.getHoststation(index).getCplBudget());
			this.statsPanel.add(this.cplBudgetSlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.cplBudgetField.setText(Integer.toString(this.map.getHoststation(index).getCplBudget()));
			this.statsPanel.add(this.cplBudgetField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.percentLabel[7], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 24;
			this.statsPanel.add(this.cplDelayLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.cplDelaySlider.setValue(this.map.getHoststation(index).getCplDelay());
			this.statsPanel.add(this.cplDelaySlider, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.cplDelayField.setText(Integer.toString(this.map.getHoststation(index).getCplDelay()));
			this.statsPanel.add(this.cplDelayField, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.managerConstraints.insets = new Insets(1,1,0,1);
			this.statsPanel.add(this.secondLabel[7], this.managerConstraints);
			this.managerConstraints.insets = new Insets(5,5,5,5);
			
			this.managerConstraints.gridy = 0;
			this.managerConstraints.gridx = 0;
			this.imgLabel.setIcon(new ImageIcon(this.hsVehicle));
			this.imgPanel.setBorder(BorderFactory.createTitledBorder("Hoststation type"));
			this.imgPanel.add(this.imgLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.hsType.setSelectedIndex(this.hsTypeSelected);
			this.imgPanel.add(this.hsType, this.managerConstraints);
			this.managerConstraints.gridx = 0;
			this.mbStatusPanel.setBorder(BorderFactory.createTitledBorder("MB status"));
			this.mbStatus.setText("Make this Host Station invisible in the Mission Briefing");
			if(this.map.getHoststation(index).getVisibility() == true) mbStatus.setSelected(false);
			else mbStatus.setSelected(true);
			this.mbStatusPanel.add(this.mbStatus, this.managerConstraints);
			this.applyPanel.add(this.applyUnit, this.managerConstraints);
			this.jsonPanel.setBorder(BorderFactory.createTitledBorder("Host Station behavior in file"));
			this.jsonPanel.add(this.loadJSON, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.jsonPanel.add(this.saveJSON, this.managerConstraints);
			this.currentIndex = index;
			this.managerConstraints.anchor = GridBagConstraints.CENTER;
		}else this.unitName.setText("");
	}
	
	public void showSquadOptions(int vehicle, int index) {
		if(index > -1) {
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridy = 0;
			this.managerConstraints.insets = new Insets(1,1,1,1);
			try {
				for(UAitem unit : UAdata.allUnits) {
					if(unit.getID() == vehicle) {
						this.unitImg.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png"))));
						this.unitName.setText("Unit: "+ unit.getName());
					}
				}
				for(UAitem unit : UAdata.fallbackUnits) {
					if(unit.getID() == vehicle) {
						this.unitImg.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png"))));
						this.unitName.setText("Unit: "+ unit.getName());
					}
				}
			}catch(FileNotFoundException ex) {
				System.out.println("Icon image for unit/building in manager not found");
			}catch(IOException ex) {
				System.out.println("An error occured while loading an image icon for unit/building in manager");
			}
			
			this.numPanel.add(this.numLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1; //TODO checkpoint
			this.numField.setText(Integer.toString(this.map.getSquad(index).getQuantity()));
			this.numPanel.add(this.numField, this.managerConstraints);
			this.managerConstraints.gridx = 0;
			this.ownPanel.add(this.ownLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			if(this.map.getSquad(index).getOwner() == 1) this.ownList.setSelectedIndex(0);
			else if(this.map.getSquad(index).getOwner() == 6) this.ownList.setSelectedIndex(1);
			else if(this.map.getSquad(index).getOwner() == 4) this.ownList.setSelectedIndex(2);
			else if(this.map.getSquad(index).getOwner() == 3) this.ownList.setSelectedIndex(3);
			else if(this.map.getSquad(index).getOwner() == 2) this.ownList.setSelectedIndex(4);
			else if(this.map.getSquad(index).getOwner() == 5) this.ownList.setSelectedIndex(5);
			else if(this.map.getSquad(index).getOwner() == 7) this.ownList.setSelectedIndex(6);
			this.ownPanel.add(this.ownList, this.managerConstraints);
			this.managerConstraints.gridx = 0;
			this.locationPanel.add(this.xUnitLabel, this.managerConstraints);
			this.managerConstraints.gridx = 1;
			this.xUnitField.setText(Integer.toString(this.map.getSquad(index).getUnitX() + (int)(map.getMapSize() * 0.14/ 2)));
			this.locationPanel.add(this.xUnitField, this.managerConstraints);
			this.managerConstraints.gridx = 2;
			this.locationPanel.add(this.yUnitLabel, this.managerConstraints);
			this.managerConstraints.gridx = 3;
			this.yUnitField.setText(Integer.toString(this.map.getSquad(index).getUnitY() + (int)(map.getMapSize() * 0.14/ 2)));
			this.locationPanel.add(this.yUnitField, this.managerConstraints);
			this.managerConstraints.gridx = 0;
			this.useablePanel.setBorder(BorderFactory.createTitledBorder("Useable"));
			if(this.map.getSquad(index).getUseable() == false) this.useable.setSelected(false);
			else this.useable.setSelected(true);
			this.useablePanel.add(this.useable, this.managerConstraints);
			this.unitMbStatusPanel.setBorder(BorderFactory.createTitledBorder("MB status"));
			this.mbStatus.setText("Make this squad invisible in the Mission Briefing");
			if(this.map.getSquad(index).getVisibility() == true) this.mbStatus.setSelected(false);
			else this.mbStatus.setSelected(true);
			this.unitMbStatusPanel.add(this.mbStatus, this.managerConstraints);
			this.applySquadPanel.add(this.applySquad, this.managerConstraints);
			this.currentIndex = index;
			
		}
	}
	
	public void showSectorOptions(int set, int sectorBorder, int sector, int hGrid, int vGrid) {
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;
		this.isBorder = false;
		this.selectedSector = sector;
		
		for(int i = 0,x = 0,y = 0;i <= sectorBorder; i++) {
			if(i == sectorBorder) {
					selectedSectorX = x;
					selectedSectorY = y;
					break;
				}
			x++;
			if(x > (hGrid + 1)) {
				x = 0;
				y++;
			}
		}

		this.sectorInfo.setText("Sector x:"+selectedSectorX+" y:"+selectedSectorY);
		if(selectedSectorX == 0 || selectedSectorY == 0) isBorder = true;
		if(selectedSectorX > hGrid || selectedSectorY > vGrid) isBorder = true;
		if(isBorder == true) {
			this.borderLabel.setText("This sector is border");
			this.borderPanel.add(this.borderLabel);
		}
		this.managerConstraints.insets = new Insets(5,25,5,25);
		if(isBorder == false) {
			this.ownMapPanel.setBorder(BorderFactory.createTitledBorder("Sector owner"));
			if(this.map.getOwnMap(sector) == 0) {
				this.ownMapLabel.setText("This sector is neutral");
			}else if(this.map.getOwnMap(sector) == 1) {
				this.ownMapLabel.setText("Resistance owns this sector");
			}else if(this.map.getOwnMap(sector) == 6) {
				this.ownMapLabel.setText("Ghorkov owns this sector");
			}else if(this.map.getOwnMap(sector) == 4) {
				this.ownMapLabel.setText("Taerkasten owns this sector");
			}else if(this.map.getOwnMap(sector) == 3) {
				this.ownMapLabel.setText("Mykonian owns this sector");
			}else if(this.map.getOwnMap(sector) == 2) {
				this.ownMapLabel.setText("Sulgogar owns this sector");
			}else if(this.map.getOwnMap(sector) == 5) {
				this.ownMapLabel.setText("Black Sect owns this sector");
			}else if(this.map.getOwnMap(sector) == 7) {
				this.ownMapLabel.setText("This sector is neutral(for buildings)");
			}
			this.ownMapPanel.add(this.ownMapLabel);
			
			for(UAitem building : UAdata.allBuildings) {
				if(this.map.getBlgMap(sector) == building.getID()) {
					this.blgMapLabel.setText(building.getName());
					this.blgMapPanel.setBorder(BorderFactory.createTitledBorder("Special building"));
					this.blgMapPanel.add(this.blgMapLabel, this.managerConstraints);
				}
			}
			for(UAitem building : UAdata.fallbackBuildings) {
				if(this.map.getBlgMap(sector) == building.getID()) {
					this.blgMapLabel.setText(building.getName());
					this.blgMapPanel.setBorder(BorderFactory.createTitledBorder("Special building"));
					this.blgMapPanel.add(this.blgMapLabel, this.managerConstraints);
				}
			}
			
			boolean error = true;
			try {
				if(set == 0) {
					for(int i = 0; i < 256; i++) {
						if(i == 54) i = 59;
						if(i == 60) i = 66;
						if(i == 83) i = 95;
						if(i == 105) i = 110;
						if(i == 114) i = 120;
						if(i == 122) i = 130;
						if(i == 142) i = 150;
						if(i == 190) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 228;
						if(i == 237) i = 239;
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set1_with_hex/Set1_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}	
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}else if(set == 1) {
					for(int i = 0; i < 256; i++) {
						if(i == 25) i = 27;
						if(i == 105) i = 110;
						if(i == 114) i = 118;
						if(i == 132) i = 133;
						if(i == 134) i = 150;
						if(i == 196) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 210;
						if(i == 226) i = 228;
						if(i == 231) i = 239;
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResourceAsStream("/img/Sector_images/Set2_with_hex/Set2_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}	
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}else if(set == 2) {
					for(int i = 0; i < 256; i++) {
						if(i == 50) i = 59;
						if(i == 60) i = 66;
						if(i == 83) i = 100;
						if(i == 105) i = 110;
						if(i == 114) i = 121;
						if(i == 122) i = 130;
						if(i == 142) i = 150;
						if(i == 190) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 228;
						if(i == 231) i = 239;
						
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResourceAsStream("/img/Sector_images/Set3_with_hex/Set3_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}else if(set == 3) {
					for(int i = 0; i < 256; i++) {
						if(i == 50) i = 59;
						if(i == 61) i = 66;
						if(i == 83) i = 100;
						if(i == 105) i = 110;
						if(i == 114) i = 121;
						if(i == 122) i = 130;
						if(i == 142) i = 150;
						if(i == 190) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 228;
						if(i == 231) i = 239;
						
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResourceAsStream("/img/Sector_images/Set4_with_hex/Set4_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}else if(set == 4) {
					for(int i = 0; i < 256; i++) {
						if(i == 96) i = 97;
						if(i == 117) i = 118;
						if(i == 132) i = 133;
						if(i == 138) i = 150;
						if(i == 192) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 210;
						if(i == 226) i = 228;
						if(i == 231) i = 239;
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResourceAsStream("/img/Sector_images/Set5_with_hex/Set5_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}else if(set == 5) {
					for(int i = 0; i < 256; i++) {
						if(i == 50) i = 59;
						if(i == 60) i = 66;
						if(i == 83) i = 95;
						if(i == 105) i = 110;
						if(i == 114) i = 121;
						if(i == 122) i = 130;
						if(i == 142) i = 150;
						if(i == 190) i = 198;
						if(i == 206) i = 207;
						if(i == 209) i = 228;
						if(i == 236) i = 239;
						
						
						if(i == this.map.getTypMap(sector)) {
							this.typMapLabel.setText("");
							this.typMapImg = ImageIO.read(this.getClass().getResourceAsStream("/img/Sector_images/Set6_with_hex/Set6_sector"+i+".jpg"));
							this.typMapImg = resizeMap(200,200, this.typMapImg);
							this.typMapLabel.setIcon(new ImageIcon(this.typMapImg));
							this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+i));
							this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
							error = false;
							break;
						}
					}
					if(error == true) {
						this.typMapLabel.setIcon(null);
						this.typMapLabel.setForeground(Color.RED);
						this.typMapLabel.setText("This typ map doesn't exist. Please change it to avoid game crash");
						this.typMapPanel.setBorder(BorderFactory.createTitledBorder("Typ Map "+this.map.getTypMap(sector)));
						this.typMapPanel.add(this.typMapLabel, this.managerConstraints);
					}
				}
				
				if(this.map.getBeamGate(selectedSectorX, selectedSectorY) != null){
					this.beamGatePanel.setBorder(BorderFactory.createTitledBorder("Beam gate "+ this.map.getBeamGateNumber(selectedSectorX, selectedSectorY)));
					this.beamGateLabel.setText("This sector has beam gate");
					this.managerConstraints.gridwidth = 6;
					this.managerConstraints.gridx = 0;
					this.beamGatePanel.add(this.beamGateLabel, this.managerConstraints);
					this.managerConstraints.gridwidth = 1;
					this.managerConstraints.gridy = 1;
					this.managerConstraints.gridwidth = 4;
					this.managerConstraints.gridx = 0;
					this.openedBgLabel.setText("Opened beam gate appearance:");
					this.beamGatePanel.add(this.openedBgLabel, this.managerConstraints);
					this.managerConstraints.gridx = 3;
					this.managerConstraints.gridwidth = 3;
					if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getOpenedType() == 1) this.openedBgList.setSelectedIndex(0);
					else if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getOpenedType() == 2) this.openedBgList.setSelectedIndex(1);
					this.beamGatePanel.add(this.openedBgList, this.managerConstraints);
					this.managerConstraints.gridy = 2;
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridwidth = 4;
					this.closedBgLabel.setText("Closed beam gate appearance:");
					this.beamGatePanel.add(this.closedBgLabel, this.managerConstraints);
					this.managerConstraints.gridx = 3;
					this.managerConstraints.gridwidth = 3;
					if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getClosedType() == 1) this.closedBgList.setSelectedIndex(0);
					else if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getClosedType() == 2) this.closedBgList.setSelectedIndex(1);
					this.beamGatePanel.add(this.closedBgList, this.managerConstraints);
					this.managerConstraints.gridy = 3;
					this.managerConstraints.gridx = 0;
					this.bgMbStatus.setText("Make this beam gate invisible in the Mission Briefing");
					this.managerConstraints.gridwidth = 7;
					if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getVisibility() == false) this.bgMbStatus.setSelected(true);
					else this.bgMbStatus.setSelected(false);
					this.beamGatePanel.add(this.bgMbStatus, this.managerConstraints);
					this.managerConstraints.gridwidth = 1;
					this.bgGridy = 5;
					if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getKeysectors().size() > 0) {
						this.managerConstraints.gridy = 4;
						this.managerConstraints.gridx = 0;
						this.managerConstraints.gridwidth = 6;
						this.bgKeysectorInfo.setText("It contains keysectors:");
						this.beamGatePanel.add(this.bgKeysectorInfo, this.managerConstraints);
						for(int i = 0; i < this.map.getBeamGate(selectedSectorX, selectedSectorY).getKeysectors().size(); i++) {
							this.keysectorList.add(new JLabel());
							this.managerConstraints.gridy = bgGridy;
							this.keysectorList.get(this.keysectorList.size()-1).setText("Keysector "+(i+1)+" at x:"+this.map.getBeamGate(selectedSectorX, selectedSectorY).getKeysectors().get(i).getX()+" y:"+this.map.getBeamGate(selectedSectorX, selectedSectorY).getKeysectors().get(i).getY());
							this.beamGatePanel.add(this.keysectorList.get(this.keysectorList.size()-1), this.managerConstraints);
							
							this.bgGridy++;
						}
						
						this.managerConstraints.gridwidth = 1;	
					}

					this.managerConstraints.gridy = this.bgGridy;
					this.managerConstraints.gridwidth = 3;
					this.beamGatePanel.add(this.levelTargetLabel, this.managerConstraints);
					this.managerConstraints.gridwidth = 3;
					this.managerConstraints.gridx = 2;
					if(map.getContent() == 0)
						this.levelTargetList = new JComboBox<String>(this.levelNames);
					else if(map.getContent() == 1)
						this.levelTargetList = new JComboBox<String>(this.mdLevelNames);
					this.beamGatePanel.add(this.levelTargetList, this.managerConstraints);
					this.managerConstraints.gridwidth = 2;
					this.managerConstraints.gridx = 3;
					this.beamGatePanel.add(this.addlevelTarget, this.managerConstraints);
					this.managerConstraints.gridx = 4;
					this.managerConstraints.gridwidth = 5;
					this.managerConstraints.insets = new Insets(1,50,1,1);
					this.beamGatePanel.add(this.applyBg, this.managerConstraints);
					this.managerConstraints.insets = new Insets(1,1,1,1);
					this.managerConstraints.gridwidth = 4;
					this.bgGridy++;
					this.managerConstraints.gridy = this.bgGridy;
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridwidth = 9;
					this.beamGatePanel.add(this.bgSep, this.managerConstraints);
					this.managerConstraints.gridwidth = 4;
					this.bgGridy++;
					this.managerConstraints.gridy = this.bgGridy;
					this.managerConstraints.gridx = 0;
					
					updateTargetLevel();
					if(this.map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().size() == 0) {
						this.managerConstraints.gridwidth = 9;
						this.beamGatePanel.add(this.errorBg, this.managerConstraints);
						this.managerConstraints.gridwidth = 4;
					}
						
						
					this.managerConstraints.gridwidth = 1;
					this.managerConstraints.gridx = 0;
				}
				
				for(int i = 0; i < this.map.getBeamGateList().size();i++) {
					for(int j = 0; j < this.map.getBeamGateList().get(i).getKeysectors().size(); j++) {
						if(this.map.getBeamGateList().get(i).getKeysectors().get(j).getX() == selectedSectorX && this.map.getBeamGateList().get(i).getKeysectors().get(j).getY() == selectedSectorY) {
							this.keysectorLabel.setText("This key sector belongs to beam gate "+(i+1));
							this.keySectorPanel.setBorder(BorderFactory.createTitledBorder("Key sector "+(j+1)));
							this.keySectorPanel.add(this.keysectorLabel, managerConstraints);
							break;
						}
					}
				}
				
				if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY) != null) {
					this.managerConstraints.gridy = 0;
					this.techUpgradePanel.setBorder(BorderFactory.createTitledBorder("Tech upgrade"));
					this.managerConstraints.insets = new Insets(5,0,5,0);
					this.techUpgradePanel.add(this.techUpgradeLabel, this.managerConstraints);
					this.techUpgradeLabel.setText("Building type: ");
					this.managerConstraints.gridx = 1;
					this.managerConstraints.gridwidth = 5;
					if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 60) {
						this.techUpgradeBuilding.setSelectedIndex(0);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 61) {
						this.techUpgradeBuilding.setSelectedIndex(1);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 4) {
						this.techUpgradeBuilding.setSelectedIndex(2);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 7) {
						this.techUpgradeBuilding.setSelectedIndex(3);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 15) {
						this.techUpgradeBuilding.setSelectedIndex(4);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 51) {
						this.techUpgradeBuilding.setSelectedIndex(5);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 50) {
						this.techUpgradeBuilding.setSelectedIndex(6);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 16) {
						this.techUpgradeBuilding.setSelectedIndex(7);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 65) {
						this.techUpgradeBuilding.setSelectedIndex(8);
						this.techUpgradePanel.add(this.techUpgradeBuilding, this.managerConstraints);
					}
					this.managerConstraints.gridwidth = 1;
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridy = 1;
					this.techUpgradeTypeLabel.setText("Type (sound): ");
					this.techUpgradePanel.add(this.techUpgradeTypeLabel, this.managerConstraints);
					this.managerConstraints.gridx = 1;
					this.managerConstraints.gridwidth = 4;
					if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == -1) {
						this.techUpgradeType.setSelectedIndex(5);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);	
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 1) {
						this.techUpgradeType.setSelectedIndex(0);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 2) {
						this.techUpgradeType.setSelectedIndex(1);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 3) {
						this.techUpgradeType.setSelectedIndex(2);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 4) {
						this.techUpgradeType.setSelectedIndex(3);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);
					}else if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 5) {
						this.techUpgradeType.setSelectedIndex(4);
						this.techUpgradePanel.add(this.techUpgradeType, this.managerConstraints);
					}
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridy = 2;
					this.managerConstraints.gridwidth = 5;
					this.tuMbStatus.setText("Make this tech upgrade invisible in the Mission Briefing");
					if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVisibility() == true) this.tuMbStatus.setSelected(false);
					else this.tuMbStatus.setSelected(true);
					this.techUpgradePanel.add(this.tuMbStatus, this.managerConstraints);
					this.managerConstraints.gridwidth = 1;
					this.managerConstraints.gridy = 3;
					this.managerConstraints.gridx = 0;
					this.managerConstraints.insets = new Insets(1, 1 , 1, 1);
					this.techUpgradePanel.add(this.modifyLabel, this.managerConstraints);
					this.managerConstraints.gridx = 1;
					//this.managerConstraints.anchor = GridBagConstraints.WEST;
					this.techUpgradePanel.add(this.selectModifier, this.managerConstraints);
					this.managerConstraints.gridx = 2;
					this.managerConstraints.insets = new Insets(1, 1 , 1, 1);
					this.techUpgradePanel.add(this.addModifier, this.managerConstraints);
					this.managerConstraints.insets = new Insets(1, 1 , 1, 1);
					this.managerConstraints.gridx = 3;
					this.techUpgradePanel.add(this.saveModifiers, this.managerConstraints);
					updateTechUpgrade();
					
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridwidth = 1;
				}
				
				if(this.map.getStoudsonBomb(selectedSectorX, selectedSectorY) != null) {
					this.stoudsonBombPanel.setBorder(BorderFactory.createTitledBorder("Stoudson Bomb "+ this.map.getStoudsonBombNumber(selectedSectorX, selectedSectorY)));
					this.bombLabel.setText("Bomb appearance:");
					this.managerConstraints.insets = new Insets(5, 1, 5, 1);
					this.stoudsonBombPanel.add(this.bombLabel, this.managerConstraints);
					this.managerConstraints.gridx = 1;
					this.managerConstraints.gridwidth = 1;
					if(this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getBombStyle() == 1) this.bombList.setSelectedIndex(0);
					else if(this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getBombStyle() == 2) this.bombList.setSelectedIndex(1);
					this.stoudsonBombPanel.add(this.bombList, this.managerConstraints);
					this.managerConstraints.gridwidth = 1;
					this.managerConstraints.gridy = 3;
					this.managerConstraints.gridx = 0;
					this.countdownLabel.setText("Bomb countdown:");
					this.stoudsonBombPanel.add(this.countdownLabel, this.managerConstraints);
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridy = 0;
					this.cdHours.setText(Integer.toString(this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getCountdown() / 3600));
					this.countdownPanel.add(this.cdHours, this.managerConstraints);
					this.managerConstraints.gridx = 1;
					this.cdHoursLabel.setText("hours");
					this.countdownPanel.add(this.cdHoursLabel, this.managerConstraints);
					this.managerConstraints.gridx = 2;
					this.cdMinutes.setText(Integer.toString((this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getCountdown() % 3600) / 60));
					this.countdownPanel.add(this.cdMinutes, this.managerConstraints);
					this.cdMinutesLabel.setText("minutes");
					this.managerConstraints.gridx = 3;
					this.countdownPanel.add(this.cdMinutesLabel, this.managerConstraints);
					this.managerConstraints.gridx = 4;
					this.cdSeconds.setText(Integer.toString((this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getCountdown() % 3600) % 60));
					this.countdownPanel.add(this.cdSeconds, this.managerConstraints);
					this.managerConstraints.gridx = 5;
					this.cdSecondsLabel.setText("seconds");
					this.countdownPanel.add(this.cdSecondsLabel, this.managerConstraints);
					
					this.managerConstraints.gridwidth = 6;
					this.managerConstraints.gridy = 3;
					this.managerConstraints.gridx = 1;
					this.stoudsonBombPanel.add(this.countdownPanel, this.managerConstraints);
					this.managerConstraints.gridwidth = 3;
					this.managerConstraints.gridy = 4;
					this.managerConstraints.gridx = 1;
					this.managerConstraints.insets = new Insets(1,1,1,60);
					this.stoudsonBombPanel.add(this.applyBomb, this.managerConstraints);
					if(this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getReactors().size() > 0) {
						this.managerConstraints.gridy = 5;
						this.managerConstraints.gridx = 0;
						this.managerConstraints.insets = new Insets(1,1,1,1);
						this.managerConstraints.gridwidth = 9;
						this.rectorInfoLabel.setText("This bomb contains reactors:");
						this.stoudsonBombPanel.add(this.rectorInfoLabel, this.managerConstraints);
						this.managerConstraints.gridx = 1;
						this.managerConstraints.gridy = 6;
						this.managerConstraints.gridwidth = 3;
						this.managerConstraints.insets = new Insets(1,1,1,60);
						for(int i = 0; i < this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getReactors().size(); i++) {
							this.reactorList.add(new JLabel("Reactor "+(i+1)+" at x:"+this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getReactors().get(i).getX()+" y:"+this.map.getStoudsonBomb(selectedSectorX, selectedSectorY).getReactors().get(i).getY()));
							this.stoudsonBombPanel.add(this.reactorList.get(this.reactorList.size()-1), this.managerConstraints);
							this.sbGridy++;
							this.managerConstraints.gridy = this.sbGridy;
						}
					}
					
					this.managerConstraints.insets = new Insets(1,1,1,1);
					this.managerConstraints.anchor = GridBagConstraints.CENTER;
					this.managerConstraints.gridy = 0;
					this.managerConstraints.gridx = 0;
					this.managerConstraints.gridwidth = 1;
				}
				this.managerConstraints.insets = new Insets(5,5,5,5);
				for(int i = 0; i < this.map.getStoudsonBombList().size(); i++) {
					for(int j = 0; j < this.map.getStoudsonBombList().get(i).getReactors().size(); j++) {
						if(this.map.getStoudsonBombList().get(i).getReactors().get(j).getX() == selectedSectorX && this.map.getStoudsonBombList().get(i).getReactors().get(j).getY() == selectedSectorY) {
							this.reactorTypmapLabel.setText("Reactor appearance: ");
							this.reactorLabel.setText("This reactor belongs to Stoudson Bomb "+(i+1));
							this.reactorPanel.setBorder(BorderFactory.createTitledBorder("Reactor "+(j+1) ));
							this.reactorPanel.add(this.reactorTypmapLabel, managerConstraints);
							this.managerConstraints.gridx = 1;
							if(this.map.getStoudsonBombList().get(i).getBombStyle() == 1) {
								this.reactorTypmapList = new JComboBox<String>(normalReactor);
								if(this.map.getTypMap(selectedSector) == 243) this.reactorTypmapList.setSelectedIndex(0);
								else if(this.map.getTypMap(selectedSector) == 244) this.reactorTypmapList.setSelectedIndex(1);
								else this.reactorTypmapList.setSelectedIndex(2);
							}
							if(this.map.getStoudsonBombList().get(i).getBombStyle() == 2) {
								this.reactorTypmapList = new JComboBox<String>(parasiteReactor);
								if(this.map.getTypMap(selectedSector) == 233) this.reactorTypmapList.setSelectedIndex(0);
								else if(this.map.getTypMap(selectedSector) == 234) this.reactorTypmapList.setSelectedIndex(1);
								else if(this.map.getTypMap(selectedSector) == 232) this.reactorTypmapList.setSelectedIndex(2);
								else if(this.map.getTypMap(selectedSector) == 231) this.reactorTypmapList.setSelectedIndex(3);
								else if(this.map.getTypMap(selectedSector) == 243) this.reactorTypmapList.setSelectedIndex(4);
								else if(this.map.getTypMap(selectedSector) == 244) this.reactorTypmapList.setSelectedIndex(5);
								else this.reactorTypmapList.setSelectedIndex(6);
							}
							this.reactorPanel.add(this.reactorTypmapList, managerConstraints);
							this.managerConstraints.gridy = 1;
							this.managerConstraints.gridx = 0;
							this.managerConstraints.gridwidth = 2;
							this.reactorPanel.add(this.applyReactor, managerConstraints);
							this.managerConstraints.gridy = 2;
							this.managerConstraints.gridx = 0;
							this.reactorPanel.add(this.reactorLabel, managerConstraints);
							this.managerConstraints.anchor = GridBagConstraints.CENTER;
							this.managerConstraints.gridy = 0;
							this.managerConstraints.gridx = 0;
							this.managerConstraints.gridwidth = 1;
							break;
						}
					}

				}
					

			}catch(IOException ex) {
				System.out.println("An error occured while reading a typ_map image");
			}
			this.managerConstraints.insets = new Insets(1,1,1,1);
		}// end isBorder
	}
	
	public void showSectorOptions(int set, ArrayList<Integer> sectorBorder, int hGrid, int vGrid) {
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;
		this.multiGridy = 0;
		sectorInfo.setText("");
		
		for(int sect : sectorBorder) {
			for(int i = 0,x = 0,y = 0;i <= sect; i++) {
				if(i == sect) {
						selectedSectorX = x;
						selectedSectorY = y;
						break;
					}
				x++;
				if(x > (hGrid + 1)) {
					x = 0;
					y++;
				}
			}
			this.sectorsInfo.add(new JLabel("Selected sector x:"+selectedSectorX+" y:"+selectedSectorY));
		}
		for(JLabel infos : sectorsInfo)	{
			this.multiInfoPanel.add(infos,managerConstraints);
			multiGridy++;
			this.managerConstraints.gridy = multiGridy;
		}
	}
	
	public void noUnit() {
		this.applySquadPanel.remove(this.applySquad);
		this.unitMbStatusPanel.remove(this.mbStatus);
		this.unitMbStatusPanel.setBorder(null);
		this.useablePanel.remove(this.useable);
		this.useablePanel.setBorder(null);
		this.locationPanel.remove(this.yUnitField);
		this.locationPanel.remove(this.yUnitLabel);
		this.locationPanel.remove(this.xUnitField);
		this.locationPanel.remove(this.xUnitLabel);
		this.ownPanel.remove(this.ownList);
		this.ownPanel.remove(this.ownLabel);
		this.numPanel.remove(this.numField);
		this.numPanel.remove(this.numLabel);
		
		this.jsonPanel.setBorder(null);
		this.jsonPanel.remove(this.saveJSON);
		this.jsonPanel.remove(this.loadJSON);
		this.applyPanel.remove(this.applyUnit);
		this.imgPanel.setBorder(null);
		this.imgPanel.remove(this.hsType);
		this.imgPanel.remove(this.imgLabel);
		this.mbStatusPanel.setBorder(null);
		this.mbStatusPanel.remove(this.mbStatus);
		this.statsPanel.setBorder(null);
		this.statsPanel.remove(this.secondLabel[7]);
		this.statsPanel.remove(this.cplDelayField);
		this.statsPanel.remove(this.cplDelaySlider);
		this.statsPanel.remove(this.cplDelayLabel);
		this.statsPanel.remove(this.percentLabel[7]);
		this.statsPanel.remove(this.cplBudgetField);
		this.statsPanel.remove(this.cplBudgetSlider);
		this.statsPanel.remove(this.cplBudgetLabel);
		this.statsPanel.remove(this.sep[7]);
		this.statsPanel.remove(this.secondLabel[6]);
		this.statsPanel.remove(this.safDelayField);
		this.statsPanel.remove(this.safDelaySlider);
		this.statsPanel.remove(this.safDelayLabel);
		this.statsPanel.remove(this.percentLabel[6]);
		this.statsPanel.remove(this.safBudgetField);
		this.statsPanel.remove(this.safBudgetSlider);
		this.statsPanel.remove(this.safBudgetLabel);
		this.statsPanel.remove(this.sep[6]);
		this.statsPanel.remove(this.secondLabel[5]);
		this.statsPanel.remove(this.radDelayField);
		this.statsPanel.remove(this.radDelaySlider);
		this.statsPanel.remove(this.radDelayLabel);
		this.statsPanel.remove(this.percentLabel[5]);
		this.statsPanel.remove(this.radBudgetField);
		this.statsPanel.remove(this.radBudgetSlider);
		this.statsPanel.remove(this.radBudgetLabel);
		this.statsPanel.remove(this.sep[5]);
		this.statsPanel.remove(this.secondLabel[4]);
		this.statsPanel.remove(this.powDelayField);
		this.statsPanel.remove(this.powDelaySlider);
		this.statsPanel.remove(this.powDelayLabel);
		this.statsPanel.remove(this.percentLabel[4]);
		this.statsPanel.remove(this.powBudgetField);
		this.statsPanel.remove(this.powBudgetSlider);
		this.statsPanel.remove(this.powBudgetLabel);
		this.statsPanel.remove(this.sep[4]);
		this.statsPanel.remove(this.secondLabel[3]);
		this.statsPanel.remove(this.robDelayField);
		this.statsPanel.remove(this.robDelaySlider);
		this.statsPanel.remove(this.robDelayLabel);
		this.statsPanel.remove(this.percentLabel[3]);
		this.statsPanel.remove(this.robBudgetField);
		this.statsPanel.remove(this.robBudgetSlider);
		this.statsPanel.remove(this.robBudgetLabel);
		this.statsPanel.remove(this.sep[3]);
		this.statsPanel.remove(this.secondLabel[2]);
		this.statsPanel.remove(this.recDelayField);
		this.statsPanel.remove(this.recDelaySlider);
		this.statsPanel.remove(this.recDelayLabel);
		this.statsPanel.remove(this.percentLabel[2]);
		this.statsPanel.remove(this.recBudgetField);
		this.statsPanel.remove(this.recBudgetSlider);
		this.statsPanel.remove(this.recBudgetLabel);
		this.statsPanel.remove(this.sep[2]);
		this.statsPanel.remove(this.secondLabel[1]);
		this.statsPanel.remove(this.defDelayField);
		this.statsPanel.remove(this.defDelaySlider);
		this.statsPanel.remove(this.defDelayLabel);
		this.statsPanel.remove(this.percentLabel[1]);
		this.statsPanel.remove(this.defBudgetField);
		this.statsPanel.remove(this.defBudgetSlider);
		this.statsPanel.remove(this.defBudgetLabel);
		this.statsPanel.remove(this.sep[1]);
		this.statsPanel.remove(this.secondLabel[0]);
		this.statsPanel.remove(this.conDelayField);
		this.statsPanel.remove(this.conDelaySlider);
		this.statsPanel.remove(this.conDelayLabel);
		this.statsPanel.remove(this.percentLabel[0]);
		this.statsPanel.remove(this.conBudgetField);
		this.statsPanel.remove(this.conBudgetSlider);
		this.statsPanel.remove(this.conBudgetLabel);
		this.statsPanel.remove(this.sep[0]);
		this.statsPanel.remove(this.preStats);
		this.statsPanel.remove(this.preStatsLabel);
		this.positionPanel.remove(this.zField);
		this.positionPanel.remove(this.zPosLabel);
		this.positionPanel.remove(this.yField);
		this.positionPanel.remove(this.yPosLabel);
		this.positionPanel.remove(this.xField);
		this.positionPanel.remove(this.xPosLabel);
		this.reloadConstPanel.remove(this.reloadConstField);
		this.reloadConstPanel.remove(this.reloadConstLabel);
		this.viewAnglePanel.remove(this.viewAngleField);
		this.viewAnglePanel.remove(this.viewAngleLabel);
		this.energyPanel.remove(this.energyField);
		this.energyPanel.remove(this.energyLabel);
		
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;
		this.unitName.setText("No unit selected");
		this.unitImg.setIcon(null);
	}
	
	public void noSector() {
		this.tuGridy = 6;
		
		this.sectorsInfo.clear();
		this.multiInfoPanel.removeAll();
		
		this.removeBuilding.clear();
		this.targetHSBuildingEnabler.clear();
		this.blasecBuildingEnabler.clear();
		this.sulgBuildingEnabler.clear();
		this.mykoBuildingEnabler.clear();
		this.taerBuildingEnabler.clear();
		this.ghorBuildingEnabler.clear();
		this.resBuildingEnabler.clear();
		this.buildingAdded.clear();
		this.buildingIDs.clear();
		
		this.removeVehicle.clear();
		this.addShotTimeUserField.clear();
		this.addShotTimeUserLabel.clear();
		this.addShotTimeField.clear();
		this.addShotTimeLabel.clear();
		this.addDamageField.clear();
		this.addDamageLabel.clear();
		this.addRadarField.clear();
		this.addRadarLabel.clear();
		this.addWeaponField.clear();
		this.addWeaponLabel.clear();
		this.addShieldField.clear();
		this.addShieldLabel.clear();
		this.addEnergyField.clear();
		this.addEnergyLabel.clear();
		this.targetHSEnabler.clear();
		this.blasecEnabler.clear();
		this.sulgEnabler.clear();
		this.mykoEnabler.clear();
		this.taerEnabler.clear();
		this.ghorEnabler.clear();
		this.resEnabler.clear();
		this.vehicleAdded.clear();
		this.vehicleIDs.clear();
		
		this.stoudsonBombPanel.removeAll();
		this.stoudsonBombPanel.setBorder(null);
		
		this.techUpgradePanel.removeAll();
		this.techUpgradePanel.remove(this.addModifier);
		this.techUpgradePanel.remove(this.tuMbStatus);
		this.techUpgradePanel.remove(this.techUpgradeType);
		this.techUpgradePanel.remove(this.techUpgradeTypeLabel);
		this.techUpgradePanel.remove(this.techUpgradeBuilding);
		this.techUpgradePanel.remove(this.techUpgradeLabel);
		this.techUpgradePanel.setBorder(null);
		
		this.keySectorPanel.removeAll();
		this.keySectorPanel.setBorder(null);
		
		this.reactorPanel.removeAll();
		this.reactorPanel.setBorder(null);
		
		this.removeLevelTarget.clear();
		this.levelTargets.clear();
		this.beamGatePanel.removeAll();
		this.beamGatePanel.remove(this.beamGateLabel);
		this.beamGatePanel.remove(this.addlevelTarget);
		this.beamGatePanel.setBorder(null);
		
		this.typMapPanel.setBorder(null);
		this.typMapPanel.remove(this.typMapLabel);
		this.blgMapPanel.setBorder(null);
		this.blgMapPanel.remove(this.blgMapLabel);
		this.ownMapPanel.setBorder(null);
		this.ownMapPanel.remove(this.ownMapLabel);
		this.borderPanel.remove(this.borderLabel);
		
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridy = 0;
		this.sectorInfo.setText("No sector selected");
	}
	
	void refreshHoststationXfield(int index) {
		this.xField.setText(Integer.toString(this.map.getHoststation(index).getUnitX()+ (int)(this.map.getMapSize() * 0.5/ 2)));
	}
	void refreshHoststationYfield(int index) {
		this.yField.setText(Integer.toString(this.map.getHoststation(index).getUnitY()+ (int)(this.map.getMapSize() * 0.5/ 2)));
	}
	void refreshSquadXfield(int index) {
		this.xUnitField.setText(Integer.toString(this.map.getSquad(index).getUnitX()+ (int)(this.map.getMapSize() * 0.14/ 2)));
	}
	void refreshSquadYfield(int index) {
		this.yUnitField.setText(Integer.toString(this.map.getSquad(index).getUnitY()+ (int)(this.map.getMapSize() * 0.14/ 2)));
	}
	
	private class ListenerClass implements ChangeListener, ActionListener, KeyListener{
		// TODO ListenerClass starts here
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == conBudgetSlider) {
				conBudgetField.setText(Integer.toString(conBudgetSlider.getValue()) );
			}else if(e.getSource() == conDelaySlider) {
				conDelayField.setText(Integer.toString(conDelaySlider.getValue()) );
			}else if(e.getSource() == defBudgetSlider) {
				defBudgetField.setText(Integer.toString(defBudgetSlider.getValue()) );
			}else if(e.getSource() == defDelaySlider) {
				defDelayField.setText(Integer.toString(defDelaySlider.getValue()) );
			}else if(e.getSource() == recBudgetSlider) {
				recBudgetField.setText(Integer.toString(recBudgetSlider.getValue()) );
			}else if(e.getSource() == recDelaySlider) {
				recDelayField.setText(Integer.toString(recDelaySlider.getValue()) );
			}else if(e.getSource() == robBudgetSlider) {
				robBudgetField.setText(Integer.toString(robBudgetSlider.getValue()) );
			}else if(e.getSource() == robDelaySlider) {
				robDelayField.setText(Integer.toString(robDelaySlider.getValue()) );
			}else if(e.getSource() == powBudgetSlider) {
				powBudgetField.setText(Integer.toString(powBudgetSlider.getValue()) );
			}else if(e.getSource() == powDelaySlider) {
				powDelayField.setText(Integer.toString(powDelaySlider.getValue()) );
			}else if(e.getSource() == radBudgetSlider) {
				radBudgetField.setText(Integer.toString(radBudgetSlider.getValue()) );
			}else if(e.getSource() == radDelaySlider) {
				radDelayField.setText(Integer.toString(radDelaySlider.getValue()) );
			}else if(e.getSource() == safBudgetSlider) {
				safBudgetField.setText(Integer.toString(safBudgetSlider.getValue()) );
			}else if(e.getSource() == safDelaySlider) {
				safDelayField.setText(Integer.toString(safDelaySlider.getValue()) );
			}else if(e.getSource() == cplBudgetSlider) {
				cplBudgetField.setText(Integer.toString(cplBudgetSlider.getValue()) );
			}else if(e.getSource() == cplDelaySlider) {
				cplDelayField.setText(Integer.toString(cplDelaySlider.getValue()) );
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == hsType) {
				try {
					if(hsType.getSelectedIndex() == 0) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/resistanceHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 1) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/ghorkov1HS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 2) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/ghorkov2HS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 3) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/taerkastenHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 4) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/mykonianHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 5) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/sulgogarHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 6) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/blackSectHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}else if(hsType.getSelectedIndex() == 7) {
						hsVehicle = ImageIO.read(this.getClass().getResourceAsStream("/img/hsImg/targetHS.png"));
						imgLabel.setIcon(new ImageIcon(hsVehicle));
					}
					
				}catch(IOException ex) {
					System.out.println("An error occured while loading hoststation image");
				}
			}
			if(e.getSource() == applyUnit) {
				try {
					map.getHoststation(currentIndex).setEnergy(Integer.parseInt(energyField.getText()));
					map.getHoststation(currentIndex).setViewAngle(Integer.parseInt(viewAngleField.getText()));
					map.getHoststation(currentIndex).setReloadConst(Integer.parseInt(reloadConstField.getText()));
					if(Integer.parseInt(xField.getText()) < map.getLeftCorner()) xField.setText(Integer.toString(map.getLeftCorner()));
					if(Integer.parseInt(xField.getText()) > map.getRightCorner()) xField.setText(Integer.toString(map.getRightCorner()));
					map.getHoststation(currentIndex).setUnitX(Integer.parseInt(xField.getText()) - (int)(map.getMapSize() * 0.5/ 2));
					if(Integer.parseInt(yField.getText()) < map.getTopCorner()) yField.setText(Integer.toString(map.getTopCorner()));
					if(Integer.parseInt(yField.getText()) > map.getDownCorner()) yField.setText(Integer.toString(map.getDownCorner()));
					map.getHoststation(currentIndex).setUnitY(Integer.parseInt(yField.getText()) - (int)(map.getMapSize() * 0.5/ 2));
					map.getHoststation(currentIndex).setHeight(Integer.parseInt(zField.getText()));
					
					
					if(Integer.parseInt(conBudgetField.getText()) < 0) conBudgetField.setText("0");
					if(Integer.parseInt(conBudgetField.getText()) > 100) conBudgetField.setText("100");
					map.getHoststation(currentIndex).setConBudget(Integer.parseInt(conBudgetField.getText()));
					conBudgetSlider.setValue(Integer.parseInt(conBudgetField.getText()));
					if(Integer.parseInt(conDelayField.getText()) < 0) conDelayField.setText("0");
					if(Integer.parseInt(conDelayField.getText()) > 9000) conDelayField.setText("9000");
					map.getHoststation(currentIndex).setConDelay(Integer.parseInt(conDelayField.getText()));
					conDelaySlider.setValue(Integer.parseInt(conDelayField.getText()));
					
					if(Integer.parseInt(defBudgetField.getText()) < 0) defBudgetField.setText("0");
					if(Integer.parseInt(defBudgetField.getText()) > 100) defBudgetField.setText("100");
					map.getHoststation(currentIndex).setDefBudget(Integer.parseInt(defBudgetField.getText()));
					defBudgetSlider.setValue(Integer.parseInt(defBudgetField.getText()));
					if(Integer.parseInt(defDelayField.getText()) < 0) defDelayField.setText("0");
					if(Integer.parseInt(defDelayField.getText()) > 9000) defDelayField.setText("9000");
					map.getHoststation(currentIndex).setDefDelay(Integer.parseInt(defDelayField.getText()));
					defDelaySlider.setValue(Integer.parseInt(defDelayField.getText()));
					
					if(Integer.parseInt(recBudgetField.getText()) < 0) recBudgetField.setText("0");
					if(Integer.parseInt(recBudgetField.getText()) > 100) recBudgetField.setText("100");
					map.getHoststation(currentIndex).setRecBudget(Integer.parseInt(recBudgetField.getText()));
					recBudgetSlider.setValue(Integer.parseInt(recBudgetField.getText()));
					if(Integer.parseInt(recDelayField.getText()) < 0) recDelayField.setText("0");
					if(Integer.parseInt(recDelayField.getText()) > 9000) recDelayField.setText("9000");
					map.getHoststation(currentIndex).setRecDelay(Integer.parseInt(recDelayField.getText()));
					recDelaySlider.setValue(Integer.parseInt(recDelayField.getText()));
					
					if(Integer.parseInt(robBudgetField.getText()) < 0) robBudgetField.setText("0");
					if(Integer.parseInt(robBudgetField.getText()) > 100) robBudgetField.setText("100");
					map.getHoststation(currentIndex).setRobBudget(Integer.parseInt(robBudgetField.getText()));
					robBudgetSlider.setValue(Integer.parseInt(robBudgetField.getText()));
					if(Integer.parseInt(robDelayField.getText()) < 0) robDelayField.setText("0");
					if(Integer.parseInt(robDelayField.getText()) > 9000) robDelayField.setText("9000");
					map.getHoststation(currentIndex).setRobDelay(Integer.parseInt(robDelayField.getText()));
					robDelaySlider.setValue(Integer.parseInt(robDelayField.getText()));
					
					if(Integer.parseInt(powBudgetField.getText()) < 0) powBudgetField.setText("0");
					if(Integer.parseInt(powBudgetField.getText()) > 100) powBudgetField.setText("100");
					map.getHoststation(currentIndex).setPowBudget(Integer.parseInt(powBudgetField.getText()));
					powBudgetSlider.setValue(Integer.parseInt(powBudgetField.getText()));
					if(Integer.parseInt(powDelayField.getText()) < 0) powDelayField.setText("0");
					if(Integer.parseInt(powDelayField.getText()) > 9000) powDelayField.setText("9000");
					map.getHoststation(currentIndex).setPowDelay(Integer.parseInt(powDelayField.getText()));
					powDelaySlider.setValue(Integer.parseInt(powDelayField.getText()));
					
					if(Integer.parseInt(radBudgetField.getText()) < 0) radBudgetField.setText("0");
					if(Integer.parseInt(radBudgetField.getText()) > 100) radBudgetField.setText("100");
					map.getHoststation(currentIndex).setRadBudget(Integer.parseInt(radBudgetField.getText()));
					radBudgetSlider.setValue(Integer.parseInt(radBudgetField.getText()));
					if(Integer.parseInt(radDelayField.getText()) < 0) radDelayField.setText("0");
					if(Integer.parseInt(radDelayField.getText()) > 9000) radDelayField.setText("9000");
					map.getHoststation(currentIndex).setRadDelay(Integer.parseInt(radDelayField.getText()));
					radDelaySlider.setValue(Integer.parseInt(radDelayField.getText()));
					
					if(Integer.parseInt(safBudgetField.getText()) < 0) safBudgetField.setText("0");
					if(Integer.parseInt(safBudgetField.getText()) > 100) safBudgetField.setText("100");
					map.getHoststation(currentIndex).setSafBudget(Integer.parseInt(safBudgetField.getText()));
					safBudgetSlider.setValue(Integer.parseInt(safBudgetField.getText()));
					if(Integer.parseInt(safDelayField.getText()) < 0) safDelayField.setText("0");
					if(Integer.parseInt(safDelayField.getText()) > 9000) safDelayField.setText("9000");
					map.getHoststation(currentIndex).setSafDelay(Integer.parseInt(safDelayField.getText()));
					safDelaySlider.setValue(Integer.parseInt(safDelayField.getText()));
					
					if(Integer.parseInt(cplBudgetField.getText()) < 0) cplBudgetField.setText("0");
					if(Integer.parseInt(cplBudgetField.getText()) > 100) cplBudgetField.setText("100");
					map.getHoststation(currentIndex).setCplBudget(Integer.parseInt(cplBudgetField.getText()));
					cplBudgetSlider.setValue(Integer.parseInt(cplBudgetField.getText()));
					if(Integer.parseInt(cplDelayField.getText()) < 0) cplDelayField.setText("0");
					if(Integer.parseInt(cplDelayField.getText()) > 9000) cplDelayField.setText("9000");
					map.getHoststation(currentIndex).setCplDelay(Integer.parseInt(cplDelayField.getText()));
					cplDelaySlider.setValue(Integer.parseInt(cplDelayField.getText()));
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"Values must be numeric", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
				
				if(mbStatus.isSelected()) map.getHoststation(currentIndex).setVisibility(false);
				else map.getHoststation(currentIndex).setVisibility(true);
				
				if(hsType.getSelectedIndex() == 0) {
					map.getHoststation(currentIndex).setVehicle(56);
				}else if(hsType.getSelectedIndex() == 1) {
					map.getHoststation(currentIndex).setVehicle(59);
				}else if(hsType.getSelectedIndex() == 2) {
					map.getHoststation(currentIndex).setVehicle(57);
				}else if(hsType.getSelectedIndex() == 3) {
					map.getHoststation(currentIndex).setVehicle(60);
				}else if(hsType.getSelectedIndex() == 4) {
					map.getHoststation(currentIndex).setVehicle(58);
				}else if(hsType.getSelectedIndex() == 5) {
					map.getHoststation(currentIndex).setVehicle(61);
				}else if(hsType.getSelectedIndex() == 6) {
					map.getHoststation(currentIndex).setVehicle(62);
				}else if(hsType.getSelectedIndex() == 7) {
					map.getHoststation(currentIndex).setVehicle(132);
				}
				map.makeUnsaved();
				map.repaint();
			}
			
			if(e.getSource() == applySquad) {
				try {
					map.getSquad(currentIndex).setQuantity(Integer.parseInt(numField.getText()));
					if(map.getSquad(currentIndex).getQuantity() > 32) {
						map.getSquad(currentIndex).setQuantity(32);
						numField.setText("32");
					}else if(map.getSquad(currentIndex).getQuantity() < 1) {
						map.getSquad(currentIndex).setQuantity(1);
						numField.setText("1");
					}
						
					if(ownList.getSelectedIndex() == 0) {
						map.getSquad(currentIndex).setOwner(1);
					}else if(ownList.getSelectedIndex() == 1) {
						map.getSquad(currentIndex).setOwner(6);
					}else if(ownList.getSelectedIndex() == 2) {
						map.getSquad(currentIndex).setOwner(4);
					}else if(ownList.getSelectedIndex() == 3) {
						map.getSquad(currentIndex).setOwner(3);
					}else if(ownList.getSelectedIndex() == 4) {
						map.getSquad(currentIndex).setOwner(2);
					}else if(ownList.getSelectedIndex() == 5) {
						map.getSquad(currentIndex).setOwner(5);
					}else if(ownList.getSelectedIndex() == 6) {
						map.getSquad(currentIndex).setOwner(7);
					}
					map.getSquad(currentIndex).updateImage();
					
					if(Integer.parseInt(xUnitField.getText()) < map.getLeftCorner()) xUnitField.setText(Integer.toString(map.getLeftCorner()) );
					if(Integer.parseInt(xUnitField.getText()) > map.getRightCorner()) xUnitField.setText(Integer.toString(map.getRightCorner()) );
					map.getSquad(currentIndex).setUnitX(Integer.parseInt(xUnitField.getText()) - (int)(map.getMapSize() * 0.14/ 2));
					if(Integer.parseInt(yUnitField.getText()) < map.getTopCorner()) yUnitField.setText(Integer.toString(map.getTopCorner()) );
					if(Integer.parseInt(yUnitField.getText()) > map.getDownCorner()) yUnitField.setText(Integer.toString(map.getDownCorner()) );
					map.getSquad(currentIndex).setUnitY(Integer.parseInt(yUnitField.getText()) - (int)(map.getMapSize() * 0.14/ 2));
					
					if(useable.isSelected() == false) map.getSquad(currentIndex).setUseable(false);
					else map.getSquad(currentIndex).setUseable(true);
					if(mbStatus.isSelected() == false) map.getSquad(currentIndex).setVisibility(true);
					else map.getSquad(currentIndex).setVisibility(false);
						
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null,"Values must be numeric", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
				map.makeUnsaved();
				map.repaint();
			}
			if(e.getSource() == addModifier) {
				managerConstraints.gridy = tuGridy;
				managerConstraints.gridwidth = 8;
				boolean newVehicle = false;
				int vehID = 0;
				String vehName = "";

				for(UAitem unit : UAdata.allUnits) {
					if (selectModifier.getSelectedItem() == unit.getName()) {
						newVehicle = true;
						vehID = unit.getID();
						vehName = unit.getName();
					}
				}
				for(UAitem unit : UAdata.fallbackUnits) {
					if (selectModifier.getSelectedItem() == unit.getName()) {
						newVehicle = true;
						vehID = unit.getID();
						vehName = unit.getName();
					}
				}
				for(UAitem unit : UAdata.techUpgradeItems) {
					if (selectModifier.getSelectedItem() == unit.getName()) {
						newVehicle = true;
						vehID = unit.getID();
						vehName = unit.getName();
					}
				}


				if(newVehicle) {
					vehicleIDs.add(vehID);
					vehicleAdded.add(new JPanel(new GridBagLayout()));
					vehicleAdded.get(vehicleAdded.size()-1).setBorder(BorderFactory.createTitledBorder(vehName));
					techUpgradePanel.add(vehicleAdded.get(vehicleAdded.size()-1), managerConstraints);
					resEnabler.add(new JCheckBox("Enable for Resistance"));
					ghorEnabler.add(new JCheckBox("Enable for Ghorkov"));
					taerEnabler.add(new JCheckBox("Enable for Taerkasten"));
					mykoEnabler.add(new JCheckBox("Enable for Mykonian"));
					sulgEnabler.add(new JCheckBox("Enable for Sulgogar"));
					blasecEnabler.add(new JCheckBox("Enable for Black Sect"));
					targetHSEnabler.add(new JCheckBox("Enable for Target Host Station"));
					addEnergyLabel.add(new JLabel("Add energy:"));
					addEnergyField.add(new JTextField(4));
					addEnergyField.get(addEnergyField.size() -1).setText("0");
					addShieldLabel.add(new JLabel("Add shield:"));
					addShieldField.add(new JTextField(4));
					addShieldField.get(addShieldField.size() -1).setText("0");
					addWeaponLabel.add(new JLabel("Add weapon:"));
					addWeaponField.add(new JTextField(4));
					addWeaponField.get(addWeaponField.size() -1).setText("0");
					addRadarLabel.add(new JLabel("Add radar:"));
					addRadarField.add(new JTextField(4));
					addRadarField.get(addRadarField.size() -1).setText("0");
					addDamageLabel.add(new JLabel("Add damage:"));
					addDamageField.add(new JTextField(4));
					addDamageField.get(addDamageField.size() -1).setText("0");
					addShotTimeLabel.add(new JLabel("Add shot time:"));
					addShotTimeField.add(new JTextField(4));
					addShotTimeField.get(addShotTimeField.size() -1).setText("0");
					addShotTimeUserLabel.add(new JLabel("Add shot time user:"));
					addShotTimeUserField.add(new JTextField(4));
					addShotTimeUserField.get(addShotTimeUserField.size() -1).setText("0");
					removeVehicle.add(new JButton("Remove"));
					
					managerConstraints.gridy = 0;
					managerConstraints.gridwidth = 3;
					vehicleAdded.get(vehicleAdded.size()-1).add(resEnabler.get(resEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(ghorEnabler.get(ghorEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 2;
					vehicleAdded.get(vehicleAdded.size()-1).add(taerEnabler.get(taerEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 3;
					vehicleAdded.get(vehicleAdded.size()-1).add(mykoEnabler.get(mykoEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 4;
					vehicleAdded.get(vehicleAdded.size()-1).add(sulgEnabler.get(sulgEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 5;
					vehicleAdded.get(vehicleAdded.size()-1).add(blasecEnabler.get(blasecEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 6;
					vehicleAdded.get(vehicleAdded.size()-1).add(targetHSEnabler.get(targetHSEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 7;
					managerConstraints.gridwidth = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addEnergyLabel.get(addEnergyLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addEnergyField.get(addEnergyField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 8;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShieldLabel.get(addShieldLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShieldField.get(addShieldField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 9;
					vehicleAdded.get(vehicleAdded.size()-1).add(addWeaponLabel.get(addWeaponLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addWeaponField.get(addWeaponField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 10;
					vehicleAdded.get(vehicleAdded.size()-1).add(addRadarLabel.get(addRadarLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addRadarField.get(addRadarField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 11;
					vehicleAdded.get(vehicleAdded.size()-1).add(addDamageLabel.get(addDamageLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addDamageField.get(addDamageField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 12;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeLabel.get(addShotTimeLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeField.get(addShotTimeField.size()-1), managerConstraints);
					managerConstraints.gridx = 0;
					managerConstraints.gridy = 13;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeUserLabel.get(addShotTimeUserLabel.size()-1), managerConstraints);
					managerConstraints.gridx = 1;
					vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeUserField.get(addShotTimeUserField.size()-1), managerConstraints);

					
					techUpgradePanel.repaint();
					techUpgradePanel.revalidate();
					tuGridy++;
				}
				managerConstraints.gridx = 0;
				managerConstraints.gridy = tuGridy;
				managerConstraints.gridwidth = 8;

				boolean newBui = false;
				int buiID = 0;
				String buiName = "";

				for(UAitem building : UAdata.allBuildings) {
					if (selectModifier.getSelectedItem() == building.getName()) {
						newBui = true;
						buiID = building.getID();
						buiName = building.getName();
					}
				}
				for(UAitem building : UAdata.fallbackBuildings) {
					if (selectModifier.getSelectedItem() == building.getName()) {
						newBui = true;
						buiID = building.getID();
						buiName = building.getName();
					}
				}
			
				if(newBui) {
					buildingIDs.add(buiID);
					buildingAdded.add(new JPanel(new GridBagLayout()));
					buildingAdded.get(buildingAdded.size()-1).setBorder(BorderFactory.createTitledBorder(buiName));
					techUpgradePanel.add(buildingAdded.get(buildingAdded.size()-1), managerConstraints);
					resBuildingEnabler.add(new JCheckBox("Enable for Resistance"));
					ghorBuildingEnabler.add(new JCheckBox("Enable for Ghorkov"));
					taerBuildingEnabler.add(new JCheckBox("Enable for Taerkasten"));
					mykoBuildingEnabler.add(new JCheckBox("Enable for Mykonian"));
					sulgBuildingEnabler.add(new JCheckBox("Enable for Sulgogar"));
					blasecBuildingEnabler.add(new JCheckBox("Enable for Black Sect"));
					targetHSBuildingEnabler.add(new JCheckBox("Enable for Target Host Station"));
					removeBuilding.add(new JButton("Remove"));
					
					managerConstraints.gridy = 0;
					managerConstraints.gridwidth = 3;
					buildingAdded.get(buildingAdded.size()-1).add(resBuildingEnabler.get(resBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 1;
					buildingAdded.get(buildingAdded.size()-1).add(ghorBuildingEnabler.get(ghorBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 2;
					buildingAdded.get(buildingAdded.size()-1).add(taerBuildingEnabler.get(taerBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 3;
					buildingAdded.get(buildingAdded.size()-1).add(mykoBuildingEnabler.get(mykoBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 4;
					buildingAdded.get(buildingAdded.size()-1).add(sulgBuildingEnabler.get(sulgBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 5;
					buildingAdded.get(buildingAdded.size()-1).add(blasecBuildingEnabler.get(blasecBuildingEnabler.size()-1), managerConstraints);
					managerConstraints.gridy = 6;
					buildingAdded.get(buildingAdded.size()-1).add(targetHSBuildingEnabler.get(targetHSBuildingEnabler.size()-1), managerConstraints);
					
					techUpgradePanel.repaint();
					techUpgradePanel.revalidate();
					tuGridy++;
				}
				
				managerConstraints.gridwidth = 1;
			}
			
			if(e.getSource() == saveModifiers) {
				if(techUpgradeBuilding.getSelectedIndex() == 0) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(60);
				else if(techUpgradeBuilding.getSelectedIndex() == 1) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(61);
				else if(techUpgradeBuilding.getSelectedIndex() == 2) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(4);
				else if(techUpgradeBuilding.getSelectedIndex() == 3) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(7);
				else if(techUpgradeBuilding.getSelectedIndex() == 4) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(15);
				else if(techUpgradeBuilding.getSelectedIndex() == 5) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(51);
				else if(techUpgradeBuilding.getSelectedIndex() == 6) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(50);
				else if(techUpgradeBuilding.getSelectedIndex() == 7) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(16);
				else if(techUpgradeBuilding.getSelectedIndex() == 8) 
					map.getTechUpgrade(selectedSectorX, selectedSectorY).setBuilding(65);

				
				if(techUpgradeType.getSelectedIndex() == 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(1);
				else if(techUpgradeType.getSelectedIndex() == 1) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(2);
				else if(techUpgradeType.getSelectedIndex() == 2) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(3);
				else if(techUpgradeType.getSelectedIndex() == 3) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(4);
				else if(techUpgradeType.getSelectedIndex() == 4) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(5);
				else if(techUpgradeType.getSelectedIndex() == 5) map.getTechUpgrade(selectedSectorX, selectedSectorY).setType(-1);
				
				if(tuMbStatus.isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).setVisibility(false);
				else map.getTechUpgrade(selectedSectorX, selectedSectorY).setVisibility(true);
				
				for(int i = 0; i < vehicleAdded.size(); i++) {
					if(resEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 1);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 1);
					if(ghorEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 6);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 6);
					if(taerEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 4);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 4);
					if(mykoEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 3);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 3);
					if(sulgEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 2);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 2);
					if(blasecEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 5);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 5);
					if(targetHSEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableVehicle(vehicleIDs.get(i), 7);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableVehicle(vehicleIDs.get(i), 7);
					
					try {
						if(Integer.parseInt(addEnergyField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addEnergy(vehicleIDs.get(i), Integer.parseInt(addEnergyField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).setEnergy(0);
						if(Integer.parseInt(addShieldField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addShield(vehicleIDs.get(i), Integer.parseInt(addShieldField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).setShield(0);
						if(Integer.parseInt(addWeaponField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addWeapon(vehicleIDs.get(i), Integer.parseInt(addWeaponField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).setWeaponNum(0);
						if(Integer.parseInt(addRadarField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addRadar(vehicleIDs.get(i), Integer.parseInt(addRadarField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).setRadar(0);
						if(Integer.parseInt(addDamageField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addDamage(vehicleIDs.get(i), Integer.parseInt(addDamageField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).setEnergy(0);
						if(Integer.parseInt(addShotTimeField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addShotTime(vehicleIDs.get(i), Integer.parseInt(addShotTimeField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).setShotTime(0);
						if(Integer.parseInt(addShotTimeUserField.get(i).getText()) != 0) map.getTechUpgrade(selectedSectorX, selectedSectorY).addShotTimeUser(vehicleIDs.get(i), Integer.parseInt(addShotTimeUserField.get(i).getText()));
						else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().size() > i) map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).setShotTimeUser(0);
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,"All the values should be numbers", "Wrong value", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				for(int i = 0; i < buildingAdded.size(); i++) {
					if(resBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 1);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 1);
					if(ghorBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 6);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 6);
					if(taerBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 4);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 4);
					if(mykoBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 3);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 3);
					if(sulgBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 2);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 2);
					if(blasecBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 5);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 5);
					if(targetHSBuildingEnabler.get(i).isSelected()) map.getTechUpgrade(selectedSectorX, selectedSectorY).enableBuilding(buildingIDs.get(i), 7);
					else map.getTechUpgrade(selectedSectorX, selectedSectorY).disableBuilding(buildingIDs.get(i), 7);
					
				}
				map.repaint();
				
				
				
				if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 60) {
					techUpgradeBuilding.setSelectedIndex(0);
					map.setTypMap(selectedSector, 106);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 61) {
					techUpgradeBuilding.setSelectedIndex(1);
					map.setTypMap(selectedSector, 113);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 4) {
					techUpgradeBuilding.setSelectedIndex(2);
					map.setTypMap(selectedSector, 100);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 7) {
					techUpgradeBuilding.setSelectedIndex(3);
					map.setTypMap(selectedSector, 73);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 15) {
					techUpgradeBuilding.setSelectedIndex(4);
					map.setTypMap(selectedSector, 104);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 51) {
					techUpgradeBuilding.setSelectedIndex(5);
					map.setTypMap(selectedSector, 101);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 50) {
					techUpgradeBuilding.setSelectedIndex(6);
					map.setTypMap(selectedSector, 102);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 16) {
					techUpgradeBuilding.setSelectedIndex(7);
					map.setTypMap(selectedSector, 103);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuilding() == 65) {
					techUpgradeBuilding.setSelectedIndex(8);
					map.setTypMap(selectedSector, 110);
				}
				if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == -1) {
					techUpgradeType.setSelectedIndex(5);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 1) {
					techUpgradeType.setSelectedIndex(0);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 2) {
					techUpgradeType.setSelectedIndex(1);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 3) {
					techUpgradeType.setSelectedIndex(2);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 4) {
					techUpgradeType.setSelectedIndex(3);
				}else if(map.getTechUpgrade(selectedSectorX, selectedSectorY).getType() == 5) {
					techUpgradeType.setSelectedIndex(4);
				}
				techUpgradePanel.repaint();
				techUpgradePanel.revalidate();
				noSector();
				map.makeUnsaved();
				map.updateMap();
			}
			
			for(int i = 0; i < removeVehicle.size(); i++) {
				if(e.getSource() == removeVehicle.get(i)) {
					map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().remove(i);
					map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().remove(i);
					
					
					removeVehicle.remove(removeVehicle.get(i));
					addShotTimeUserField.remove(addShotTimeUserField.get(i));
					addShotTimeUserLabel.remove(addShotTimeUserLabel.get(i));
					addShotTimeField.remove(addShotTimeField.get(i));
					addShotTimeLabel.remove(addShotTimeLabel.get(i));
					addDamageField.remove(addDamageField.get(i));
					addDamageLabel.remove(addDamageLabel.get(i));
					addRadarField.remove(addRadarField.get(i));
					addRadarLabel.remove(addRadarLabel.get(i));
					addWeaponField.remove(addWeaponField.get(i));
					addWeaponLabel.remove(addWeaponLabel.get(i));
					addShieldField.remove(addShieldField.get(i));
					addShieldLabel.remove(addShieldLabel.get(i));
					addEnergyField.remove(addEnergyField.get(i));
					addEnergyLabel.remove(addEnergyLabel.get(i));
					targetHSEnabler.remove(targetHSEnabler.get(i));
					blasecEnabler.remove(blasecEnabler.get(i));
					sulgEnabler.remove(sulgEnabler.get(i));
					mykoEnabler.remove(mykoEnabler.get(i));
					taerEnabler.remove(taerEnabler.get(i));
					ghorEnabler.remove(ghorEnabler.get(i));
					resEnabler.remove(resEnabler.get(i));
					vehicleAdded.get(i).setBorder(null);
					vehicleAdded.get(i).removeAll();
					vehicleAdded.remove(vehicleAdded.get(i));
					vehicleIDs.remove(vehicleIDs.get(i));

					techUpgradePanel.repaint();
					techUpgradePanel.revalidate();
					map.makeUnsaved();
				}
			}
			
			for(int i = 0; i < removeBuilding.size(); i++) {
				if(e.getSource() == removeBuilding.get(i)) {
					map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().remove(i);
					
					removeBuilding.remove(removeBuilding.get(i));
					targetHSBuildingEnabler.remove(targetHSBuildingEnabler.get(i));
					blasecBuildingEnabler.remove(blasecBuildingEnabler.get(i));
					sulgBuildingEnabler.remove(sulgBuildingEnabler.get(i));
					mykoBuildingEnabler.remove(mykoBuildingEnabler.get(i));
					taerBuildingEnabler.remove(taerBuildingEnabler.get(i));
					ghorBuildingEnabler.remove(ghorBuildingEnabler.get(i));
					resBuildingEnabler.remove(resBuildingEnabler.get(i));
					buildingAdded.get(i).setBorder(null);
					buildingAdded.get(i).removeAll();
					buildingAdded.remove(buildingAdded.get(i));
					buildingIDs.remove(buildingIDs.get(i));
					
					techUpgradePanel.repaint();
					techUpgradePanel.revalidate();
					map.makeUnsaved();
				}
			}
			
			if(e.getSource() == addlevelTarget) {
				managerConstraints.gridwidth = 4;
				beamGatePanel.remove(errorBg);
				if(map.getContent() == 0) {
					for(int i = 0; i < 44; i++) {
						if(levelTargetList.getSelectedIndex() == i && !map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().contains(levelIDs[i]))
							addLevelTarget(levelNames[i], levelIDs[i]);
					}
				}else if(map.getContent() == 1) {
					for(int i = 0; i < 31; i++) {
						if(levelTargetList.getSelectedIndex() == i && !map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().contains(mdLevelIDs[i]))
							addLevelTarget(mdLevelNames[i], mdLevelIDs[i]);
					}
				}
				
				beamGatePanel.repaint();
				beamGatePanel.revalidate();
				map.makeUnsaved();
				map.repaint();
				managerConstraints.gridx = 0;
				managerConstraints.gridwidth = 1;
				bgGridy++;
			}
			
			for(int i = 0; i < removeLevelTarget.size(); i++) {
				if(e.getSource() == removeLevelTarget.get(i)) {
					map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().remove(i);
					beamGatePanel.remove(removeLevelTarget.get(i));
					beamGatePanel.remove(levelTargets.get(i));
					removeLevelTarget.remove(removeLevelTarget.get(i));
					levelTargets.remove(levelTargets.get(i));

					managerConstraints.gridx = 0;
					managerConstraints.gridwidth = 1;
					bgGridy--;
					managerConstraints.gridy = bgGridy;
					if(map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().size() == 0) {
						managerConstraints.gridwidth = 9;
						beamGatePanel.add(errorBg, managerConstraints);
						managerConstraints.gridwidth = 4;
					}
					
					managerConstraints.gridwidth = 1;
					beamGatePanel.repaint();
					beamGatePanel.revalidate();
					map.makeUnsaved();
					map.repaint();
				}
			}
			if(e.getSource() == applyBg) {
				managerConstraints.insets = new Insets(1,50,1,1);
				if(openedBgList.getSelectedIndex() == 0) map.getBeamGate(selectedSectorX, selectedSectorY).setOpenedType(1);
				else if(openedBgList.getSelectedIndex() == 1) map.getBeamGate(selectedSectorX, selectedSectorY).setOpenedType(2);
				if(closedBgList.getSelectedIndex() == 0) {
					map.getBeamGate(selectedSectorX, selectedSectorY).setClosedType(1);
					map.setTypMap(selectedSector, 202);
				}
				else if(closedBgList.getSelectedIndex() == 1) {
					map.getBeamGate(selectedSectorX, selectedSectorY).setClosedType(2);
					map.setTypMap(selectedSector, 3);
				}
				if(bgMbStatus.isSelected()) map.getBeamGate(selectedSectorX, selectedSectorY).setVisibility(false);
				else map.getBeamGate(selectedSectorX, selectedSectorY).setVisibility(true);
				noSector();
				map.makeUnsaved();
				map.updateMap();
				managerConstraints.insets = new Insets(1,1,1,1);
			}
			if(e.getSource() == applyBomb) {
				if(bombList.getSelectedIndex() == 0) {
					map.getStoudsonBomb(selectedSectorX, selectedSectorY).setBombStyle(1);
					map.setTypMap(selectedSector, 245);
				}
				else if (bombList.getSelectedIndex() == 1) {
					map.getStoudsonBomb(selectedSectorX, selectedSectorY).setBombStyle(2);
					map.setTypMap(selectedSector, 235);
				}
				try {
					if(Integer.parseInt(cdHours.getText()) >=0 && Integer.parseInt(cdMinutes.getText()) >= 0 && Integer.parseInt(cdSeconds.getText()) >=0)
						map.getStoudsonBomb(selectedSectorX, selectedSectorY).setCountdown((Integer.parseInt(cdHours.getText()) * 3600)+(Integer.parseInt(cdMinutes.getText()) * 60)+(Integer.parseInt(cdSeconds.getText())));
					else JOptionPane.showMessageDialog(null,"Values shouldn't be negative", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"Entered value is not a number", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
				noSector();
				map.makeUnsaved();
				map.updateMap();
			}
			if(e.getSource() == applyReactor) {
				for(int i = 0; i < map.getStoudsonBombList().size(); i++) {
					for(int j = 0; j < map.getStoudsonBombList().get(i).getReactors().size(); j++) {
						if(map.getStoudsonBombList().get(i).getReactors().get(j).getX() == selectedSectorX && map.getStoudsonBombList().get(i).getReactors().get(j).getY() == selectedSectorY) {
							if(map.getStoudsonBombList().get(i).getBombStyle() == 1){
								if(reactorTypmapList.getSelectedIndex() == 0) 
									map.setTypMap(selectedSector, 243);
								else if(reactorTypmapList.getSelectedIndex() == 1) 
									map.setTypMap(selectedSector, 244);
							}else if(map.getStoudsonBombList().get(i).getBombStyle() == 2) {
								if(reactorTypmapList.getSelectedIndex() == 0) 
									map.setTypMap(selectedSector, 233);
								else if(reactorTypmapList.getSelectedIndex() == 1) 
									map.setTypMap(selectedSector, 234);
								else if(reactorTypmapList.getSelectedIndex() == 2) 
									map.setTypMap(selectedSector, 232);
								else if(reactorTypmapList.getSelectedIndex() == 3) 
									map.setTypMap(selectedSector, 231);
								else if(reactorTypmapList.getSelectedIndex() == 4) 
									map.setTypMap(selectedSector, 243);
								else if(reactorTypmapList.getSelectedIndex() == 5) 
									map.setTypMap(selectedSector, 244);
							}
									
							break;
						}
					}
				}
				
				noSector();
				map.makeUnsaved();
				map.updateMap();
			}
			if(e.getSource() == loadJSON) {
				if(JFileChooser.APPROVE_OPTION == selectOpenJSON.showOpenDialog(null))
					openJSON(selectOpenJSON.getSelectedFile());
			}
			
			if(e.getSource() == saveJSON) {
				if(JFileChooser.APPROVE_OPTION == selectSaveJSON.showSaveDialog(null))
					saveJSON(selectSaveJSON.getSelectedFile());
			}
		}// end Action Performed
		
		void addLevelTarget(String name, int id) {
			map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().add(id);
			levelTargets.add(new JLabel("This beam gate unlocks: "+name));
			beamGatePanel.add(levelTargets.get(levelTargets.size()-1), managerConstraints);
			managerConstraints.gridx = 3;
			managerConstraints.gridwidth = 4;
			removeLevelTarget.add(new JButton("Remove"));
			beamGatePanel.add(removeLevelTarget.get(removeLevelTarget.size()-1), managerConstraints);
			removeLevelTarget.get(removeLevelTarget.size()-1).addActionListener(this);
			bgGridy++;
			managerConstraints.gridy = bgGridy;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_COMMA) {
				map.previousTypMap();
			}else if(e.getKeyCode() == KeyEvent.VK_PERIOD) {
				map.nextTypMap();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}// TODO end Listener class

	void updateTargetLevel() {
		for(int i = 0; i < map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().size(); i++) {
			for(int j = 0; j < 44; j++) {
				if(map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().get(i) == levelIDs[j])
					levelTargets.add(new JLabel("This beam gate unlocks: "+levelNames[j]));
				else if(j < 31){
					if(map.getBeamGate(selectedSectorX, selectedSectorY).getTargetLevel().get(i) == mdLevelIDs[j])
						levelTargets.add(new JLabel("This beam gate unlocks: "+mdLevelNames[j]));
				}
			}
			
			beamGatePanel.add(levelTargets.get(i), managerConstraints);
			managerConstraints.gridx = 3;
			managerConstraints.gridwidth = 4;
			removeLevelTarget.add(new JButton("Remove"));
			beamGatePanel.add(removeLevelTarget.get(removeLevelTarget.size()-1), managerConstraints);
			removeLevelTarget.get(removeLevelTarget.size()-1).addActionListener(listener);
			managerConstraints.gridx = 1;
			managerConstraints.gridwidth = 3;
			bgGridy++;
			managerConstraints.gridy = bgGridy;
		}
		
		this.beamGatePanel.repaint();
		this.beamGatePanel.revalidate();
		this.managerConstraints.gridx = 0;
		this.managerConstraints.gridwidth = 1;
		this.bgGridy++;
	}
	
	void updateTechUpgrade() {

		for(int i = 0; i < this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().size(); i++){
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 8;
			this.managerConstraints.gridy = tuGridy;
			this.vehicleIDs.add(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getVehicleID());
			this.vehicleAdded.add(new JPanel(new GridBagLayout()));
			String vehicleName = UAdata.getUnit(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getVehicleID()).getName();

			this.vehicleAdded.get(this.vehicleAdded.size()-1).setBorder(BorderFactory.createTitledBorder(vehicleName));
			techUpgradePanel.add(vehicleAdded.get(vehicleAdded.size()-1), managerConstraints);
			resEnabler.add(new JCheckBox("Enable for Resistance"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(1)) resEnabler.get(resEnabler.size()-1).setSelected(true);
			ghorEnabler.add(new JCheckBox("Enable for Ghorkov"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(6)) ghorEnabler.get(ghorEnabler.size()-1).setSelected(true);
			taerEnabler.add(new JCheckBox("Enable for Taerkasten"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(4)) taerEnabler.get(taerEnabler.size()-1).setSelected(true);
			mykoEnabler.add(new JCheckBox("Enable for Mykonian"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(3)) mykoEnabler.get(mykoEnabler.size()-1).setSelected(true);
			sulgEnabler.add(new JCheckBox("Enable for Sulgogar"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(2)) sulgEnabler.get(sulgEnabler.size()-1).setSelected(true);
			blasecEnabler.add(new JCheckBox("Enable for Black Sect"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(5)) blasecEnabler.get(blasecEnabler.size()-1).setSelected(true);
			targetHSEnabler.add(new JCheckBox("Enable for Target Host Station"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnable(7)) targetHSEnabler.get(targetHSEnabler.size()-1).setSelected(true);
			addEnergyLabel.add(new JLabel("Add energy:"));
			addEnergyField.add(new JTextField(4));
			addEnergyField.get(addEnergyField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getEnergy()));
			addShieldLabel.add(new JLabel("Add shield:"));
			addShieldField.add(new JTextField(4));
			addShieldField.get(addShieldField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getShield()));
			addWeaponLabel.add(new JLabel("Add weapon:"));
			addWeaponField.add(new JTextField(4));
			addWeaponField.get(addWeaponField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getWeaponNum()));
			addRadarLabel.add(new JLabel("Add radar:"));
			addRadarField.add(new JTextField(4));
			addRadarField.get(addRadarField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getVehicleList().get(i).getRadar()));
			addDamageLabel.add(new JLabel("Add damage:"));
			addDamageField.add(new JTextField(4));
			addDamageField.get(addDamageField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).getEnergy()));
			addShotTimeLabel.add(new JLabel("Add shot time:"));
			addShotTimeField.add(new JTextField(4));
			addShotTimeField.get(addShotTimeField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).getShotTime()));
			addShotTimeUserLabel.add(new JLabel("Add shot time user:"));
			addShotTimeUserField.add(new JTextField(4));
			addShotTimeUserField.get(addShotTimeUserField.size() -1).setText(Integer.toString(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getWeaponList().get(i).getShotTimeUser()));
			removeVehicle.add(new JButton("Remove"));

			managerConstraints.gridy = 0;
			managerConstraints.gridwidth = 3;
			vehicleAdded.get(vehicleAdded.size()-1).add(resEnabler.get(resEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(ghorEnabler.get(ghorEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 2;
			vehicleAdded.get(vehicleAdded.size()-1).add(taerEnabler.get(taerEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 3;
			vehicleAdded.get(vehicleAdded.size()-1).add(mykoEnabler.get(mykoEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 4;
			vehicleAdded.get(vehicleAdded.size()-1).add(sulgEnabler.get(sulgEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 5;
			vehicleAdded.get(vehicleAdded.size()-1).add(blasecEnabler.get(blasecEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 6;
			vehicleAdded.get(vehicleAdded.size()-1).add(targetHSEnabler.get(targetHSEnabler.size()-1), managerConstraints);
			managerConstraints.gridy = 7;
			managerConstraints.gridx = 0;
			managerConstraints.gridwidth = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addEnergyLabel.get(addEnergyLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addEnergyField.get(addEnergyField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 8;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShieldLabel.get(addShieldLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShieldField.get(addShieldField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 9;
			vehicleAdded.get(vehicleAdded.size()-1).add(addWeaponLabel.get(addWeaponLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addWeaponField.get(addWeaponField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 10;
			vehicleAdded.get(vehicleAdded.size()-1).add(addRadarLabel.get(addRadarLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addRadarField.get(addRadarField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 11;
			vehicleAdded.get(vehicleAdded.size()-1).add(addDamageLabel.get(addDamageLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addDamageField.get(addDamageField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 12;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeLabel.get(addShotTimeLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeField.get(addShotTimeField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 13;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeUserLabel.get(addShotTimeUserLabel.size()-1), managerConstraints);
			managerConstraints.gridx = 1;
			vehicleAdded.get(vehicleAdded.size()-1).add(addShotTimeUserField.get(addShotTimeUserField.size()-1), managerConstraints);
			managerConstraints.gridx = 0;
			managerConstraints.gridy = 14;

			managerConstraints.gridwidth = 3;
			vehicleAdded.get(vehicleAdded.size()-1).add(removeVehicle.get(removeVehicle.size()-1), managerConstraints);
			removeVehicle.get(removeVehicle.size()-1).addActionListener(listener);

			this.techUpgradePanel.repaint();
			this.techUpgradePanel.revalidate();
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 1;
			this.tuGridy++;
		}
		
		for(int i = 0; i < this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().size(); i++) {
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 8;
			this.managerConstraints.gridy = tuGridy;
			this.buildingIDs.add(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getBuildingID());
			this.buildingAdded.add(new JPanel(new GridBagLayout()));
			String buildingName = UAdata.getBuilding(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getBuildingID()).getName();
			
			this.buildingAdded.get(this.buildingAdded.size()-1).setBorder(BorderFactory.createTitledBorder(buildingName));
			this.techUpgradePanel.add(buildingAdded.get(buildingAdded.size()-1), managerConstraints);
			this.resBuildingEnabler.add(new JCheckBox("Enable for Resistance"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(1)) resBuildingEnabler.get(resBuildingEnabler.size()-1).setSelected(true);
			this.ghorBuildingEnabler.add(new JCheckBox("Enable for Ghorkov"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(6)) ghorBuildingEnabler.get(ghorBuildingEnabler.size()-1).setSelected(true);
			this.taerBuildingEnabler.add(new JCheckBox("Enable for Taerkasten"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(4)) taerBuildingEnabler.get(taerBuildingEnabler.size()-1).setSelected(true);
			this.mykoBuildingEnabler.add(new JCheckBox("Enable for Mykonian"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(3)) mykoBuildingEnabler.get(mykoBuildingEnabler.size()-1).setSelected(true);
			this.sulgBuildingEnabler.add(new JCheckBox("Enable for Sulgogar"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(2)) sulgBuildingEnabler.get(sulgBuildingEnabler.size()-1).setSelected(true);
			this.blasecBuildingEnabler.add(new JCheckBox("Enable for Black Sect"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(5)) blasecBuildingEnabler.get(blasecBuildingEnabler.size()-1).setSelected(true);
			this.targetHSBuildingEnabler.add(new JCheckBox("Enable for Target Host Station"));
			if(this.map.getTechUpgrade(selectedSectorX, selectedSectorY).getBuildingList().get(i).getEnable(7)) targetHSBuildingEnabler.get(targetHSBuildingEnabler.size()-1).setSelected(true);
			this.removeBuilding.add(new JButton("Remove"));
			
			this.managerConstraints.gridy = 0;
			this.managerConstraints.gridwidth = 3;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.resBuildingEnabler.get(this.resBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 1;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.ghorBuildingEnabler.get(this.ghorBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 2;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.taerBuildingEnabler.get(this.taerBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 3;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.mykoBuildingEnabler.get(this.mykoBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 4;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.sulgBuildingEnabler.get(this.sulgBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 5;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.blasecBuildingEnabler.get(this.blasecBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 6;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.targetHSBuildingEnabler.get(this.targetHSBuildingEnabler.size()-1), managerConstraints);
			this.managerConstraints.gridy = 7;
			this.buildingAdded.get(this.buildingAdded.size()-1).add(this.removeBuilding.get(this.removeBuilding.size()-1), managerConstraints);
			this.removeBuilding.get(this.removeBuilding.size()-1).addActionListener(listener);
			
			this.techUpgradePanel.repaint();
			this.techUpgradePanel.revalidate();
			this.managerConstraints.gridx = 0;
			this.managerConstraints.gridwidth = 1;
			this.tuGridy++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveJSON(File f) {
		JSONObject stats = new JSONObject();
		stats.put("con_budget", Integer.toString(map.getHoststation(this.currentIndex).getConBudget()));
		stats.put("con_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawConDelay()));
		stats.put("def_budget", Integer.toString(map.getHoststation(this.currentIndex).getDefBudget()));
		stats.put("def_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawDefDelay()));
		stats.put("rec_budget", Integer.toString(map.getHoststation(this.currentIndex).getRecBudget()));
		stats.put("rec_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawRecDelay()));
		stats.put("rob_budget", Integer.toString(map.getHoststation(this.currentIndex).getRobBudget()));
		stats.put("rob_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawRobDelay()));
		stats.put("pow_budget", Integer.toString(map.getHoststation(this.currentIndex).getPowBudget()));
		stats.put("pow_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawPowDelay()));
		stats.put("rad_budget", Integer.toString(map.getHoststation(this.currentIndex).getRadBudget()));
		stats.put("rad_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawRadDelay()));
		stats.put("saf_budget", Integer.toString(map.getHoststation(this.currentIndex).getSafBudget()));
		stats.put("saf_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawSafDelay()));
		stats.put("cpl_budget", Integer.toString(map.getHoststation(this.currentIndex).getCplBudget()));
		stats.put("cpl_delay", Integer.toString(map.getHoststation(this.currentIndex).getRawCplDelay()));
		try {
			statSaver = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			statSaver.println(stats.toString());
			statSaver.close();
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null,"An I/O Error Occurred while saving the JSON file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void openJSON(File f) {
		JSONParser stats = new JSONParser();
		
		try {
			int secToMs = 0;
			Object obj = stats.parse(new FileReader(f));
			JSONObject jsonObject = (JSONObject) obj;
			conBudgetField.setText((String)jsonObject.get("con_budget"));
			conBudgetSlider.setValue(Integer.parseInt(conBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("con_delay"));
			secToMs /= 1000;
			conDelayField.setText(Integer.toString(secToMs));
			conDelaySlider.setValue(secToMs);
			defBudgetField.setText((String)jsonObject.get("def_budget"));
			defBudgetSlider.setValue(Integer.parseInt(defBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("def_delay"));
			secToMs /= 1000;
			defDelayField.setText(Integer.toString(secToMs));
			defDelaySlider.setValue(secToMs);
			recBudgetField.setText((String)jsonObject.get("rec_budget"));
			recBudgetSlider.setValue(Integer.parseInt(recBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("rec_delay"));
			secToMs /= 1000;
			recDelayField.setText(Integer.toString(secToMs));
			recDelaySlider.setValue(secToMs);
			robBudgetField.setText((String)jsonObject.get("rob_budget"));
			robBudgetSlider.setValue(Integer.parseInt(robBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("rob_delay"));
			secToMs /= 1000;
			robDelayField.setText(Integer.toString(secToMs));
			robDelaySlider.setValue(secToMs);
			powBudgetField.setText((String)jsonObject.get("pow_budget"));
			powBudgetSlider.setValue(Integer.parseInt(powBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("pow_delay"));
			secToMs /= 1000;
			powDelayField.setText(Integer.toString(secToMs));
			powDelaySlider.setValue(secToMs);
			radBudgetField.setText((String)jsonObject.get("rad_budget"));
			radBudgetSlider.setValue(Integer.parseInt(radBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("rad_delay"));
			secToMs /= 1000;
			radDelayField.setText(Integer.toString(secToMs));
			radDelaySlider.setValue(secToMs);
			safBudgetField.setText((String)jsonObject.get("saf_budget"));
			safBudgetSlider.setValue(Integer.parseInt(safBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("saf_delay"));
			secToMs /= 1000;
			safDelayField.setText(Integer.toString(secToMs));
			safDelaySlider.setValue(secToMs);
			cplBudgetField.setText((String)jsonObject.get("cpl_budget"));
			cplBudgetSlider.setValue(Integer.parseInt(cplBudgetField.getText()));
			secToMs = Integer.parseInt((String)jsonObject.get("cpl_delay"));
			secToMs /= 1000;
			cplDelayField.setText(Integer.toString(secToMs));
			cplDelaySlider.setValue(secToMs);
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Provided JSON file doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null,"An I/O Error occurred while opening the JSON file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(ParseException ex){
			JOptionPane.showMessageDialog(null,"An I/O Error occurred while parsing the JSON file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"An unknown error occurred while opening the JSON file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void switchTab(int i) {
		this.tabs.setSelectedIndex(i);
	}
	
	public BufferedImage resizeMap(int newW, int newH, BufferedImage img) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    img = dimg;
	    return img;
	}
}// end LevelManager class
