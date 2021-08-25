package main;

public class Hoststation extends Unit {
	
	private int height; // exchange to -pos_y
	private int energy;
	private int view_angle;
	private int reload_const;
	private int con_budget;
	private int con_delay;
	private int def_budget;
	private int def_delay;
	private int rec_budget;
	private int rec_delay;
	private int rob_budget;
	private int rob_delay;
	private int pow_budget;
	private int pow_delay;
	private int rad_budget;
	private int rad_delay;
	private int saf_budget;
	private int saf_delay;
	private int cpl_budget;
	private int cpl_delay;
	
	Hoststation(int X, int Y, int own,int vehicle) {// Y is -Z in ldf file
		super(X, Y, own, vehicle);
		this.height = 300; 
		this.energy = 300000;
		this.view_angle = 0;
		this.reload_const = 0;
		this.con_budget = 30;
		this.con_delay = 0;
		this.def_budget = 90;
		this.def_delay = 0;
		this.rec_budget = 80;
		this.rec_delay = 0;
		this.rob_budget = 100;
		this.rob_delay = 0;
		this.pow_budget = 50;
		this.pow_delay = 0;
		this.rad_budget = 30;
		this.rad_delay = 0;
		this.saf_budget = 50;
		this.saf_delay = 0;
		this.cpl_budget = 100;
		this.cpl_delay = 0;
	}
	//getters
	public int getHeight(){
		return this.height;
	}
	public int getEnergy() {
		return this.energy/400;
	}
	public int getRawEnergy() {
		return this.energy;
	}
	public int getViewAngle() {
		return this.view_angle;
	}
	public int getReloadConst() {
		return this.reload_const;
	}
	public int getConBudget() {
		return this.con_budget;
	}
	public int getConDelay() {
		return this.con_delay / 1000;
	}
	public int getRawConDelay() {
		return this.con_delay;
	}
	public int getDefBudget() {
		return this.def_budget;
	}
	public int getDefDelay() {
		return this.def_delay / 1000;
	}
	public int getRawDefDelay() {
		return this.def_delay;
	}
	public int getRecBudget() {
		return this.rec_budget;
	}
	public int getRecDelay() {
		return this.rec_delay / 1000;
	}
	public int getRawRecDelay() {
		return this.rec_delay;
	}
	public int getRobBudget() {
		return this.rob_budget;
	}
	public int getRobDelay() {
		return this.rob_delay / 1000;
	}
	public int getRawRobDelay() {
		return this.rob_delay;
	}
	public int getPowBudget() {
		return this.pow_budget;
	}
	public int getPowDelay() {
		return this.pow_delay / 1000;
	}
	public int getRawPowDelay() {
		return this.pow_delay;
	}
	public int getRadBudget() {
		return this.rad_budget;
	}
	public int getRadDelay() {
		return this.rad_delay / 1000;
	}
	public int getRawRadDelay() {
		return this.rad_delay;
	}
	public int getSafBudget() {
		return this.saf_budget;
	}
	public int getSafDelay() {
		return this.saf_delay / 1000;
	}
	public int getRawSafDelay() {
		return this.saf_delay;
	}
	public int getCplBudget() {
		return this.cpl_budget;
	}
	public int getCplDelay() {
		return this.cpl_delay / 1000;
	}
	public int getRawCplDelay() {
		return this.cpl_delay;
	}
	//setters
	public void setHeight(int h) {
		this.height = h;
	}
	public void setEnergy(int e) {
		if(e>0) this.energy = e*400;
	}
	public void setRawEnergy(int e) {
		this.energy = e;
	}
	public void setViewAngle(int v) {
		this.view_angle = v;
	}
	public void setReloadConst(int r) {
		this.reload_const = r;
	}
	public void setConBudget(int n) {
		this.con_budget = n;
	}
	public void setConDelay(int n) {
		this.con_delay = n * 1000;
	}
	public void setRawConDelay(int n) {
		this.con_delay = n;
	}
	public void setDefBudget(int n) {
		this.def_budget = n;
	}
	public void setDefDelay(int n) {
		this.def_delay = n * 1000;
	}
	public void setRawDefDelay(int n) {
		this.def_delay = n;
	}
	public void setRecBudget(int n) {
		this.rec_budget = n;
	}
	public void setRecDelay(int n) {
		this.rec_delay = n * 1000;
	}
	public void setRawRecDelay(int n) {
		this.rec_delay = n;
	}
	public void setRobBudget(int n) {
		this.rob_budget = n;
	}
	public void setRobDelay(int n) {
		this.rob_delay = n * 1000;
	}
	public void setRawRobDelay(int n) {
		this.rob_delay = n;
	}
	public void setPowBudget(int n) {
		this.pow_budget = n;
	}
	public void setPowDelay(int n) {
		this.pow_delay = n * 1000;
	}
	public void setRawPowDelay(int n) {
		this.pow_delay = n;
	}
	public void setRadBudget(int n) {
		this.rad_budget = n;
	}
	public void setRadDelay(int n) {
		this.rad_delay = n * 1000;
	}
	public void setRawRadDelay(int n) {
		this.rad_delay = n;
	}
	public void setSafBudget(int n) {
		this.saf_budget = n;
	}
	public void setSafDelay(int n) {
		this.saf_delay = n * 1000;
	}
	public void setRawSafDelay(int n) {
		this.saf_delay = n;
	}
	public void setCplBudget(int n) {
		this.cpl_budget = n;
	}
	public void setCplDelay(int n) {
		this.cpl_delay = n * 1000;
	}
	public void setRawCplDelay(int n) {
		this.cpl_delay = n;
	}
}

