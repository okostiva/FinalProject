package angryNerds;
import java.awt.Graphics;

public class Protractor extends Weapon {
	
	public Protractor() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Protractor(int damage, int level, WEAPON_TYPE weaponType, String imgPath) {
		super(damage, level, weaponType, imgPath);
	}
	
	@Override
	public void draw(Graphics g)
	{
		return;
	}

	@Override
	public String getWeaponName() {
		return "protractor";
	}

}