import java.awt.Graphics;

public class Book extends Weapon {

	private String subject;
	
	public Book() {
		// TODO Auto-generated constructor stub
		super();
		subject = "???";
	}
	
	public Book(int damage, String imgPath, String subject) {
		super(damage, imgPath);
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