package angryNerds;
import java.awt.Graphics;

public class Protractor extends Weapon {
	
	public Protractor() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Protractor(int damage, String imgPath) {
		super(damage, imgPath);
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