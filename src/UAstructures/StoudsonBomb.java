package UAstructures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class StoudsonBomb {
	private int inactiveType;
	private int activeType;
	private int triggerType;
	private int sec_x;
	private int sec_y;
	private ArrayList<SpecialBuilding> reactors;
	private int countdown;
	private BufferedImage bomb;
	private BufferedImage reactor;
	
	public StoudsonBomb(int x, int y, int c){
		this.sec_x = x;
		this.sec_y = y;
		this.inactiveType = 35; // 68
		this.activeType = 36; // 69
		this.triggerType = 37; // 70
		this.reactors = new ArrayList<SpecialBuilding>();
		this.countdown = c;
		try {
			this.bomb = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/mainbomb.png"));
			this.reactor = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/sectorbomb.png"));
		}catch(IOException ex) {
			System.out.println("Error while loading stoudson bomb image: "+ ex );
		}
	}
	//getters
	public int getBombStyle() {
		if(this.inactiveType == 35) return 1;
		else if (this.inactiveType == 68) return 2;
		return 0;
	}
	public int getInactiveType() {
		return this.inactiveType;
	}
	public int getActiveType() {
		return this.activeType;
	}
	public int getTriggerType() {
		return this.triggerType;
	}
	public int getX() {
		return this.sec_x;
	}
	public int getY() {
		return this.sec_y;
	}
	public ArrayList<SpecialBuilding> getReactors() {
		return this.reactors;
	}
	public int getCountdown() {
		return this.countdown / 1024;
	}
	public int getRawCountdown() {
		return this.countdown;
	}
	public BufferedImage getImage() {
		return this.bomb;
	}
	//setters
	public void setBombStyle(int s) {
		if(s == 1) {
			this.inactiveType = 35;
			this.activeType = 36;
			this.triggerType = 37;
		}else if(s == 2) {
			this.inactiveType = 68;
			this.activeType = 69;
			this.triggerType = 70;
		}
	}
	public void setInactiveType(int t) {
		this.inactiveType = t;
	}
	public void setActiveType(int t) {
		this.activeType = t;
	}
	public void setTriggerType(int t) {
		this.triggerType = t;
	}
	public void setX(int x) {
		this.sec_x = x;
	}
	public void setY(int y) {
		this.sec_y = y;
	}
	public void addReactor(int x, int y){
		reactors.add(new SpecialBuilding(x, y, reactor));
	}
	public void removeReactor(SpecialBuilding r) {
		reactors.remove(r);
	}
	public void setCountdown(int c) {
		this.countdown = c * 1024;
		if(this.countdown < 0) this.countdown = Integer.MAX_VALUE;
	}
	
	public void resize(int newW, int newH) { 
	    Image tmp = this.bomb.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    this.bomb = dimg;
	}  
	
}
