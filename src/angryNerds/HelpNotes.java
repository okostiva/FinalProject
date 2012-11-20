package angryNerds;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HelpNotes extends JDialog {
	private JLabel weapons;
	String str = "";
	private JTextField listOfWeapons;
	private JTextArea instructions;
	ArrayList<Weapon> nerdWeapons;

	public HelpNotes(Nerd nerd) {
		setTitle("Help");
		setSize(300, 300);
		nerdWeapons = nerd.getWeapons();
		instructions = new JTextArea("You are an ANGRY NERD! \nDestroy all windows, bullies, and exams \nin each level to win the game! \nEach time you hit a target, its health will decrease.");
		instructions.setEditable(false);
		for(Weapon w : nerdWeapons) {
			str += w.getWeaponName() + ": " + w.getDamage() + " damage\n";
		}
		listOfWeapons = new JTextField(str);
		weapons = new JLabel("Weapons:");
		
		add(instructions);
		add(weapons);
		add(listOfWeapons);
		listOfWeapons.setEditable(false);
		setLayout(new GridLayout(0, 1));
	}
}
