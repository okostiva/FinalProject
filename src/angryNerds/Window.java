package angryNerds;
import java.awt.Graphics;
import java.awt.Image;


public class Window extends Target {
	
	public Window() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Window(int x, int y, int health, int points, int level, String imgPath) {
		super(x, y, health, points, level, imgPath);
	}
	
	public Window(int x, int y, int health, int points, int level, Image image) {
		super(x, y, health, points, level, image);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
		
		return;
	}
	
	public void paintComponent(Graphics g)
	{
		this.draw(g);
		return;
	}

}
