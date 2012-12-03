package angryNerds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpNotes extends JDialog {
	Graphics g;
	private JLabel weapons, targets;
	String weaponsStr = "";
	String targetsStr = "";
	private JTextArea instructions, instructions2, listOfWeapons, listOfTargets;
	ArrayList<Weapon> nerdWeapons;
	ArrayList<Target> allTargets;
	Image pencilPicture = Toolkit.getDefaultToolkit().getImage("images/pencil.png");
	Image bookPicture = Toolkit.getDefaultToolkit().getImage("images/book.png");
	Image protPicture = Toolkit.getDefaultToolkit().getImage("images/protractor.png");
	Image windowPicture = Toolkit.getDefaultToolkit().getImage("images/window.png");
	Image bullyPicture = Toolkit.getDefaultToolkit().getImage("images/bully.png");
	Image examPicture = Toolkit.getDefaultToolkit().getImage("images/exam.png");
	ImageIcon pencilIcon = new ImageIcon(pencilPicture);
	ImageIcon bookIcon = new ImageIcon(bookPicture);
	ImageIcon protIcon = new ImageIcon(protPicture);
	ImageIcon windowIcon = new ImageIcon(windowPicture);
	ImageIcon bullyIcon = new ImageIcon(bullyPicture);
	ImageIcon examIcon = new ImageIcon(examPicture);
	
	public HelpNotes(Nerd nerd, ArrayList<Target> t) {
		setTitle("Help");
		setSize(800, 800);
		Font font = new Font("Batang", Font.BOLD, 18);
		Font font2 = new Font("Matisse ITC", Font.BOLD, 20);

		nerdWeapons = nerd.getWeapons();
		allTargets = t;
		instructions = new JTextArea("You are an ANGRY NERD! \nDestroy all windows, bullies, and exams in each level to win the game! \nEach time you hit a target, its health will decrease. After you fire a weapon a quiz\nwill pop up. Answer the question correctly for a higher score! Do not worry about units.");
		instructions.setFont(font2);
		instructions.setForeground(Color.WHITE);
		instructions.setBackground(Color.BLACK);
		instructions.setEditable(false);
		
		ArrayList<String> oneOfEachWeapon = new ArrayList<String>();
		ArrayList<String> oneOfEachTarget = new ArrayList<String>();
		for(Weapon w : nerdWeapons) {
			if(!oneOfEachWeapon.contains(w.getWeaponName())) {
				weaponsStr += w.getWeaponName() + ": " + w.getDamage() + " damage	      ";
				oneOfEachWeapon.add(w.getWeaponName());
			}
		}
		for(Target target : allTargets) {
			if(!oneOfEachTarget.contains(target.getName())) {
				targetsStr += target.getName() + ": " + target.getHealth() + " health	      ";
				oneOfEachTarget.add(target.getName());
			}
		}
		listOfWeapons = new JTextArea(weaponsStr);
		listOfWeapons.setFont(font);
		listOfWeapons.setForeground(Color.WHITE);
		listOfWeapons.setBackground(Color.BLACK);
		
		listOfTargets = new JTextArea(targetsStr);
		listOfTargets.setFont(font);
		listOfTargets.setForeground(Color.WHITE);
		listOfTargets.setBackground(Color.BLACK);
		
		JLabel weaponImages = new JLabel();
		weaponImages.setLayout(new GridLayout(1, 3));
		JLabel pencilImage = new JLabel(pencilIcon);
		JLabel bookImage = new JLabel(bookIcon);
		JLabel protImage = new JLabel(protIcon);
		weaponImages.add(pencilImage);
		weaponImages.add(bookImage);
		weaponImages.add(protImage);
		weaponImages.setOpaque(true);
		weaponImages.setBackground(Color.BLACK);
		
		JLabel targetImages = new JLabel();
		targetImages.setLayout(new GridLayout(1, 3));
		JLabel windowImage = new JLabel(windowIcon);
		JLabel bullyImage = new JLabel(bullyIcon);
		JLabel examImage = new JLabel(examIcon);
		targetImages.add(windowImage);
		targetImages.add(bullyImage);
		targetImages.add(examImage);
		targetImages.setOpaque(true);
		targetImages.setBackground(Color.BLACK);
		
		weapons = new JLabel("Weapons:");
		weapons.setFont(font);
		weapons.setForeground(Color.WHITE);
		weapons.setOpaque(true);
		weapons.setBackground(Color.BLACK);
		
		targets = new JLabel("Targets: ");
		targets.setOpaque(true);
		targets.setBackground(Color.BLACK);
		targets.setFont(font);
		targets.setForeground(Color.WHITE);

		add(instructions);
		add(weapons);
		add(weaponImages);
		add(listOfWeapons);
		add(targets);
		add(targetImages);
		add(listOfTargets);
		listOfWeapons.setEditable(false);
		listOfTargets.setEditable(false);
		setLayout(new GridLayout(0, 1));
	}
}
