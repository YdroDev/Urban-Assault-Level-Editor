import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpecialBuilding {
	private int keysec_x;
	private int keysec_y;
	private BufferedImage secImg;

	SpecialBuilding(int x, int y, BufferedImage img){
		this.keysec_x = x;
		this.keysec_y = y;
		this.secImg = img;
	}
	// getters
	public int getX() {
		return keysec_x;
	}
	public int getY() {
		return keysec_y;
	}
	public BufferedImage getImg() {
		return this.secImg;
	}
	
	// setters
	public void setX(int x) {
		this.keysec_x = x;
	}
	
	public void setY(int y) {
		this.keysec_y = y;
	}
	
	public void resize(int newW, int newH) { 
	    Image tmp = this.secImg.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    this.secImg = dimg;

	}  
}
