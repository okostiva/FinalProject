package tests;
import static org.junit.Assert.*;

import java.util.Timer;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import angryNerds.Book;
import angryNerds.Bully;
import angryNerds.Nerd;
import angryNerds.Pencil;
import angryNerds.Protractor;
import angryNerds.Window;
import angryNerds.Exam;


public class TestGame {
	Nerd nerd;
	Book book;
	Pencil pencil;
	Protractor prot;
	Window window;
	Bully bully;
	Exam exam;
	
	@BeforeClass
	public void beforeTest (){
		nerd = new Nerd("NERD", "");
		book = new Book(50, "", "MATH");
		pencil = new Pencil(20, "");
		prot = new Protractor(30, "");
		window = new Window(20, 20, 45, "");
		bully = new Bully(40, 30, 66, "", "BULLY");
		exam = new Exam(50, 30, 88, "", "PHYSICS");
	}
	
	@Test
	public void testNerd() {
		nerd.AddWeapon(book);
		nerd.AddWeapon(pencil);
		nerd.AddWeapon(prot);
		Assert.assertEquals(book, nerd.getWeapon(0));
		Assert.assertEquals(pencil, nerd.getWeapon(1));
		Assert.assertEquals(prot, nerd.getWeapon(2));
		Assert.assertEquals(3,nerd.getWeapons().size());
	}
	
	@Test
	public void testDamage() {
		// A way to test that the right amount of damage is done when 
		Assert.assertEquals(45, window.getHealth());
		window.notDestroyed = false;
		Assert.assertEquals(-5, 45-nerd.getWeapon(0).getDamage());
		Assert.assertEquals(66, bully.getHealth());
		bully.notDestroyed = false;
		Assert.assertEquals(46, 66-nerd.getWeapon(1).getDamage());
		Assert.assertEquals(88, exam.getHealth());
		exam.notDestroyed = false;
		Assert.assertEquals(58, 88-nerd.getWeapon(2).getDamage());
	}
	
	
	@Test
	public void testToss() {
		// testing the change of location after a specified time...
	}
}
