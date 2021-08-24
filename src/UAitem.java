import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UAitem { 

	private int id;
	private String name;
	private String iconName;
	private int typMapID;
	private BufferedImage img;
	private int mapIcon;
	
	UAitem(){
		this.name = "";
		this.id = 0;
		this.iconName = "";
		this.typMapID = 0;
		this.img = null;
		this.mapIcon = 0;
	}
	
	public UAitem setID(int id) {
		this.id = id;
		return this;
	}
	
	public UAitem setName(String name) {
		this.name = name;
		return this;
	}
	
	public UAitem setIconName(String name) {
		this.iconName = name;
		return this;
	}

	public UAitem setTypMapID(int id) {
		this.typMapID = id;
		return this;
	}
	
	public UAitem setImg(String url) {
		try {
			this.img = ImageIO.read(this.getClass().getResourceAsStream(url));
		}catch(IOException ex) {
			System.out.println("An error occured during image opening process");
			System.out.println(ex);
		}
		return this;
	}

	public UAitem setMapIcon(int i) {
		this.mapIcon = i;
		return this;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getIconName() {
		return this.iconName;
	}
	
	public int getTypMapID() {
		return this.typMapID;
	}
	public BufferedImage getImg() {
		return this.img;
	}

	public int getMapIcon() { return this.mapIcon; }
	
	public UAitem resizeImg(int newW, int newH) { 
	    Image tmp = this.img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    this.img = dimg;
	    
	    return this;
	}
}
