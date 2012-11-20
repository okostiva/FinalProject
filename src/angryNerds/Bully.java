package angryNerds;
import java.awt.Graphics;
import java.awt.Image;


public class Bully extends Target {

	private String name;
	
	public Bully() {
		// TODO Auto-generated constructor stub
		super();
		this.name = "???";
	}
	
	public Bully(int x, int y, int health, int points, String imgPath, String name) {
		super(x, y, health, points, imgPath);
		this.name = name;
	}
	
	public Bully(int x, int y, int health, int points, Image image, String name) {
		super(x, y, health, points, image);
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
