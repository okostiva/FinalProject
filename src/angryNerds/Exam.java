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
	
	public Exam(int x, int y, int health, int points, String imgPath, String subject) {
		super(x, y, health, points, imgPath);
		this.subject = subject;
	}
	
	public Exam(int x, int y, int health, int points, Image image, String subject) {
		super(x, y, health, points, image);
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
