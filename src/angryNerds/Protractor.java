package angryNerds;
import java.awt.Color;
import java.awt.Graphics;

public class Protractor extends Weapon {
	
	public Protractor() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Protractor(int damage, int level, WEAPON_TYPE weaponType, String imgPath) {
		super(damage, level, weaponType, imgPath);
	}
	
	public void draw(Graphics g) 
	{
		if (image == null)
		{
			g.setColor(Color.BLUE);
			g.fillOval(x, y, 20, 20);
		}
		else 
		{
			g.drawImage(image, x, y, null);
		}
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
		return "Protractor";
	}

}