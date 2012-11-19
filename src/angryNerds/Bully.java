package angryNerds;
import java.awt.Graphics;


public class Bully extends Target {

	private String name;
	
	public Bully() {
		// TODO Auto-generated constructor stub
		super();
		this.name = "???";
	}
	
	public Bully(int x, int y, int health, String imgPath, String name) {
		super(x, y, health, imgPath);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void draw(Graphics g)
	{
		return;
	}

}
