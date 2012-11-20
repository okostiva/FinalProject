package angryNerds;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Weapon {

	public enum WEAPON_TYPE {PENCIL, PROTRACTOR, BOOK};
	
	protected int damage;
	protected Image image;
	protected int x, y, level;
	protected WEAPON_TYPE weaponType;
	
	public Weapon() {
		// TODO Auto-generated constructor stub
		damage = 0;
		x = 0;
		y = 0;
		level = 0;
		image = Toolkit.getDefaultToolkit().getImage("/images/none.jpg");
	}
	
	public Weapon(int damage, int level, WEAPON_TYPE weaponType, Image image) {
		this.damage = damage;
		this.level = level;
		this.weaponType = weaponType;
		this.image = image;
		x = 0;
		y = 0;
	}
	
	public Weapon(int damage, int level, WEAPON_TYPE weaponType, String imagePath) {
		this.damage = damage;
		this.level = level;
		this.weaponType = weaponType;
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
		x = 0;
		y = 0;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public WEAPON_TYPE getWeaponType() {
		return this.weaponType;
	}
	
	public abstract String getWeaponName();
	
	public abstract void draw(Graphics g);
}
