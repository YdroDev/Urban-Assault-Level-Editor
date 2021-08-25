package UAstructures;

public class Squad extends Unit {
	
	private int quantity;
	private boolean useable;
	
	Squad(int X, int Y, int own,int vehicle) {
		super(X, Y, own, vehicle);
		this.quantity = 1;
		this.useable = false;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public boolean getUseable() {
		return this.useable;
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
	}
	public void setUseable(boolean status) {
		this.useable = status;
	}
	
}
