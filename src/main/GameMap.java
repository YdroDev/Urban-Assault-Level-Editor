package main;

import State.EditorState;
import State.UAdata;
import UAstructures.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


public class GameMap extends JComponent{
	
	private final MainWindow mainwindow;
	private int horizontalGrid = 0;
	private int verticalGrid = 0;
	private int sumIndentX;
	private int sumIndentY;
	private int hSize = 1;
	private int vSize = 1;
	private int sectorCounter;
	private int borderSectorCounter;
	private final JPopupMenu currentPosMenu;
	private final JPopupMenu selectedPopUp;
	private final JPopupMenu selectedPopUpSquad;
	private int currentClickedX;
	private int currentClickedY;
	private int currentHeight;
	private int topCorner;
	private int downCorner;
	private int leftCorner;
	private int rightCorner;
	private JMenu addHS;
	private JMenu addSquad;
	private JMenu changeOwner;
	private JMenu resUnits;
	private JMenu ghorUnits;
	private JMenu taerUnits;
	private JMenu mykoUnits;
	private JMenu sulgUnits;
	private JMenu trainUnits;
	private JMenu specialUnit;
	private JMenu specialBuilding;
	private JMenu sectorItem;
	private JMenu newItem;
	private final JMenu bgKeySector;
	private final JMenu bombReactors;
	private JMenuItem addResistance, addGhorkov, addTaerkasten, addMykonian, addSulgogar, addBlacksect, addTrainingHS;
	private final JMenuItem sectorResistance, sectorGhorkov, sectorTaerkasten, sectorMykonian, sectorSulgogar, sectorBlacksect, sectorTrainingHS, sectorNeutral;
	private final JMenuItem setHgt;
	
	private final ArrayList<JMenuItem> resUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> ghorUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> taerUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> mykoUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> sulgUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> trainingUnitsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> specialUnitsMenu = new ArrayList<>();
	
	private final ArrayList<JMenuItem> resBuildingsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> ghorBuildingsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> taerBuildingsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> mykoBuildingsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> blackSectBuildingsMenu = new ArrayList<>();
	private final ArrayList<JMenuItem> miscBuildingsMenu = new ArrayList<>();
	
	private final JMenuItem removeHS;
	private final JMenuItem removeSquad;
	
	private final JMenuItem clearSector;
	private final JMenuItem sectorApperance;
	private final JMenuItem newBeamGate;
	private final JMenuItem newBomb;
	private final JMenuItem newTechUpgrade;
	
	private final EditorMouseListener mouselisten;
	private final ArrayList<JMenuItem> bgList;
	private final ArrayList<JMenuItem> bombList;

	private final ArrayList<Boolean> error_map;
	
	private BufferedImage errorimg;
	
	private final BufferedImage[] set1Images;
	private final BufferedImage[] set2Images;
	private final BufferedImage[] set3Images;
	private final BufferedImage[] set4Images;
	private final BufferedImage[] set5Images;
	private final BufferedImage[] set6Images;
	
	private Unit selected;
	private BeamGate selectedBG;
	private SpecialBuilding selectedKS;
	private StoudsonBomb selectedBomb;
	private SpecialBuilding selectedReactor;
	private TechUpgrade selectedTU;
	private int selectedSectorX;
	private int selectedSectorY;
	private int selectedSector;
	private int borderSelectedSector;
	private final ArrayList<Integer> borderSelectedSectors;
	private int temp;
	
	Font font;

	GameMap(MainWindow mw){
		this.mainwindow = mw;
		mouselisten = new EditorMouseListener();
		currentPosMenu = new JPopupMenu();
		addHS = new JMenu("Add host station here");
		addSquad = new JMenu("Add squad here");
		changeOwner = new JMenu("Set sector faction to ");
		try {
			addResistance = new JMenuItem("Resistance", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/res.png"))));
			addGhorkov = new JMenuItem("Ghorkov", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Ghor.png"))));
			addTaerkasten = new JMenuItem("Taerkasten", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Taer.png"))));
			addMykonian = new JMenuItem("Mykonian", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Myko.png"))));
			addSulgogar = new JMenuItem("Sulgogar", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Sulg.png"))));
			addBlacksect = new JMenuItem("Black sect", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Blacksect.png"))));
			addTrainingHS = new JMenuItem("Training host station", resizeIcon(20,20,ImageIO.read(getClass().getResourceAsStream("/img/Training.png"))));
		}catch(FileNotFoundException ex) {
			System.out.println("Icon image for host station not found");
		}catch(IOException ex) {
			System.out.println("An error occured while loading an image icon for hoststation");
		}
		resUnits = new JMenu("Resistance unit");
		ghorUnits = new JMenu("Ghorkov unit");
		taerUnits = new JMenu("Taerkasten unit");
		mykoUnits = new JMenu("Mykonian unit");
		sulgUnits = new JMenu("Sulgogar unit");
		trainUnits = new JMenu("Training unit");
		specialUnit = new JMenu("Special unit");
		
		initAddUnits();
		
		sectorResistance = new JMenuItem("Resistance");
		sectorGhorkov = new JMenuItem("Ghorkov");
		sectorTaerkasten = new JMenuItem("Taerkasten");
		sectorMykonian = new JMenuItem("Mykonian");
		sectorSulgogar = new JMenuItem("Sulgogar");
		sectorBlacksect = new JMenuItem("Blacksect");
		sectorTrainingHS = new JMenuItem("Training host station");
		sectorNeutral = new JMenuItem("Neutral");
		
		setHgt = new JMenuItem("Set height here");
		
		specialBuilding = new JMenu("Set special building here");
		
		
		
		sectorItem = new JMenu("Add sector item here");
		newItem = new JMenu("New Item");
		
		newBeamGate = new JMenuItem("Beam Gate");
		newBomb = new JMenuItem("Stoudson Bomb");
		newTechUpgrade = new JMenuItem("Tech Upgrade");
		
		bgKeySector = new JMenu("Add key sector to");
		bombReactors = new JMenu("Add reactor to");
		
		addHS.add(addResistance);
		addResistance.addActionListener(mouselisten);
		addHS.add(addGhorkov);
		addGhorkov.addActionListener(mouselisten);
		addHS.add(addTaerkasten);
		addTaerkasten.addActionListener(mouselisten);
		addHS.add(addMykonian);
		addMykonian.addActionListener(mouselisten);
		addHS.add(addSulgogar);
		addSulgogar.addActionListener(mouselisten);
		addHS.add(addBlacksect);
		addBlacksect.addActionListener(mouselisten);
		addHS.add(addTrainingHS);
		addTrainingHS.addActionListener(mouselisten);
		currentPosMenu.add(addHS);
		
		
		currentPosMenu.add(addSquad);
		
		changeOwner.add(sectorResistance);
		sectorResistance.addActionListener(mouselisten);
		changeOwner.add(sectorGhorkov);
		sectorGhorkov.addActionListener(mouselisten);
		changeOwner.add(sectorTaerkasten);
		sectorTaerkasten.addActionListener(mouselisten);
		changeOwner.add(sectorMykonian);
		sectorMykonian.addActionListener(mouselisten);
		changeOwner.add(sectorSulgogar);
		sectorSulgogar.addActionListener(mouselisten);
		changeOwner.add(sectorBlacksect);
		sectorBlacksect.addActionListener(mouselisten);
		changeOwner.add(sectorTrainingHS);
		sectorTrainingHS.addActionListener(mouselisten);
		changeOwner.add(sectorNeutral);
		sectorNeutral.addActionListener(mouselisten);
		currentPosMenu.add(changeOwner);
		
		setHgt.addActionListener(mouselisten);
		currentPosMenu.add(setHgt);
		
		initAddBuildings();
		
		currentPosMenu.add(specialBuilding);
		
		sectorApperance = new JMenuItem("Set building(typ map) here");
		sectorApperance.addActionListener(mouselisten);
		currentPosMenu.add(sectorApperance);
		
		newItem.add(newBeamGate);
		newBeamGate.addActionListener(mouselisten);
		newItem.add(newBomb);
		newBomb.addActionListener(mouselisten);
		newItem.add(newTechUpgrade);
		newTechUpgrade.addActionListener(mouselisten);
		sectorItem.add(newItem);
		
		sectorItem.add(bgKeySector);
		sectorItem.add(bombReactors);
		currentPosMenu.add(sectorItem);
		
		clearSector = new JMenuItem("Clear this sector");
		clearSector.addActionListener(mouselisten);
		currentPosMenu.add(clearSector);
		
		selectedPopUp = new JPopupMenu();
		removeHS = new JMenuItem("Remove this hoststation");
		
		selectedPopUpSquad = new JPopupMenu();
		removeSquad = new JMenuItem("Remove this squad");
		
		selectedPopUp.add(removeHS);
		removeHS.addActionListener(mouselisten);
		
		selectedPopUpSquad.add(removeSquad);
		removeSquad.addActionListener(mouselisten);
		
		bgList = new ArrayList<>();
		bombList = new ArrayList<>();

		error_map = new ArrayList<>();
		
		borderSelectedSectors = new ArrayList<>();
		
		font = new Font("Helvetica", Font.PLAIN, 10);

		try {
			errorimg = ImageIO.read(this.getClass().getResourceAsStream("/img/blg_map/error.png"));
			errorimg = resize(EditorState.sectorSize,EditorState.sectorSize, errorimg);
		}catch(IOException ex) {
			System.out.println("Couldn't load special building image:");
			System.out.println(ex);
		}
		resizeBuildingImg();
		
		set1Images = new BufferedImage[256];
		set2Images = new BufferedImage[256];
		set3Images = new BufferedImage[256];
		set4Images = new BufferedImage[256];
		set5Images = new BufferedImage[256];
		set6Images = new BufferedImage[256];
		
		try {
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
				
				set1Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set1_above/Set1_sector_"+i+".jpg"));
			}
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
				
				set2Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set2_above/Set2_sector_"+i+".jpg"));
			}
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
				
				set3Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set3_above/Set3_sector_"+i+".jpg"));
			}
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
				
				set4Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set4_above/Set4_sector_"+i+".jpg"));
			}
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
				
				set5Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set5_above/Set5_sector_"+i+".jpg"));
			}
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
				
				set6Images[i] = ImageIO.read(this.getClass().getResource("/img/Sector_images/Set6_above/Set6_sector_"+i+".jpg"));
			}
		}catch(IOException ex) {
			System.out.println("An error occured while reading a typ_map image from above");
		}
		
		this.addMouseListener(mouselisten);
		this.addMouseMotionListener(mouselisten);
		this.addKeyListener(mouselisten);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g) { // TODO drawing starts here
		Graphics2D drawMap = (Graphics2D) g;
		drawMap.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawMap.setFont(MainWindow.hgtFont); 
		for(int i = 0; i < error_map.size(); i++) error_map.set(i, false);
		Shape sector;
		hSize = 0;
		vSize = topCorner;
		this.sectorCounter = 0;
		for(int i = 0;i < verticalGrid; i++) {
			hSize = leftCorner;
			for(int j = 0;j < horizontalGrid; j++) {
				sector = new Rectangle2D.Float(hSize, vSize, EditorState.sectorSize, EditorState.sectorSize);
				if(EditorState.own_map.get(sectorCounter) == 0) {
					drawMap.setColor(Color.BLACK);
				}else if(EditorState.own_map.get(sectorCounter) == 1) {
					drawMap.setColor(Color.BLUE);
				}else if(EditorState.own_map.get(sectorCounter) == 2) {
					drawMap.setColor(Color.GREEN);
				}else if(EditorState.own_map.get(sectorCounter) == 3) {
					drawMap.setColor(Color.WHITE);
				}else if(EditorState.own_map.get(sectorCounter) == 4) {
					drawMap.setColor(Color.YELLOW);
				}else if(EditorState.own_map.get(sectorCounter) == 5) {
					drawMap.setColor(Color.GRAY);
				}else if(EditorState.own_map.get(sectorCounter) == 6) {
					drawMap.setColor(Color.RED);
				}else if(EditorState.own_map.get(sectorCounter) == 7) {
					drawMap.setColor(Color.BLACK);
				}
				
				
				if(EditorState.set == 1) {
					if(EditorState.typ_map.get(sectorCounter) > 53 && EditorState.typ_map.get(sectorCounter) < 59)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 59 && EditorState.typ_map.get(sectorCounter) < 66)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 82 && EditorState.typ_map.get(sectorCounter) < 95)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 104 && EditorState.typ_map.get(sectorCounter) < 110)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 113 && EditorState.typ_map.get(sectorCounter) < 120)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 121 && EditorState.typ_map.get(sectorCounter) < 130)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 141 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 189 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 236 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set1Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}else if(EditorState.set == 2) {
					if(EditorState.typ_map.get(sectorCounter) > 24 && EditorState.typ_map.get(sectorCounter) < 27)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 104 && EditorState.typ_map.get(sectorCounter) < 110)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 113 && EditorState.typ_map.get(sectorCounter) < 118)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 131 && EditorState.typ_map.get(sectorCounter) < 133)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 133 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 195 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 210)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 225 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 230 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set2Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}else if(EditorState.set == 3) {
					if(EditorState.typ_map.get(sectorCounter) > 49 && EditorState.typ_map.get(sectorCounter) < 59)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 59 && EditorState.typ_map.get(sectorCounter) < 66)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 82 && EditorState.typ_map.get(sectorCounter) < 100)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 104 && EditorState.typ_map.get(sectorCounter) < 110)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 113 && EditorState.typ_map.get(sectorCounter) < 121)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 121 && EditorState.typ_map.get(sectorCounter) < 130)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 141 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 189 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 230 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set3Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}else if(EditorState.set == 4) {
					if(EditorState.typ_map.get(sectorCounter) > 49 && EditorState.typ_map.get(sectorCounter) < 59)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 60 && EditorState.typ_map.get(sectorCounter) < 66)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 82 && EditorState.typ_map.get(sectorCounter) < 100)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 104 && EditorState.typ_map.get(sectorCounter) < 110)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 113 && EditorState.typ_map.get(sectorCounter) < 121)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 121 && EditorState.typ_map.get(sectorCounter) < 130)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 141 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 189 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 230 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set4Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}else if(EditorState.set == 5) {
					if(EditorState.typ_map.get(sectorCounter) > 95 && EditorState.typ_map.get(sectorCounter) < 97)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 116 && EditorState.typ_map.get(sectorCounter) < 118)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 131 && EditorState.typ_map.get(sectorCounter) < 133)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 137 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 191 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 210)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 225 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 230 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set5Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}else if(EditorState.set == 6) {
					if(EditorState.typ_map.get(sectorCounter) > 49 && EditorState.typ_map.get(sectorCounter) < 59)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 59 && EditorState.typ_map.get(sectorCounter) < 66)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 82 && EditorState.typ_map.get(sectorCounter) < 95)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 104 && EditorState.typ_map.get(sectorCounter) < 110)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 113 && EditorState.typ_map.get(sectorCounter) < 121)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 121 && EditorState.typ_map.get(sectorCounter) < 130)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 141 && EditorState.typ_map.get(sectorCounter) < 150)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 189 && EditorState.typ_map.get(sectorCounter) < 198)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 205 && EditorState.typ_map.get(sectorCounter) < 207)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 208 && EditorState.typ_map.get(sectorCounter) < 228)
						error_map.set(sectorCounter, true);
					if(EditorState.typ_map.get(sectorCounter) > 235 && EditorState.typ_map.get(sectorCounter) < 239)
						error_map.set(sectorCounter, true);
					
					if(!error_map.get(sectorCounter) && mainwindow.IsImgEnabled())
						drawMap.drawImage(set6Images[EditorState.typ_map.get(sectorCounter)], hSize, vSize, this);
				}
				
				for(UAitem building : UAdata.allBuildings) {
					if(EditorState.blg_map.get(sectorCounter) == building.getID())
						drawMap.drawImage(building.getImg(), hSize, vSize, this);
				}
				for(UAitem building : UAdata.fallbackBuildings) {
					if(EditorState.blg_map.get(sectorCounter) == building.getID())
						drawMap.drawImage(building.getImg(), hSize, vSize, this);
				}
				
				if(EditorState.getBeamGate(j+1, i+1) != null) {
					if(EditorState.getBeamGate(j+1, i+1).getTargetLevel().size() == 0)
						error_map.set(sectorCounter, true);
				}	
				
				sectorCounter++;
				drawMap.draw(sector);
				hSize += EditorState.sectorSize;
				hSize += EditorState.sectorIndent;
			}

			hSize = 0;
			vSize += EditorState.sectorSize;
			vSize += EditorState.sectorIndent;
		}

		borderSectorCounter = 0;
		hSize = 0;
		vSize = 0;
		if(verticalGrid > 0 && horizontalGrid > 0) {
			for(int i = 0; i < (verticalGrid + 2); i++) {
				for(int j = 0; j < (horizontalGrid + 2); j++) {
					if((borderSectorCounter - (horizontalGrid+2)) >= 0) {
						if(EditorState.hgt_map.get(borderSectorCounter) - EditorState.hgt_map.get(borderSectorCounter - (horizontalGrid+2)) > 4) {
							drawMap.setColor(Color.CYAN);
							drawMap.setStroke(new BasicStroke(4));
							drawMap.drawLine((int)(hSize + (EditorState.sectorSize * 0.07)), (int)(vSize + (EditorState.sectorSize * 0.07)), (int)(hSize + (EditorState.sectorSize * 0.93)), (int)(vSize + (EditorState.sectorSize * 0.07)));
						}
					}
					if((borderSectorCounter + 1) < EditorState.hgt_map.size() && ((horizontalGrid + 2) - j) != 1) {
						if(EditorState.hgt_map.get(borderSectorCounter) - EditorState.hgt_map.get(borderSectorCounter+1) > 4) {
							drawMap.setColor(Color.CYAN);
							drawMap.setStroke(new BasicStroke(4));
							drawMap.drawLine((int)(hSize + (EditorState.sectorSize * 0.93)), (int)(vSize + (EditorState.sectorSize * 0.07)), (int)(hSize + (EditorState.sectorSize * 0.93)), (int)(vSize + (EditorState.sectorSize * 0.93)));
						}
					}
					if(borderSectorCounter + (horizontalGrid + 2) < EditorState.hgt_map.size()) {
						if(EditorState.hgt_map.get(borderSectorCounter) - EditorState.hgt_map.get(borderSectorCounter+(horizontalGrid + 2)) > 4) {
							drawMap.setColor(Color.CYAN);
							drawMap.setStroke(new BasicStroke(4));
							drawMap.drawLine((int)(hSize + (EditorState.sectorSize * 0.07)), (int)(vSize + (EditorState.sectorSize * 0.93)), (int)(hSize + (EditorState.sectorSize * 0.93)), (int)(vSize + (EditorState.sectorSize * 0.93)));
						}
					}
					if((borderSectorCounter - 1) >= 0 && j != 0) {
						if(EditorState.hgt_map.get(borderSectorCounter) - EditorState.hgt_map.get(borderSectorCounter-1) > 4) {
							drawMap.setColor(Color.CYAN);
							drawMap.setStroke(new BasicStroke(4));
							drawMap.drawLine((int)(hSize + (EditorState.sectorSize * 0.07)), (int)(vSize + (EditorState.sectorSize * 0.07)), (int)(hSize + (EditorState.sectorSize * 0.07)), (int)(vSize + (EditorState.sectorSize * 0.93)));
						}
					}
					
					borderSectorCounter++;
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
				vSize += EditorState.sectorSize;
				vSize += EditorState.sectorIndent;
				hSize = 0;
			}
		}
		
		for(BeamGate bg : EditorState.beamGates) {
			hSize = leftCorner;
			vSize = topCorner;
			if(bg.getX() > 1) {
				for(int i = 2;i <= bg.getX(); i++) {
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
			}
			if(bg.getY() > 1) {
				for(int i = 2;i <= bg.getY(); i++) {
					vSize += EditorState.sectorSize;
					vSize += EditorState.sectorIndent;
				}
			}

			bg.resize(EditorState.sectorSize, EditorState.sectorSize);
			drawMap.drawImage(bg.getImage(), hSize, vSize, this);
			
			for(SpecialBuilding keysector : bg.getKeysectors()) {
				hSize = leftCorner;
				vSize = topCorner;
				if(keysector.getX() > 1) {
					for(int i = 2;i <= keysector.getX(); i++) {
						hSize += EditorState.sectorSize;
						hSize += EditorState.sectorIndent;
					}
				}
				if(keysector.getY() > 1) {
					for(int i = 2;i <= keysector.getY(); i++) {
						vSize += EditorState.sectorSize;
						vSize += EditorState.sectorIndent;
					}
				}
				
				keysector.resize(EditorState.sectorSize, EditorState.sectorSize);
				drawMap.drawImage(keysector.getImg(), hSize, vSize, this);
			}
		}
		
		for(StoudsonBomb bomb : EditorState.bombs) {
			hSize = leftCorner;
			vSize = topCorner;
			if(bomb.getX() > 1) {
				for(int i = 2;i <= bomb.getX(); i++) {
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
			}
			if(bomb.getY() > 1) {
				for(int i = 2;i <= bomb.getY(); i++) {
					vSize += EditorState.sectorSize;
					vSize += EditorState.sectorIndent;
				}
			}

			bomb.resize(EditorState.sectorSize, EditorState.sectorSize);
			drawMap.drawImage(bomb.getImage(), hSize, vSize, this);
			
			for(SpecialBuilding reactor: bomb.getReactors()) {
				hSize = leftCorner;
				vSize = topCorner;
				if(reactor.getX() > 1) {
					for(int i = 2;i <= reactor.getX(); i++) {
						hSize += EditorState.sectorSize;
						hSize += EditorState.sectorIndent;
					}
				}
				if(reactor.getY() > 1) {
					for(int i = 2;i <= reactor.getY(); i++) {
						vSize += EditorState.sectorSize;
						vSize += EditorState.sectorIndent;
					}
				}
				
				reactor.resize(EditorState.sectorSize, EditorState.sectorSize);
				drawMap.drawImage(reactor.getImg(), hSize, vSize, this);
			}
		}
		
		for(TechUpgrade tu : EditorState.techUpgrades) {
			hSize = leftCorner;
			vSize = topCorner;
			if(tu.getX() > 1) {
				for(int i = 2;i <= tu.getX(); i++) {
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
			}
			if(tu.getY() > 1) {
				for(int i = 2;i <= tu.getY(); i++) {
					vSize += EditorState.sectorSize;
					vSize += EditorState.sectorIndent;
				}
			}
			tu.resize(EditorState.sectorSize, EditorState.sectorSize);
			drawMap.drawImage(tu.getImg(), hSize, vSize, this);
		}
		
		for(Unit hs : EditorState.hostStations) {
			hs.resize((int)(EditorState.sectorSize * 0.5), (int)(EditorState.sectorSize * 0.5));
			drawMap.drawImage(hs.getImage(), hs.getUnitX(), hs.getUnitY(), this);
		}
		
		for(Unit squad : EditorState.predefinedSquads) {
			squad.resize((int)(EditorState.sectorSize * 0.14), (int)(EditorState.sectorSize * 0.14));
			drawMap.drawImage(squad.getImage(), squad.getUnitX(), squad.getUnitY(), this);
		}
		
		sectorCounter = 0;
		hSize = EditorState.sectorSize;
		vSize = EditorState.sectorSize;
		String hInfo;
		if(verticalGrid > 0 && horizontalGrid > 0) {
			for(int i = 0; i < verticalGrid ; i++) {
				for(int j = 0; j < horizontalGrid; j++) {
					if(mainwindow.IsTypEnabled()) {
						hInfo = "Typ: " + EditorState.typ_map.get(sectorCounter);
						drawMap.setColor(Color.BLACK);
						drawMap.fillRect((int)(hSize + (EditorState.sectorSize * 0.1)),(int)(vSize + (EditorState.sectorSize * 0.1)), (int)(EditorState.sectorSize * 0.9) , (int)(EditorState.sectorSize * 0.3));
						drawMap.setColor(Color.WHITE);
						drawMap.drawString(hInfo, (int)(hSize + (EditorState.sectorSize * 0.1)), (int)(vSize + (EditorState.sectorSize * 0.3)));
					}
					
					if(mainwindow.IsBlgEnabled()) {
						hInfo = "Blg: " + EditorState.blg_map.get(sectorCounter);
						drawMap.setColor(Color.BLACK);
						drawMap.fillRect((int)(hSize + (EditorState.sectorSize * 0.1)),(int)(vSize + (EditorState.sectorSize * 0.35)), (int)(EditorState.sectorSize * 0.9) , (int)(EditorState.sectorSize * 0.27));
						drawMap.setColor(Color.WHITE);
						drawMap.drawString(hInfo, (int)(hSize + (EditorState.sectorSize * 0.1)), (int)(vSize + (EditorState.sectorSize * 0.5)));
					}
					
					if(mainwindow.IsOwnEnabled()) {
						hInfo = "Own: " + EditorState.own_map.get(sectorCounter);
						drawMap.setColor(Color.BLACK);
						drawMap.fillRect((int)(hSize + (EditorState.sectorSize * 0.1)),(int)(vSize + (EditorState.sectorSize * 0.5)), (int)(EditorState.sectorSize * 0.9) , (int)(EditorState.sectorSize * 0.3));
						drawMap.setColor(Color.WHITE);
						drawMap.drawString(hInfo, (int)(hSize + (EditorState.sectorSize * 0.1)), (int)(vSize + (EditorState.sectorSize * 0.7)));
					}
					
					sectorCounter++;
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
				vSize += EditorState.sectorSize;
				vSize += EditorState.sectorIndent;
				hSize = EditorState.sectorSize;
			}
		}
		
		borderSectorCounter = 0;
		hSize = 0;
		vSize = 0;
		if(verticalGrid > 0 && horizontalGrid > 0) {
			for(int i = 0; i < (verticalGrid + 2); i++) {
				for(int j = 0; j < (horizontalGrid + 2); j++) {
					if(borderSelectedSectors.size() > 1) {
						for(int sect : borderSelectedSectors) {
							if(sect == borderSectorCounter) {
								drawMap.setColor(new Color(255,255,255,54));
								drawMap.fillRect(hSize+1, vSize+1, EditorState.sectorSize-1, EditorState.sectorSize-1);
							}
						}
					} else
					if(borderSelectedSector == borderSectorCounter) {
						drawMap.setColor(new Color(255,255,255,54));
						drawMap.fillRect(hSize+1, vSize+1, EditorState.sectorSize-1, EditorState.sectorSize-1);
					}
					
					if(mainwindow.IsHeightEnabled()) {
						hInfo = "Hgt: " + EditorState.hgt_map.get(borderSectorCounter);
						drawMap.setColor(Color.WHITE);
						drawMap.drawString(hInfo, (int)(hSize + (EditorState.sectorSize * 0.1)), (int)(vSize + (EditorState.sectorSize * 0.85)));
					}
					borderSectorCounter++;
					hSize += EditorState.sectorSize;
					hSize += EditorState.sectorIndent;
				}
				vSize += EditorState.sectorSize;
				vSize += EditorState.sectorIndent;
				hSize = 0;
			}
		}
		
		hSize = leftCorner;
		vSize = topCorner;
		this.sectorCounter = 0;
		for(int i = 0; i < verticalGrid; i++) {
			for(int j = 0; j < horizontalGrid; j++) {
				if(error_map.get(this.sectorCounter)) {
					drawMap.drawImage(errorimg, hSize, vSize, this);
				}
				hSize += EditorState.sectorSize;
				hSize += EditorState.sectorIndent;
				this.sectorCounter++;
			}
			vSize += EditorState.sectorSize;
			vSize += EditorState.sectorIndent;
			hSize = leftCorner;
		}
		
	} // TODO end of drawComponent
	
	void createMap(int hGrid, int vGrid) {
		this.horizontalGrid = hGrid;
		this.verticalGrid = vGrid;
		this.error_map.clear();
		for(int i = 0;i < this.horizontalGrid * this.verticalGrid; i++) {
			EditorState.typ_map.add(0);
			EditorState.own_map.add(0);
			EditorState.blg_map.add(0);
			this.error_map.add(false);
		}
		for(int i = 0;i < (this.horizontalGrid + 2) * (this.verticalGrid + 2); i++) {
			EditorState.hgt_map.add(127);
			
		}
		
		this.sumIndentX = (hGrid -1) * EditorState.sectorIndent;
		this.sumIndentY = (vGrid -1) * EditorState.sectorIndent;
		this.topCorner = EditorState.sectorSize + EditorState.sectorIndent;
		this.downCorner = this.topCorner + this.verticalGrid * EditorState.sectorSize + sumIndentY;
		this.leftCorner = EditorState.sectorSize + EditorState.sectorIndent;
		this.rightCorner = this.leftCorner + this.horizontalGrid * EditorState.sectorSize + sumIndentX;
		this.setPreferredSize(new Dimension((horizontalGrid + 2) * EditorState.sectorSize + sumIndentX + 6, (verticalGrid + 2) * EditorState.sectorSize + sumIndentY + 6));
		
		this.mainwindow.updateManagerUnit(null,-1);
		this.mainwindow.updateManagerSector(-1, 0, 0, 0);
		this.repaint();
	}

	void openMap(int hGrid, int vGrid) {
		this.horizontalGrid = hGrid;
		this.verticalGrid = vGrid;
		this.sumIndentX = (hGrid -1) * EditorState.sectorIndent;
		this.sumIndentY = (vGrid -1) * EditorState.sectorIndent;
		this.topCorner = EditorState.sectorSize + EditorState.sectorIndent;
		this.downCorner = this.topCorner + this.verticalGrid * EditorState.sectorSize + sumIndentY;
		this.leftCorner = EditorState.sectorSize + EditorState.sectorIndent;
		this.rightCorner = this.leftCorner + this.horizontalGrid * EditorState.sectorSize + sumIndentX;
		this.setPreferredSize(new Dimension((horizontalGrid + 2) * EditorState.sectorSize + sumIndentX + 6, (verticalGrid + 2) * EditorState.sectorSize + sumIndentY + 6));
		for(int i = 0;i < this.horizontalGrid * this.verticalGrid; i++) 
			this.error_map.add(false);

		this.mainwindow.updateManagerUnit(null,-1);
		this.mainwindow.updateManagerSector(-1, 0, 0, 0);
		
		bgList.clear();
		bgKeySector.removeAll();
		for(int i = 0; i < EditorState.beamGates.size(); i++) {
			this.bgList.add(new JMenuItem("Beam Gate " + (i+1) ));
			this.bgKeySector.add(bgList.get(i));
			this.bgList.get(i).addActionListener(mouselisten);
		}
		bombList.clear(); 
		bombReactors.removeAll();
		for(int i = 0; i < EditorState.bombs.size(); i++) {
			bombList.add(new JMenuItem("Stoudson Bomb " + (i+1) ));
			bombReactors.add(bombList.get(i));
			bombList.get(i).addActionListener(mouselisten);
		}
		this.repaint();
	}
	
	void closeMap() {
		this.horizontalGrid = 0;
		this.verticalGrid = 0;
		this.error_map.clear();
		this.sumIndentX = 0;
		this.sumIndentY = 0;
		this.topCorner = 0;
		this.downCorner = 0;
		this.leftCorner = 0;
		this.rightCorner = 0;
		this.setPreferredSize(new Dimension(300, 300));
		
		this.mainwindow.updateManagerUnit(null,-1);
		this.mainwindow.updateManagerSector(-1, 0, 0, 0);
		this.bgKeySector.removeAll();
		this.repaint();
	}
	public void updateMap() {
		mainwindow.updateManagerSector(borderSelectedSector, selectedSector,horizontalGrid, verticalGrid);
	}
	public int getLeftCorner() {
		return this.leftCorner;
	}
	public int getRightCorner() {
		return this.rightCorner;
	}
	public int getTopCorner() {
		return this.topCorner;
	}
	public int getDownCorner() {
		return this.downCorner;
	}
	public int getSelectedBorderSector() {
		return this.borderSelectedSector;
	}
	public int getSelectedSector() {
		return this.selectedSector;
	}

	private class EditorMouseListener implements MouseListener, MouseMotionListener, ActionListener, KeyListener{
		// TODO EditorMouseListener begins here
		private int diffX;
		private int diffY;
		private int hgt;
		private int typ;
		private int sectorOwnerKey = -1;
		private boolean ctrlDown = false;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3) {
				if(e.getX() <= rightCorner + EditorState.sectorSize + EditorState.sectorIndent && e.getY() <= downCorner + EditorState.sectorSize + EditorState.sectorIndent && verticalGrid > 0 && horizontalGrid > 0) {
					currentClickedX = e.getX();
					currentClickedY = e.getY();
					
					if(selected instanceof Hoststation)
					{
						selectedPopUp.show(e.getComponent(),e.getX(),e.getY());
					}else if(selected instanceof Squad)
					{
						selectedPopUpSquad.show(e.getComponent(),e.getX(),e.getY());
					}else
					currentPosMenu.show(e.getComponent(),e.getX(),e.getY());
				}
			}
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getClickCount() == 2) {
					if(selected != null) mainwindow.showManager(0);
					else if(borderSelectedSector != -1) mainwindow.showManager(1);
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1 && verticalGrid > 0 && horizontalGrid > 0) {
				HandleSelection(e.getX(), e.getY());
			}
			if(e.getButton() == MouseEvent.BUTTON3 && verticalGrid > 0 && horizontalGrid > 0) {
				HandleSelection(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(JMenuItem addUnit : resUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 1, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : ghorUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 6, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : taerUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 4, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : mykoUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 3, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : sulgUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 2, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : trainingUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 7, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}
			for(JMenuItem addUnit : specialUnitsMenu) {
				if(e.getSource() == addUnit) {
					CreateSquad(currentClickedX - (int)(EditorState.sectorSize * 0.14/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.14/ 2), 1, UAdata.getUnitIDfromName(addUnit.getText()));
				}
			}			
			
			if(e.getSource() == removeSquad) {
				EditorState.predefinedSquads.remove(selected);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				selected = null;
				mainwindow.updateManagerUnit(null,-1);
			}else if(e.getSource() == removeHS) {
				EditorState.hostStations.remove(selected);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				selected = null;
				mainwindow.updateManagerUnit(null,-1);
				if(EditorState.hostStations.size() <= EditorState.playerSelected && EditorState.hostStations.size() > 0) EditorState.playerSelected = EditorState.hostStations.size()-1;
			} else 
				
			if(e.getSource() == sectorResistance && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 1);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorGhorkov && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 6);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorTaerkasten && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 4);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorMykonian && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 3);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorSulgogar && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 2);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorBlacksect && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 5);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorTrainingHS && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.own_map.set(selectedSector, 7);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == sectorNeutral && horizontalGrid * verticalGrid > selectedSector) {
				if(EditorState.blg_map.get(selectedSector) != 0)
					EditorState.own_map.set(selectedSector, 7);
				else
					EditorState.own_map.set(selectedSector, 0);
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else if(e.getSource() == setHgt) {
				try {
					hgt = Integer.parseInt(JOptionPane.showInputDialog("Enter height value from 0 to 255"));
					if(hgt >= 0 && hgt < 256) {
						EditorState.hgt_map.set(borderSelectedSector, hgt);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
					}else JOptionPane.showMessageDialog(mainwindow,"Entered height value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(mainwindow,"Entered height value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
				
			}
				// adding buildings to blg_map
			for(JMenuItem buildingItem : resBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 1, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			for(JMenuItem buildingItem : ghorBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 6, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			for(JMenuItem buildingItem : taerBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 4, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			for(JMenuItem buildingItem : mykoBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 3, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			for(JMenuItem buildingItem : blackSectBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 5, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			for(JMenuItem buildingItem : miscBuildingsMenu) {
				if(e.getSource() == buildingItem) {
					AddSpecialBuilding(UAdata.getTypMapfromName(buildingItem.getText()), 7, UAdata.getBuildingIDfromName(buildingItem.getText()));
				}
			}
			
			if(e.getSource() == clearSector && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.typ_map.set(selectedSector, 0);
				EditorState.blg_map.set(selectedSector, 0);
				EditorState.beamGates.remove(selectedBG);
				selectedBG = null;
				EditorState.bombs.remove(selectedBomb);
				selectedBomb = null;
				EditorState.techUpgrades.remove(selectedTU);
				selectedTU = null;
				
				for(int i = 0; i < EditorState.beamGates.size(); i++) {
					if(selectedKS != null) {
						for(SpecialBuilding keysector : EditorState.beamGates.get(i).getKeysectors()) {
							if(keysector == selectedKS) {
								EditorState.beamGates.get(i).removeKeysector(selectedKS);
								selectedKS = null;
								break;
							}
						}
					}
				}
				
				for(int i = 0; i < EditorState.bombs.size(); i++) {
					if(selectedReactor != null) {
						for(SpecialBuilding reactor : EditorState.bombs.get(i).getReactors()) {
							if(reactor == selectedReactor) {
								EditorState.bombs.get(i).removeReactor(selectedReactor);
								selectedReactor = null;
								break;
							}
						}
					}
				}
				
				bgList.clear();
				bgKeySector.removeAll();
				for(int i = 0; i < EditorState.beamGates.size(); i++) {
					bgList.add(new JMenuItem("Beam Gate " + (i+1) ));
					bgKeySector.add(bgList.get(i));
					bgList.get(i).addActionListener(this);
				}
				
				bombList.clear();
				bombReactors.removeAll();
				for(int i = 0; i < EditorState.bombs.size(); i++) {
					bombList.add(new JMenuItem("Stoudson Bomb " + (i+1) ));
					bombReactors.add(bombList.get(i));
					bombList.get(i).addActionListener(this);
				}
				
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}else
			
			if(e.getSource() == sectorApperance && horizontalGrid * verticalGrid > selectedSector) {
				try {
					typ = Integer.parseInt(JOptionPane.showInputDialog("Enter value from 0 to 255"));
					if(typ >= 0 && typ < 256) {
						EditorState.typ_map.set(selectedSector, typ);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.cleanManager();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
				}else JOptionPane.showMessageDialog(mainwindow,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(mainwindow,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
				}
			}else if(e.getSource() == newBeamGate) {
				if(selectedBG == null && horizontalGrid * verticalGrid > selectedSector) {
					EditorState.beamGates.add(new BeamGate(selectedSectorX, selectedSectorY));
					EditorState.typ_map.set(selectedSector, 202);
					EditorState.blg_map.set(selectedSector, 0);
					bgKeySector.removeAll();
					bgList.clear();
					for(int i = 0; i < EditorState.beamGates.size(); i++) {
						bgList.add(new JMenuItem("Beam Gate " + (i+1)));
						bgList.get(i).addActionListener(this);
						bgKeySector.add(bgList.get(i));
					}
				}
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
				mainwindow.showManager(1);
			}else if(e.getSource() == newBomb) {
				if(horizontalGrid * verticalGrid > selectedSector) {
					try {
						int count = Integer.parseInt(JOptionPane.showInputDialog("Enter bomb countdown in seconds"));
						if(count > 0) {
							count *= 1024;
							EditorState.bombs.add(new StoudsonBomb(selectedSectorX, selectedSectorY, count));
							EditorState.typ_map.set(selectedSector, 245);
							EditorState.blg_map.set(selectedSector, 0);
							bombReactors.removeAll();
							bombList.clear();
							for(int i = 0; i < EditorState.bombs.size(); i++) {
								bombList.add(new JMenuItem("Stoudson Bomb " + (i+1)));
								bombList.get(i).addActionListener(this);
								bombReactors.add(bombList.get(i));
							}
							mainwindow.makeUnsaved();
							mainwindow.repaint();
							mainwindow.cleanManager();
							mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
						}else JOptionPane.showMessageDialog(mainwindow,"Entered value must be greater than 0", "Wrong value", JOptionPane.ERROR_MESSAGE);
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(mainwindow,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}else if(e.getSource() == newTechUpgrade) {
				if(horizontalGrid * verticalGrid > selectedSector) {
					EditorState.techUpgrades.add(new TechUpgrade(selectedSectorX, selectedSectorY));
					EditorState.typ_map.set(selectedSector, 100);
					EditorState.blg_map.set(selectedSector, 0);
					mainwindow.makeUnsaved();
					mainwindow.repaint();
					mainwindow.showManager(1);
					mainwindow.cleanManager();
					mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
				}
			}else
				
				if(e.getSource() == addResistance) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 1, 56);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addGhorkov) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 6, 59);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addTaerkasten) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 4, 60);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addMykonian) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 3, 58);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addSulgogar) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 2, 61);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addBlacksect) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 5, 62);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}else if(e.getSource() == addTrainingHS) {
					if(EditorState.hostStations.size() < 8) {
						CreateHoststation(currentClickedX - (int)(EditorState.sectorSize * 0.5/ 2), currentClickedY - (int)(EditorState.sectorSize * 0.5/ 2), 7, 132);
					}else {
						JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 8 host stations in one level", "Host station's limit reached", JOptionPane.ERROR_MESSAGE);
					}
				} 
			
			for(int i = 0;i < bgList.size(); i++) {
				if(horizontalGrid * verticalGrid > selectedSector) {
					if(bgList.get(i) == e.getSource()) {
						if(!(EditorState.beamGates.get(i).getKeysectors().size() > 15)) {
							EditorState.beamGates.get(i).addKeysector(selectedSectorX, selectedSectorY);
							mainwindow.makeUnsaved();
							mainwindow.repaint();
							mainwindow.cleanManager();
							mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
						}else JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 16 key sectors for one beam gate", "Key sectors' limit reached", JOptionPane.ERROR_MESSAGE);
						
					}
				}
			}
			
			for(int i = 0;i < bombList.size(); i++) {
				if(horizontalGrid * verticalGrid > selectedSector) {
					if(bombList.get(i) == e.getSource()) {
						if(!(EditorState.bombs.get(i).getReactors().size() > 15)) {
							EditorState.bombs.get(i).addReactor(selectedSectorX, selectedSectorY);
							EditorState.typ_map.set(selectedSector, 243);
							mainwindow.makeUnsaved();
							mainwindow.repaint();
							mainwindow.cleanManager();
							mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
						}else JOptionPane.showMessageDialog(mainwindow,"You cannot create more than 16 reactors for one stoudson bomb", "Reactors' limit reached", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if(selected != null) {
				diffX = e.getX() - diffX;
				diffY = e.getY() - diffY;
					
				if(selected instanceof Hoststation) {
					if((selected.getUnitX() + (int)(EditorState.sectorSize * 0.5/ 2) < leftCorner && diffX < 0) || ((selected.getUnitX() + (int)(EditorState.sectorSize * 0.5 / 2) > rightCorner) && diffX > 0)){
						diffX = 0;
					}else if(e.getX() < leftCorner || e.getX() > rightCorner)diffX = 0;
					
					if((selected.getUnitY() + (int)(EditorState.sectorSize * 0.5/ 2) < topCorner && diffY < 0) || ((selected.getUnitY() + (int)(EditorState.sectorSize * 0.5 / 2) > downCorner) && diffY > 0)){
						diffY = 0;
					}else if(e.getY() < topCorner || e.getY() > downCorner)diffY = 0;
				}else if(selected instanceof Squad) {
					if((selected.getUnitX() + (int)(EditorState.sectorSize * 0.14/ 2) < leftCorner && diffX < 0) || ((selected.getUnitX() + (int)(EditorState.sectorSize * 0.14 / 2) > rightCorner) && diffX > 0)){
						diffX = 0;
					}else if(e.getX() < leftCorner || e.getX() > rightCorner)diffX = 0;
					
					if((selected.getUnitY() + (int)(EditorState.sectorSize * 0.14/ 2) < topCorner && diffY < 0) || ((selected.getUnitY() + (int)(EditorState.sectorSize * 0.14 / 2) > downCorner) && diffY > 0)){
						diffY = 0;
					}else if(e.getY() < topCorner || e.getY() > downCorner)diffY = 0;
				}
				
				selected.setUnitX(selected.getUnitX() + diffX);
				selected.setUnitY(selected.getUnitY() + diffY);
				
				if(selected instanceof Hoststation) mainwindow.refreshHoststationManager(EditorState.hostStations.indexOf(selected));
				if(selected instanceof Squad) mainwindow.refreshSquadManager(EditorState.predefinedSquads.indexOf(selected));
				mainwindow.makeUnsaved();
				mainwindow.repaint();
			}

			diffX = e.getX();
			diffY = e.getY();	
			if((sectorOwnerKey >= 0 && sectorOwnerKey < 8) || ctrlDown) {
				HandleSelection(e.getX(), e.getY());
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			if((horizontalGrid + 2) * (verticalGrid + 2) > selectedSector) {
				
				if(e.getKeyChar() == '+') {
					if(borderSelectedSectors.size() > 1) {
						for(int sectorHeight : borderSelectedSectors) {
							currentHeight = EditorState.hgt_map.get(sectorHeight);
							if(currentHeight < 255) {
								currentHeight++;
								EditorState.hgt_map.set(sectorHeight, currentHeight);
							}
						}
					} else {
						if(currentHeight < 255) {
							currentHeight = EditorState.hgt_map.get(borderSelectedSector);
							currentHeight++;
							EditorState.hgt_map.set(borderSelectedSector, currentHeight);
						}
					}
					mainwindow.makeUnsaved();
					mainwindow.repaint();
				}else if(e.getKeyChar() == '-') {
					if(borderSelectedSectors.size() > 1) {
						for(int sectorHeight : borderSelectedSectors) {
							currentHeight = EditorState.hgt_map.get(sectorHeight);
							if(currentHeight > 0) {
								currentHeight--;
								EditorState.hgt_map.set(sectorHeight, currentHeight);
							}
						}
					} else {
						if(currentHeight > 0) {
							currentHeight = EditorState.hgt_map.get(borderSelectedSector);
							currentHeight--;
							EditorState.hgt_map.set(borderSelectedSector, currentHeight);
						}
					}
					mainwindow.makeUnsaved();
					mainwindow.repaint();
				}
				if((horizontalGrid * verticalGrid) > selectedSector) {
					if(e.getKeyChar() == '1') {
						EditorState.own_map.set(selectedSector, 1);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '2') {
						EditorState.own_map.set(selectedSector, 2);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '3') {
						EditorState.own_map.set(selectedSector, 3);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '4') {
						EditorState.own_map.set(selectedSector, 4);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '5') {
						EditorState.own_map.set(selectedSector, 5);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '6') {
						EditorState.own_map.set(selectedSector, 6);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '7') {
						EditorState.own_map.set(selectedSector, 7);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else if(e.getKeyChar() == '0') {
						if(EditorState.blg_map.get(selectedSector) != 0)
							EditorState.own_map.set(selectedSector, 7);
						else
							EditorState.own_map.set(selectedSector, 0);
						mainwindow.makeUnsaved();
						mainwindow.repaint();
						mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
					}else
					
					if(e.getKeyChar() == 't' || e.getKeyChar() == 'T' && verticalGrid > 0 && horizontalGrid > 0) {
						if(borderSelectedSector >= 0) {
							try {
								typ = Integer.parseInt(JOptionPane.showInputDialog("Enter value from 0 to 255"));
								if(typ >= 0 && typ < 256) {
									EditorState.typ_map.set(selectedSector, typ);
									mainwindow.makeUnsaved();
									mainwindow.repaint();
									mainwindow.cleanManager();
									mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
							}else JOptionPane.showMessageDialog(mainwindow,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
							}catch(NumberFormatException ex) {
								JOptionPane.showMessageDialog(mainwindow,"Entered value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
							}
						}
					}				
				}
				if(e.getKeyChar() == 'h' || e.getKeyChar() == 'H') {
					if(borderSelectedSector >= 0 && verticalGrid > 0 && horizontalGrid > 0) {
						try {
							hgt = Integer.parseInt(JOptionPane.showInputDialog("Enter height value from 0 to 255"));
							if(hgt >= 0 && hgt < 256) {
								if(borderSelectedSectors.size() > 1) {
									for(int sectorHeight : borderSelectedSectors) {
										EditorState.hgt_map.set(sectorHeight, hgt);
									}
								}else
									EditorState.hgt_map.set(borderSelectedSector, hgt);
								mainwindow.makeUnsaved();
								mainwindow.repaint();
							}else JOptionPane.showMessageDialog(mainwindow,"Entered height value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
						}catch(NumberFormatException ex) {
							JOptionPane.showMessageDialog(mainwindow,"Entered height value is not valid", "Wrong value", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_COMMA) {
				previousTypMap();
			}else if(e.getKeyCode() == KeyEvent.VK_PERIOD) {
				nextTypMap();
			}
			if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiersEx() & KeyEvent.CTRL_MASK) != 0)) {
				 mainwindow.savePrompt();
			}
			if(e.getKeyCode() == KeyEvent.VK_0) {
				sectorOwnerKey = 0;
			} else
			if(e.getKeyCode() == KeyEvent.VK_1) {
				sectorOwnerKey = 1;
			} else
			if(e.getKeyCode() == KeyEvent.VK_2) {
				sectorOwnerKey = 2;
			} else
			if(e.getKeyCode() == KeyEvent.VK_3) {
				sectorOwnerKey = 3;
			} else
			if(e.getKeyCode() == KeyEvent.VK_4) {
				sectorOwnerKey = 4;
			} else
			if(e.getKeyCode() == KeyEvent.VK_5) {
				sectorOwnerKey = 5;
			} else
			if(e.getKeyCode() == KeyEvent.VK_6) {
				sectorOwnerKey = 6;
			} else
			if(e.getKeyCode() == KeyEvent.VK_7) {
				sectorOwnerKey = 7;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
				ctrlDown = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DELETE && horizontalGrid * verticalGrid > selectedSector) {
				EditorState.typ_map.set(selectedSector, 0);
				EditorState.blg_map.set(selectedSector, 0);
				EditorState.beamGates.remove(selectedBG);
				selectedBG = null;
				EditorState.bombs.remove(selectedBomb);
				selectedBomb = null;
				EditorState.techUpgrades.remove(selectedTU);
				selectedTU = null;
				
				for(int i = 0; i < EditorState.beamGates.size(); i++) {
					if(selectedKS != null) {
						for(SpecialBuilding keysector : EditorState.beamGates.get(i).getKeysectors()) {
							if(keysector == selectedKS) {
								EditorState.beamGates.get(i).removeKeysector(selectedKS);
								selectedKS = null;
								break;
							}
						}
					}
				}
				
				for(int i = 0; i < EditorState.bombs.size(); i++) {
					if(selectedReactor != null) {
						for(SpecialBuilding reactor : EditorState.bombs.get(i).getReactors()) {
							if(reactor == selectedReactor) {
								EditorState.bombs.get(i).removeReactor(selectedReactor);
								selectedReactor = null;
								break;
							}
						}
					}
				}
				
				bgList.clear();
				bgKeySector.removeAll();
				for(int i = 0; i < EditorState.beamGates.size(); i++) {
					bgList.add(new JMenuItem("Beam Gate " + (i+1) ));
					bgKeySector.add(bgList.get(i));
					bgList.get(i).addActionListener(this);
				}
				
				bombList.clear();
				bombReactors.removeAll();
				for(int i = 0; i < EditorState.bombs.size(); i++) {
					bombList.add(new JMenuItem("Stoudson Bomb " + (i+1) ));
					bombReactors.add(bombList.get(i));
					bombList.get(i).addActionListener(this);
				}
				
				mainwindow.makeUnsaved();
				mainwindow.repaint();
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			sectorOwnerKey = -1;
			if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
				ctrlDown = false;
			}
		}
		
		public void HandleSelection(int clickX, int clickY) {
			if(!ctrlDown) {
				diffX = clickX;
				diffY = clickY;
				borderSelectedSectors.clear();
				for(Unit check : EditorState.hostStations) {
					if((clickX > check.getUnitX()) && (clickX < (check.getUnitX() + (int)(EditorState.sectorSize * 0.5))) && (clickY > check.getUnitY()) && (clickY < (check.getUnitY() + (int)(EditorState.sectorSize * 0.5))) ) {
						selected = check;
						break;
					}else selected = null;
				}
				if(EditorState.hostStations.size() == 0) selected = null;
				if(selected == null) {
					for(Unit check : EditorState.predefinedSquads) {
						if((clickX > check.getUnitX()) && (clickX < (check.getUnitX() + (int)(EditorState.sectorSize * 0.14))) && (clickY > check.getUnitY()) && (clickY < (check.getUnitY() + (int)(EditorState.sectorSize * 0.14))) ) {
							selected = check;
							break;
						}else selected = null;
					}
				} 
				
				hSize = 0;
				vSize = 0;
				borderSectorCounter = 0;
				sectorCounter = 0;
				for(int i = 0; i < verticalGrid + 2; i++) {
					for(int j = 0; j < horizontalGrid + 2; j++) {
						if(clickX > hSize && clickX < (hSize + EditorState.sectorSize) && clickY > vSize && clickY < (vSize + EditorState.sectorSize) ) {
								selectedSector = sectorCounter;
								borderSelectedSector = borderSectorCounter;
								borderSelectedSectors.add(borderSelectedSector);
							break;
						}else if(clickX > (horizontalGrid + 2) *(EditorState.sectorSize + EditorState.sectorIndent) || clickY > (verticalGrid + 2) *(EditorState.sectorSize + EditorState.sectorIndent)){
							borderSelectedSector = -1;
							borderSelectedSectors.clear();
							break;
						}
						if(hSize >= leftCorner && hSize <= rightCorner && vSize >= topCorner && vSize <= downCorner) {
							sectorCounter++;
							borderSectorCounter++;
						}else borderSectorCounter++;
						
						hSize += EditorState.sectorSize;
						hSize += EditorState.sectorIndent;
					}
	
					hSize = 0;
					vSize += EditorState.sectorSize;
					vSize += EditorState.sectorIndent;
				}
				
				for(int i = 0,x = 1,y = 1;i <= selectedSector; i++) {
					if(i == selectedSector) {
							selectedSectorX = x;
							selectedSectorY = y;
						}
					x++;
					if(x > horizontalGrid) {
						x = 1;
						y++;
					}
				}
				selectedKS = null;
				for(BeamGate bg : EditorState.beamGates) {
					if(bg.getX() == selectedSectorX && bg.getY() == selectedSectorY) {
						selectedBG = bg;
						break;
					}
					else selectedBG = null;
					if(selectedKS == null) {
						for(SpecialBuilding keysector : bg.getKeysectors()) {
							if(keysector.getX() == selectedSectorX && keysector.getY() == selectedSectorY) {
								selectedKS = keysector;
								break;
							}
						}
					}
	
				}
				selectedBomb = null;
				for(StoudsonBomb bomb : EditorState.bombs) {
					if(bomb.getX() == selectedSectorX && bomb.getY() == selectedSectorY) {
						selectedBomb = bomb;
						break;
					}
					else selectedReactor = null;
					if(selectedReactor == null) {
						for(SpecialBuilding reactor : bomb.getReactors()) {
							if(reactor.getX() == selectedSectorX && reactor.getY() == selectedSectorY) {
								selectedReactor = reactor;
								break;
							}
						}
					}
				}
				selectedTU = null;
				for(TechUpgrade tu : EditorState.techUpgrades) {
					if(tu.getX() == selectedSectorX && tu.getY() == selectedSectorY) {
						selectedTU = tu;
						break;
					}
				}
				mainwindow.cleanManager();
				if(selected instanceof Hoststation) mainwindow.updateManagerUnit(selected, EditorState.hostStations.indexOf(selected));
				else if(selected instanceof Squad) mainwindow.updateManagerUnit(selected, EditorState.predefinedSquads.indexOf(selected));
				else mainwindow.updateManagerUnit(null,-1);
				mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
				mainwindow.repaint();
			} else {
				hSize = 0;
				vSize = 0;
				borderSectorCounter = 0;
				sectorCounter = 0;
				for(int i = 0; i < verticalGrid + 2; i++) {
					for(int j = 0; j < horizontalGrid + 2; j++) {
						if(clickX > hSize && clickX < (hSize + EditorState.sectorSize) && clickY > vSize && clickY < (vSize + EditorState.sectorSize) ) {
								selectedSector = sectorCounter;
								borderSelectedSector = borderSectorCounter;
								if(!borderSelectedSectors.contains(borderSelectedSector))
									borderSelectedSectors.add(borderSelectedSector);
							break;
						}else if(clickX > (horizontalGrid + 2) *(EditorState.sectorSize + EditorState.sectorIndent) || clickY > (verticalGrid + 2) *(EditorState.sectorSize + EditorState.sectorIndent)){
							borderSelectedSector = -1;
							break;
						}
						if(hSize >= leftCorner && hSize <= rightCorner && vSize >= topCorner && vSize <= downCorner) {
							sectorCounter++;
							borderSectorCounter++;
						}else borderSectorCounter++;
						
						hSize += EditorState.sectorSize;
						hSize += EditorState.sectorIndent;
					}
	
					hSize = 0;
					vSize += EditorState.sectorSize;
					vSize += EditorState.sectorIndent;
				}
				mainwindow.cleanManager();
				mainwindow.updateManagerSector(borderSelectedSectors, horizontalGrid, verticalGrid);
				mainwindow.repaint();
			}
		}
	}// TODO EditorMouseListener ends here
	private void CreateHoststation(int x, int y, int own, int vehicle) {
		Hoststation newHostStation = new Hoststation(x, y, own, vehicle);
		if(newHostStation.getUnitX()  < leftCorner) {
			newHostStation.setUnitX(leftCorner - (int)(EditorState.sectorSize * 0.5/ 2));
		}
		if(newHostStation.getUnitX()  > rightCorner) {
			newHostStation.setUnitX(rightCorner - (int)(EditorState.sectorSize * 0.5/ 2));
		}
		if(newHostStation.getUnitY()  < topCorner) {
			newHostStation.setUnitY(topCorner - (int)(EditorState.sectorSize * 0.5/ 2));
		}
		if(newHostStation.getUnitY()  > downCorner) {
			newHostStation.setUnitY(downCorner - (int)(EditorState.sectorSize * 0.5/ 2));
		}
		selected = newHostStation;
		EditorState.hostStations.add(newHostStation);
		mainwindow.updateManagerUnit(selected, EditorState.hostStations.size() - 1);
		mainwindow.showManager(0);
		mainwindow.makeUnsaved();
		mainwindow.repaint();
	}
	private void CreateSquad(int x, int y, int own, int vehicle) {
		Squad newSquad = new Squad(x, y, own, vehicle);

		if(newSquad.getUnitX()  < leftCorner) {
			newSquad.setUnitX(leftCorner - (int)(EditorState.sectorSize * 0.14/ 2));
		}
		if(newSquad.getUnitX()  > rightCorner) {
			newSquad.setUnitX(rightCorner - (int)(EditorState.sectorSize * 0.14/ 2));
		}
		if(newSquad.getUnitY()  < topCorner) {
			newSquad.setUnitY(topCorner - (int)(EditorState.sectorSize * 0.14/ 2));
		}
		if(newSquad.getUnitY()  > downCorner) {
			newSquad.setUnitY(downCorner - (int)(EditorState.sectorSize * 0.14/ 2));
		}
		selected = newSquad;
		EditorState.predefinedSquads.add(newSquad);
		mainwindow.updateManagerUnit(selected, EditorState.predefinedSquads.size() - 1);
		mainwindow.makeUnsaved();
		mainwindow.repaint();
	}
	private void AddSpecialBuilding(int typ, int own, int blg) {
		if(selectedBG != null) {
			EditorState.beamGates.remove(selectedBG);
			selectedBG = null;
		}
		if(selectedBomb != null) {
			EditorState.bombs.remove(selectedBomb);
			selectedBomb = null;
		}
		if(selectedTU != null) {
			EditorState.techUpgrades.remove(selectedTU);
			selectedTU = null;
		}
		EditorState.typ_map.set(selectedSector, typ);
		EditorState.own_map.set(selectedSector, own);
		EditorState.blg_map.set(selectedSector, blg);
		mainwindow.makeUnsaved();
		mainwindow.repaint();
		mainwindow.cleanManager();
		mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
	}
	
	public void previousTypMap() {
		if(horizontalGrid * verticalGrid > selectedSector) {
			temp = EditorState.typ_map.get(selectedSector);
			temp--;
			if(temp < 0) temp = 255;
			if(EditorState.set == 1) {
				if(temp > 53 && temp < 59) temp = 53;
				if(temp > 59 && temp < 66) temp = 59;
				if(temp > 82 && temp < 95) temp = 82;
				if(temp > 104 && temp < 110) temp = 104;
				if(temp > 113 && temp < 120) temp = 113;
				if(temp > 121 && temp < 130) temp = 121;
				if(temp > 141 && temp < 150) temp = 141;
				if(temp > 189 && temp < 198) temp = 189;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 228) temp = 208;
				if(temp > 236 && temp < 239) temp = 236;
			}else if(EditorState.set == 2) {
				if(temp > 24 && temp < 27) temp = 24;
				if(temp > 104 && temp < 110) temp = 104;
				if(temp > 113 && temp < 118) temp = 113;
				if(temp > 131 && temp < 133) temp = 131;
				if(temp > 133 && temp < 150) temp = 133;
				if(temp > 195 && temp < 198) temp = 195;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 210) temp = 208;
				if(temp > 225 && temp < 228) temp = 225;
				if(temp > 230 && temp < 239)  temp = 230;
			}else if(EditorState.set == 3) {
				if(temp > 49 && temp < 59) temp = 49;
				if(temp > 59 && temp < 66) temp = 59;
				if(temp > 82 && temp < 100) temp = 82;
				if(temp > 104 && temp < 110) temp = 104;
				if(temp > 113 && temp < 121) temp = 113;
				if(temp > 121 && temp < 130) temp = 121;
				if(temp > 141 && temp < 150) temp = 141;
				if(temp > 189 && temp < 198) temp = 189;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 228) temp = 208;
				if(temp > 230 && temp < 239) temp = 230;
			}else if(EditorState.set == 4) {
				if(temp > 49 && temp < 59) temp = 49;
				if(temp > 60 && temp < 66) temp = 60;
				if(temp > 82 && temp < 100) temp = 82;
				if(temp > 104 && temp < 110) temp = 104;
				if(temp > 113 && temp < 121) temp = 113;
				if(temp > 121 && temp < 130) temp = 121;
				if(temp > 141 && temp < 150) temp = 141;
				if(temp > 189 && temp < 198) temp = 189;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 228) temp = 208;
				if(temp > 230 && temp < 239) temp = 230;
			}else if(EditorState.set == 5) {
				if(temp > 95 && temp < 97) temp = 95;
				if(temp > 116 && temp < 118) temp = 116;
				if(temp > 131 && temp < 133) temp = 131;
				if(temp > 137 && temp < 150) temp = 137;
				if(temp > 191 && temp < 198) temp = 191;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 210) temp = 208;
				if(temp > 225 && temp < 228) temp = 225;
				if(temp > 230 && temp < 239) temp = 230;
			}else if(EditorState.set == 6) {
				if(temp > 49 && temp < 59) temp = 49;
				if(temp > 59 && temp < 66) temp = 59;
				if(temp > 82 && temp < 95) temp = 82;
				if(temp > 104 && temp < 110) temp = 104;
				if(temp > 113 && temp < 121) temp = 113;
				if(temp > 121 && temp < 130) temp = 121;
				if(temp > 141 && temp < 150) temp = 141;
				if(temp > 189 && temp < 198) temp = 189;
				if(temp > 205 && temp < 207) temp = 205;
				if(temp > 208 && temp < 228) temp = 208;
				if(temp > 235 && temp < 239) temp = 235;
			}
			EditorState.typ_map.set(selectedSector, temp);
			mainwindow.cleanManager();
			mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			mainwindow.makeUnsaved();
			mainwindow.repaint();
		}
	}
	
	public void nextTypMap() { 
		if(horizontalGrid * verticalGrid > selectedSector) {
			temp = EditorState.typ_map.get(selectedSector);
			temp++;
			if(EditorState.set == 1) {
				if(temp > 53 && temp < 59) temp = 59;
				if(temp > 59 && temp < 66) temp = 66;
				if(temp > 82 && temp < 95) temp = 95;
				if(temp > 104 && temp < 110) temp = 110;
				if(temp > 113 && temp < 120) temp = 120;
				if(temp > 121 && temp < 130) temp = 130;
				if(temp > 141 && temp < 150) temp = 150;
				if(temp > 189 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 228) temp = 228;
				if(temp > 236 && temp < 239) temp = 239;
			}else if(EditorState.set == 2) {
				if(temp > 24 && temp < 27) temp = 27;
				if(temp > 104 && temp < 110) temp = 110;
				if(temp > 113 && temp < 118) temp = 118;
				if(temp > 131 && temp < 133) temp = 133;
				if(temp > 133 && temp < 150) temp = 150;
				if(temp > 195 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 210) temp = 210;
				if(temp > 225 && temp < 228) temp = 228;
				if(temp > 230 && temp < 239)  temp = 239;
			}else if(EditorState.set == 3) {
				if(temp > 49 && temp < 59) temp = 59;
				if(temp > 59 && temp < 66) temp = 66;
				if(temp > 82 && temp < 100) temp = 100;
				if(temp > 104 && temp < 110) temp = 110;
				if(temp > 113 && temp < 121) temp = 121;
				if(temp > 121 && temp < 130) temp = 130;
				if(temp > 141 && temp < 150) temp = 150;
				if(temp > 189 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 228) temp = 228;
				if(temp > 230 && temp < 239) temp = 239;
			}else if(EditorState.set == 4) {
				if(temp > 49 && temp < 59) temp = 59;
				if(temp > 60 && temp < 66) temp = 66;
				if(temp > 82 && temp < 100) temp = 100;
				if(temp > 104 && temp < 110) temp = 110;
				if(temp > 113 && temp < 121) temp = 121;
				if(temp > 121 && temp < 130) temp = 130;
				if(temp > 141 && temp < 150) temp = 150;
				if(temp > 189 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 228) temp = 228;
				if(temp > 230 && temp < 239) temp = 239;
			}else if(EditorState.set == 5) {
				if(temp > 95 && temp < 97) temp = 97;
				if(temp > 116 && temp < 118) temp = 118;
				if(temp > 131 && temp < 133) temp = 133;
				if(temp > 137 && temp < 150) temp = 150;
				if(temp > 191 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 210) temp = 210;
				if(temp > 225 && temp < 228) temp = 228;
				if(temp > 230 && temp < 239) temp = 239;
			}else if(EditorState.set == 6) {
				if(temp > 49 && temp < 59) temp = 59;
				if(temp > 59 && temp < 66) temp = 66;
				if(temp > 82 && temp < 95) temp = 95;
				if(temp > 104 && temp < 110) temp = 110;
				if(temp > 113 && temp < 121) temp = 121;
				if(temp > 121 && temp < 130) temp = 130;
				if(temp > 141 && temp < 150) temp = 150;
				if(temp > 189 && temp < 198) temp = 198;
				if(temp > 205 && temp < 207) temp = 207;
				if(temp > 208 && temp < 228) temp = 228;
				if(temp > 235 && temp < 239) temp = 239;
			}
			if(temp > 255) temp = 0;
			EditorState.typ_map.set(selectedSector, temp);
			mainwindow.cleanManager();
			mainwindow.updateManagerSector(borderSelectedSector, selectedSector, horizontalGrid, verticalGrid);
			mainwindow.makeUnsaved();
			mainwindow.repaint();
		}
	}
	public BufferedImage resize(int newW, int newH, BufferedImage img) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    img = dimg;
	    return img;
	}  
	public ImageIcon resizeIcon(int newW, int newH, BufferedImage img) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    img = dimg;
	    ImageIcon ico = new ImageIcon(img);
	    return ico;
	}  
	
	public void makeUnsaved() {
		mainwindow.makeUnsaved();
	}
	
	public void initAddUnits(){
		try {
			for(UAitem unit : UAdata.resUnits) {
				resUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				resUnitsMenu.get(resUnitsMenu.size()-1).addActionListener(mouselisten);
				resUnits.add(resUnitsMenu.get(resUnitsMenu.size()-1));
			}
			addSquad.add(resUnits);
			for(UAitem unit : UAdata.ghorUnits) {
				ghorUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				ghorUnitsMenu.get(ghorUnitsMenu.size()-1).addActionListener(mouselisten);
				ghorUnits.add(ghorUnitsMenu.get(ghorUnitsMenu.size()-1));
			}
			addSquad.add(ghorUnits);
			for(UAitem unit : UAdata.taerUnits) {
				taerUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				taerUnitsMenu.get(taerUnitsMenu.size()-1).addActionListener(mouselisten);
				taerUnits.add(taerUnitsMenu.get(taerUnitsMenu.size()-1));
			}
			addSquad.add(taerUnits);
			for(UAitem unit : UAdata.mykoUnits) {
				mykoUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				mykoUnitsMenu.get(mykoUnitsMenu.size()-1).addActionListener(mouselisten);
				mykoUnits.add(mykoUnitsMenu.get(mykoUnitsMenu.size()-1));
			}
			addSquad.add(mykoUnits);
			for(UAitem unit : UAdata.sulgUnits) {
				sulgUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				sulgUnitsMenu.get(sulgUnitsMenu.size()-1).addActionListener(mouselisten);
				sulgUnits.add(sulgUnitsMenu.get(sulgUnitsMenu.size()-1));
			}
			addSquad.add(sulgUnits);
			for(UAitem unit : UAdata.trainingUnits) {
				trainingUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				trainingUnitsMenu.get(trainingUnitsMenu.size()-1).addActionListener(mouselisten);
				trainUnits.add(trainingUnitsMenu.get(trainingUnitsMenu.size()-1));
			}
			addSquad.add(trainUnits);
			for(UAitem unit : UAdata.specialUnits) {
				specialUnitsMenu.add(new JMenuItem(unit.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				specialUnitsMenu.get(specialUnitsMenu.size()-1).addActionListener(mouselisten);
				specialUnit.add(specialUnitsMenu.get(specialUnitsMenu.size()-1));
			}
			addSquad.add(specialUnit);
		}catch(FileNotFoundException ex) {
			System.out.println("Icon image for special building not found");
		}catch(IOException ex) {
			System.out.println("An error occured while loading an image icon for special building");
		}
	}
	
	public void removeAddUnits() {
		resUnits.removeAll();
		ghorUnits.removeAll();
		taerUnits.removeAll();
		mykoUnits.removeAll();
		sulgUnits.removeAll();
		trainUnits.removeAll();
		specialUnit.removeAll();
	}
	
	public void initAddBuildings() {
		try {
			for(UAitem building : UAdata.resBuildings) {
				resBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				resBuildingsMenu.get(resBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(resBuildingsMenu.get(resBuildingsMenu.size()-1));
			}
			for(UAitem building : UAdata.ghorBuildings) {
				ghorBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				ghorBuildingsMenu.get(ghorBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(ghorBuildingsMenu.get(ghorBuildingsMenu.size()-1));
			}
			for(UAitem building : UAdata.taerBuildings) {
				taerBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				taerBuildingsMenu.get(taerBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(taerBuildingsMenu.get(taerBuildingsMenu.size()-1));
			}
			for(UAitem building : UAdata.mykoBuildings) {
				mykoBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				mykoBuildingsMenu.get(mykoBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(mykoBuildingsMenu.get(mykoBuildingsMenu.size()-1));
			}
			for(UAitem building : UAdata.blackSectBuildings) {
				blackSectBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				blackSectBuildingsMenu.get(blackSectBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(blackSectBuildingsMenu.get(blackSectBuildingsMenu.size()-1));
			}
			for(UAitem building : UAdata.miscBuildings) {
				miscBuildingsMenu.add(new JMenuItem(building.getName(), new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				miscBuildingsMenu.get(miscBuildingsMenu.size()-1).addActionListener(mouselisten);
				specialBuilding.add(miscBuildingsMenu.get(miscBuildingsMenu.size()-1));
			}
		}catch(IOException ex) {
			System.out.println("An error occured during reading an image for addBuilding ItemMenu");
		}
	}
	
	public void resizeBuildingImg() {
		for(UAitem building : UAdata.allBuildings) {
			building.resizeImg(EditorState.sectorSize, EditorState.sectorSize);
		}
		for(UAitem building : UAdata.fallbackBuildings) {
			building.resizeImg(EditorState.sectorSize, EditorState.sectorSize);
		}
	}
	
	public void removeSpecialBuildings() {
		specialBuilding.removeAll();
	}
	
}

