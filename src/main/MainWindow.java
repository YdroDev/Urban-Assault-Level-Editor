package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import Dialogs.*;

import LevelIO.SingleplayerLevelOpener;
import LevelIO.SingleplayerLevelSaver;
import UAstructures.Hoststation;
import UAstructures.Squad;
import UAstructures.Unit;
import com.jtattoo.plaf.noire.NoireLookAndFeel;

public class MainWindow extends JFrame {
	private final NewLevel newLevelDialog;
	private final LevelParameters levelParametersDialog;
	private final UnitEnabler unitEnablerDialog;
	private final BriefingAndDebriefing briefingAndDebriefingDialog;
	private final PlayerHostStation playerHostStationDialog;
	private final LevelDescription levelDescriptionDialog;
	private final LevelModifications levelModificationsDialog;
	private final TypMapGenerator typMapGenerator;
	private final GameContent gameContentDialog;
	private final CampaignMaps campaignMapsDialog;
	private final KeyboardShortcuts keyboardShortcutsDialog;
	private final About aboutDialog;

	private final SingleplayerLevelSaver singleplayerLevelSaver = new SingleplayerLevelSaver();
	private final SingleplayerLevelOpener singleplayerLevelOpener = new SingleplayerLevelOpener();

	private static JFrame loadingScreen;
	private final JScrollPane mapScroller;
	private final JMenuItem newMap, openMap, saveMap, saveAsMap, clear, exit;
	private int savedMap;
	private final JFileChooser selectSaveFile;
	private final JFileChooser selectOpenFile;

	private final JMenuItem showManager;
	private final JMenuItem sectorImgSwitch;
	private final JMenuItem sectorHeight;
	private final JMenuItem sectorTyp;
	private final JMenuItem sectorOwner;
	private final JMenuItem sectorBlg;
	private final JMenuItem menuParams;
	private final JMenuItem resEnabler, ghorEnabler, taerEnabler, mykoEnabler, sulgEnabler, blasecEnabler, trainingEnabler;
	private final JMenuItem briefingMaps;
	private final JMenuItem playerHS;

	private final JMenuItem levelDescription;
	private final JMenuItem modifications;
	private final JMenuItem randomTypMap;
	private final JMenuItem gameContent;

	private boolean imageVisible;
	private boolean heightVisible;
	private boolean typVisible;
	private boolean ownVisible;
	private boolean blgVisible;

	private final JMenuItem campaignInfo;
	private final JMenuItem shortcutsInfo;
	private final JMenuItem aboutInfo;
	
	private final GameMap currentMap;
	private final LevelManager manager;
	static Font font;
	static Font mainFont;
	static Font hgtFont;
	
	public static void main(final String[] args) {
		initLoadingScreen();

		EditorState.resetState();
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | FontFormatException | IOException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(MainWindow::new);
	}
	
	MainWindow(){
		BorderLayout layout = new BorderLayout();
		JPanel window = new JPanel(layout);
		newLevelDialog = new NewLevel(this);
		levelParametersDialog = new LevelParameters(this);
		unitEnablerDialog = new UnitEnabler(this);
		briefingAndDebriefingDialog = new BriefingAndDebriefing(this);
		playerHostStationDialog = new PlayerHostStation(this);
		levelDescriptionDialog = new LevelDescription(this);
		levelModificationsDialog = new LevelModifications(this);
		typMapGenerator = new TypMapGenerator();
		gameContentDialog = new GameContent(this);
		campaignMapsDialog = new CampaignMaps(this);
		keyboardShortcutsDialog = new KeyboardShortcuts(this);
		aboutDialog = new About(this);

		int wHeight = 720;
		int wWidth = 880;
		this.setSize(wWidth, wHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Urban Assault Level Editor");
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("/img/icon.png")));
		}catch(IOException ex) {
			System.out.println("Couldn't load main icon for the application");
		}

		MainMenuListener listenToMenu = new MainMenuListener();

		imageVisible = false;
		heightVisible = false;
		typVisible = false;
		ownVisible = false;
		blgVisible = false;

		JMenuBar mainMenu = new JMenuBar();
		this.addWindowListener(listenToMenu);

		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		JMenu optionsMenu = new JMenu("Options");
		JMenu helpMenu = new JMenu("Help");
		
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

		FileNameExtensionFilter ldfFilter = new FileNameExtensionFilter("Urban Assault level file (.ldf)", "ldf");

		selectSaveFile = new JFileChooser();
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

		JMenu enabler = new JMenu("Show enabler for");
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
		
		shortcutsInfo = new JMenuItem("Keyboard shortcuts");
		helpMenu.add(shortcutsInfo);
		shortcutsInfo.addActionListener(listenToMenu);
		
		aboutInfo = new JMenuItem("About");
		helpMenu.add(aboutInfo);
		aboutInfo.addActionListener(listenToMenu);
		
		mainMenu.add(helpMenu);
		
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

		mapScroller = new JScrollPane(currentMap,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(mapScroller);

		this.setJMenuBar(mainMenu);	
		this.add(window);
		loadingScreen.setVisible(false);
		this.setVisible(true);
	}
	
	private class MainMenuListener implements WindowListener, ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newMap) {
				newLevelDialog.render();
			}
			if(e.getSource() == openMap) {
				if(EditorState.isSaved) {
					if(JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
						openLevel(selectOpenFile.getSelectedFile());
						savedMap = JFileChooser.APPROVE_OPTION;
						selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
						EditorState.isSaved = true;
					}
				}else {
					if (JOptionPane.showConfirmDialog(null, "Current level changes are not saved. Are you sure you want to discard them?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
							openLevel(selectOpenFile.getSelectedFile());
							savedMap = JFileChooser.APPROVE_OPTION;
							selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
							EditorState.isSaved = true;
						}
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
				if(!EditorState.isSaved) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap)
							saveLevel(selectSaveFile.getSelectedFile());
						currentMap.closeMap();
						setTitle("Urban Assault Level Editor");
						savedMap = JFileChooser.CANCEL_OPTION;
						EditorState.isSaved = true;
					}else {
						currentMap.closeMap();
						setTitle("Urban Assault Level Editor");
						savedMap = JFileChooser.CANCEL_OPTION;
						EditorState.isSaved = true;
					}
				}else {
					currentMap.closeMap();
					setTitle("Urban Assault Level Editor");
					savedMap = JFileChooser.CANCEL_OPTION;
				}
			}
			if(e.getSource() == exit) {
				if(!EditorState.isSaved) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap)
							saveLevel(selectSaveFile.getSelectedFile());
						System.exit(0);
						EditorState.isSaved = true;
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
			if(e.getSource() == menuParams) levelParametersDialog.render();
			if(e.getSource() == resEnabler) unitEnablerDialog.render(1);
			if(e.getSource() == ghorEnabler) unitEnablerDialog.render(6);
			if(e.getSource() == taerEnabler) unitEnablerDialog.render(4);
			if(e.getSource() == mykoEnabler) unitEnablerDialog.render(3);
			if(e.getSource() == sulgEnabler) unitEnablerDialog.render(2);
			if(e.getSource() == blasecEnabler) unitEnablerDialog.render(5);
			if(e.getSource() == trainingEnabler) unitEnablerDialog.render(7);
			if(e.getSource() == modifications) levelModificationsDialog.render();
			if(e.getSource() == randomTypMap) {
				typMapGenerator.generate();
				repaint();
				currentMap.updateMap();
				makeUnsaved();
			}
			if(e.getSource() == gameContent) gameContentDialog.render();
			if(e.getSource() == briefingMaps) briefingAndDebriefingDialog.render();
			if(e.getSource() == playerHS) playerHostStationDialog.render();
			if(e.getSource() == levelDescription) levelDescriptionDialog.render();
			if(e.getSource() == campaignInfo) campaignMapsDialog.render();
			if(e.getSource() == shortcutsInfo) keyboardShortcutsDialog.render();
			if(e.getSource() == aboutInfo) aboutDialog.render();
			if(e.getSource() == showManager) manager.setVisible(true);
		}
		@Override
		public void windowOpened(WindowEvent e) {}
		@Override
		public void windowClosing(WindowEvent e) {
			if(e.getSource() == MainWindow.this) {
				if(!EditorState.isSaved) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap)
							saveLevel(selectSaveFile.getSelectedFile());
						EditorState.isSaved = true;
					}
				}
			}
		}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
	}

	public void createNewMap() {
		currentMap.createMap(EditorState.horizontalSectors, EditorState.verticalSectors);
		mapScroller.revalidate();
		setTitle("Urban Assault Level Editor");
	}
	public void savePrompt() {
		if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
		if(savedMap == JFileChooser.APPROVE_OPTION) saveLevel(selectSaveFile.getSelectedFile());
	}
	public void makeUnsaved() {
		if(EditorState.isSaved)
			this.setTitle("*"+this.getTitle());
		EditorState.isSaved = false;
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
		if(sectorBorder >= 0 ) this.manager.showSectorOptions(EditorState.set, sectorBorder, sector, hGrid, vGrid);
		else this.manager.noSector();
	}
	public void updateManagerSector(ArrayList<Integer> sectorBorder, int hGrid, int vGrid) {
		this.manager.showSectorOptions(EditorState.set, sectorBorder, hGrid, vGrid);
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
	public void updateEditor() {
		repaint();
		cleanManager();
		updateManagerSector(currentMap.getSelectedBorderSector(), currentMap.getSelectedSector(), EditorState.horizontalSectors, EditorState.verticalSectors);
		makeUnsaved();
	}
	public void updateGameContent() {
		currentMap.removeAddUnits();
		currentMap.initAddUnits();
		currentMap.removeSpecialBuildings();
		currentMap.initAddBuildings();
		currentMap.resizeBuildingImg();
		cleanManager();
	}
	public void saveLevel(File f) {
		singleplayerLevelSaver.save(f);
		this.setTitle(f+" ("+EditorState.horizontalSectors+"x"+EditorState.verticalSectors+") - Urban Assault Level Editor");
	}
	public void openLevel(File f) {
		currentMap.closeMap();
		EditorState.resetState();

		singleplayerLevelOpener.open(f);
		currentMap.removeAddUnits();
		currentMap.initAddUnits();
		currentMap.removeSpecialBuildings();
		currentMap.initAddBuildings();
		currentMap.openMap(EditorState.horizontalSectors, EditorState.verticalSectors);
		mapScroller.revalidate();
		setTitle(f +" ("+EditorState.horizontalSectors+"x"+EditorState.verticalSectors+") - Urban Assault Level Editor");
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
}
