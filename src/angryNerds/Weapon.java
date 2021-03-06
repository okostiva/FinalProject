package angryNerds;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Timer;


public abstract class Weapon extends JPanel {

	public enum WEAPON_TYPE {PENCIL, PROTRACTOR, BOOK};
	
	protected int damage;
	protected Image image;
	protected int x = 10;
	protected int y = GameBoard.BOARD_HEIGHT - 140; 
	protected int level;
	protected WEAPON_TYPE weaponType;
	
	public Weapon() {
		// TODO Auto-generated constructor stub
		damage = 0;	
		level = 0;
		image = Toolkit.getDefaultToolkit().getImage("/images/none.jpg");
	}
	
	public Weapon(int damage, int level, WEAPON_TYPE weaponType, Image image) {
		this.damage = damage;
		this.level = level;
		this.weaponType = weaponType;
		this.image = image;
	}
	
	public Weapon(int damage, int level, WEAPON_TYPE weaponType, String imagePath) {
		this.damage = damage;
		this.level = level;
		this.weaponType = weaponType;
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
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
	
	public boolean translate(int dx, int dy, int angle, int power) {
		if (power > 0)
		{
			dx = (int)Math.ceil(Math.cos(Math.toRadians(angle))*dx);
			dy = (int)Math.ceil(Math.sin(Math.toRadians(angle))*dy - ((11/power)*x*0.0098));
			
			if ((this.y > GameBoard.BOARD_HEIGHT) ||  (this.x > GameBoard.BOARD_WIDTH) || (this.x < 0))
			{
				return true;
			}
			this.x += dx;
			this.y -= dy;
	
			// Must include this to see changes
			//repaint();
		}
		else
		{
			return true;
		}
		return false;
	}
	
	public abstract String getWeaponName();
	
	public abstract void draw(Graphics g);
}
