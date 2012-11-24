package tests;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import angryNerds.Book;
import angryNerds.Bully;
import angryNerds.ControlPanel;
import angryNerds.Exam;
import angryNerds.GameBoard;
import angryNerds.Nerd;
import angryNerds.Pencil;
import angryNerds.Protractor;
import angryNerds.Target;
import angryNerds.Weapon;
import angryNerds.Weapon.WEAPON_TYPE;
import angryNerds.Window;


public class TestGame {
	private static Nerd nerd;
	private static Book book;
	private static Pencil pencil;
	private static Protractor prot;
	private static Window window;
	private static Bully bully;
	private static Exam exam;
	
	@BeforeClass
	public static void beforeTest () {
		nerd = new Nerd("NERD", "");
		book = new Book(50, 1, WEAPON_TYPE.BOOK, "", "MATH");
		pencil = new Pencil(20, 1, WEAPON_TYPE.PENCIL, "");
		prot = new Protractor(30, 1, WEAPON_TYPE.PROTRACTOR, "");
		window = new Window(20, 20, 45, 100, 1, "");
		bully = new Bully(40, 30, 66, 150, 1, "", "BULLY");
		exam = new Exam(50, 30, 88, 200, 1, "", "PHYSICS");
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
		// A way to test that the right amount of damage is done when a weapon hits a target
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
		nerd.toss(0, 0);
		nerd.toss(1, 2);
		nerd.toss(5, 10);
		Assert.assertEquals(0, nerd.getWeapons().size());
		
		nerd.AddWeapon(book);
		nerd.AddWeapon(pencil);
		nerd.AddWeapon(prot);
		nerd.toss(45, 10);
		Assert.assertEquals(0, window.getHealth());
		Assert.assertFalse(window.notDestroyed);
		nerd.toss(60, 50);
		Assert.assertEquals(46, bully.getHealth());
		Assert.assertTrue(bully.notDestroyed);
		nerd.toss(89, 100);
		
		int expectedWindowHealth = window.getHealth();
		int expectedBullyHealth = bully.getHealth();
		int expectedExamHealth = exam.getHealth();
		
		Assert.assertEquals(expectedWindowHealth, window.getHealth());
		Assert.assertFalse(window.notDestroyed);
		Assert.assertEquals(expectedBullyHealth, bully.getHealth());
		Assert.assertTrue(bully.notDestroyed);
		Assert.assertEquals(expectedExamHealth, exam.getHealth());
		Assert.assertTrue(exam.notDestroyed);
	}
	
	//These are the tests to reinforce the math problems
	//This test is for the directional velocity (HARD)
	@Test
	public void testVelocityProblems() {
		ControlPanel controlPanel = new ControlPanel(new GameBoard());
		
		controlPanel.setAngle("45");
		controlPanel.setPower("10");
		double expectedX = Math.cos(Math.toRadians(45.0))*10.0;
		double expectedY = Math.sin(Math.toRadians(45.0))*10.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
		
		controlPanel.setAngle("30");
		controlPanel.setPower("10");
		expectedX = Math.cos(Math.toRadians(30.0))*10.0;
		expectedY = Math.sin(Math.toRadians(30.0))*10.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());	
		
		controlPanel.setAngle("0");
		controlPanel.setPower("10");
		expectedX = Math.cos(Math.toRadians(0.0))*10.0;
		expectedY = Math.sin(Math.toRadians(0.0))*10.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
		
		controlPanel.setAngle("90");
		controlPanel.setPower("10");
		expectedX = Math.cos(Math.toRadians(90.0))*10.0;
		expectedY = Math.sin(Math.toRadians(90.0))*10.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
		
		controlPanel.setAngle("30");
		controlPanel.setPower("100");
		expectedX = Math.cos(Math.toRadians(30.0))*100.0;
		expectedY = Math.sin(Math.toRadians(30.0))*100.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
		
		controlPanel.setAngle("30");
		controlPanel.setPower("10");
		expectedX = Math.cos(Math.toRadians(30.0))*0.0;
		expectedY = Math.sin(Math.toRadians(30.0))*0.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
		
		controlPanel.setAngle("30");
		controlPanel.setPower("70");
		expectedX = Math.cos(Math.toRadians(30.0))*70.0;
		expectedY = Math.sin(Math.toRadians(30.0))*70.0;
		Assert.assertEquals(expectedX, controlPanel.getVelocityX());
		Assert.assertEquals(expectedY, controlPanel.getVelocityY());
	}
	
	//This test is for the angular difference (EASY)
	@Test
	public void testAngleDifferenceProblems() {
		ControlPanel controlPanel  = new ControlPanel(new GameBoard());
		
		controlPanel.setAngle("45");
		Assert.assertEquals(45, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("90");
		Assert.assertEquals(0, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("0");
		Assert.assertEquals(90, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("60");
		Assert.assertEquals(30, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("30");
		Assert.assertEquals(60, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("73");
		Assert.assertEquals(17, controlPanel.getAngleDifference());
		
		controlPanel.setAngle("24");
		Assert.assertEquals(66, controlPanel.getAngleDifference());
	}
	
	@Test
	public void testLoadConfigs() {
		// Tests to make sure the code is loading the configuration files correctly
		// We need a way to incorporate "level"
		GameBoard gameboard = new GameBoard();
		ArrayList<Target> targets = gameboard.getTargets();
		ArrayList<Weapon> weapons = gameboard.getNerd().getWeapons();
		int level1Weapons = 0; 
		int level10targets = 0;
		
		Assert.assertEquals(25, targets.size());
		
		Assert.assertEquals(25, targets.get(0).getX());
		Assert.assertEquals(25, targets.get(0).getY());
		Assert.assertEquals(1, targets.get(0).getLevel());
		Assert.assertEquals(50, targets.get(0).getHealth());
		Assert.assertEquals(50, targets.get(0).getPoints());
		
		Assert.assertEquals(30, targets.get(4).getX());
		Assert.assertEquals(100, targets.get(4).getY());
		Assert.assertEquals(3, targets.get(4).getLevel());
		Assert.assertEquals(100, targets.get(4).getHealth());
		Assert.assertEquals(100, targets.get(4).getPoints());
		
		Assert.assertEquals(50, targets.get(24).getX());
		Assert.assertEquals(90, targets.get(24).getY());
		Assert.assertEquals(10, targets.get(24).getLevel());
		Assert.assertEquals(75, targets.get(24).getHealth());
		Assert.assertEquals(75, targets.get(24).getPoints());
		
		for (Target t : targets)
		{
			if (t.getLevel() == 10)
			{
				level10targets++;
			}
		}
		
		Assert.assertEquals(5, level10targets);
		
		Assert.assertEquals(70, weapons.size());
		
		Assert.assertEquals(20, weapons.get(0).getDamage());
		Assert.assertEquals(1, weapons.get(0).getLevel());
		Assert.assertEquals(WEAPON_TYPE.PENCIL, weapons.get(0).getWeaponType());
		
		Assert.assertEquals(50, weapons.get(22).getDamage());
		Assert.assertEquals(3, weapons.get(22).getLevel());
		Assert.assertEquals(WEAPON_TYPE.BOOK, weapons.get(22).getWeaponType());
		
		Assert.assertEquals(35, weapons.get(69).getDamage());
		Assert.assertEquals(10, weapons.get(69).getLevel());
		Assert.assertEquals(WEAPON_TYPE.PROTRACTOR, weapons.get(69).getWeaponType());
		
		for (Weapon w : weapons)
		{
			if (w.getLevel() == 1)
			{
				level1Weapons++;
			}
		}
		
		Assert.assertEquals(6, level1Weapons);
	}
}
