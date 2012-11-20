package angryNerds;
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
		return;
	}

	@Override
	public String getWeaponName() {
		return "pencil";
	}
}
