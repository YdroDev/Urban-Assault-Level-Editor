package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class CheckList {
	
	private JPanel group;
	private Border border;
	private JPanel box;
	private GridBagConstraints enablerConstraints;
	
	JSeparator separator;
	
	private ArrayList<JPanel> units;
	private ArrayList<JCheckBox> unitCheckBoxes;
	
	private ArrayList<JPanel> buildings;
	private ArrayList<JCheckBox> buildingCheckBoxes;
	
	CheckList(String hostStation){
		border = BorderFactory.createTitledBorder(hostStation);
		group = new JPanel();
		group.setBorder(border);
		group.setLayout(new GridBagLayout());
		box = new JPanel(new GridBagLayout());
		enablerConstraints = new GridBagConstraints();
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(150,3));
		
		units = new ArrayList<JPanel>();
		unitCheckBoxes = new ArrayList<JCheckBox>();
		
		buildings = new ArrayList<JPanel>();
		buildingCheckBoxes = new ArrayList<JCheckBox>();
	}
	
	public void addUnits(ArrayList<UAitem> hsUnits) {
		for(UAitem unit : hsUnits) {
			units.add(new JPanel(new GridBagLayout())); 
			unitCheckBoxes.add(new JCheckBox(unit.getName()));
			try {
				units.get(units.size()-1).add(new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/img/icons/"+unit.getIconName()+".png")))));
				units.get(units.size()-1).add(unitCheckBoxes.get(unitCheckBoxes.size()-1));
			}catch(FileNotFoundException ex) {
				System.out.println("Icon image for unit in enabler not found");
			}catch(IOException ex) {
				System.out.println("An error occured while loading the image icon for unit in enabler");
			}
		}
	}
	
	public void addBuildings(ArrayList<UAitem> hsBuildings) {
		for(UAitem building : hsBuildings) {
			buildings.add(new JPanel(new GridBagLayout()));
			buildingCheckBoxes.add(new JCheckBox(building.getName()));
			try {
				buildings.get(buildings.size()-1).add(new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/img/icons/"+building.getIconName()+".png")))));
				buildings.get(buildings.size()-1).add(buildingCheckBoxes.get(buildingCheckBoxes.size()-1));
			}catch(FileNotFoundException ex) {
				System.out.println("Icon image for building in enabler not found");
			}catch(IOException ex) {
				System.out.println("An error occured while loading an image icon for building in enabler");
			}
		}
	}
	
	public void makeList() {
		enablerConstraints.gridx = 0;
		enablerConstraints.anchor = GridBagConstraints.WEST;
		int gridy = 0;
		for(int i = 0; i < units.size(); i++, gridy++) {
			enablerConstraints.gridy = gridy;
			box.add(units.get(i), enablerConstraints);
		}

		if(units.size() > 0 && buildings.size() > 0) {
			enablerConstraints.gridy = gridy;
			box.add(separator, enablerConstraints);
			gridy++;
		}

		for(int j = 0; j < buildings.size(); j++, gridy++) {
			enablerConstraints.gridy = gridy;
			box.add(buildings.get(j), enablerConstraints);
		}
		
		group.add(box);
		enablerConstraints.anchor = GridBagConstraints.CENTER;
	}
	
	public JPanel getGroup() {
		return group;
	}
	
	public ArrayList<JCheckBox> getUnitCheckBoxes() {
		return unitCheckBoxes;
	}
	
	public ArrayList<JCheckBox> getBuildingCheckBoxes() {
		return buildingCheckBoxes;
	}
	
	public void toggleUnitCheckBoxes(ArrayList<UAitem> hsUnits, boolean state) {
		for(int i = 0; i < hsUnits.size(); i++) {
			unitCheckBoxes.get(i).setEnabled(state);
		}
	}
	
	public void toggleBuildingCheckBoxes(ArrayList<UAitem> hsBuildings, boolean state) {
		for(int i = 0; i < hsBuildings.size(); i++) {
			buildingCheckBoxes.get(i).setEnabled(state);
		}
	}
	
}
