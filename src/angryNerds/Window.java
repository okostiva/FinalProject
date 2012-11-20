package angryNerds;
import java.awt.Graphics;


public class Window extends Target {
	
	public Window() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Window(int x, int y, int health, int points, String imgPath) {
		super(x, y, health, points, imgPath);
	}
	
	@Override
	public void draw(Graphics g)
	{
		return;
	}

}
