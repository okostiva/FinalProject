package angryNerds;
import java.awt.Color;
import java.awt.Graphics;


public class Pencil extends Weapon {

	public Pencil() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Pencil(int damage, int level, WEAPON_TYPE weaponType, String imagePath) {
		super (damage, level, weaponType, imagePath);
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.fillOval(10, 10, 20, 20);
		return;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		draw(g);
		return;
	}

	@Override
	public String getWeaponName() {
		return "pencil";
	}
}
