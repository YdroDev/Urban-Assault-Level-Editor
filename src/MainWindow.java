import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import com.jtattoo.plaf.noire.NoireLookAndFeel;



@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private JPanel window;
	private MainMenuListener listenToMenu;
	private int wWidth = 880;
	private int wHeight = 720;
	private int horizontalSectors = 0;
	private int verticalSectors = 0;
	private static JFrame loadingScreen;
	private JMenuBar mainMenu;
	private JScrollPane mapscroller;
	private JMenu fileMenu;
	private JMenu viewMenu;
	private JMenu optionsMenu;
	private JMenu helpMenu;
	private JMenuItem newMap, openMap, saveMap, saveAsMap, clear, exit;
	private int savedMap;
	private FileNameExtensionFilter ldfFilter;
	private JFileChooser selectSaveFile;
	private JFileChooser selectOpenFile;
	private PrintWriter mapSaver;
	private BufferedReader mapOpener;
	private JMenuItem showManager;
	private JMenuItem sectorImgSwitch;
	private JMenuItem sectorHeight;
	private JMenuItem sectorTyp;
	private JMenuItem sectorOwner;
	private JMenuItem sectorBlg;
	private JMenu enabler;
	private JMenuItem menuParams;
	private JMenuItem resEnabler, ghorEnabler, taerEnabler, mykoEnabler, sulgEnabler, blasecEnabler, trainingEnabler;
	private JMenuItem briefingMaps;
	private JMenuItem playerHS;
	private int playerSelected;
	private int playerGridy;
	private JLabel playerHSLabel;
	private JPanel hsListPanel;
	private ArrayList<JRadioButton> availableHS;
	private ButtonGroup playerHSgroup;
	private JLabel noHSavailable;
	private JButton savePlayer;
	private JButton cancelPlayer;
	private JMenuItem levelDescription;
	private JMenuItem modifications;
	private JMenuItem randomTypMap;
	private JMenuItem gameContent;
	private JPanel contentList;
	private JButton saveContent;
	private JButton cancelContent;
	private JRadioButton noneContent;
	private JRadioButton mdContent;
	private int savedContent;
	
	private JDialog newMapDialog;
	private JTextField horizontalNum;
	private JTextField verticalNum;
	private JLabel borderInfo;
	private JDialog paramDialog;
	private JDialog enablerDialog;
	private JDialog briefingDialog;
	private JDialog playerHSDialog;
	private JDialog descriptionDialog;
	private JDialog modsDialog;
	private JDialog contentDialog;
	private JDialog mapsDialog;
	private JPanel mapsPanel;
	private JButton mapsClose;
	private JTabbedPane mapsTabs;
	private JPanel originalMap, MDghorMap, MDtaerMap;
	private JLabel originalLabel, MDghorLabel, MDtaerLabel;
	private BufferedImage originalImg, MDghorImg, MDtaerImg;
	private JDialog shortcutsDialog;
	private JButton shortcutsClose;
	private JDialog aboutDialog;
	private JButton aboutClose;
	private Border MBborder, MDborder;
	private JPanel MBpanel, MDpanel;
	private JTextField MBsizeXField;
	private JTextField MBsizeYField;
	private JTextField MDsizeXField;
	private JTextField MDsizeYField;
	private int MBsizeX;
	private int MBsizeY;
	private int MDsizeX;
	private int MDsizeY;
	private JLabel MBlabelX;
	private JLabel MBlabelY;
	private JLabel MDlabelX;
	private JLabel MDlabelY;
	
	private CheckList resList;
	private CheckList ghorList;
	private CheckList taerList;
	private CheckList mykoList;
	private CheckList sulgList;
	private CheckList blackSectList;
	private CheckList trainingList;
	private CheckList specialList;
	private JCheckBox unlocker;
	
	private GridBagConstraints gridConstraints, paramConstraints , enablerConstraints, playerHSConstraints, descConstraints, modsConstraints, briefingConstraints, mapsConstraints, shortcutsConstraints, contentConstraints, aboutConstraints;
	private boolean imageVisible;
	private boolean heightVisible;
	private boolean typVisible;
	private boolean ownVisible;
	private boolean blgVisible;
	private String descString;
	private String modsString;
	private BorderLayout layout;
	private BufferedImage[] mbMap; 
	private BufferedImage[] mbMapXp;
	private BufferedImage[] dbMap;
	private BufferedImage[] dbMapXp;
	private BufferedImage[] sky;
	private JComboBox<String> mbList;
	private JComboBox<String> dbList;
	private JComboBox<String> setList;
	private JComboBox<String> skyList;
	private JComboBox<String> musicList;
	private JComboBox<String> movieList;
	private JComboBox<String> eventLoopList;
	private String[] mbMaps;
	private String[] dbMaps;
	private String[] mbMapsXp;
	private String[] dbMapsXp;
	private String[] musics;
	private JLabel[] mbMapframe;
	private JLabel[] dbMapframe;
	private JLabel[] mbMapframeXp;
	private JLabel[] dbMapframeXp;
	private JLabel[] skyFrame;
	private JPanel setPanel;
	private JLabel setText;
	private JPanel skyPanel;
	private JLabel skyText;
	private JPanel musicPanel;
	private JPanel moviePanel;
	private JPanel eventLoopPanel;
	private JButton playMusic;
	private JLabel minBreakText;
	private JLabel maxBreakText;
	private JTextField minBreakValue;
	private JTextField maxBreakValue;
	private JSlider minBreakSlider;
	private JSlider maxBreakSlider;
	private JButton saveParams;
	private JButton cancelParams;
	private JButton saveBriefing;
	private JButton cancelBriefing;
	private JButton saveDesc;
	private JButton cancelDesc;
	private AdvancedPlayer player;
	private BufferedInputStream bisPlayer;
	private int selectedMB;
	private int selectedDB;
	private int selectedSet;
	private int selectedSky;
	private int selectedMovie;
	private int selectedEventLoop;
	private int selectedMusic;
	private int selectedMinBreak;
	private int selectedMaxBreak;
	private int savedMB;
	private int savedDB;
	private int savedSet;
	private int savedSky;
	private int savedMovie;
	private int savedEventLoop;
	private int savedMusic;
	private int savedMinBreak;
	private int savedMaxBreak;
	private JMenuItem campaignInfo;
	private JMenuItem shortcutsInfo;
	private JMenuItem aboutInfo;
	
	private GameMap currentMap;
	@SuppressWarnings("rawtypes")
	private SwingWorker audioThread;
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	
	private ArrayList<Integer> resUnits;
	private ArrayList<Integer> ghorUnits;
	private ArrayList<Integer> taerUnits;
	private ArrayList<Integer> mykoUnits;
	private ArrayList<Integer> sulgUnits;
	private ArrayList<Integer> blasecUnits;
	private ArrayList<Integer> trainingUnits;
	
	private ArrayList<Integer> resBuildings;
	private ArrayList<Integer> ghorBuildings;
	private ArrayList<Integer> taerBuildings;
	private ArrayList<Integer> mykoBuildings;
	private ArrayList<Integer> sulgBuildings;
	private ArrayList<Integer> blasecBuildings;
	private ArrayList<Integer> trainingBuildings;
	
	private LevelManager manager;
	static Font font;
	static Font mainFont;
	static Font hgtFont;
	private boolean saved;
	
	public static void main(final String[] args) {
		initLoadingScreen();
		
		UAdata.addOriginalData();
		try {
			Properties props = new Properties();
			props.put("logoString", "");
			NoireLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			font = Font.createFont(Font.PLAIN, MainWindow.class.getResourceAsStream("/Xolonium-Regular.ttf"));
			mainFont = font.deriveFont(font.getSize() * 12f);
			hgtFont = font.deriveFont(font.getSize() * 9f);
			setUIFont (new javax.swing.plaf.FontUIResource(mainFont));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}catch (FontFormatException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
	}
	
	MainWindow(){	
		layout = new BorderLayout();
		window = new JPanel(layout);
		this.setSize(wWidth,wHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Urban Assault Level Editor");
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("/img/icon.png")));
		}catch(IOException ex) {
			System.out.println("Couldn't load main icon for the application");
		}
		this.saved = true;

		listenToMenu = new MainMenuListener();
		gridConstraints = new GridBagConstraints();
		paramConstraints = new GridBagConstraints();
		enablerConstraints = new GridBagConstraints();
		playerHSConstraints = new GridBagConstraints();
		modsConstraints = new GridBagConstraints();
		briefingConstraints = new GridBagConstraints();
		mapsConstraints = new GridBagConstraints();
		descConstraints = new GridBagConstraints();
		contentConstraints = new GridBagConstraints();
		shortcutsConstraints = new GridBagConstraints();
		aboutConstraints = new GridBagConstraints();
		newMapDialog = new JDialog(this, "Create a new map", Dialog.ModalityType.DOCUMENT_MODAL);
		paramDialog = new JDialog(this, "Level parameters", Dialog.ModalityType.DOCUMENT_MODAL);
		enablerDialog = new JDialog(this, "Enabler", Dialog.ModalityType.DOCUMENT_MODAL);
		briefingDialog = new JDialog(this, "Set briefing/debriefing map", Dialog.ModalityType.DOCUMENT_MODAL);
		playerHSDialog = new JDialog(this, "Select Host Station for player", Dialog.ModalityType.DOCUMENT_MODAL);
		descriptionDialog = new JDialog(this, "Set level description", Dialog.ModalityType.DOCUMENT_MODAL);
		modsDialog = new JDialog(this, "Prototype Modifications", Dialog.ModalityType.DOCUMENT_MODAL);
		contentDialog = new JDialog(this, "Additional game content", Dialog.ModalityType.DOCUMENT_MODAL);
		mapsDialog = new JDialog(this, "Campaign maps");
		mapsPanel = new JPanel(new GridBagLayout());
		mapsTabs = new JTabbedPane();
		originalMap = new JPanel();
		MDghorMap = new JPanel();
		MDtaerMap = new JPanel();
		originalLabel = new JLabel();
		MDghorLabel = new JLabel();
		MDtaerLabel = new JLabel();
		shortcutsDialog = new JDialog(this, "Available shortcuts", Dialog.ModalityType.DOCUMENT_MODAL);
		aboutDialog = new JDialog(this, "Urban Assault Level Editor", Dialog.ModalityType.DOCUMENT_MODAL);
		
		dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		now = LocalDateTime.now();
		
		descString = "------ Level name: \n------ Created on: "+dtf.format(now)+" \n------ Designed By: ";
		modsString = "include data:scripts/startup2.scr";
		imageVisible = false;
		heightVisible = false;
		typVisible = false;
		ownVisible = false;
		blgVisible = false;
		MBsizeX = 480;
		MBsizeY = 480;
		MDsizeX = 480;
		MDsizeY = 480;
		mbMaps = new String[74];
		mbMapsXp = new String[31];
		dbMaps = new String[114];
		dbMapsXp = new String[71];
		mbMap = new BufferedImage[74];
		mbMapXp = new BufferedImage[31];
		dbMap = new BufferedImage[114];
		dbMapXp = new BufferedImage[71];
		sky = new BufferedImage[73];
		mbMapframe = new JLabel[74];
		mbMapframeXp = new JLabel[31];
		dbMapframe = new JLabel[114];
		dbMapframeXp = new JLabel[71];
		skyFrame = new JLabel[72];
		selectedMB = 0;
		selectedDB = 0;
		savedMB = 0;
		savedDB = 0;
		

		for(int i = 0, j= 0; i < 74; i++, j++) {
			if(j == 0) {
				mbMaps[i] = "mb";
			}
			if(j > 0 && j < 9) {
				mbMaps[i] = "mb_0" + j;
			}
			if(j == 9) j = 10;
			if(j > 9 && j < 24) {
				mbMaps[i] = "mb_" + j;
			}
			if(j == 24) j = 25;
			if(j > 24 && j < 27) {
				mbMaps[i] = "mb_" + j;
			}
			if(j == 27) j = 28;
			if(j > 27 && j < 49) {
				mbMaps[i] = "mb_" + j;
			}
			if(j == 49)j = 50;
			if(j > 49 && j < 59) {
				mbMaps[i] = "mb_" + j;
			}
			if(j == 59) j = 60;
			if(j > 59 && j < 76) {
				mbMaps[i] = "mb_" + j;
			}
			if(j == 76) j = 77;
			if(j > 76) {
				mbMaps[i] = "mb_" + j;
			}
		}

		for(int i = 0, j = 1; i < 114; i++, j++) {
			if(j < 6) {
				dbMaps[i] = "db_0" + j;
			}
			if(j == 6) j = 10;
			if(j > 9 && j < 13) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 13) j = 15;
			if(j == 15) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 16) j = 20;
			if(j > 19 && j < 24) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 24) j = 25;
			if(j > 24 && j < 27) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 27) j = 30;
			if(j > 29 && j < 35) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 35) j = 40;
			if(j > 39 && j < 45) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 45) j = 50;
			if(j > 49 && j < 55) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 55) j = 60;
			if(j > 59 && j < 65) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 65) j = 66;
			if(j == 66) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 67) j = 70;
			if(j > 69 && j < 76) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 76) j = 80;
			if(j > 79 && j < 97) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 97) j = 100;
			if(j > 99 && j < 136) {
				dbMaps[i] = "db_" + j;
			}
			if(j == 136) j = 140;
			if(j > 139) {
				dbMaps[i] = "db_" + j;
			}
		}
		
		for(int i = 0, j = 0; i < 31; i++, j++) {
			if(j == 0) j = 6;
			if(j > 5 && j < 9)
				mbMapsXp[i] = "mb_0"+j;
			if(j == 9) j = 13;
			if(j > 12 && j < 15)
				mbMapsXp[i] = "mb_"+j;
			if(j == 15) j = 16;
			if(j > 15 && j < 20)
				mbMapsXp[i] = "mb_"+j;
			if(j == 20) j = 28;
			if(j > 27 && j < 30)
				mbMapsXp[i] = "mb_"+j;
			if(j == 30) j = 35;
			if(j > 34 && j < 40)
				mbMapsXp[i] = "mb_"+j;
			if(j == 40) j = 45;
			if(j > 44 && j < 49)
				mbMapsXp[i] = "mb_"+j;
			if(j == 49) j = 55;
			if(j > 54 && j < 59)
				mbMapsXp[i] = "mb_"+j;
			if(j == 59) j = 65;
			if(j == 65) 
				mbMapsXp[i] = "mb_"+j;
			if(j == 66) j = 67;
			if(j > 66 && j < 70)
				mbMapsXp[i] = "mb_"+j;
			if(j == 70) j = 77;
			if(j > 76)
				mbMapsXp[i] = "mb_"+j;
		}
		
		for(int i = 0, j = 0; i < 71; i++, j++) {
			if(j == 0) j = 80;
			if(j > 79 && j < 97)
				dbMapsXp[i] = "db_"+j;
			if(j == 97) j = 100;
			if(j > 99 && j < 121)
				dbMapsXp[i] = "db_"+j;
			if(j == 121) j = 122;
			if(j > 121 && j < 136)
				dbMapsXp[i] = "db_"+j;
			if(j == 136) j = 140;
			if(j > 139)
				dbMapsXp[i] = "db_"+j;
		}
		
		resUnits = new ArrayList<Integer>();
		ghorUnits = new ArrayList<Integer>();
		taerUnits = new ArrayList<Integer>();
		mykoUnits = new ArrayList<Integer>();
		sulgUnits = new ArrayList<Integer>();
		blasecUnits = new ArrayList<Integer>();
		trainingUnits = new ArrayList<Integer>();
		
		resBuildings = new ArrayList<Integer>();
		ghorBuildings = new ArrayList<Integer>();
		taerBuildings = new ArrayList<Integer>();
		mykoBuildings = new ArrayList<Integer>();
		sulgBuildings = new ArrayList<Integer>();
		blasecBuildings = new ArrayList<Integer>();
		trainingBuildings = new ArrayList<Integer>();
		
		resUnits.add(16);
		ghorUnits.add(24);	
		taerUnits.add(32);
		mykoUnits.add(65);
		sulgUnits.add(73);
		trainingUnits.add(138);

		mainMenu = new JMenuBar();
		newMapDialog.addWindowListener(listenToMenu);
		paramDialog.addWindowListener(listenToMenu);
		enablerDialog.addWindowListener(listenToMenu);
		briefingDialog.addWindowListener(listenToMenu);
		playerHSDialog.addWindowListener(listenToMenu);
		descriptionDialog.addWindowListener(listenToMenu);
		modsDialog.addWindowListener(listenToMenu);
		contentDialog.addWindowListener(listenToMenu);
		mapsDialog.addWindowListener(listenToMenu);
		this.addWindowListener(listenToMenu);
		
		fileMenu = new JMenu("File");
		viewMenu = new JMenu("View");
		optionsMenu = new JMenu("Options");
		helpMenu = new JMenu("Help");
		
		newMap = new JMenuItem("New map");
		mainMenu.add(fileMenu);
		fileMenu.add(newMap);
		newMap.addActionListener(listenToMenu);
		
		openMap = new JMenuItem("Open map");
		fileMenu.add(openMap);
		openMap.addActionListener(listenToMenu);
		
		fileMenu.add(new JSeparator());
		saveMap = new JMenuItem("Save");
		fileMenu.add(saveMap);
		saveMap.addActionListener(listenToMenu);
		
		savedMap = JFileChooser.CANCEL_OPTION;
		selectSaveFile = new JFileChooser();
		ldfFilter = new FileNameExtensionFilter("Urban Assault level file (.ldf)", "ldf");
		selectSaveFile.setFileFilter(ldfFilter);
		selectSaveFile.addActionListener(listenToMenu);
		
		selectOpenFile = new JFileChooser();
		selectOpenFile.setFileFilter(ldfFilter);
		selectOpenFile.addActionListener(listenToMenu);
		
		saveAsMap = new JMenuItem("Save As...");
		fileMenu.add(saveAsMap);
		saveAsMap.addActionListener(listenToMenu);
		
		fileMenu.add(new JSeparator());
		clear = new JMenuItem("Close current map");
		fileMenu.add(clear);
		clear.addActionListener(listenToMenu);
		
		fileMenu.add(new JSeparator());
		exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(listenToMenu);
		
		currentMap = new GameMap(this);
		manager = new LevelManager(currentMap);
		
		mainMenu.add(viewMenu);
		
		showManager = new JMenuItem("Show level manager");
		viewMenu.add(showManager);
		showManager.addActionListener(listenToMenu);
		
		
		sectorImgSwitch = new JMenuItem("Show/hide TypMap images");
		viewMenu.add(sectorImgSwitch);
		sectorImgSwitch.addActionListener(listenToMenu);
		
		sectorHeight = new JMenuItem("Show/hide height values");
		viewMenu.add(sectorHeight);
		sectorHeight.addActionListener(listenToMenu);
		
		sectorTyp = new JMenuItem("Show/hide TypMap values");
		viewMenu.add(sectorTyp);
		sectorTyp.addActionListener(listenToMenu);
		
		sectorOwner = new JMenuItem("Show/hide owner values");
		viewMenu.add(sectorOwner);
		sectorOwner.addActionListener(listenToMenu);
		
		sectorBlg = new JMenuItem("Show/hide BlgMap values");
		viewMenu.add(sectorBlg);
		sectorBlg.addActionListener(listenToMenu);
		
		
		
		mainMenu.add(optionsMenu);
		
		menuParams = new JMenuItem("Set level parameters");
		optionsMenu.add(menuParams);
		menuParams.addActionListener(listenToMenu);
		
		enabler = new JMenu("Show enabler for");
		optionsMenu.add(enabler);
		enabler.addActionListener(listenToMenu);
		
		briefingMaps = new JMenuItem("Set briefing/debriefing map for this level");
		optionsMenu.add(briefingMaps);
		briefingMaps.addActionListener(listenToMenu);
		
		playerHS = new JMenuItem("Select Host Station for player");
		optionsMenu.add(playerHS);
		playerHS.addActionListener(listenToMenu);
		
		campaignInfo = new JMenuItem("Campaign maps");
		helpMenu.add(campaignInfo);
		campaignInfo.addActionListener(listenToMenu);
		mapsClose = new JButton("Close");
		mapsClose.addActionListener(listenToMenu);
		
		shortcutsInfo = new JMenuItem("Keyboard shortcuts");
		helpMenu.add(shortcutsInfo);
		shortcutsInfo.addActionListener(listenToMenu);
		shortcutsClose = new JButton("Close");
		shortcutsClose.addActionListener(listenToMenu);
		initShortcuts();
		initGameContent();
		
		aboutInfo = new JMenuItem("About");
		helpMenu.add(aboutInfo);
		aboutInfo.addActionListener(listenToMenu);
		aboutClose = new JButton("Close");
		aboutClose.addActionListener(listenToMenu);
		initAbout();
		
		mainMenu.add(helpMenu);
		
		availableHS = new ArrayList<JRadioButton>();
		
		playerSelected = 0;
		
		levelDescription = new JMenuItem("Set level description");
		optionsMenu.add(levelDescription);
		levelDescription.addActionListener(listenToMenu);
		
		modifications = new JMenuItem("Prototype modifications (Advanced)");
		optionsMenu.add(modifications);
		modifications.addActionListener(listenToMenu);
		
		randomTypMap = new JMenuItem("Generate level appearance randomly");
		optionsMenu.add(randomTypMap);
		randomTypMap.addActionListener(listenToMenu);
		
		gameContent = new JMenuItem("Additional game content");
		optionsMenu.add(gameContent);
		gameContent.addActionListener(listenToMenu);
		
		resEnabler = new JMenuItem("resistance");
		enabler.add(resEnabler);
		resEnabler.addActionListener(listenToMenu);
		
		ghorEnabler = new JMenuItem("ghorkov");
		enabler.add(ghorEnabler);
		ghorEnabler.addActionListener(listenToMenu);
		
		taerEnabler = new JMenuItem("taerkasten");
		enabler.add(taerEnabler);
		taerEnabler.addActionListener(listenToMenu);
		
		mykoEnabler = new JMenuItem("mykonian");
		enabler.add(mykoEnabler);
		mykoEnabler.addActionListener(listenToMenu);
		
		sulgEnabler = new JMenuItem("sulgogar");
		enabler.add(sulgEnabler);
		sulgEnabler.addActionListener(listenToMenu);
		
		blasecEnabler = new JMenuItem("black sect");
		enabler.add(blasecEnabler);
		blasecEnabler.addActionListener(listenToMenu);
		
		trainingEnabler = new JMenuItem("training host station");
		enabler.add(trainingEnabler);
		trainingEnabler.addActionListener(listenToMenu);
		
		currentMap.setPreferredSize(new Dimension(500, 500));

		mapscroller = new JScrollPane(currentMap,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(mapscroller);

		this.setJMenuBar(mainMenu);	
		this.add(window);
		loadingScreen.setVisible(false);
		this.setVisible(true);
	}
	
	private class MainMenuListener implements WindowListener, MenuListener, ActionListener, KeyListener, ChangeListener{

		JButton confirmBut;
		JButton cancelBut;
		JButton resSaveEnabler;
		JButton ghorSaveEnabler;
		JButton taerSaveEnabler;
		JButton mykoSaveEnabler;
		JButton sulgSaveEnabler;
		JButton blasecSaveEnabler;
		JButton trainingSaveEnabler;
		JButton cancelEnabler;
		JLabel dialogNewMap;
		JLabel horizontalDialog;
		JLabel verticalDialog;
		JLabel descInfo;
		JLabel modsInfo;
		JTextArea descData;
		JTextArea modsData;
		JScrollPane modsScroller;
		JScrollPane descScroller;
		JButton saveMods;
		JButton resetMods;
		JButton resetGhorMods;
		JButton resetTaerMods;
		JButton cancelMods;
		int enablerOwner;
		
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == newMap) {
				if(saved == false) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
						if(savedMap == JFileChooser.APPROVE_OPTION) saveLevel(selectSaveFile.getSelectedFile());
					}
				}
				newMapDialog.setSize(400,250);
				newMapDialog.setLocationRelativeTo(null);
				newMapDialog.setResizable(false);
				newMapDialog.setLayout(new GridBagLayout());
				horizontalNum = new JTextField("", 3);
				verticalNum = new JTextField("", 3);
				borderInfo = new JLabel("(Borders will be added automatically to these values)");
				confirmBut = new JButton("OK");
				cancelBut = new JButton("Cancel");
				dialogNewMap = new JLabel("Enter number of sectors for horizontal and vertical space:");
				dialogNewMap.setFont(mainFont);
				horizontalDialog = new JLabel("Horizontal sectors: ");
				verticalDialog = new JLabel("Vertical sectors: ");
				
				gridConstraints.gridy = 0;
				gridConstraints.gridx = 0;
				gridConstraints.gridwidth = 5;
				gridConstraints.insets = new Insets(1,0,20,0);
				newMapDialog.add(dialogNewMap, gridConstraints);
				gridConstraints.gridy = 2;
				gridConstraints.gridx = 0;
				gridConstraints.gridwidth = 1;
				newMapDialog.add(horizontalDialog,gridConstraints);
				gridConstraints.gridx = 1;
				gridConstraints.gridwidth = 1;
				newMapDialog.add(horizontalNum,gridConstraints);
				gridConstraints.gridy = 3;
				gridConstraints.gridx = 0;
				gridConstraints.gridwidth = 1;
				gridConstraints.insets = new Insets(0,13,20,0);
				newMapDialog.add(verticalDialog,gridConstraints);
				gridConstraints.gridx = 1;
				gridConstraints.gridwidth = 1;
				gridConstraints.insets = new Insets(1,0,20,0);
				newMapDialog.add(verticalNum,gridConstraints);
				gridConstraints.gridy = 4;
				gridConstraints.gridx = 0;
				gridConstraints.gridwidth = 6;
				newMapDialog.add(borderInfo,gridConstraints);
				gridConstraints.gridwidth = 1;
				gridConstraints.gridy = 5;
				gridConstraints.gridx = 1;
				gridConstraints.insets = new Insets(20,1,20,10);
				confirmBut.addActionListener(this);
				newMapDialog.add(confirmBut,gridConstraints);
				gridConstraints.gridwidth = 2;
				gridConstraints.gridx = 2;
				gridConstraints.insets = new Insets(20,1,20,0);
				cancelBut.addActionListener(this);
				newMapDialog.add(cancelBut,gridConstraints);
				
				
				newMapDialog.setVisible(true);
			}
			
			if(e.getSource() == openMap) {
				if(saved == false) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Are you sure you want to discard them?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if(JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
							openLevel(selectOpenFile.getSelectedFile());
							savedMap = JFileChooser.APPROVE_OPTION;
							selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
							saved = true;
						}
					}
				}else {
					if(JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
						openLevel(selectOpenFile.getSelectedFile());
						savedMap = JFileChooser.APPROVE_OPTION;
						selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
						saved = true;
					}
				}
			}
			
			if(e.getSource() == saveMap) {
				if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
				if(savedMap == JFileChooser.APPROVE_OPTION) saveLevel(selectSaveFile.getSelectedFile());				
			}

			if(e.getSource() == saveAsMap) {
				if(JFileChooser.APPROVE_OPTION == selectSaveFile.showSaveDialog(null)) 
					saveLevel(selectSaveFile.getSelectedFile());
			}
			
			if(e.getSource() == clear) {
				if(saved == false) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap) 
							saveLevel(selectSaveFile.getSelectedFile());
						currentMap.closeMap();
						setTitle("Urban Assault Level Editor");
						savedMap = JFileChooser.CANCEL_OPTION;
						saved = true;
					}else {
						currentMap.closeMap();
						setTitle("Urban Assault Level Editor");
						savedMap = JFileChooser.CANCEL_OPTION;
						saved = true;
					}
				}else {
					currentMap.closeMap();
					setTitle("Urban Assault Level Editor");
					savedMap = JFileChooser.CANCEL_OPTION;
				}
			}
			
			if(e.getSource() == exit) {
				if(saved == false) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap) 
							saveLevel(selectSaveFile.getSelectedFile());
						System.exit(0);
						saved = true;
					}else System.exit(0);
				}else System.exit(0);
				
			}
			
			if(e.getSource() == sectorImgSwitch) {
				toggleImgSector();
				repaint();
			}
			
			if(e.getSource() == sectorHeight) {
				toggleHeightSector();
				repaint();
			}
			
			if(e.getSource() == sectorTyp) {
				toggleTypSector();
				repaint();
			}
			
			if(e.getSource() == sectorOwner) {
				toggleOwnSector();
				repaint();
			}
			
			if(e.getSource() == sectorBlg) {
				toggleBlgSector();
				repaint();
			}
			
			if(e.getSource() == confirmBut) {
				String hText = horizontalNum.getText();
				String vText = verticalNum.getText();
				int warn = JOptionPane.YES_OPTION;
				
				try {
					horizontalSectors = Integer.parseInt(hText);
					verticalSectors = Integer.parseInt(vText);
					if(horizontalSectors > 0 && verticalSectors > 0) {
						if(horizontalSectors > 64 || verticalSectors > 64) {
							warn = JOptionPane.showConfirmDialog(null,"Playing on level with number of sectors greater than 64 may be unstable. Do you want to proceed?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						}
						if(warn == JOptionPane.YES_OPTION) {
							// CREATE NEW MAP
							currentMap.createMap(horizontalSectors, verticalSectors);
							dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							now = LocalDateTime.now();
							descString = "------ Level name: \n------ Created on: "+dtf.format(now)+" \n------ Designed By: ";
							modsString = "include data:scripts/startup2.scr";
							mapscroller.revalidate();
							setTitle("Urban Assault Level Editor");
							playerSelected = 0;
							removeNewMapDialog();
							newMapDialog.setVisible(false);
							savedMap = JFileChooser.CANCEL_OPTION;
							resUnits.clear();
							resUnits.add(16);
							resBuildings.clear();
							ghorUnits.clear();
							ghorUnits.add(24);
							ghorBuildings.clear();
							taerUnits.clear();
							taerUnits.add(32);
							taerBuildings.clear();
							mykoUnits.clear();
							mykoUnits.add(65);
							mykoBuildings.clear();
							sulgUnits.clear();
							sulgUnits.add(73);
							sulgBuildings.clear();
							blasecUnits.clear();
							blasecBuildings.clear();
							trainingUnits.clear();
							trainingUnits.add(138);
							trainingBuildings.clear();
							makeUnsaved();
						}
					}else {
						JOptionPane.showMessageDialog(newMapDialog,"Both numbers should be greater than 0", "Wrong value", JOptionPane.ERROR_MESSAGE);
					}
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(newMapDialog,"Both fields must be a number", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(e.getSource() == cancelBut) {
				removeNewMapDialog();
				newMapDialog.setVisible(false);
			}	
			if(e.getSource() == menuParams) {
				initParamsDialog();
			}
			if(e.getSource() == resEnabler) {
				showEnabler(1);
			}
			if(e.getSource() == resSaveEnabler) {
				resUnits.clear();
				resBuildings.clear();
				
				enableUnits(resUnits);
				enableBuildings(resBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == ghorEnabler) {
				showEnabler(6);
			}
			if(e.getSource() == ghorSaveEnabler) {
				ghorUnits.clear();
				ghorBuildings.clear();
				
				enableUnits(ghorUnits);
				enableBuildings(ghorBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == taerEnabler) {
				showEnabler(4);
			}
			if(e.getSource() == taerSaveEnabler) {
				taerUnits.clear();
				taerBuildings.clear();
				
				enableUnits(taerUnits);
				enableBuildings(taerBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == mykoEnabler) {
				showEnabler(3);
			}
			if(e.getSource() == mykoSaveEnabler) {
				mykoUnits.clear();
				mykoBuildings.clear();
				
				enableUnits(mykoUnits);
				enableBuildings(mykoBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == sulgEnabler) {
				showEnabler(2);
			}
			if(e.getSource() == sulgSaveEnabler) {
				sulgUnits.clear();
				sulgBuildings.clear();
				
				enableUnits(sulgUnits);
				enableBuildings(sulgBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == blasecEnabler) {
				showEnabler(5);
			}
			if(e.getSource() == blasecSaveEnabler) {
				blasecUnits.clear();
				blasecBuildings.clear();
				
				enableUnits(blasecUnits);
				enableBuildings(blasecBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == trainingEnabler) {
				showEnabler(7);
			}
			if(e.getSource() == trainingSaveEnabler) {
				trainingUnits.clear();
				trainingBuildings.clear();
				
				enableUnits(trainingUnits);
				enableBuildings(trainingBuildings);
				
				removeEnablerDialog();
				enablerDialog.setVisible(false);
				makeUnsaved();
			}
			
			
			
			if(e.getSource() == cancelEnabler) {
				removeEnablerDialog();
				enablerDialog.setVisible(false);
			}
			
			if(e.getSource() == unlocker) {
				if(unlocker.isSelected()) unlock();
				else lock();
			}
			
			if(e.getSource() == savePlayer) {
				for(int i = 0; i < availableHS.size(); i++) {
					if(availableHS.get(i).isSelected())
						playerSelected = i;
				}
				removePlayerHSDialog();
				playerHSDialog.setVisible(false);
				makeUnsaved();
			}
			
			if(e.getSource() == cancelPlayer) {
				removePlayerHSDialog();
				playerHSDialog.setVisible(false);
			}
			
			if(e.getSource() == modifications) {
				modsDialog.setSize(900, 720);
				modsDialog.setLocationRelativeTo(null);
				modsDialog.setResizable(false);
				modsDialog.setLayout(new GridBagLayout());
				
				modsInfo = new JLabel("This section allows you to modify any vehicle, weapon or building. \nThis is for advanced users so typing incorrect data may crash the game");
				modsConstraints.gridy = 0;
				modsConstraints.gridx = 0;
				modsConstraints.insets = new Insets (10,0,10,0);
				modsConstraints.gridwidth = 6;
				modsDialog.add(modsInfo, modsConstraints);
				modsData = new JTextArea(30,78);
				modsData.setText(modsString);
				modsData.setLineWrap(true);
				modsData.setWrapStyleWord(true);
				modsScroller = new JScrollPane(modsData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				modsConstraints.gridy = 1;
				modsDialog.add(modsScroller, modsConstraints);
				
				modsConstraints.anchor = GridBagConstraints.WEST;
				modsConstraints.gridwidth = 1;
				modsConstraints.insets = new Insets (2,0,2,0);
				modsConstraints.gridx = 1;
				modsConstraints.gridy = 2;
				resetMods = new JButton("Reset for original campaign");
				modsDialog.add(resetMods, modsConstraints);
				resetMods.addActionListener(this);
				
				modsConstraints.gridy = 3;
				resetGhorMods = new JButton("Reset for metropoils dawn(Ghorkov)");
				modsDialog.add(resetGhorMods, modsConstraints);
				resetGhorMods.addActionListener(this);
				
				modsConstraints.gridy = 4;
				resetTaerMods = new JButton("Reset for metropoils dawn(Taerkasten)");
				modsDialog.add(resetTaerMods, modsConstraints);
				resetTaerMods.addActionListener(this);
				
				saveMods = new JButton("Save");
				modsConstraints.gridx = 5;
				modsConstraints.anchor = GridBagConstraints.EAST;
				modsConstraints.gridy = 2;
				modsDialog.add(saveMods, modsConstraints);
				saveMods.addActionListener(this);
				
				modsConstraints.insets = new Insets (10,0,10,0);
				cancelMods = new JButton("Cancel");
				modsConstraints.gridy = 3;
				modsDialog.add(cancelMods, modsConstraints);
				cancelMods.addActionListener(this);
				modsConstraints.anchor = GridBagConstraints.CENTER;
				
				modsDialog.setVisible(true);
			}
			if(e.getSource() == randomTypMap) {
				if(currentMap.getHorizontalGrid() > 0 && currentMap.getVerticalGrid() > 0) {
					if(JOptionPane.showConfirmDialog(null,"This will change typ_map on every sector based on current set. Are you sure you want to execute this?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						for(int i = 0, rand = 0, fail = 1; i < (currentMap.getHorizontalGrid() * currentMap.getVerticalGrid()); i++) {
							fail = 1;
							if(savedSet == 0) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 53 && rand < 59) fail = 1;
									if(rand > 59 && rand < 66) fail = 1;
									if(rand > 82 && rand < 95) fail = 1;
									if(rand > 104 && rand < 110) fail = 1;
									if(rand > 113 && rand < 120) fail = 1;
									if(rand > 121 && rand < 130) fail = 1;
									if(rand > 141 && rand < 150) fail = 1;
									if(rand > 189 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 228) fail = 1;
									if(rand > 236 && rand < 239) fail = 1;
								}
								
							}else if(savedSet == 1) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 24 && rand < 27) fail = 1;
									if(rand > 104 && rand < 110) fail = 1;
									if(rand > 113 && rand < 118) fail = 1;
									if(rand > 131 && rand < 133) fail = 1;
									if(rand > 133 && rand < 150) fail = 1;
									if(rand > 195 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 210) fail = 1;
									if(rand > 225 && rand < 228) fail = 1;
									if(rand > 230 && rand < 239) fail = 1;
								}
							}else if(savedSet == 2) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 49 && rand < 59) fail = 1;
									if(rand > 59 && rand < 66) fail = 1;
									if(rand > 82 && rand < 100) fail = 1;
									if(rand > 104 && rand < 110) fail = 1;
									if(rand > 113 && rand < 121) fail = 1;
									if(rand > 121 && rand < 130) fail = 1;
									if(rand > 141 && rand < 150) fail = 1;
									if(rand > 189 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 228) fail = 1;
									if(rand > 230 && rand < 239) fail = 1;
								}
							}else if(savedSet == 3) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 49 && rand < 59) fail = 1;
									if(rand > 60 && rand < 66) fail = 1;
									if(rand > 82 && rand < 100) fail = 1;
									if(rand > 104 && rand < 110) fail = 1;
									if(rand > 113 && rand < 121) fail = 1;
									if(rand > 121 && rand < 130) fail = 1;
									if(rand > 141 && rand < 150) fail = 1;
									if(rand > 189 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 228) fail = 1;
									if(rand > 230 && rand < 239) fail = 1;
								}
							}else if(savedSet == 4) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 95 && rand < 97) fail = 1;
									if(rand > 116 && rand < 118) fail = 1;
									if(rand > 131 && rand < 133) fail = 1;
									if(rand > 137 && rand < 150) fail = 1;
									if(rand > 191 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 210) fail = 1;
									if(rand > 225 && rand < 228) fail = 1;
									if(rand > 230 && rand < 239) fail = 1;
								}
							}else if(savedSet == 5) {
								while(fail == 1){
									rand = (int)(Math.random() * ((255 - 0) + 1));
									fail = 0;
									if(rand > 49 && rand < 59) fail = 1;
									if(rand > 59 && rand < 66) fail = 1;
									if(rand > 82 && rand < 95) fail = 1;
									if(rand > 104 && rand < 110) fail = 1;
									if(rand > 113 && rand < 121) fail = 1;
									if(rand > 121 && rand < 130) fail = 1;
									if(rand > 141 && rand < 150) fail = 1;
									if(rand > 189 && rand < 198) fail = 1;
									if(rand > 205 && rand < 207) fail = 1;
									if(rand > 208 && rand < 228) fail = 1;
									if(rand > 235 && rand < 239) fail = 1;
								}
							}
							currentMap.setTypMap(i, rand);
						}
						repaint();
						currentMap.updateMap();
						makeUnsaved();
					}
				}
			}
			
			if(e.getSource() == gameContent) {
				contentDialog.setVisible(true);
			}
			if(e.getSource() == saveMods) {
				modsString = modsData.getText();
				removeModsDialog();
				modsDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == resetMods) {
				modsData.setText("include data:scripts/startup2.scr");
			}
			if(e.getSource() == resetGhorMods) {
				modsData.setText("include script:startupG.scr");
			}
			if(e.getSource() == resetTaerMods) {
				modsData.setText("include script:startupT.scr");
			}
			
			if(e.getSource() == cancelMods) {
				removeModsDialog();
				modsDialog.setVisible(false);
			}
			
			if(e.getSource() == briefingMaps) {
				initBriefingMaps();
			}
			
			if(e.getSource() == playerHS) {
				playerHSDialog.setSize(300, 400);
				playerHSDialog.setResizable(false);
				playerHSDialog.setLocationRelativeTo(null);
				playerHSDialog.setLayout(new GridBagLayout());
				
				playerHSConstraints.gridx = 0;
				playerHSConstraints.gridy = 0;
				playerHSLabel = new JLabel("Select Host Station you want to play with");
				playerHSConstraints.gridwidth = 6;
				playerHSDialog.add(playerHSLabel, playerHSConstraints);
				playerHSConstraints.gridwidth = 1;
				hsListPanel = new JPanel(new GridBagLayout());
				playerHSgroup = new ButtonGroup();
				playerHSConstraints.insets = new Insets(10,1,10,1);
				playerGridy = 1;
				playerHSConstraints.gridy = playerGridy;
				if(currentMap.getHoststation(0) == null) {
					noHSavailable = new JLabel("No Host Station available");
					hsListPanel.add(noHSavailable, playerHSConstraints);
				}else {
					playerHSConstraints.insets = new Insets(5,1,5,1);
					playerHSConstraints.anchor = GridBagConstraints.WEST;
					hsListPanel.setBorder(BorderFactory.createTitledBorder("Available Host Stations"));
					for(int i = 0;;i++) {
						if(currentMap.getHoststation(i) == null) break;
						
						if(currentMap.getHoststation(i).getOwner() == 1) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Resistance"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 6) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Ghorkov"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 4) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Taerkasten"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 3) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Mykonian"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 2) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Sulgogar"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 5) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Black Sect"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						if(currentMap.getHoststation(i).getOwner() == 7) {
							availableHS.add(new JRadioButton("Host Station "+(i+1)+": Target Host Station"));
							playerHSgroup.add(availableHS.get(availableHS.size()-1));
							hsListPanel.add(availableHS.get(availableHS.size()-1),  playerHSConstraints);
							playerGridy++;
							playerHSConstraints.gridy = playerGridy;
						}
						
						
					}
					if(availableHS.size() <= playerSelected) playerSelected = availableHS.size()-1;
					availableHS.get(playerSelected).setSelected(true);
				}
				playerHSConstraints.anchor = GridBagConstraints.CENTER;
				
				playerHSConstraints.gridy = 1;
				playerHSConstraints.gridwidth = 6;
				playerHSConstraints.gridx = 1;
				playerHSDialog.add(hsListPanel, playerHSConstraints);
				playerHSConstraints.gridwidth = 2;
				playerHSConstraints.gridx = 3;
				savePlayer = new JButton("Save");
				playerHSConstraints.gridy = 2;
				playerHSConstraints.insets = new Insets(5,60,5,1);
				playerHSDialog.add(savePlayer, playerHSConstraints);
				playerHSConstraints.gridwidth = 1;
				savePlayer.addActionListener(this);
				playerHSConstraints.gridx = 5;
				playerHSConstraints.insets = new Insets(5,1,5,30);
				cancelPlayer = new JButton("Cancel");
				playerHSDialog.add(cancelPlayer, playerHSConstraints);
				cancelPlayer.addActionListener(this);
				playerHSConstraints.insets = new Insets(1,1,1,1);
				
				playerHSDialog.setVisible(true);
			}
			
			if(e.getSource() == mbList) {
				selectedMB = mbList.getSelectedIndex();
				removeBriefingMapDialog();
				initBriefingMaps();
			}
			
			if(e.getSource() == dbList) {
				selectedDB = dbList.getSelectedIndex();
				removeBriefingMapDialog();
				initBriefingMaps();
			}
			
			if(e.getSource() == saveBriefing) {
				try {
					MBsizeX = Integer.parseInt(MBsizeXField.getText());
					MBsizeY = Integer.parseInt(MBsizeYField.getText());
					MDsizeX = Integer.parseInt(MDsizeXField.getText());
					MDsizeY = Integer.parseInt(MDsizeYField.getText());
					removeBriefingMapDialog();
					savedMB = selectedMB;
					savedDB = selectedDB;
					briefingDialog.setVisible(false);
					makeUnsaved();
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(briefingDialog,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);	
				}
			}
			if(e.getSource() == cancelBriefing) {
				removeBriefingMapDialog();
				selectedMB = savedMB;
				selectedDB = savedDB;
				briefingDialog.setVisible(false);
			}
			if(e.getSource() == levelDescription){
				descriptionDialog.setSize(900, 880);
				descriptionDialog.setLocationRelativeTo(null);
				descriptionDialog.setResizable(false);
				descriptionDialog.setLayout(new GridBagLayout());
				
				descInfo = new JLabel("You can write description to this level here which will be added at the beginning of ldf file");
				descData = new JTextArea(42, 80);
				
				descData.setText(descString);
				descScroller = new JScrollPane(descData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				descScroller.setPreferredSize(new Dimension(890, 700));
				saveDesc = new JButton("Save");
				cancelDesc = new JButton("Cancel");
				
				descConstraints.gridx = 0;
				descConstraints.gridy = 0;
				descConstraints.insets = new Insets(3, 2, 2, 2);
				descriptionDialog.add(descInfo, descConstraints);
				descConstraints.gridy = 1;
				descConstraints.insets = new Insets(20, 2, 2, 2);
				descriptionDialog.add(descScroller, descConstraints);
				descConstraints.gridy = 2;
				descriptionDialog.add(saveDesc, descConstraints);
				saveDesc.addActionListener(this);
				descConstraints.gridy = 3;
				descriptionDialog.add(cancelDesc, descConstraints);
				cancelDesc.addActionListener(this);
				
				descriptionDialog.setVisible(true);
			}
				
			if(e.getSource() == setList) {
				selectedSet = setList.getSelectedIndex();
			}
			if(e.getSource() == movieList) {
				selectedMovie = movieList.getSelectedIndex();
			}
			if(e.getSource() == eventLoopList) {
				selectedEventLoop = eventLoopList.getSelectedIndex();
			}
			if(e.getSource() == skyList) {
				selectedSky = skyList.getSelectedIndex();
				removeParamsDialog();
				initParamsDialog();
			}
			if(e.getSource() == musicList) {
				selectedMusic = musicList.getSelectedIndex();
			}
			if(e.getSource() == playMusic) {
				initMusic();
				audioThread.execute();
			}
			
			
			if(e.getSource() == saveParams) {
				try
				{
					savedSet = selectedSet;
					savedMovie = selectedMovie;
					savedEventLoop = selectedEventLoop;
					savedSky = selectedSky;
					savedMusic = selectedMusic;
					selectedMinBreak = Integer.parseInt(minBreakValue.getText());
					selectedMaxBreak = Integer.parseInt(maxBreakValue.getText());
					if(selectedMinBreak < 0) selectedMinBreak = -selectedMinBreak;
					if(selectedMinBreak > 1000000) selectedMinBreak = 1000000;
					if(selectedMaxBreak < 0) selectedMaxBreak = -selectedMaxBreak;
					if(selectedMaxBreak > 1000000) selectedMaxBreak = 1000000;
					savedMinBreak = selectedMinBreak;
					savedMaxBreak = selectedMaxBreak;
					if(player != null)player.close();
					removeParamsDialog();
					paramDialog.setVisible(false);
					repaint();
					cleanManager();
					updateManagerSector(currentMap.getSelectedBorderSector(), currentMap.getSelectedSector(), currentMap.getHorizontalGrid(), currentMap.getVerticalGrid());
					makeUnsaved();
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(paramDialog,"Values must be numbers", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(e.getSource() == cancelParams) {
				selectedSet = savedSet;
				selectedMovie = savedMovie;
				selectedEventLoop = savedEventLoop;
				selectedSky = savedSky;
				selectedMusic = savedMusic;
				selectedMinBreak = savedMinBreak;
				selectedMaxBreak = savedMaxBreak;
				if(player != null)player.close();
				removeParamsDialog();
				paramDialog.setVisible(false);
			}
			if(e.getSource() == saveDesc) {
				descString = descData.getText();
				removeDescDialog();
				descriptionDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == cancelDesc) {
				removeDescDialog();
				descriptionDialog.setVisible(false);
			}
			if(e.getSource() == showManager) {
				manager.setVisible(true);
			}
			if(e.getSource() == campaignInfo) {
				mapsDialog.setSize(670, 580);
				mapsDialog.setLocationRelativeTo(null);
				mapsDialog.setResizable(false);
				
				try {
					originalImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/originalMap.jpg"));
					originalLabel.setIcon(new ImageIcon(originalImg));
					MDghorImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/MDGhorMap.jpg"));
					MDghorLabel.setIcon(new ImageIcon(MDghorImg));
					MDtaerImg = ImageIO.read(this.getClass().getResource("/img/campaignMaps/MDTaerMap.jpg"));
					MDtaerLabel.setIcon(new ImageIcon(MDtaerImg));
				}catch(IOException ex) {
					System.out.println("campaign map could not load");
				}
				originalMap.add(originalLabel);
				MDghorMap.add(MDghorLabel);
				MDtaerMap.add(MDtaerLabel);
				mapsTabs.addTab("Original levels", originalMap);
				mapsTabs.addTab("Metropolis Dawn levels(Ghorkov)", MDghorMap);
				mapsTabs.addTab("Metropolis Dawn levels(Taerkasten)", MDtaerMap);
				mapsConstraints.gridx = 0;
				mapsConstraints.gridy = 0;
				mapsPanel.add(mapsTabs, mapsConstraints);
				mapsConstraints.gridy = 1;
				mapsPanel.add(mapsClose, mapsConstraints);
				
				mapsDialog.add(mapsPanel);
				mapsDialog.setVisible(true);
			}
			if(e.getSource() == mapsClose) {
				removeMapsDialog();
				mapsDialog.setVisible(false);
			}
			if(e.getSource() == shortcutsInfo) {
				shortcutsDialog.setVisible(true);
			}
			if(e.getSource() == shortcutsClose) {
				shortcutsDialog.setVisible(false);
			}
			if(e.getSource() == aboutInfo) {
				aboutDialog.setVisible(true);
			}
			if(e.getSource() == aboutClose) {
				aboutDialog.setVisible(false);
			}
			if(e.getSource() == saveContent) {
				if(noneContent.isSelected()) {
					savedContent = 0;
					UAdata.addOriginalData();
				}
				else if(mdContent.isSelected()) {
					savedContent = 1;
					UAdata.addMetropolisDawnData();
				}
				savedMB = 0;
				selectedMB = 0;
				savedDB = 0;
				selectedDB = 0;
				currentMap.removeAddUnits();
				currentMap.initAddUnits();
				currentMap.removeSpecialBuildings();
				currentMap.initAddBuildings();
				currentMap.resizeBuildingImg();
				cleanManager();
				contentDialog.setVisible(false);
				makeUnsaved();
			}
			if(e.getSource() == cancelContent) {
				if(savedContent == 0)
					noneContent.setSelected(true);
				else if(savedContent == 1)
					mdContent.setSelected(true);
				contentDialog.setVisible(false);
			}
		}// end actionPerformed
		@Override
		public void menuSelected(MenuEvent e) {

		}

		@Override
		public void menuDeselected(MenuEvent e) {

			
		}

		@Override
		public void menuCanceled(MenuEvent e) {

			
		}
		
		void initEnabler(int owner) {
			resList = new CheckList("Resistance");
			ghorList = new CheckList("Ghorkov");
			taerList = new CheckList("Taerkasten");
			mykoList = new CheckList("Mykonian");
			sulgList = new CheckList("Sulgogar");
			blackSectList = new CheckList("Black Sect");
			trainingList = new CheckList("Training");
			specialList = new CheckList("Special");
			
			resList.addUnits(UAdata.resUnits);
			resList.addBuildings(UAdata.resBuildings);
			resList.makeList();
			
			ghorList.addUnits(UAdata.ghorUnits);
			ghorList.addBuildings(UAdata.ghorBuildings);
			ghorList.makeList();
			
			taerList.addUnits(UAdata.taerUnits);
			taerList.addBuildings(UAdata.taerBuildings);
			taerList.makeList();
			
			mykoList.addUnits(UAdata.mykoUnits);
			mykoList.addBuildings(UAdata.mykoBuildings);
			mykoList.makeList();
			
			sulgList.addUnits(UAdata.sulgUnits);
			sulgList.makeList();
			
			blackSectList.addBuildings(UAdata.blackSectBuildings);
			blackSectList.makeList();
			
			trainingList.addUnits(UAdata.trainingUnits);
			trainingList.makeList();
			
			specialList.addUnits(UAdata.specialUnits);
			specialList.makeList();

			unlocker = new JCheckBox("Unlock all");
			unlocker.addActionListener(this);		

			switch(owner) 
			{
				case 1:
					checkUnits(resUnits);
					checkBuildings(resBuildings);
					break;
				case 6:
					checkUnits(ghorUnits);
					checkBuildings(ghorBuildings);
					break;
				case 4:
					checkUnits(taerUnits);
					checkBuildings(taerBuildings);
					break;
				case 3:
					checkUnits(mykoUnits);
					checkBuildings(mykoBuildings);
					break;
				case 2:
					checkUnits(sulgUnits);
					checkBuildings(sulgBuildings);
					break;
				case 5:
					checkUnits(blasecUnits);
					checkBuildings(blasecBuildings);
					break;
				case 7:
					checkUnits(trainingUnits);
					checkBuildings(trainingBuildings);
					break;
			}
			
			this.enablerOwner = owner;
			enablerConstraints.gridx = 0;
			enablerConstraints.gridy = 0;
			lock();
		}
		
		void lock() {
			if(this.enablerOwner != 1) {
				resList.toggleUnitCheckBoxes(UAdata.resUnits, false);
				resList.toggleBuildingCheckBoxes(UAdata.resBuildings, false);
			}
			if(this.enablerOwner != 6) {
				ghorList.toggleUnitCheckBoxes(UAdata.ghorUnits, false);
				ghorList.toggleBuildingCheckBoxes(UAdata.ghorBuildings, false);
			}
			if(this.enablerOwner != 4) {
				taerList.toggleUnitCheckBoxes(UAdata.taerUnits, false);
				taerList.toggleBuildingCheckBoxes(UAdata.taerBuildings, false);
			}
			if(this.enablerOwner != 3) {
				mykoList.toggleUnitCheckBoxes(UAdata.mykoUnits, false);
				mykoList.toggleBuildingCheckBoxes(UAdata.mykoBuildings, false);
			}
			if(this.enablerOwner != 2) {
				sulgList.toggleUnitCheckBoxes(UAdata.sulgUnits, false);
			}
			if(this.enablerOwner != 5) {
				blackSectList.toggleBuildingCheckBoxes(UAdata.blackSectBuildings, false);	
			}
			if(this.enablerOwner != 7) {
				trainingList.toggleUnitCheckBoxes(UAdata.trainingUnits, false);
			}
			specialList.toggleUnitCheckBoxes(UAdata.specialUnits, false);
		}
		
		
		void unlock() {
			if(this.enablerOwner != 1) {
				resList.toggleUnitCheckBoxes(UAdata.resUnits, true);
				resList.toggleBuildingCheckBoxes(UAdata.resBuildings, true);
			}
			if(this.enablerOwner != 6) {
				ghorList.toggleUnitCheckBoxes(UAdata.ghorUnits, true);
				ghorList.toggleBuildingCheckBoxes(UAdata.ghorBuildings, true);
			}
			if(this.enablerOwner != 4) {
				taerList.toggleUnitCheckBoxes(UAdata.taerUnits, true);
				taerList.toggleBuildingCheckBoxes(UAdata.taerBuildings, true);
			}
			if(this.enablerOwner != 3) {
				mykoList.toggleUnitCheckBoxes(UAdata.mykoUnits, true);
				mykoList.toggleBuildingCheckBoxes(UAdata.mykoBuildings, true);
			}
			if(this.enablerOwner != 2) {
				sulgList.toggleUnitCheckBoxes(UAdata.sulgUnits, true);
			}
			if(this.enablerOwner != 5) {
				blackSectList.toggleBuildingCheckBoxes(UAdata.blackSectBuildings, true);	
			}
			if(this.enablerOwner != 7) {
				trainingList.toggleUnitCheckBoxes(UAdata.trainingUnits, true);
			}
			specialList.toggleUnitCheckBoxes(UAdata.specialUnits, true);
		}
		
		void checkUnits(ArrayList<Integer> hsUnits) {
			for(JCheckBox resUnitBox : resList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(resUnitBox.getText()))) {
					resUnitBox.setSelected(true);
				}
			}
			for(JCheckBox ghorUnitBox : ghorList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(ghorUnitBox.getText()))) {
					ghorUnitBox.setSelected(true);
				}
			}
			for(JCheckBox taerUnitBox : taerList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(taerUnitBox.getText()))) {
					taerUnitBox.setSelected(true);
				}
			}
			for(JCheckBox mykoUnitBox : mykoList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(mykoUnitBox.getText()))) {
					mykoUnitBox.setSelected(true);
				}
			}
			for(JCheckBox sulgUnitBox : sulgList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(sulgUnitBox.getText()))) {
					sulgUnitBox.setSelected(true);
				}
			}
			for(JCheckBox trainingUnitBox : trainingList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(trainingUnitBox.getText()))) {
					trainingUnitBox.setSelected(true);
				}
			}
			for(JCheckBox specialUnitBox : specialList.getUnitCheckBoxes()) {
				if(hsUnits.contains(UAdata.getUnitIDfromName(specialUnitBox.getText()))) {
					specialUnitBox.setSelected(true);
				}
			}
		}
		
		void checkBuildings(ArrayList<Integer> hsBuildings) {
			for(JCheckBox resBuildingBox : resList.getBuildingCheckBoxes()) {
				if(hsBuildings.contains(UAdata.getBuildingIDfromName(resBuildingBox.getText()))) {
					resBuildingBox.setSelected(true);
				}
			}
			for(JCheckBox ghorBuildingBox : ghorList.getBuildingCheckBoxes()) {
				if(hsBuildings.contains(UAdata.getBuildingIDfromName(ghorBuildingBox.getText()))) {
					ghorBuildingBox.setSelected(true);
				}
			}
			for(JCheckBox taerBuildingBox : taerList.getBuildingCheckBoxes()) {
				if(hsBuildings.contains(UAdata.getBuildingIDfromName(taerBuildingBox.getText()))) {
					taerBuildingBox.setSelected(true);
				}
			}
			for(JCheckBox mykoBuildingBox : mykoList.getBuildingCheckBoxes()) {
				if(hsBuildings.contains(UAdata.getBuildingIDfromName(mykoBuildingBox.getText()))) {
					mykoBuildingBox.setSelected(true);
				}
			}
			for(JCheckBox blackSectBuildingBox : blackSectList.getBuildingCheckBoxes()) {
				if(hsBuildings.contains(UAdata.getBuildingIDfromName(blackSectBuildingBox.getText()))) {
					blackSectBuildingBox.setSelected(true);
				}
			}
		}
		
		void enableUnits(ArrayList<Integer> hsUnits) {
			for(JCheckBox resUnitBox : resList.getUnitCheckBoxes()) {
				if(resUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(resUnitBox.getText()));
			}
			for(JCheckBox ghorUnitBox : ghorList.getUnitCheckBoxes()) {
				if(ghorUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(ghorUnitBox.getText()));
			}
			for(JCheckBox taerUnitBox : taerList.getUnitCheckBoxes()) {
				if(taerUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(taerUnitBox.getText()));
			}
			for(JCheckBox mykoUnitBox : mykoList.getUnitCheckBoxes()) {
				if(mykoUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(mykoUnitBox.getText()));
			}
			for(JCheckBox sulgUnitBox : sulgList.getUnitCheckBoxes()) {
				if(sulgUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(sulgUnitBox.getText()));
			}
			for(JCheckBox trainingUnitBox : trainingList.getUnitCheckBoxes()) {
				if(trainingUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(trainingUnitBox.getText()));
			}
			for(JCheckBox specialUnitBox : specialList.getUnitCheckBoxes()) {
				if(specialUnitBox.isSelected())
					hsUnits.add(UAdata.getUnitIDfromName(specialUnitBox.getText()));
			}
		}
		
		void enableBuildings(ArrayList<Integer> hsBuildings) {
			for(JCheckBox resBuildingBox : resList.getBuildingCheckBoxes()) {
				if(resBuildingBox.isSelected())
					hsBuildings.add(UAdata.getBuildingIDfromName(resBuildingBox.getText()));
			}
			for(JCheckBox ghorBuildingBox : ghorList.getBuildingCheckBoxes()) {
				if(ghorBuildingBox.isSelected())
					hsBuildings.add(UAdata.getBuildingIDfromName(ghorBuildingBox.getText()));
			}
			for(JCheckBox taerBuildingBox : taerList.getBuildingCheckBoxes()) {
				if(taerBuildingBox.isSelected())
					hsBuildings.add(UAdata.getBuildingIDfromName(taerBuildingBox.getText()));
			}
			for(JCheckBox mykoBuildingBox : mykoList.getBuildingCheckBoxes()) {
				if(mykoBuildingBox.isSelected())
					hsBuildings.add(UAdata.getBuildingIDfromName(mykoBuildingBox.getText()));
			}
			for(JCheckBox blackSectBuildingBox : blackSectList.getBuildingCheckBoxes()) {
				if(blackSectBuildingBox.isSelected())
					hsBuildings.add(UAdata.getBuildingIDfromName(blackSectBuildingBox.getText()));
			}
		}
		
		void removeNewMapDialog() {
			newMapDialog.remove(cancelBut);
			newMapDialog.remove(confirmBut);
			newMapDialog.remove(borderInfo);
			newMapDialog.remove(verticalNum);
			newMapDialog.remove(verticalDialog);
			newMapDialog.remove(horizontalNum);
			newMapDialog.remove(horizontalDialog);
			newMapDialog.remove(dialogNewMap);
		}
		
		void removeParamsDialog() {
			if(cancelParams != null) paramDialog.remove(cancelParams);
			if(saveParams != null) paramDialog.remove(saveParams);
			if(musicPanel != null) paramDialog.remove(musicPanel);
			if(skyPanel != null) paramDialog.remove(skyPanel);
			if(eventLoopPanel != null) paramDialog.remove(eventLoopPanel);
			if(moviePanel != null) paramDialog.remove(moviePanel);
			if(setPanel != null) paramDialog.remove(setPanel);
		}
		
		void removeEnablerDialog() {
			if(cancelEnabler != null) enablerDialog.remove(cancelEnabler);
			if(trainingSaveEnabler != null) enablerDialog.remove(trainingSaveEnabler);
			if(ghorSaveEnabler != null) enablerDialog.remove(ghorSaveEnabler);
			if(blasecSaveEnabler != null) enablerDialog.remove(blasecSaveEnabler);
			if(taerSaveEnabler != null) enablerDialog.remove(taerSaveEnabler);
			if(mykoSaveEnabler != null) enablerDialog.remove(mykoSaveEnabler);
			if(sulgSaveEnabler != null) enablerDialog.remove(sulgSaveEnabler);
			if(resSaveEnabler != null) enablerDialog.remove(resSaveEnabler);
			if(specialList.getGroup() != null) enablerDialog.remove(specialList.getGroup());
			if(trainingList.getGroup() != null) enablerDialog.remove(trainingList.getGroup());
			if(blackSectList.getGroup() != null) enablerDialog.remove(blackSectList.getGroup());
			if(sulgList.getGroup() != null) enablerDialog.remove(sulgList.getGroup());
			if(mykoList.getGroup() != null) enablerDialog.remove(mykoList.getGroup());
			if(taerList.getGroup() != null) enablerDialog.remove(taerList.getGroup());
			if(ghorList.getGroup() != null) enablerDialog.remove(ghorList.getGroup());
			if(resList.getGroup() != null) enablerDialog.remove(resList.getGroup());
			if(unlocker != null) enablerDialog.remove(unlocker);
		}

		void removeBriefingMapDialog() {
			if(cancelBriefing != null) briefingDialog.remove(cancelBriefing);
			if(saveBriefing != null) briefingDialog.remove(saveBriefing);
			if(MDpanel != null) briefingDialog.remove(MDpanel);
			if(MBpanel != null) briefingDialog.remove(MBpanel);
		}
		
		void removePlayerHSDialog() {
			if(cancelPlayer != null) playerHSDialog.remove(cancelPlayer);
			if(savePlayer != null) playerHSDialog.remove(savePlayer);
			if(noHSavailable != null) playerHSDialog.remove(noHSavailable);
			if(hsListPanel != null) playerHSDialog.remove(hsListPanel);
			availableHS.clear();
			hsListPanel.setBorder(null);
			if(playerHSLabel != null) playerHSDialog.remove(playerHSLabel);
		}
		
		void removeDescDialog() {
			if(cancelDesc != null) descriptionDialog.remove(cancelDesc);
			if(saveDesc != null) descriptionDialog.remove(saveDesc);
			if(descScroller != null) descriptionDialog.remove(descScroller);
			if(descInfo != null) descriptionDialog.remove(descInfo);
		}
		
		void removeModsDialog() {
			if(cancelMods != null) modsDialog.remove(cancelMods);
			if(resetMods != null) modsDialog.remove(resetMods);
			if(resetGhorMods != null) modsDialog.remove(resetGhorMods);
			if(resetTaerMods != null) modsDialog.remove(resetTaerMods);
			if(saveMods != null) modsDialog.remove(saveMods);
			if(modsData != null) modsDialog.remove(modsData);
			if(modsScroller != null) modsDialog.remove(modsScroller);
			if(modsInfo != null) modsDialog.remove(modsInfo);
		}
		
		void removeMapsDialog() {
			if(mapsClose != null) mapsDialog.remove(mapsClose);
		}
		
		@Override
		public void windowOpened(WindowEvent e) {

			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			if(e.getSource() == MainWindow.this) {
				if(saved == false) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap) 
							saveLevel(selectSaveFile.getSelectedFile());
						saved = true;
					}
				}
			}
			if(e.getSource() == newMapDialog) {
				removeNewMapDialog();
				newMapDialog.setVisible(false);
			}
			if(e.getSource() == paramDialog) {
				selectedSet = savedSet;
				selectedMovie = savedMovie;
				selectedEventLoop = savedEventLoop;
				selectedSky = savedSky;
				selectedMusic = savedMusic;
				selectedMinBreak = savedMinBreak;
				selectedMaxBreak = savedMaxBreak;
				if(player != null)player.close();
				removeParamsDialog();
				paramDialog.setVisible(false);
			}
			if(e.getSource() == enablerDialog) {
				removeEnablerDialog();
				enablerDialog.setVisible(false);
			}
			if(e.getSource() == playerHSDialog) {
				removePlayerHSDialog();
				playerHSDialog.setVisible(false);
			}
			if(e.getSource() == modsDialog) {
				removeModsDialog();
				modsDialog.setVisible(false);
			}
			if(e.getSource() == descriptionDialog) {
				removeDescDialog();
				descriptionDialog.setVisible(false);
			}
			if(e.getSource() == briefingDialog) {
				removeBriefingMapDialog();
				selectedMB = savedMB;
				selectedDB = savedDB;
				briefingDialog.setVisible(false);
			}
			if(e.getSource() == contentDialog) {
				if(savedContent == 0)
					noneContent.setSelected(true);
				else if(savedContent == 1)
					mdContent.setSelected(true);
			}
			if(e.getSource() == mapsDialog) {
				removeMapsDialog();
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
			
		}

		@Override
		public void windowIconified(WindowEvent e) {

			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {

			
		}

		@Override
		public void windowActivated(WindowEvent e) {

			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {

			
		}
		public void initParamsDialog() {
			paramDialog.setSize(700,500);
			paramDialog.setLocationRelativeTo(null);
			paramDialog.setResizable(false);
			paramDialog.setLayout(new GridBagLayout());
			
			setPanel = new JPanel();
			setPanel.setLayout(new GridBagLayout());
			setList = new JComboBox<String>(sets);
			setText = new JLabel("Set: ");
			skyPanel = new JPanel();
			skyPanel.setLayout(new GridBagLayout());
			skyList = new JComboBox<String>(skies);
			skyText = new JLabel("Sky: ");
			setPanel.setBorder(BorderFactory.createTitledBorder("Select sector set"));
			musicPanel = new JPanel();
			musicPanel.setLayout(new GridBagLayout());
			musicList = new JComboBox<String>(musics);
			playMusic = new JButton("Play");
			moviePanel = new JPanel();
			moviePanel.setLayout(new GridBagLayout());
			moviePanel.setBorder(BorderFactory.createTitledBorder("Select movie"));
			movieList = new JComboBox<String>(movies);
			eventLoopPanel = new JPanel();
			eventLoopPanel.setLayout(new GridBagLayout());
			eventLoopPanel.setBorder(BorderFactory.createTitledBorder("Select event loop"));
			eventLoopList = new JComboBox<String>(eventLoops);
			minBreakText = new JLabel("Minimum break:");
			minBreakSlider = new JSlider(0, 1000000, selectedMinBreak);
			minBreakSlider.setPreferredSize(new Dimension(224,20));
			minBreakValue = new JTextField(5);
			minBreakValue.setText(Integer.toString(selectedMinBreak));
			maxBreakText = new JLabel("Maximum break:");
			maxBreakSlider = new JSlider(0, 1000000, selectedMaxBreak);
			maxBreakSlider.setPreferredSize(new Dimension(224,20));
			maxBreakValue = new JTextField(5);
			maxBreakValue.setText(Integer.toString(selectedMaxBreak));
			saveParams = new JButton("Save");
			cancelParams = new JButton("Cancel");
			
			paramConstraints.insets = new Insets(2,20,2,4);
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 0;
			paramConstraints.gridwidth = 1;
			paramConstraints.gridheight = 1;
			paramConstraints.anchor = GridBagConstraints.WEST;
			setPanel.add(setText, paramConstraints);
			paramConstraints.gridx = 1;
			paramConstraints.insets = new Insets(2,1,2,30);
			setList.setSelectedIndex(selectedSet);
			setPanel.add(setList, paramConstraints);
			setList.addActionListener(this);
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 0;
			paramDialog.add(setPanel, paramConstraints);
			
			paramConstraints.gridx = 0;
			movieList.setSelectedIndex(selectedMovie);
			moviePanel.add(movieList, paramConstraints);
			movieList.addActionListener(this);
			paramConstraints.gridx = 1;
			paramConstraints.anchor = GridBagConstraints.WEST;
			paramDialog.add(moviePanel, paramConstraints);
			
			paramConstraints.gridx = 0;
			paramConstraints.insets = new Insets(2,35,2,35);
			eventLoopList.setSelectedIndex(selectedEventLoop);
			eventLoopPanel.add(eventLoopList, paramConstraints);
			eventLoopList.addActionListener(this);
			paramConstraints.gridx = 2;
			paramConstraints.insets = new Insets(2,1,2,20);
			paramDialog.add(eventLoopPanel, paramConstraints);
			
			skyPanel.setBorder(BorderFactory.createTitledBorder("Select level sky"));
			paramConstraints.insets = new Insets(2,20,2,4);
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 0;
			skyPanel.add(skyText, paramConstraints);
			paramConstraints.gridx = 1;
			paramConstraints.insets = new Insets(2,1,2,20);
			skyList.setSelectedIndex(selectedSky);
			skyPanel.add(skyList, paramConstraints);
			skyList.addActionListener(this);
			paramConstraints.gridx = 2;
			paramConstraints.gridheight = 2;	
			try {
				sky[selectedSky] = ImageIO.read(this.getClass().getResourceAsStream("/img/sky-images/"+skies[selectedSky]+".jpg"));
				sky[selectedSky] = resizeMap(400, 200, sky[selectedSky]);
				skyFrame[selectedSky] = new JLabel(new ImageIcon(sky[selectedSky]));
			}catch(IOException ex) {
				System.out.println("The sky "+skies[selectedSky]+".jpg couldn't be loaded");
			}
			skyPanel.add(skyFrame[selectedSky], paramConstraints);
			
			musicPanel.setBorder(BorderFactory.createTitledBorder("Select music"));
			paramConstraints.gridheight = 1;
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 0;
			paramConstraints.insets = new Insets(2,1,2,2);
			musicList.setSelectedIndex(selectedMusic);
			musicPanel.add(musicList, paramConstraints);
			musicList.addActionListener(this);
			paramConstraints.gridx = 1;
			paramConstraints.gridwidth = 3;
			musicPanel.add(playMusic, paramConstraints);
			playMusic.addActionListener(this);
			paramConstraints.gridy = 1;
			paramConstraints.gridx = 0;
			paramConstraints.insets = new Insets(20,1,2,2);
			musicPanel.add(minBreakText, paramConstraints);
			paramConstraints.gridx = 1;
			paramConstraints.insets = new Insets(20,85,2,2);
			minBreakValue.setText(Integer.toString(selectedMinBreak));
			musicPanel.add(minBreakValue, paramConstraints);
			paramConstraints.insets = new Insets(2,1,2,2);
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 2;
			musicPanel.add(minBreakSlider, paramConstraints);
			minBreakSlider.addChangeListener(this);
			paramConstraints.gridy = 3;
			musicPanel.add(maxBreakText, paramConstraints);
			paramConstraints.gridx = 1;
			paramConstraints.insets = new Insets(20,85,2,2);
			musicPanel.add(maxBreakValue, paramConstraints);
			paramConstraints.insets = new Insets(2,1,2,2);
			paramConstraints.gridy = 4;
			paramConstraints.gridx = 0;
			musicPanel.add(maxBreakSlider, paramConstraints);
			maxBreakSlider.addChangeListener(this);
			
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 1;
			paramConstraints.gridwidth = 4;
			paramDialog.add(skyPanel, paramConstraints);
			
			paramConstraints.gridx = 0;
			paramConstraints.gridy = 2;
			paramConstraints.anchor = GridBagConstraints.WEST;
			paramDialog.add(musicPanel, paramConstraints);
			
			paramConstraints.gridx = 3;
			paramConstraints.gridy = 2;
			paramConstraints.gridwidth = 2;
			paramConstraints.insets = new Insets(2,6,2,7);
			paramDialog.add(saveParams, paramConstraints);
			saveParams.addActionListener(this);
			paramConstraints.insets = new Insets(80,1,2,2);
			paramDialog.add(cancelParams, paramConstraints);
			cancelParams.addActionListener(this);
			paramConstraints.insets = new Insets(0,0,0,0);
			
			paramDialog.setVisible(true);
		}
		
		public void showEnabler(int owner) {
			enablerDialog.setSize(1200,800);
			enablerDialog.setLocationRelativeTo(null);
			enablerDialog.setResizable(false);
			enablerDialog.setLayout(new GridBagLayout());
			initEnabler(owner);
			switch(owner) {
			case 1:
				enablerDialog.setTitle("Resistance enabler");
				break;
			case 2:
				enablerDialog.setTitle("Sulgogar enabler");
				break;
			case 3:
				enablerDialog.setTitle("Mykonian enabler");
				break;
			case 4:
				enablerDialog.setTitle("Taerkasten enabler");
				break;
			case 5:
				enablerDialog.setTitle("Black sect enabler");
				break;
			case 6:
				enablerDialog.setTitle("Ghorkov enabler");
				break;
			case 7:
				enablerDialog.setTitle("Training enabler");
				break;
			}
			enablerConstraints.gridx = 0;
			enablerConstraints.gridy = 0;
			enablerDialog.add(unlocker,enablerConstraints);
			enablerConstraints.gridy = 1;
			enablerConstraints.anchor = GridBagConstraints.NORTH;
			enablerDialog.add(resList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 1;
			enablerDialog.add(ghorList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 2;
			enablerDialog.add(taerList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 3;
			enablerDialog.add(mykoList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 4;
			enablerDialog.add(sulgList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 5;
			enablerDialog.add(blackSectList.getGroup(), enablerConstraints);
			enablerConstraints.gridx = 6;
			enablerDialog.add(trainingList.getGroup(), enablerConstraints);
			enablerConstraints.gridy = 1;
			enablerConstraints.insets = new Insets(80,0,0,0);
			enablerDialog.add(specialList.getGroup(), enablerConstraints);
			enablerConstraints.insets = new Insets(20,0,0,0);
			
			switch(owner) {
				case 1:
					resSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(resSaveEnabler, enablerConstraints);
					resSaveEnabler.addActionListener(this);
					break;
				case 2:
					sulgSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(sulgSaveEnabler, enablerConstraints);
					sulgSaveEnabler.addActionListener(this);
					break;
				case 3:
					mykoSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(mykoSaveEnabler, enablerConstraints);
					mykoSaveEnabler.addActionListener(this);
					break;
				case 4:
					taerSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(taerSaveEnabler, enablerConstraints);
					taerSaveEnabler.addActionListener(this);
					break;
				case 5:
					blasecSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(blasecSaveEnabler, enablerConstraints);
					blasecSaveEnabler.addActionListener(this);
					break;
				case 6:
					ghorSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(ghorSaveEnabler, enablerConstraints);
					ghorSaveEnabler.addActionListener(this);
					break;
				case 7:
					trainingSaveEnabler = new JButton("Save");
					enablerConstraints.gridy = 2;
					enablerDialog.add(trainingSaveEnabler, enablerConstraints);
					trainingSaveEnabler.addActionListener(this);
					break;
			}
			cancelEnabler = new JButton("Cancel");
			enablerConstraints.gridy = 3;
			enablerConstraints.insets = new Insets(20,0,0,0);
			enablerDialog.add(cancelEnabler, enablerConstraints);
			cancelEnabler.addActionListener(this);
			enablerConstraints.insets = new Insets(0,0,0,0);
			
			enablerDialog.setVisible(true);
		}
		public void initBriefingMaps() {
			
		
			briefingDialog.setSize(500,880);
			briefingDialog.setLocationRelativeTo(null);
			briefingDialog.setResizable(false);
			briefingDialog.setLayout(new GridBagLayout());
			MBborder = BorderFactory.createTitledBorder("Select briefing map");
			MDborder = BorderFactory.createTitledBorder("Select debriefing map");
			MBpanel = new JPanel(new GridBagLayout());
			MDpanel = new JPanel(new GridBagLayout());
			
			if(savedContent == 0) {
				mbList = new JComboBox<String>(mbMaps);
				mbList.setSelectedIndex(selectedMB);
				mbList.addActionListener(this);
				dbList = new JComboBox<String>(dbMaps);
				dbList.setSelectedIndex(selectedDB);
				dbList.addActionListener(this);
			}else if(savedContent == 1) {
				mbList = new JComboBox<String>(mbMapsXp);
				mbList.setSelectedIndex(selectedMB);
				mbList.addActionListener(this);
				dbList = new JComboBox<String>(dbMapsXp);
				dbList.setSelectedIndex(selectedDB);
				dbList.addActionListener(this);
			}
			MBlabelX = new JLabel("Size x:");
			MBlabelY = new JLabel("Size y:");
			MDlabelX = new JLabel("Size x:");
			MDlabelY = new JLabel("Size y:");
			MBsizeXField = new JTextField(4);
			MBsizeYField = new JTextField(4);
			MDsizeXField = new JTextField(4);
			MDsizeYField = new JTextField(4);
			saveBriefing = new JButton("Save");
			saveBriefing.addActionListener(this);
			cancelBriefing = new JButton("Cancel");
			cancelBriefing.addActionListener(this);
			MBsizeXField.setText(Integer.toString(MBsizeX));
			MBsizeYField.setText(Integer.toString(MBsizeY));
			MDsizeXField.setText(Integer.toString(MDsizeX));
			MDsizeYField.setText(Integer.toString(MDsizeY));
			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 0;
			
			MBpanel.setBorder(MBborder);
			//MBpanel.setSize(700, 300);
			MDpanel.setBorder(MDborder);
			//MDpanel.setSize(500, 300);

			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 0;
			briefingConstraints.gridwidth = 2;
			briefingConstraints.insets = new Insets(10,2,4,2);
			MBpanel.add(mbList, briefingConstraints);
			briefingConstraints.gridwidth = 1;
			briefingConstraints.gridy = 1;
			MBpanel.add(MBlabelX, briefingConstraints);
			briefingConstraints.gridx = 1;
			MBpanel.add(MBsizeXField, briefingConstraints);
			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 2;
			MBpanel.add(MBlabelY, briefingConstraints);
			briefingConstraints.gridx = 1;
			MBpanel.add(MBsizeYField, briefingConstraints);
			briefingConstraints.gridx = 2;
			briefingConstraints.gridy = 0;
			briefingConstraints.gridheight = 4;
			try {
				if(savedContent == 0) {
					mbMap[selectedMB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+mbMaps[selectedMB]+".png"));
					if(mbMap[selectedMB].getWidth() > 400 || mbMap[selectedMB].getHeight() > 400) 
						mbMap[selectedMB] = resizeMap((int)(mbMap[selectedMB].getWidth() / 1.5), (int)(mbMap[selectedMB].getHeight() / 1.5), mbMap[selectedMB]);
				
					mbMapframe[selectedMB] = new JLabel(new ImageIcon(mbMap[selectedMB]));
					MBpanel.add(mbMapframe[selectedMB], briefingConstraints);
				}else if(savedContent == 1) {
					mbMapXp[selectedMB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+mbMapsXp[selectedMB]+".png"));
					if(mbMapXp[selectedMB].getWidth() > 400 || mbMapXp[selectedMB].getHeight() > 400) 
						mbMapXp[selectedMB] = resizeMap((int)(mbMapXp[selectedMB].getWidth() / 1.5), (int)(mbMapXp[selectedMB].getHeight() / 1.5), mbMapXp[selectedMB]);
				
					mbMapframeXp[selectedMB] = new JLabel(new ImageIcon(mbMapXp[selectedMB]));
					MBpanel.add(mbMapframeXp[selectedMB], briefingConstraints);
				}
			}catch(IOException ex) {
				System.out.println("An error occured while loading a briefing map");
			}
			briefingConstraints.gridheight = 1;
			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 1;
			briefingDialog.add(MBpanel, briefingConstraints);
			
			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 0;
			briefingConstraints.gridwidth = 2;
			MDpanel.add(dbList, briefingConstraints);
			briefingConstraints.gridwidth = 1;
			briefingConstraints.gridy = 1;
			MDpanel.add(MDlabelX, briefingConstraints);
			briefingConstraints.gridx = 1;
			MDpanel.add(MDsizeXField, briefingConstraints);
			briefingConstraints.gridy = 2;
			briefingConstraints.gridx = 0;
			MDpanel.add(MDlabelY, briefingConstraints);
			briefingConstraints.gridx = 1;
			MDpanel.add(MDsizeYField, briefingConstraints);
			briefingConstraints.gridx = 2;
			briefingConstraints.gridy = 0;
			briefingConstraints.gridheight = 4;
			try {
				if(savedContent == 0) {
					dbMap[selectedDB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbgfx/"+dbMaps[selectedDB]+".png"));
					if(dbMap[selectedDB].getWidth() > 400 || dbMap[selectedDB].getHeight() > 400) {
						dbMap[selectedDB] = resizeMap((int)(dbMap[selectedDB].getWidth() / 1.5), (int)(dbMap[selectedDB].getHeight() / 1.5), dbMap[selectedDB]);
					}
					dbMapframe[selectedDB] = new JLabel(new ImageIcon(dbMap[selectedDB]));
					MDpanel.add(dbMapframe[selectedDB], briefingConstraints);
				}else if(savedContent == 1) {
					dbMapXp[selectedDB] = ImageIO.read(this.getClass().getResourceAsStream("/img/mbXpgfx/"+dbMapsXp[selectedDB]+".png"));
					if(dbMapXp[selectedDB].getWidth() > 400 || dbMapXp[selectedDB].getHeight() > 400) {
						dbMapXp[selectedDB] = resizeMap((int)(dbMapXp[selectedDB].getWidth() / 1.5), (int)(dbMapXp[selectedDB].getHeight() / 1.5), dbMapXp[selectedDB]);
					}
					dbMapframeXp[selectedDB] = new JLabel(new ImageIcon(dbMapXp[selectedDB]));
					MDpanel.add(dbMapframeXp[selectedDB], briefingConstraints);
				}
				
			}catch(IOException ex) {
				System.out.println("An error occured while loading a debriefing map");
			}
			briefingConstraints.gridheight = 1;
			briefingConstraints.gridx = 0;
			briefingConstraints.gridy = 2;
			briefingDialog.add(MDpanel, briefingConstraints);
			briefingConstraints.gridy = 3;
			briefingDialog.add(saveBriefing, briefingConstraints);
			briefingConstraints.gridy = 4;
			briefingDialog.add(cancelBriefing, briefingConstraints);
			
			briefingDialog.setVisible(true);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == minBreakSlider) {
				minBreakValue.setText(Integer.toString(minBreakSlider.getValue()));
				selectedMinBreak = Integer.parseInt(minBreakValue.getText());
			}
			if(e.getSource() == maxBreakSlider) {
				maxBreakValue.setText(Integer.toString(maxBreakSlider.getValue()));
				selectedMaxBreak = Integer.parseInt(maxBreakValue.getText());
			}
		}
		
	}// end MainMenuListener class
	public int getPlayerSelected() {
		return this.playerSelected;
	}
	public void setPlayerSelected(int s) {
		this.playerSelected = s;
	}
	public void savePrompt() {
		if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
		if(savedMap == JFileChooser.APPROVE_OPTION) saveLevel(selectSaveFile.getSelectedFile());
	}
	void saveLevel(File f) {

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
			mapSaver.println(Integer.toString(savedSet+1));
			mapSaver.print("\t");
			mapSaver.print("sky = ");
			mapSaver.println("objects/"+skies[savedSky]+".base");
			mapSaver.println("\tslot0 = palette/standard.pal");
			mapSaver.println("\tslot1 = palette/red.pal");
			mapSaver.println("\tslot2 = palette/blau.pal");
			mapSaver.println("\tslot3 = palette/gruen.pal");
			mapSaver.println("\tslot4 = palette/inverse.pal");
			mapSaver.println("\tslot5 = palette/invdark.pal");
			mapSaver.println("\tslot6 = palette/sw.pal");
			mapSaver.println("\tslot7 = palette/invtuerk.pal");
			if(savedEventLoop > 0) mapSaver.println("\tevent_loop = "+savedEventLoop);
			if(savedMusic > 0) {
				mapSaver.print("\tambiencetrack = "+(savedMusic+1));
				if(savedMinBreak < 10) mapSaver.print("_0"+savedMinBreak);
				else mapSaver.print("_"+savedMinBreak);
				if(savedMaxBreak < 10) mapSaver.println("_0"+savedMaxBreak);
				else mapSaver.println("_"+savedMaxBreak);
			}
			switch(savedMovie) {
			case 1:
				mapSaver.println("\tmovie = mov:intro.mpg");
				break;
			case 2:
				mapSaver.println("\tmovie = mov:tut1.mpg");
				break;
			case 3:
				mapSaver.println("\tmovie = mov:tut2.mpg");
				break;
			case 4:
				mapSaver.println("\tmovie = mov:tut3.mpg");
				break;
			case 5:
				mapSaver.println("\tmovie = mov:kyt.mpg");
				break;
			case 6:
				mapSaver.println("\tmovie = mov:taer.mpg");
				break;
			case 7:
				mapSaver.println("\tmovie = mov:myk.mpg");
				break;
			case 8:
				mapSaver.println("\tmovie = mov:sulg.mpg");
				break;
			case 9:
				mapSaver.println("\tmovie = mov:black.mpg");
				break;
			case 10:
				mapSaver.println("\tmovie = mov:lose.mpg");
				break;
			case 11:
				mapSaver.println("\tmovie = mov:win.mpg");
				break;
			}
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
	
	void openLevel(File f) {

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
		
	}// end OpenMap
	
	@SuppressWarnings("rawtypes")
	public void initMusic() {
		audioThread = new SwingWorker() {

			protected Object doInBackground() throws Exception {
				if(player != null)player.close();
				
					switch(selectedMusic) {
					case 0:
						player = null;
						break;
					case 1:
						bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-2.mp3"));
						try {
							player = new AdvancedPlayer(bisPlayer);
							player.play();
						}catch(JavaLayerException ex) {
							System.out.println("can't play track-2.mp3");
						}
						break;
					case 2:
						bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-3.mp3"));
						try {
							player = new AdvancedPlayer(bisPlayer);
							player.play();
						}catch(JavaLayerException ex) {
							System.out.println("can't play track-3.mp3");
						}
						break;
					case 3:
						bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-4.mp3"));
						try {
							player = new AdvancedPlayer(bisPlayer);
							player.play();
						}catch(JavaLayerException ex) {
							System.out.println("can't play track-4.mp3");
						}
						break;
					case 4:
						bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-5.mp3"));
						try {
							player = new AdvancedPlayer(bisPlayer);
							player.play();
						}catch(JavaLayerException ex) {
							System.out.println("can't play track-5.mp3");
						}
						break;
					case 5:
						bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-6.mp3"));
						try {
							player = new AdvancedPlayer(bisPlayer);
							player.play();
						}catch(JavaLayerException ex) {
							System.out.println("can't play track-6.mp3");
						}
						break;
				}
				
				return null;
			}
		
			
		};
	}
	public void initShortcuts() {
		shortcutsDialog.setSize(450,490);
		shortcutsDialog.setLocationRelativeTo(null);
		shortcutsDialog.setResizable(false);
		shortcutsDialog.setLayout(new GridBagLayout());
		shortcutsConstraints.gridx = 0;
		shortcutsConstraints.gridy = 0;
		shortcutsConstraints.insets = new Insets(5,1,5,1);
		shortcutsConstraints.anchor = GridBagConstraints.WEST;
		
		shortcutsDialog.add(new JLabel("'1' - set selected sector owner to resistance"), shortcutsConstraints);
		shortcutsConstraints.gridy = 1;
		shortcutsDialog.add(new JLabel("'2' - set selected sector owner to sulgogar"), shortcutsConstraints);
		shortcutsConstraints.gridy = 2;
		shortcutsDialog.add(new JLabel("'3' - set selected sector owner to mykonian"), shortcutsConstraints);
		shortcutsConstraints.gridy = 3;
		shortcutsDialog.add(new JLabel("'4' - set selected sector owner to taerkasten"), shortcutsConstraints);
		shortcutsConstraints.gridy = 4;
		shortcutsDialog.add(new JLabel("'5' - set selected sector owner to black sect"), shortcutsConstraints);
		shortcutsConstraints.gridy = 5;
		shortcutsDialog.add(new JLabel("'6' - set selected sector owner to ghorkov"), shortcutsConstraints);
		shortcutsConstraints.gridy = 6;
		shortcutsDialog.add(new JLabel("'7' - set selected sector owner to neutral(for buildings)"), shortcutsConstraints);
		shortcutsConstraints.gridy = 7;
		shortcutsDialog.add(new JLabel("'0' - set selected sector owner to neutral"), shortcutsConstraints);
		shortcutsConstraints.gridy = 8;
		shortcutsDialog.add(new JLabel("'+' - increase selected sector height by 1"), shortcutsConstraints);
		shortcutsConstraints.gridy = 9;
		shortcutsDialog.add(new JLabel("'-' - decrease selected sector height by 1"), shortcutsConstraints);
		shortcutsConstraints.gridy = 10;
		shortcutsDialog.add(new JLabel("'.' - change selected sector appearance(typ_map) to next one"), shortcutsConstraints);
		shortcutsConstraints.gridy = 11;
		shortcutsDialog.add(new JLabel("',' - change selected sector appearance(typ_map) to previous one"), shortcutsConstraints);
		shortcutsConstraints.gridy = 12;
		shortcutsDialog.add(new JLabel("'H' - change selected sector/sectors height"), shortcutsConstraints);
		shortcutsConstraints.gridy = 13;
		shortcutsDialog.add(new JLabel("'T' - change selected sector appearance(typ_map)"), shortcutsConstraints);
		shortcutsConstraints.gridy = 14;
		shortcutsDialog.add(new JLabel("'Del' - clear selected sector"), shortcutsConstraints);
		shortcutsConstraints.gridy = 15;
		shortcutsDialog.add(new JLabel("'Ctrl + S' - save current level"), shortcutsConstraints);
		shortcutsConstraints.gridy = 16;
		shortcutsConstraints.anchor = GridBagConstraints.CENTER;
		shortcutsDialog.add(shortcutsClose, shortcutsConstraints);
	}
	public void initGameContent() {
		ButtonGroup contentGroup = new ButtonGroup();
		noneContent = new JRadioButton("none");
		mdContent = new JRadioButton("Metropolis Dawn");
		JPanel contentNote = new JPanel(new GridBagLayout());
		JPanel contentButtons = new JPanel(new GridBagLayout());
		saveContent = new JButton("Apply");
		cancelContent = new JButton("Cancel");
		contentDialog.setSize(500, 400);
		contentDialog.setLocationRelativeTo(null);
		contentDialog.setResizable(false);
		contentDialog.setLayout(new GridBagLayout());
		
		contentConstraints.gridx = 0;
		contentConstraints.gridy = 0;
		contentConstraints.insets = new Insets(2,5,2,20);
		contentDialog.add(new JLabel("Select additional content for the game"), contentConstraints);
		contentList = new JPanel(new GridBagLayout());
		contentList.setBorder(BorderFactory.createTitledBorder("Available game content"));
		contentConstraints.anchor = GridBagConstraints.WEST;
		contentGroup.add(noneContent);
		contentGroup.add(mdContent);
		contentList.add(noneContent, contentConstraints);
		contentConstraints.gridy = 1;
		contentList.add(mdContent, contentConstraints);
		noneContent.setSelected(true);
		
		contentConstraints.insets = new Insets(2,2,2,2);
		contentConstraints.gridy = 0;
		contentNote.setBorder(BorderFactory.createTitledBorder(""));
		contentNote.add(new JLabel("Note: In order to add Metropolis Dawn content properly,"), contentConstraints);
		contentConstraints.gridy = 1;
		contentNote.add(new JLabel("you have to decide which host station you want to play with "), contentConstraints);
		contentConstraints.gridy = 2;
		contentNote.add(new JLabel("then click on proper button in Options > Prototype Modifications."), contentConstraints);
		contentConstraints.gridy = 3;
		contentConstraints.insets = new Insets(10,2,2,2);
		contentNote.add(new JLabel("For Ghorkov click \"Reset for metropolis dawn(Ghorkov)\""), contentConstraints);
		contentConstraints.gridy = 4;
		contentConstraints.insets = new Insets(2,2,2,2);
		contentNote.add(new JLabel("or just change the first line to: include script:startupG.scr"), contentConstraints);
		contentConstraints.gridy = 5;
		contentConstraints.insets = new Insets(10,2,2,2);
		contentNote.add(new JLabel("For Taerkasten click \"Reset for metropolis dawn(Taerkasten)\""), contentConstraints);
		contentConstraints.gridy = 6;
		contentConstraints.insets = new Insets(2,2,2,2);
		contentNote.add(new JLabel("or just change the first line to: include script:startupT.scr"), contentConstraints);
		
		contentConstraints.gridy = 0;
		contentButtons.add(saveContent, contentConstraints);
		saveContent.addActionListener(listenToMenu);
		contentConstraints.insets = new Insets(2,20,2,20);
		contentConstraints.gridx = 1;
		contentButtons.add(cancelContent, contentConstraints);
		cancelContent.addActionListener(listenToMenu);
		
		contentConstraints.gridx = 0;
		contentConstraints.anchor = GridBagConstraints.CENTER;
		contentConstraints.gridy = 1;
		contentConstraints.insets = new Insets(15,1,1,1);
		contentDialog.add(contentList, contentConstraints);
		contentConstraints.gridy = 2;
		contentDialog.add(contentNote, contentConstraints);
		contentConstraints.gridy = 3;
		contentDialog.add(contentButtons, contentConstraints);
		savedContent = 0;
	}
	public void initAbout() { 
		aboutDialog.setSize(400,260);
		aboutDialog.setLocationRelativeTo(null);
		aboutDialog.setResizable(false);
		aboutDialog.setLayout(new GridBagLayout());
		aboutConstraints.gridx = 0;
		aboutConstraints.gridy = 0;
		aboutConstraints.gridwidth = 5;
		JLabel header = new JLabel("Urban Assault Level Editor");
		header.setFont(header.getFont().deriveFont (22f));
		aboutDialog.add(header, aboutConstraints);
		
		shortcutsConstraints.anchor = GridBagConstraints.WEST;
		aboutConstraints.gridx = 0;
		aboutConstraints.gridwidth = 1;
		aboutConstraints.insets = new Insets(30,1,1,1);
		aboutConstraints.gridy = 1;
		aboutDialog.add(new JLabel("Version: 1.4.0a"), aboutConstraints);
		aboutConstraints.gridwidth = 2; // TODO update about
		aboutConstraints.insets = new Insets(10,1,1,1);
		aboutConstraints.gridy = 2;
		aboutDialog.add(new JLabel("Compile Date: 2020-11-14"), aboutConstraints);
		
		aboutConstraints.gridy = 3;
		aboutConstraints.gridwidth = 3;
		aboutDialog.add(new JLabel("Compiled with: JRE 1.8.0_271 "), aboutConstraints);
		shortcutsConstraints.anchor = GridBagConstraints.CENTER;
		aboutConstraints.gridy = 4;
		aboutConstraints.gridwidth = 5;
		aboutConstraints.insets = new Insets(20,1,1,1);
		aboutDialog.add(aboutClose, aboutConstraints);
	}
	public void makeUnsaved() {
		if(saved)
			this.setTitle("*"+this.getTitle());
		saved = false;
	}
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    @SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
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
	public void mouseReleased() {
		//System.out.println("Mouse released");
	}
	
	public boolean IsImgEnabled() {
		return imageVisible;
	}
	
	public boolean IsTypEnabled() {
		return typVisible;
	}
	
	public boolean IsHeightEnabled() {
		return heightVisible;
	}
	
	public boolean IsOwnEnabled() {
		return ownVisible;
	}
	
	public boolean IsBlgEnabled() {
		return blgVisible;
	}
	
	public void toggleImgSector() {
		imageVisible = !imageVisible;
	}
	
	public void toggleTypSector() {
		typVisible = !typVisible;
	}
	
	public void toggleOwnSector() {
		ownVisible = !ownVisible;
	}
	
	public void toggleHeightSector() {
		heightVisible = !heightVisible;
	}
	
	public void toggleBlgSector() {
		blgVisible = !blgVisible;
	}
	
	public int getMapSet() {
		return this.savedSet;
	}
	
	public int getContent() {
		return savedContent;
	}
	
	public void showManager(int i) {
		this.manager.setVisible(true);
		this.manager.switchTab(i);
	}
	
	public void updateManagerUnit(Unit u, int index) {
		if(u instanceof Hoststation) this.manager.showHoststationOptions(u.getOwner(), index);
		else if(u instanceof Squad) this.manager.showSquadOptions(u.getVehicle(), index);
		else this.manager.noUnit();
	}
	public void updateManagerSector(int sectorBorder,int sector, int hGrid, int vGrid) {
		if(sectorBorder >= 0 ) this.manager.showSectorOptions(this.savedSet, sectorBorder, sector, hGrid, vGrid);
		else this.manager.noSector();
	}
	public void updateManagerSector(ArrayList<Integer> sectorBorder, int hGrid, int vGrid) {
		if(sectorBorder.size() >= 0 ) this.manager.showSectorOptions(this.savedSet, sectorBorder, hGrid, vGrid);
//		else this.manager.noSector();
	}
	
	public void refreshHoststationManager(int index) {
		this.manager.refreshHoststationXfield(index);
		this.manager.refreshHoststationYfield(index);
	}
	public void refreshSquadManager(int index) {
		this.manager.refreshSquadXfield(index);
		this.manager.refreshSquadYfield(index);
	}
	public void cleanManager() {
		this.manager.noUnit();
		this.manager.noSector();
		this.manager.revalidate();
	}
	static void initLoadingScreen() {
		loadingScreen = new JFrame();
		try {
			loadingScreen.setIconImage(ImageIO.read(MainWindow.class.getResourceAsStream("/img/icon.png")));
		}catch(IOException ex) {
			System.out.println("Couldn't load main icon for the application");
		}
		loadingScreen.setUndecorated(true);
		loadingScreen.setResizable(false);
		JLabel labelImg = new JLabel();
		try {
			labelImg.setIcon(new ImageIcon(ImageIO.read(MainWindow.class.getResource("/img/loading.jpg"))));
		}catch(IOException ex) {
			System.out.println("Could not load the loading image");
		}
		loadingScreen.add(labelImg);
		loadingScreen.pack();
		loadingScreen.setLocationRelativeTo(null);
		loadingScreen.setVisible(true);
	}
}
