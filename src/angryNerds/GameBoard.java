package angryNerds;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import angryNerds.Weapon.WEAPON_TYPE;

public class GameBoard extends JFrame {

	public enum DIFFICULTY { EASY, HARD };
	
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGHT = 600;
	public static final String WEAPON_CONFIG = "weapons.csv";
	public static final String TARGET_CONFIG = "targets.csv";
	public static final String BOOK_IMAGE = "images/book.png";
	public static final String PENCIL_IMAGE = "images/pencil.png";
	public static final String PROTRACTOR_IMAGE = "images/protractor.png";
	public static final String BULLY_IMAGE = "images/bully.png";
	public static final String WINDOW_IMAGE = "images/window.png";
	public static final String EXAM_IMAGE = "images/exam.png";
	private static final long serialVersionUID = -4580170113745359348L;
	private Nerd nerd;
	private int dx, dy;
	private int level = 1;
	private int score = 0;
	private int remainingTargets = 0;
	public ControlPanel controlPanel;
	private ArrayList<Target> targets;
	HelpNotes hp = null;
	private Weapon currentWeapon;
	private Timer timer;
	private DIFFICULTY difficulty = DIFFICULTY.EASY;
	private MathDialog difficultyDialog; //new MathDialog();
	private JLabel pointLabel = new JLabel("Score: " + Integer.toString((score)));
	private JLabel levelLabel = new JLabel("Level: " + level);

	public GameBoard() {
		nerd = new Nerd();
		controlPanel = new ControlPanel(this);
		targets = new ArrayList<Target>();
		timer = new Timer(1, new TimerListener());
		
		difficultyDialog = new MathDialog();

		this.setLayout(null);

		setSize(BOARD_WIDTH, BOARD_HEIGHT);

		controlPanel.setBounds(0, BOARD_HEIGHT-85, BOARD_WIDTH, 25);
		controlPanel.setOpaque(true);
		this.add(controlPanel);
		setTitle("Angry Nerds >:-|");

		JMenuBar menu = new JMenuBar();
		GridLayout layout = new GridLayout(1,8);
		menu.setLayout(layout);
		setJMenuBar(menu);
		menu.add(createFileMenu());
		menu.add(createDifficultyMenu());
		menu.add(new JLabel());
		menu.add(new JLabel());
		menu.add(new JLabel());
		menu.add(levelLabel);
		menu.add(pointLabel);
		
		loadWeapons();
		loadTargets();

		//this.add(currentWeapon, BorderLayout.CENTER);
		//this.add(targets.get(0), BorderLayout.CENTER);
		//this.add(targets.get(1), BorderLayout.PAGE_START);
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileItem("Help"));
		menu.add(createFileItem("Exit"));
		return menu;
	}

	private JMenu createDifficultyMenu() {
		JMenu menu = new JMenu("Difficulty");
		menu.add(createFileItem("Easy"));
		menu.add(createFileItem("Hard"));
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
		} else if (name.equals("Easy") || name.equals("Hard"))
		{
			newItem.addActionListener(new DifficultyListener());
		}
		return newItem;
	}

	private class ExitItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
		}		
	}

	//Shows the HelpNotes if not already showing
	private class NotesItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			if (hp == null) {
				hp = new HelpNotes(nerd, getTargets());
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

	//Draws all targets in the correct location for level 1
	public void startGame() {	
		for (Target t : targets)
		{
			t.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT-50);
			t.setVisible(false);
			this.add(t);
			repaint();
		}		
		
		this.updateDrawing(3,3);
		this.setTargets();
		this.nextWeapon();

		repaint();
	}
	
	public void toss(int angle, int power) {
		timer.start();
	}

	//Moves weapon along projectile path
	public void updateDrawing(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		timer = new Timer(10, new TimerListener());
		//timer.start();  
	}

	public void setTargets() {
		//if (currentTargets != null) {
		//	for (Target t: currentTargets) {
		//		this.remove(t);
		//		repaint();
		//	}
		//}

		//currentTargets.clear();

		for (Target t : targets)
		{
			if (t.getLevel() == this.level)
			{
				t.setVisible(true);
				remainingTargets++;
				//currentTargets.add(t);
			}
			else 
			{
				t.setVisible(false);
			}
		}

//		for (Target t : currentTargets)
//		{
//			t.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT-50);
//			this.add(t);
//			repaint();
//		}
	}

	public void nextWeapon() {
		if(currentWeapon != null)
		{
			this.remove(currentWeapon);
			nerd.removeWeapon(currentWeapon);
		}

		//Assigns a grade to the final score
		if(nerd.getWeapons().size() == 0)
		{
			char grade = 'E';
			if(score <= 1000) {
				grade = 'F';
			} else if (1000 < score && score <= 2000) {
				grade = 'D';
			} else if (2000 < score && score <= 3000) {
				grade = 'C';
			} else if (3000 < score && score <= 3500) {
				grade = 'B';
			} else if (score > 3500) {
				grade = 'A';
			}
			String title = "Game Over";
			String message = "";
			if(grade == 'F' || grade == 'D' || grade == 'C') {
				message = "You received a " + grade + "!\n" + score + " points!"; 
			} else {
				message = "Congratulations, you received a " + grade + "!\n" + score + " points!";
			}
			JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else
		{			
			currentWeapon = nerd.getWeapon(0);
			currentWeapon.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT-50);
			this.add(currentWeapon);
			repaint();

			if (currentWeapon.getLevel() != this.level) {
				level++;
				updateLevel();
				remainingTargets = 0;
				setTargets();
				repaint();
			}
		}
	}

	public void nextLevel() {
		int tempLevel = level;
		
		while(currentWeapon.getLevel() == tempLevel) {
			nextWeapon();
		}
	}
	
	public void updateScore(int points) {
		score += points;
		
		if (points < 0)	pointLabel.setForeground(Color.RED);
		else if (points == 0) pointLabel.setForeground(Color.BLACK);
		else pointLabel.setForeground(Color.GREEN);
		
		pointLabel.setText("Score: " + Integer.toString(score));
	}
	
	public void updateLevel() {
		levelLabel.setText("Level: " + level);
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
							tempWeapon = new Pencil(damage, level, WEAPON_TYPE.PENCIL, PENCIL_IMAGE);
						}
						else if (type.equalsIgnoreCase("Protractor"))
						{
							tempWeapon = new Protractor(damage, level, WEAPON_TYPE.PROTRACTOR, PROTRACTOR_IMAGE);
						}
						else if (type.equalsIgnoreCase("Book"))
						{
							tempWeapon = new Book(damage, level, WEAPON_TYPE.BOOK, BOOK_IMAGE, "MATH");
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
						tempTarget = new Window(x, y, health, points, level, WINDOW_IMAGE, "Window");
					}
					else if (type.equalsIgnoreCase("Exam"))
					{
						tempTarget = new Exam(x, y, health, points, level, EXAM_IMAGE, "Math", "Exam");
					}
					else if (type.equalsIgnoreCase("Bully"))
					{
						tempTarget = new Bully(x, y, health, points, level, BULLY_IMAGE, "Bully");
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
	
	private class DifficultyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{			
			if (e.getActionCommand().equals("Easy"))
			{
				difficulty = DIFFICULTY.EASY;
			}
			else if (e.getActionCommand().equals("Hard"))
			{
				difficulty = DIFFICULTY.HARD;
			}
		}
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			boolean weaponFinished = false;

			weaponFinished = currentWeapon.translate(dx, dy, controlPanel.getAngle(), controlPanel.getPower());
			repaint();

			for (Target t : targets)
			{				
				if (t.level == level)
				{
					if (t.hit(currentWeapon.x, currentWeapon.y)) {
						weaponFinished = true;
						t.damageDone(currentWeapon.damage);
						
						if (!t.notDestroyed) {
							t.setVisible(false);
							updateScore(t.getPoints());
							remainingTargets--;							
							repaint();
						}
						
						break;
					}
				}
			}

			if (weaponFinished) {
				timer.stop();
				nextWeapon();
				
				if (remainingTargets == 0)
				{
					nextLevel();
				}
				
				if (difficultyDialog != null)
				{
					difficultyDialog.removeCurrentPanel();
				}
				
				switch (difficulty) {
				case HARD:
					difficultyDialog.createHardPanel();
					break;
				case EASY:
				default:
					difficultyDialog.createEasyPanel();
					break;
				}
				
				difficultyDialog.setSize(700, 150);
				difficultyDialog.setVisible(true);
			}
		}
	}
	
	//Class to bring up a "quiz box" each time after a weapon is fired
	class MathDialog extends JDialog {

		private JPanel panel = null;
		private JButton done;
		/// EASY MODE...
		private JLabel angleLabel;
		private JTextField angleField;
		/// HARD MODE...
		private JLabel xLabel;
		private JLabel yLabel;
		private JTextField xField;
		private JTextField yField;
		
		public MathDialog() {
			// TODO Auto-generated constructor stub
			this.setModal(true);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
		
		//Checks the entered answers and determines what points to give
		class EasyDoneListener implements ActionListener {
			public void actionPerformed(ActionEvent e) 
			{
				repaint();
				boolean incorrect = true;
				while(incorrect) {
					try {
						if (Integer.parseInt(angleField.getText()) == controlPanel.getAngleDifference()) {
							JOptionPane.showMessageDialog(panel, "Correct!\n+20");
							updateScore(20);
						} else {
							JOptionPane.showMessageDialog(panel, "Incorrect!\n-10");
							updateScore(-10);
						}
						incorrect = false;
						setVisible(false);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(angleField, "Invalid input, please enter a number");
						incorrect = false;
						
					}
				}
				
			}
		}
		
		//Checks the entered answers and determines what points to give
		class HardDoneListener implements ActionListener {
			public void actionPerformed(ActionEvent e) 
			{
				repaint();
				boolean incorrect = true;
				while(incorrect) {
					try {
						if ((Double.parseDouble(xField.getText()) == controlPanel.getVelocityX()) && (Double.parseDouble(yField.getText()) == controlPanel.getVelocityY())) {
							JOptionPane.showMessageDialog(panel, "Correct!\n+20");
							updateScore(20);
						} else {
							JOptionPane.showMessageDialog(panel, "Incorrect!\n-10");
							updateScore(-10);
							System.out.println(controlPanel.getVelocityX() + " " + controlPanel.getVelocityY());
						}
						incorrect = false;
						setVisible(false);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(angleField, "Invalid input, please enter a number");
						incorrect = false;
					}
				}
			}
		}

		private JPanel addEasyPanel() {
			panel = new JPanel();
			angleLabel = new JLabel("What is the difference between 90 degrees and the angle you entered?");
			angleField = new JTextField(3);
			done = new JButton("Submit");

			panel.add(angleLabel);
			panel.add(angleField);
			panel.add(done);

			done.addActionListener(new EasyDoneListener());
			
			return panel;
		}
		
		private JPanel addHardPanel() {
			panel = new JPanel();
			xLabel = new JLabel("Enter the velocity in the x-direction: ");
			yLabel = new JLabel("Enter the velocity in the y-direction: ");
			xField = new JTextField(3);
			yField = new JTextField(3);
			done = new JButton("Submit");
			
			panel.setLayout(new GridLayout(2,3));
			panel.add(xLabel);
			panel.add(yLabel);
			panel.add(new JLabel());
			panel.add(xField);
			panel.add(yField);
			panel.add(done);
			
			done.addActionListener(new HardDoneListener());
			
			return panel;
		}
		
		public void createHardPanel() {
			this.add(addHardPanel());
		}
		
		public void createEasyPanel() {
			this.add(addEasyPanel());
		}
		
		public void removeCurrentPanel() 
		{
			if (this.panel != null)
			{
				this.remove(this.panel);
			}
		}
	}
	
	//The following should only be used for testing purposes and should not be called anywhere during game play
		public void setNerd(Nerd nerd) {
			this.nerd = nerd;
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String title = "Welcome to Angry Nerds!";
		String message = "Enter the desired angle and power, then click the 'Fire!' button. For more instruction, click File, then Help";
		GameBoard gameboard = new GameBoard();
		gameboard.setVisible(true);
		gameboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameboard.startGame();
		JOptionPane.showMessageDialog(gameboard, message, title, JOptionPane.INFORMATION_MESSAGE);	
	}
}
