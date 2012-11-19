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
	Pencil pen;
	Protractor prot;
	Window win;
	Bully bully;
	Exam exam;
	
	@BeforeClass
	public void beforeTest (){
		nerd = new Nerd("NERD", "");
		book = new Book(50, "", "MATH");
		pen = new Pencil(20, "");
		prot = new Protractor(30, "");
		win = new Window(20, 20, 45, "");
		bully = new Bully(40, 30, 66, "", "BULLY");
		exam = new Exam(50, 30, 88, "", "PHYSICS");
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
