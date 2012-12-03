package angryNerds;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HelpNotes extends JDialog {
	private JLabel weapons, targets;
	String weaponsStr = "";
	String targetsStr = "";
	private JTextArea instructions, instructions2, listOfWeapons, listOfTargets;
	ArrayList<Weapon> nerdWeapons;
	ArrayList<Target> allTargets;

	public HelpNotes(Nerd nerd, ArrayList<Target> t) {
		setTitle("Help");
		setSize(400, 800);
		Font font = new Font("Batang", Font.BOLD, 18);
		Font font2 = new Font("Matisse ITC", Font.BOLD, 20);

		nerdWeapons = nerd.getWeapons();
		allTargets = t;
		instructions = new JTextArea("You are an ANGRY NERD! \nDestroy all windows, bullies, and exams \nin each level to win the game! \nEach time you hit a target, its health\nwill decrease.");
		instructions.setFont(font2);
		instructions.setForeground(Color.WHITE);
		instructions.setBackground(Color.BLACK);
		instructions.setEditable(false);
		
		instructions2 = new JTextArea("After you fire a weapon a quiz will pop up.\nAnswer the question correctly for a higher\nscore!\nDo not worry about units.");
		instructions2.setFont(font2);
		instructions2.setForeground(Color.WHITE);
		instructions2.setBackground(Color.BLACK);
		instructions2.setEditable(false);
		
		ArrayList<String> oneOfEachWeapon = new ArrayList<String>();
		ArrayList<String> oneOfEachTarget = new ArrayList<String>();
		for(Weapon w : nerdWeapons) {
			if(!oneOfEachWeapon.contains(w.getWeaponName())) {
				weaponsStr += w.getWeaponName() + ": " + w.getDamage() + " damage\n";
				oneOfEachWeapon.add(w.getWeaponName());
			}
		}
		for(Target target : allTargets) {
			if(!oneOfEachTarget.contains(target.getName())) {
				targetsStr += target.getName() + ": " + target.getHealth() + " health\n";
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
		
		weapons = new JLabel("Weapons:");
		weapons.setFont(font2);
		weapons.setForeground(Color.WHITE);
		weapons.setOpaque(true);
		weapons.setBackground(Color.BLACK);
		
		targets = new JLabel("Targets: ");
		targets.setOpaque(true);
		targets.setBackground(Color.BLACK);
		targets.setFont(font2);
		targets.setForeground(Color.WHITE);

		add(instructions);
		add(weapons);
		add(listOfWeapons);
		add(targets);
		add(listOfTargets);
		add(instructions2);
		listOfWeapons.setEditable(false);
		listOfTargets.setEditable(false);
		setLayout(new GridLayout(0, 1));
	}
}
