import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Target {

	//Status of true means show the target, status of false means the target has been destroyed
	protected int x, y, health;
	protected Image image;
	protected boolean status;
	
	public Target() {
		// TODO Auto-generated constructor stub
		this.health = 0;
		x = 0;
		y = 0;
		this.status = true;
		this.image = Toolkit.getDefaultToolkit().getImage("/Images/none.jpg");
	}

	public Target(int x, int y, int health, Image image) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.image = image;
		this.status = true;
	}
	
	public Target(int x, int y, int health, String imagePath) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
		this.status = true;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean st) {
		this.status = st;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;;
	}
	
	public void setY(int y) {
		this.y = y;;
	}
	public void damageDone(int damage) {
		this.health -= damage;
		if (health <= 0)
		{
			status = false;
		}
	}
	
	public abstract void draw(Graphics g);
}
