package angryNerds;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Weapon {

	protected int damage;
	protected Image image;
	protected int x, y;
	
	public Weapon() {
		// TODO Auto-generated constructor stub
		damage = 0;
		x = 0;
		y = 0;
		image = Toolkit.getDefaultToolkit().getImage("/images/none.jpg");
	}
	
	public Weapon(int damage, Image image) {
		this.damage = damage;
		this.image = image;
		x = 0;
		y = 0;
	}
	
	public Weapon(int damage, String imagePath) {
		this.damage = damage;
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
		x = 0;
		y = 0;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public abstract String getWeaponName();
	
	public abstract void draw(Graphics g);
}
