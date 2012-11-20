package angryNerds;
import java.awt.Graphics;
import java.awt.Image;


public class Exam extends Target {

	private String subject;
	
	public Exam() {
		// TODO Auto-generated constructor stub
		super();
		subject = "???";
	}
	
	public Exam(int x, int y, int health, int points, int level, String imgPath, String subject) {
		super(x, y, health, points, level, imgPath);
		this.subject = subject;
	}
	
	public Exam(int x, int y, int health, int points, int level, Image image, String subject) {
		super(x, y, health, points, level, image);
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
