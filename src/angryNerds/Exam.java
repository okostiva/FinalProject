package angryNerds;
import java.awt.Graphics;


public class Exam extends Target {

	private String subject;
	
	public Exam() {
		// TODO Auto-generated constructor stub
		super();
		subject = "???";
	}
	
	public Exam(int x, int y, int health, String imgPath, String subject) {
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
