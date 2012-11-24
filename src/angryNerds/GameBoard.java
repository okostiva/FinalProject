package angryNerds;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import angryNerds.Weapon.WEAPON_TYPE;

public class GameBoard extends JFrame {

	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGHT = 400;
	public static final String WEAPON_CONFIG = "weapons.csv";
	public static final String TARGET_CONFIG = "targets.csv";
	/**
	 * 
	 */
	private static final long serialVersionUID = -4580170113745359348L;
	private Nerd nerd;
	private int dx, dy;
	private int level = 1;
	private ControlPanel controlPanel;
	private ArrayList<Target> targets;
	HelpNotes hp = null;
	private Weapon currentWeapon;
	private Timer timer;

	public GameBoard() {
		// TODO Auto-generated constructor stub
		nerd = new Nerd();
		controlPanel = new ControlPanel(this);
		targets = new ArrayList<Target>();
		timer = new Timer(1, new TimerListener());
		
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		this.add(controlPanel, BorderLayout.SOUTH);
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		menu.add(createFileMenu());
		
		loadWeapons();
		loadTargets();
		
		currentWeapon = nerd.getWeapon(0);
		this.add(currentWeapon, BorderLayout.CENTER);
	}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileItem("Help"));
		menu.add(createFileItem("Exit"));
		return menu;
	}

	private JMenuItem createFileItem(String name) {
		JMenuItem newItem = new JMenuItem(name);
		if(name.equals("Exit"))
		{
			newItem.addActionListener(new ExitItemListener());
		} else if (name.equals("Help"))
		{
			newItem.addActionListener(new NotesItemListener());
		}
		return newItem;
	}
	
	private class ExitItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
		}		
	}
	
	private class NotesItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			if (hp == null) {
				hp = new HelpNotes(nerd);
			}
			hp.setVisible(true);
		}
	}

	public Target getTarget(int index) {
		return targets.get(index);
	}
	
	public ArrayList<Target> getTargets() {
		return targets;
	}
	
	public Nerd getNerd() {
		return nerd;
	}
	
	public void startGame() {
		this.updateDrawing(6, 6);
	}
	
	public void loadWeapons() {
		try {
			FileReader rdr = new FileReader(WEAPON_CONFIG);
			Scanner scn = new Scanner(rdr);
			String line, type;
			String [] inputs;
			int level, damage, quantity;
			
			while (scn.hasNext())
			{
				line = scn.nextLine();
				inputs = line.split(",");
				
				if (inputs.length == 4) 
				{
					level = Integer.parseInt(inputs[0]);
					type = inputs[1];
					damage = Integer.parseInt(inputs[2]);
					quantity = Integer.parseInt(inputs[3]);
					
					for (int i = 0; i<quantity; i++)
					{
						Weapon tempWeapon;
						
						if (type.equalsIgnoreCase("Pencil"))
						{
							tempWeapon = new Pencil(damage, level, WEAPON_TYPE.PENCIL, "");
						}
						else if (type.equalsIgnoreCase("Protractor"))
						{
							tempWeapon = new Protractor(damage, level, WEAPON_TYPE.PROTRACTOR, "");
						}
						else if (type.equalsIgnoreCase("Book"))
						{
							tempWeapon = new Book(damage, level, WEAPON_TYPE.BOOK, "", "MATH");
						}
						else 
						{
							throw new Exception("ERROR: Invalid weapon type (" + type + ") detected in the weapon config file " + WEAPON_CONFIG);
						}
						
						nerd.AddWeapon(tempWeapon);
					}
				}
				else 
				{
					throw new Exception("ERROR: Invalid weapon config detected in the weapon config file " + WEAPON_CONFIG);
				}
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("ERROR: Could not open weapon config file " + WEAPON_CONFIG);
			System.exit(0);
		}
		catch (NumberFormatException ex) {
			System.out.println("ERROR: Non-numeric value detected in the weapon config file " + WEAPON_CONFIG);
			System.exit(0);
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			System.exit(0);
		}
	}
	
	public void loadTargets() {
		try {
			FileReader rdr = new FileReader(TARGET_CONFIG);
			Scanner scn = new Scanner(rdr);
			String line, type;
			String [] inputs;
			int x, y, level, health, points;
			
			while (scn.hasNext())
			{
				Target tempTarget;
				line = scn.nextLine();
				inputs = line.split(",");
				
				if (inputs.length == 6) 
				{
					level = Integer.parseInt(inputs[0]);
					type = inputs[1];
					x = Integer.parseInt(inputs[2]);
					y = Integer.parseInt(inputs[3]);
					health = Integer.parseInt(inputs[4]);
					points = Integer.parseInt(inputs[5]);
						
					if (type.equalsIgnoreCase("Window"))
					{
						tempTarget = new Window(x, y, health, points, level, "");
					}
					else if (type.equalsIgnoreCase("Exam"))
					{
						tempTarget = new Exam(x, y, health, points, level, "", "Math");
					}
					else if (type.equalsIgnoreCase("Bully"))
					{
						tempTarget = new Bully(x, y, health, points, level, "", "Bully");
					}
					else 
					{
						throw new Exception("ERROR: Invalid target type (" + type + ") detected in the target config file " + TARGET_CONFIG);
					}
						
					targets.add(tempTarget);
				}
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("ERROR: Could not open target config file " + TARGET_CONFIG);
			System.exit(0);
		}
		catch (NumberFormatException ex) {
			System.out.println("ERROR: Non-numeric value detected in the target config file " + TARGET_CONFIG);
			System.exit(0);
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			System.exit(0);
		}
	}
	
	public void toss(int angle, int power) {
		timer.start();
	}
	
	public void updateDrawing(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		timer = new Timer(1, new TimerListener());
		//timer.start();  
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			currentWeapon.translate(dx, dy, 85, 5, timer); 
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String title = "Welcome to Angry Nerds!";
		String message = "Enter the desired angle and power, then click the 'Fire!' button. For more instruction, click File, then Help";
		GameBoard gameboard = new GameBoard();
		gameboard.setVisible(true);
		gameboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(gameboard, message, title, JOptionPane.INFORMATION_MESSAGE);
		gameboard.startGame();
	}
}
