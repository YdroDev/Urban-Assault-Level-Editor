package main;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Dialogs.*;

import UAstructures.Hoststation;
import UAstructures.Squad;
import UAstructures.Unit;
import com.jtattoo.plaf.noire.NoireLookAndFeel;



@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JPanel window;
	private NewLevel newLevelDialog;
	private LevelParameters levelParametersDialog;
	private UnitEnabler unitEnablerDialog;
	private BriefingAndDebriefing briefingAndDebriefingDialog;
	private PlayerHostStation playerHostStationDialog;
	private LevelDescription levelDescriptionDialog;
	private LevelModifications levelModificationsDialog;
	private TypMapGenerator typMapGenerator;

	private MainMenuListener listenToMenu;
	private int wWidth = 880;
	private int wHeight = 720;
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

	private JMenuItem levelDescription;
	private JMenuItem modifications;
	private JMenuItem randomTypMap;
	private JMenuItem gameContent;
	private JPanel contentList;
	private JButton saveContent;
	private JButton cancelContent;
	private JRadioButton noneContent;
	private JRadioButton mdContent;

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

	private GridBagConstraints gridConstraints, mapsConstraints, shortcutsConstraints, contentConstraints, aboutConstraints;
	private boolean imageVisible;
	private boolean heightVisible;
	private boolean typVisible;
	private boolean ownVisible;
	private boolean blgVisible;
	private BorderLayout layout;

	private JMenuItem campaignInfo;
	private JMenuItem shortcutsInfo;
	private JMenuItem aboutInfo;
	
	private GameMap currentMap;
	@SuppressWarnings("rawtypes")
	private LevelManager manager;
	static Font font;
	static Font mainFont;
	static Font hgtFont;
	
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
		newLevelDialog = new NewLevel(this);
		levelParametersDialog = new LevelParameters(this);
		unitEnablerDialog = new UnitEnabler(this);
		briefingAndDebriefingDialog = new BriefingAndDebriefing(this);
		playerHostStationDialog = new PlayerHostStation(this);
		levelDescriptionDialog = new LevelDescription(this);
		levelModificationsDialog = new LevelModifications(this);
		typMapGenerator = new TypMapGenerator();

		this.setSize(wWidth,wHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Urban Assault Level Editor");
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("/img/icon.png")));
		}catch(IOException ex) {
			System.out.println("Couldn't load main icon for the application");
		}

		listenToMenu = new MainMenuListener();
		gridConstraints = new GridBagConstraints();
		mapsConstraints = new GridBagConstraints();
		contentConstraints = new GridBagConstraints();
		shortcutsConstraints = new GridBagConstraints();
		aboutConstraints = new GridBagConstraints();
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

		imageVisible = false;
		heightVisible = false;
		typVisible = false;
		ownVisible = false;
		blgVisible = false;

		mainMenu = new JMenuBar();
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
	
	private class MainMenuListener implements WindowListener, MenuListener, ActionListener, KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newMap) {
				newLevelDialog.render();
			}
			if(e.getSource() == openMap) {
				if(EditorState.isSaved) {
					if(JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
						open(selectOpenFile.getSelectedFile());
						savedMap = JFileChooser.APPROVE_OPTION;
						selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
						EditorState.isSaved = true;
					}
				}else {
					if (JOptionPane.showConfirmDialog(null, "Current level changes are not saved. Are you sure you want to discard them?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (JFileChooser.APPROVE_OPTION == selectOpenFile.showOpenDialog(null)) {
							open(selectOpenFile.getSelectedFile());
							savedMap = JFileChooser.APPROVE_OPTION;
							selectSaveFile.setSelectedFile(selectOpenFile.getSelectedFile());
							EditorState.isSaved = true;
						}
					}
				}
			}
			if(e.getSource() == saveMap) {
				if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
				if(savedMap == JFileChooser.APPROVE_OPTION) save(selectSaveFile.getSelectedFile());
			}
			if(e.getSource() == saveAsMap) {
				if(JFileChooser.APPROVE_OPTION == selectSaveFile.showSaveDialog(null)) 
					save(selectSaveFile.getSelectedFile());
			}
			if(e.getSource() == clear) {
				if(!EditorState.isSaved) {
					if(JOptionPane.showConfirmDialog(null,"Current level changes are not saved. Do you want to save the level now?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						savedMap = selectSaveFile.showSaveDialog(null);
						if(JFileChooser.APPROVE_OPTION == savedMap) 
							save(selectSaveFile.getSelectedFile());
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
							save(selectSaveFile.getSelectedFile());
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
			if(e.getSource() == menuParams) {
				levelParametersDialog.render();
			}
			if(e.getSource() == resEnabler) {
				unitEnablerDialog.render(1);
			}
			if(e.getSource() == ghorEnabler) {
				unitEnablerDialog.render(6);
			}
			if(e.getSource() == taerEnabler) {
				unitEnablerDialog.render(4);
			}
			if(e.getSource() == mykoEnabler) {
				unitEnablerDialog.render(3);
			}
			if(e.getSource() == sulgEnabler) {
				unitEnablerDialog.render(2);
			}
			if(e.getSource() == blasecEnabler) {
				unitEnablerDialog.render(5);
			}
			if(e.getSource() == trainingEnabler) {
				unitEnablerDialog.render(7);
			}
			if(e.getSource() == modifications) {
				levelModificationsDialog.render();
			}
			if(e.getSource() == randomTypMap) {
				typMapGenerator.generate();
				repaint();
				currentMap.updateMap();
				makeUnsaved();
			}
			
			if(e.getSource() == gameContent) {
				contentDialog.setVisible(true);
			}
			if(e.getSource() == briefingMaps) {
				briefingAndDebriefingDialog.render();
			}
			if(e.getSource() == playerHS) {
				playerHostStationDialog.render();
			}
			if(e.getSource() == levelDescription){
				levelDescriptionDialog.render();
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
					EditorState.gameContent = 0;
					UAdata.addOriginalData();
				}
				else if(mdContent.isSelected()) {
					EditorState.gameContent = 1;
					UAdata.addMetropolisDawnData();
				}
				briefingAndDebriefingDialog.reset();
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
				if(EditorState.gameContent == 0)
					noneContent.setSelected(true);
				else if(EditorState.gameContent == 1)
					mdContent.setSelected(true);
				contentDialog.setVisible(false);
			}
		}// end actionPerformed
		@Override
		public void menuSelected(MenuEvent e) {}
		@Override
		public void menuDeselected(MenuEvent e) {}
		@Override
		public void menuCanceled(MenuEvent e) {}

		void removeMapsDialog() {
			if(mapsClose != null) mapsDialog.remove(mapsClose);
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
							save(selectSaveFile.getSelectedFile());
						EditorState.isSaved = true;
					}
				}
			}
			if(e.getSource() == contentDialog) {
				if(EditorState.gameContent == 0)
					noneContent.setSelected(true);
				else if(EditorState.gameContent == 1)
					mdContent.setSelected(true);
			}
			if(e.getSource() == mapsDialog) {
				removeMapsDialog();
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
	}// end MainMenuListener class
	public void createNewMap() {
		currentMap.createMap(EditorState.horizontalSectors, EditorState.verticalSectors);
		mapscroller.revalidate();
		setTitle("Urban Assault Level Editor");
	}
	public void savePrompt() {
		if(savedMap == JFileChooser.CANCEL_OPTION) savedMap = selectSaveFile.showSaveDialog(null);
		if(savedMap == JFileChooser.APPROVE_OPTION) save(selectSaveFile.getSelectedFile());
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
		if(EditorState.isSaved)
			this.setTitle("*"+this.getTitle());
		EditorState.isSaved = false;
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
	public void updateEditor() {
		repaint();
		cleanManager();
		updateManagerSector(currentMap.getSelectedBorderSector(), currentMap.getSelectedSector(), currentMap.getHorizontalGrid(), currentMap.getVerticalGrid());
		makeUnsaved();
	}

	public void saveLevel(File f) {
		//TODO implement save
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
