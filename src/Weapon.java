import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Weapon {

	protected int damage;
	protected Image image;
	
	public Weapon() {
		// TODO Auto-generated constructor stub
		damage = 0;
		image = Toolkit.getDefaultToolkit().getImage("/images/none.jpg");
	}
	
	public Weapon(int damage, Image image) {
		this.damage = damage;
		this.image = image;
	}
	
	public Weapon(int damage, String imagePath) {
		this.damage = damage;
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public abstract void draw(Graphics g);
}
