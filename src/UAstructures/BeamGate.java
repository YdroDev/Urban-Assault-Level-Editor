package UAstructures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BeamGate {
	private int openedType;
	private int closedType;
	private int sec_x;
	private int sec_y;
	private ArrayList<SpecialBuilding> keysectors;
	private ArrayList<Integer> target_level;
	private BufferedImage beamgate;
	private BufferedImage keysector;
	private boolean mb_status;
	
	public BeamGate(int x, int y){
		this.sec_x = x;
		this.sec_y = y;
		this.openedType = 1; // 1 with road, 2 without road
		this.closedType = 1; // 1 with road, 2 without road
		this.mb_status = true;
		this.keysectors = new ArrayList<SpecialBuilding>();
		this.target_level = new ArrayList<Integer>();
		try {
			this.beamgate = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/beamgate.png"));
			this.keysector = ImageIO.read(this.getClass().getResourceAsStream("/img/sectorItems/keysector.png"));
		}catch(IOException ex) {
			System.out.println("Error while loading beam gate image: "+ ex );
		}
	}
	
	// getters
	public int getOpenedType() {
		return this.openedType;
	}
	public int getClosedType() {
		return this.closedType;
	}
	public int getX() {
		return this.sec_x;
	}
	public int getY() {
		return this.sec_y;
	}
	public boolean getVisibility() {
		return this.mb_status;
	}
	public ArrayList<SpecialBuilding> getKeysectors() {
		return this.keysectors;
	}
	public ArrayList<Integer> getTargetLevel(){
		return this.target_level;
	}
	public BufferedImage getImage() {
		return this.beamgate;
	}
	// setters
	public void setOpenedType(int t) {
		this.openedType = t;
	}
	public void setClosedType(int t) {
		this.closedType = t;
	}
	public void setX(int x) {
		this.sec_x = x;
	}
	public void setY(int y) {
		this.sec_y = y;
	}
	public void setVisibility(boolean m) {
		this.mb_status = m;
	}
	
	public void addKeysector(int x, int y){
		this.keysectors.add(new SpecialBuilding(x, y, keysector));
	}
	public void removeKeysector(SpecialBuilding ks) {
		this.keysectors.remove(ks);
	}
	
	public void resize(int newW, int newH) { 
	    Image tmp = this.beamgate.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    this.beamgate = dimg;

	}  

}
