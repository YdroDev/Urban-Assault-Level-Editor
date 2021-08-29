package UAstructures;

import State.UAdata;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Unit {

	private int posX;
	private int posY; // Y value change to -Y during saving a map
	private int unitID;
	private int owner;
	private boolean mb_status; // visibility on briefing window
	private BufferedImage image;
	
	Unit(int X, int Y, int own,int ID){ // posY and height have to add minus sign during saving proccess
		this.posX = X;
		this.posY = Y;
		this.owner = own;
		this.unitID = ID;
		this.mb_status = true;
		updateImage();
	}
	
	// getters
	public int getOwner() {
		return owner;
	}
	public int getUnitX(){
		return posX;
	}
	public int getUnitY(){
		return posY;
	}
	public int getVehicle() {
		return unitID;
	}
	public BufferedImage getImage() {
		return image;
	}
	public boolean getVisibility() {
		return mb_status;
	}

	// setters
	public void setOwner(int own) {
		this.owner = own;
	}
	public void setUnitX(int X){
		this.posX = X;
	}
	public void setUnitY(int Y){
		this.posY = Y;
	}
	public void setVehicle(int v) {
		this.unitID = v;
	}
	public void setVisibility(boolean v) {
		this.mb_status = v;
	}

	public void updateImage() {
			try {
				if(this.unitID == 56) image = ImageIO.read(this.getClass().getResourceAsStream("/img/res.png"));
				else if(this.unitID == 59 || this.unitID == 57 || this.unitID == 176 || this.unitID == 177) image = ImageIO.read(this.getClass().getResource("/img/Ghor.png"));
				else if(this.unitID == 60 || this.unitID == 178) image = ImageIO.read(this.getClass().getResourceAsStream("/img/Taer.png"));
				else if(this.unitID == 58) image = ImageIO.read(this.getClass().getResourceAsStream("/img/Myko.png"));
				else if(this.unitID == 61) image = ImageIO.read(this.getClass().getResourceAsStream("/img/Sulg.png"));
				else if(this.unitID == 62) image = ImageIO.read(this.getClass().getResourceAsStream("/img/Blacksect.png"));
				else if(this.unitID == 132) image = ImageIO.read(this.getClass().getResourceAsStream("/img/Training.png"));

				UAitem unit = UAdata.getUnit(unitID);
				if(unit != null){
					switch(unit.getMapIcon()){
						case 0:
							if(this.owner == 1) image = ImageIO.read(this.getClass().getResourceAsStream("/img/BlueUnit1.png"));
							if(this.owner == 2) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GreenUnit1.png"));
							if(this.owner == 3) image = ImageIO.read(this.getClass().getResourceAsStream("/img/WhiteUnit1.png"));
							if(this.owner == 4) image = ImageIO.read(this.getClass().getResourceAsStream("/img/YellowUnit1.png"));
							if(this.owner == 5) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GrayUnit1.png"));
							if(this.owner == 6) image = ImageIO.read(this.getClass().getResourceAsStream("/img/RedUnit1.png"));
							if(this.owner == 7) image = ImageIO.read(this.getClass().getResourceAsStream("/img/TrainingUnit.png"));
							break;
						case 1:
							if(this.owner == 1) image = ImageIO.read(this.getClass().getResourceAsStream("/img/BlueUnit2.png"));
							if(this.owner == 2) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GreenUnit2.png"));
							if(this.owner == 3) image = ImageIO.read(this.getClass().getResourceAsStream("/img/WhiteUnit2.png"));
							if(this.owner == 4) image = ImageIO.read(this.getClass().getResourceAsStream("/img/YellowUnit2.png"));
							if(this.owner == 5) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GrayUnit2.png"));
							if(this.owner == 6) image = ImageIO.read(this.getClass().getResourceAsStream("/img/RedUnit2.png"));
							if(this.owner == 7) image = ImageIO.read(this.getClass().getResourceAsStream("/img/TrainingUnit.png"));
							break;
						case 2:
							if(this.owner == 1) image = ImageIO.read(this.getClass().getResourceAsStream("/img/BlueUnit3.png"));
							if(this.owner == 2) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GreenUnit3.png"));
							if(this.owner == 3) image = ImageIO.read(this.getClass().getResourceAsStream("/img/WhiteUnit3.png"));
							if(this.owner == 4) image = ImageIO.read(this.getClass().getResourceAsStream("/img/YellowUnit3.png"));
							if(this.owner == 5) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GrayUnit3.png"));
							if(this.owner == 6) image = ImageIO.read(this.getClass().getResourceAsStream("/img/RedUnit3.png"));
							if(this.owner == 7) image = ImageIO.read(this.getClass().getResourceAsStream("/img/TrainingUnit.png"));
							break;
						case 3:
							if(this.owner == 1) image = ImageIO.read(this.getClass().getResourceAsStream("/img/BlueUnit4.png"));
							if(this.owner == 2) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GreenUnit4.png"));
							if(this.owner == 3) image = ImageIO.read(this.getClass().getResourceAsStream("/img/WhiteUnit4.png"));
							if(this.owner == 4) image = ImageIO.read(this.getClass().getResourceAsStream("/img/YellowUnit4.png"));
							if(this.owner == 5) image = ImageIO.read(this.getClass().getResourceAsStream("/img/GrayUnit4.png"));
							if(this.owner == 6) image = ImageIO.read(this.getClass().getResourceAsStream("/img/RedUnit4.png"));
							if(this.owner == 7) image = ImageIO.read(this.getClass().getResourceAsStream("/img/TrainingUnit.png"));
							break;
						default:
							image = ImageIO.read(this.getClass().getResourceAsStream("/img/TrainingUnit.png"));
							break;
					}
				}
		}catch (IOException e) {
			System.out.println("An error occured during unit creation proccess:");
			e.printStackTrace();
		}
	}
	
	public void resize(int newW, int newH) { 
	    Image tmp = this.image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    this.image = dimg;

	}  
}
