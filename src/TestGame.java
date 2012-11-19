import static org.junit.Assert.*;

import java.util.Timer;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestGame {
	Nerd nerd;
	Book book;
	Pencil pen;
	Protractor prot;
	Window win;
	Bully bully;
	Tests test;
	
	@BeforeClass
	public void beforeTest (){
		nerd = new Nerd("NERD", "");
		book = new Book(50, "", "MATH");
		pen = new Pencil(20, "");
		prot = new Protractor(30, "");
		win = new Window(20, 20, 45, "");
		bully = new Bully(40, 30, 66, "", "BULLY");
		test = new Tests(50, 30, 88, "", "PHYSICS");
	}
	
	@Test
	public void testNerd() {
		nerd.AddWeapon(book);
		nerd.AddWeapon(pen);
		nerd.AddWeapon(prot);
		Assert.assertEquals(book, nerd.getWeapon(0));
		Assert.assertEquals(pen, nerd.getWeapon(1));
		Assert.assertEquals(prot, nerd.getWeapon(2));
	}
	
	
	@Test
	public void testToss() {
		// testing the change of location after a specified time...
	}
}
