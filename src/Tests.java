import java.awt.Graphics;


public class Tests extends Target {

	private String subject;
	
	public Tests() {
		// TODO Auto-generated constructor stub
		super();
		subject = "???";
	}
	
	public Tests(int x, int y, int health, String imgPath, String subject) {
		super(x, y, health, imgPath);
		this.subject = subject;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	@Override
	public void draw(Graphics g)
	{
		return;
	}

}
