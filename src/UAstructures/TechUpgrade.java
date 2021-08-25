package UAstructures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TechUpgrade {
	private int sec_x;
	private int sec_y;
	private int building;
	private int type; // sound output and stat blinking 
	private BufferedImage newVehicleSphinx;
	private BufferedImage upgradeFlak;
	private BufferedImage newVehicleSmall;
	private BufferedImage newVehicleHeavy;
	private BufferedImage moreWeapon;
	private BufferedImage upgradeWeapon;
	private BufferedImage upgradeShield;
	private BufferedImage newBuilding;
	private BufferedImage newVehicleTower;
	
	private ArrayList<Modify_vehicle> vehicles;
	private ArrayList<Modify_weapon> weapons;
	private ArrayList<Modify_building> buildings;
	private boolean mb_status;
	
	TechUpgrade(int x, int y){
		this.sec_x = x;
		this.sec_y = y;
		this.building = 4;
		this.type = -1;
		try {
			newVehicleSphinx = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgradesphinx.png"));
			upgradeFlak = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgrademoreroboflak.png"));
			newVehicleSmall = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgradenewvehiclesmall.png"));
			newVehicleHeavy = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgradenewvehicleheavy.png"));
			moreWeapon = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgrademoreweaponvehicle.png"));
			upgradeWeapon = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgrademorepowerweapon.png"));
			upgradeShield = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgrademoreshieldvehicle.png"));
			newBuilding = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgradenewbuilding.png"));
			newVehicleTower = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/techupgradenewvehicletower.png"));
		}catch(IOException ex) {
			System.out.println("An error occured while loading the tech upgrade image: " + ex);
		}
		this.mb_status = true;
		
		this.vehicles = new ArrayList<Modify_vehicle>();
		this.weapons = new ArrayList<Modify_weapon>();
		this.buildings = new ArrayList<Modify_building>();
	}
	//getters
	public int getX() {
		return this.sec_x;
	}
	public int getY() {
		return this.sec_y;
	}
	public int getBuilding() {
		return this.building;
	}
	public int getType() {
		return this.type;
	}
	public BufferedImage getImg() {
		if(this.building == 60) return newVehicleSphinx;
		if(this.building == 61) return upgradeFlak;
		if(this.building == 4) return newVehicleSmall;
		if(this.building == 7) return newVehicleHeavy;
		if(this.building == 15) return moreWeapon;
		if(this.building == 51) return upgradeWeapon;
		if(this.building == 50) return upgradeShield;
		if(this.building == 16) return newBuilding;
		if(this.building == 65) return newVehicleTower;

		return null;
	}
	public ArrayList<Modify_vehicle> getVehicleList(){
		return this.vehicles;
	}
	public ArrayList<Modify_weapon> getWeaponList(){
		return this.weapons;
	}
	public ArrayList<Modify_building> getBuildingList(){
		return this.buildings;
	}
	
	public boolean getVisibility() {
		return this.mb_status;
	}
	// setters
	public void setX(int x) {
		this.sec_x = x;
	}
	public void setY(int y) {
		this.sec_y = y;
	}
	public void setBuilding(int b) {
		this.building = b;
	}
	public void setType(int t) {
		this.type = t;
	}
	public void setVisibility(boolean state) {
		this.mb_status = state;
	}
	
	public void enableVehicle(int veh, int own) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			if(((int)(Math.random() * ((1 - 0) + 1)) + 0) == 1){
				this.building = 4;
			}else this.building = 7;
			this.type = 3;
		}

		boolean newVehicle = true;
		for(int i = 0; i < vehicles.size(); i++) {// protection against duplication
			if(vehicles.get(i).getVehicleID() == veh) {
				newVehicle = false;
				vehicles.get(i).setEnable(own, true);
			}
		}
		
		if(newVehicle == true) {
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
			vehicles.get(vehicles.size()-1).setEnable(own, true);
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
		}
		
	}

	public void addEnergy(int veh, int e) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 50;
			this.type = 2;
		}
		boolean newVehicle = true;
		for(int i = 0; i < vehicles.size(); i++) {// protection against duplication
			if(vehicles.get(i).getVehicleID() == veh) {
				newVehicle = false;
				vehicles.get(i).setEnergy(e);
			}
		}
		
		if(newVehicle == true) {
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
			vehicles.get(vehicles.size()-1).setEnergy(e);
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
		}
		
	}
	
	public void addShield(int veh, int sh) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 50;
			this.type = 2;
		}
		boolean newVehicle = true;
		for(int i = 0; i < vehicles.size(); i++) {// protection against duplication
			if(vehicles.get(i).getVehicleID() == veh) {
				newVehicle = false;
				vehicles.get(i).setShield(sh);
			}
		}
		
		if(newVehicle == true) {
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
			vehicles.get(vehicles.size()-1).setShield(sh);
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
		}
		
	}
	public void addRadar(int veh, int r) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 15;
			this.type = 5;
		}
		boolean newVehicle = true;
		for(int i = 0; i < vehicles.size(); i++) {// protection against duplication
			if(vehicles.get(i).getVehicleID() == veh) {
				newVehicle = false;
				vehicles.get(i).setRadar(r);
			}
		}
		
		if(newVehicle == true) {
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
			vehicles.get(vehicles.size()-1).setRadar(r);
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
		}
	}
	public void addWeapon(int veh, int q) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 15;
			this.type = 1;
		}
		boolean newVehicle = true;
		for(int i = 0; i < vehicles.size(); i++) {// protection against duplication
			if(vehicles.get(i).getVehicleID() == veh) {
				newVehicle = false;
				vehicles.get(i).setWeaponNum(q);
			}
		}
		
		if(newVehicle == true) {
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
			vehicles.get(vehicles.size()-1).setWeaponNum(q);
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
		}
	}
	
	public void addDamage(int veh, int e) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			if(veh == 90 || veh == 91 || veh == 92 || veh == 93 || veh == 83 || veh == 77) {
				this.building = 61;
			}else this.building = 51;
			this.type = 1;
		}
		boolean newWeapon = true;
		for(int i = 0; i < weapons.size(); i++) {// protection against duplication
			if(weapons.get(i).getWeaponID() == veh) {
				newWeapon = false;
				weapons.get(i).setEnergy(e);
			}
		}
		
		if(newWeapon == true) {
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
			weapons.get(weapons.size()-1).setEnergy(e);
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
		}
		
	}
	
	public void addShotTime(int veh, int n) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 51;
			this.type = 1;
		}
		boolean newWeapon = true;
		for(int i = 0; i < weapons.size(); i++) {// protection against duplication
			if(weapons.get(i).getWeaponID() == veh) {
				newWeapon = false;
				weapons.get(i).setShotTime(n);
			}
		}
		
		if(newWeapon == true) {
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
			weapons.get(weapons.size()-1).setShotTime(n);
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
		}
		
	}
	public void addShotTimeUser(int veh, int n) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 51;
			this.type = 1;
		}
		boolean newWeapon = true;
		for(int i = 0; i < weapons.size(); i++) {// protection against duplication
			if(weapons.get(i).getWeaponID() == veh) {
				newWeapon = false;
				weapons.get(i).setShotTimeUser(n);
			}
		}
		
		if(newWeapon == true) {
			weapons.add(new Modify_weapon());
			weapons.get(weapons.size()-1).setWeaponID(veh);
			weapons.get(weapons.size()-1).setShotTimeUser(n);
			vehicles.add(new Modify_vehicle());
			vehicles.get(vehicles.size()-1).setVehicleID(veh);
		}
		
	}
	
	public void enableBuilding(int build, int own) {
		if(vehicles.size() == 0 && weapons.size() == 0 && buildings.size() == 0) {
			this.building = 16;
			this.type = 4;
		}
		boolean newBuilding = true;
		for(int i = 0; i < buildings.size(); i++) {
			if(buildings.get(i).getBuildingID() == build) {
				newBuilding = false;
				buildings.get(i).setEnable(own, true);
			}
		}
		
		if(newBuilding == true) {
			buildings.add(new Modify_building());
			buildings.get(buildings.size()-1).setBuildingID(build);
			buildings.get(buildings.size()-1).setEnable(own, true);
		}
	}
	
	public void disableVehicle(int veh, int own) {
		for(int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getVehicleID() == veh) {
				vehicles.get(i).setEnable(own, false);
			}
		}
	}
	
	public void disableBuilding(int build, int own) {
		for(int i = 0; i < buildings.size(); i++) {
			if(buildings.get(i).getBuildingID() == build) {
				buildings.get(i).setEnable(own, false);
			}
		}
	}
	
	public class Modify_vehicle{
		private int vehicleID;
		private int energy;
		private int shield;
		private boolean resEnable;
		private boolean ghorEnable;
		private boolean taerEnable;
		private boolean mykoEnable;
		private boolean sulgEnable;
		private boolean blasecEnable;
		private boolean trainingEnable;
		private int radar;
		private int weaponNum;
		private double fire_x;
		private double fire_y;
		private double fire_z;
			
		Modify_vehicle(){
			this.vehicleID = 0;
			this.energy = 0;
			this.shield = 0;
			this.resEnable = false;
			this.ghorEnable = false;
			this.taerEnable = false;
			this.mykoEnable = false;
			this.sulgEnable = false;
			this.blasecEnable = false;
			this.trainingEnable = false;
			this.radar = 0;
			this.weaponNum = 0;
			this.fire_x = 30;
			this.fire_y = 5.0;
			this.fire_z = 15;
		}
		// getters
		public int getEnergy() {
			if(this.energy != 0) return this.energy/100;
			else return 0;
		}
		public int getRawEnergy() {
			return this.energy;
		}
		public int getShield() {
			return this.shield;
		}
		public int getVehicleID() {
			return this.vehicleID;
		}
		public boolean getEnable(int own) {
			if(own == 1) return this.resEnable;
			if(own == 6) return this.ghorEnable;
			if(own == 4) return this.taerEnable;
			if(own == 3) return this.mykoEnable;
			if(own == 2) return this.sulgEnable;
			if(own == 5) return this.blasecEnable;
			if(own == 7) return this.trainingEnable;
			return false;
		}
		public int getRadar() {
			return this.radar;
		}
		public int getWeaponNum() {
			return this.weaponNum;
		}
		public double getFireX() {
			return this.fire_x;
		}
		public double getFireY() {
			return this.fire_y;
		}
		public double getFireZ() {
			return this.fire_z;
		}
			
		// setters
		public void setEnergy(int e) {
			this.energy = e*100;
		}
		public void setRawEnergy(int e) {
			this.energy = e;
		}
		public void setShield(int e) {
			this.shield = e;
		}
		public void setVehicleID(int id) {
			this.vehicleID = id;
		}
		public void setEnable(int own, boolean state) {
			if(own == 1) this.resEnable = state;
			if(own == 6) this.ghorEnable = state;
			if(own == 4) this.taerEnable = state;
			if(own == 3) this.mykoEnable = state;
			if(own == 2) this.sulgEnable = state;
			if(own == 5) this.blasecEnable = state;
			if(own == 7) this.trainingEnable = state;
		}
		public void setRadar(int r) {
			this.radar = r;
		}
		public void setWeaponNum(int n) {
			this.weaponNum = n;
		}
		public void setFireX(int n) {
			this.fire_x = n;
		}
		public void setFireY(int n) {
			this.fire_y = n;
		}
		public void setFireZ(int n) {
			this.fire_z = n;
		}
			
	}// end Modify_vehicle
	public class Modify_weapon{
		private int weaponID;
		private int energy; // damage
		private int shot_time;
		private int shot_time_user;
			
		Modify_weapon(){
			this.weaponID = 0;
			this.energy = 0;
			this.shot_time = 0;
			this.shot_time_user = 0;
		}
		//getters
		public int getWeaponID() {
			return this.weaponID;
		}
		public int getEnergy() {
			if(this.energy != 0) return this.energy/100;
			else return 0;
		}
		public int getRawEnergy() {
			return this.energy;
		}
		public int getShotTime() {
			return this.shot_time;
		}
		public int getShotTimeUser() {
			return this.shot_time_user;
		}
		//setters
		public void setWeaponID(int w) {
			this.weaponID = w;
		}
		public void setEnergy(int e) {
			this.energy = e*100;
		}
		public void setRawEnergy(int e) {
			this.energy = e;
		}
		public void setShotTime(int n) {
			this.shot_time = n;
		}
		public void setShotTimeUser(int n) {
			this.shot_time_user = n;
		}
			
	}//end Modify_weapon
	public class Modify_building{
		private int buildingID;
		private boolean resEnable;
		private boolean ghorEnable;
		private boolean taerEnable;
		private boolean mykoEnable;
		private boolean sulgEnable;
		private boolean blasecEnable;
		private boolean trainingEnable;
			
		Modify_building(){
			this.buildingID = 0;
			this.resEnable = false;
			this.ghorEnable = false;
			this.taerEnable = false;
			this.mykoEnable = false;
			this.sulgEnable = false;
			this.blasecEnable = false;
			this.trainingEnable = false;
		}
		//getters
		public int getBuildingID() {
			return this.buildingID;
		}
		public boolean getEnable(int own) {
			if(own == 1) return this.resEnable;
			if(own == 6) return this.ghorEnable;
			if(own == 4) return this.taerEnable;
			if(own == 3) return this.mykoEnable;
			if(own == 2) return this.sulgEnable;
			if(own == 5) return this.blasecEnable;
			if(own == 7) return this.trainingEnable;
			return false;
		}
		//setters
		public void setBuildingID(int b) {
			this.buildingID = b;
		}
		public void setEnable(int own, boolean state) {
			if(own == 1) this.resEnable = state;
			if(own == 6) this.ghorEnable = state;
			if(own == 4) this.taerEnable = state;
			if(own == 3) this.mykoEnable = state;
			if(own == 2) this.sulgEnable = state;
			if(own == 5) this.blasecEnable = state;
			if(own == 7) this.trainingEnable = state;
		}
		
	}// end Modify_building
		
	public void resize(int newW, int newH) { 
		Image tmp;
		BufferedImage dimg;
		Graphics2D g2d;
		switch(building) {
			case 60:
				tmp = this.newVehicleSphinx.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.newVehicleSphinx = dimg;
			break;
			case 61:
				tmp = this.upgradeFlak.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.upgradeFlak = dimg;
			    break;
			case 4:
				tmp = this.newVehicleSmall.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.newVehicleSmall = dimg;
				break;
			case 7:
				tmp = this.newVehicleHeavy.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.newVehicleHeavy = dimg;
				break;
			case 15:
				tmp = this.moreWeapon.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.moreWeapon = dimg;
				break;
			case 51:
				tmp = this.upgradeWeapon.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.upgradeWeapon = dimg;
				break;
			case 50:
				tmp = this.upgradeShield.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.upgradeShield = dimg;
				break;
			case 16:
				tmp = this.newBuilding.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.newBuilding = dimg;
				break;
			case 65:
				tmp = this.newVehicleTower.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			    dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			    g2d = dimg.createGraphics();
			    g2d.drawImage(tmp, 0, 0, null);
			    g2d.dispose();
			    this.newVehicleTower = dimg;
				break;
		}

	}  
}
