package angryNerds;

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
	private JTextArea instructions, listOfWeapons, listOfTargets;
	ArrayList<Weapon> nerdWeapons;
	ArrayList<Target> allTargets;

	public HelpNotes(Nerd nerd, ArrayList<Target> t) {
		setTitle("Help");
		setSize(300, 300);
		nerdWeapons = nerd.getWeapons();
		allTargets = t;
		instructions = new JTextArea("You are an ANGRY NERD! \nDestroy all windows, bullies, and exams \nin each level to win the game! \nEach time you hit a target, its health will decrease.");
		instructions.setEditable(false);
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
		listOfTargets = new JTextArea(targetsStr);
		weapons = new JLabel("Weapons:");
		targets = new JLabel("Targets: ");

		add(instructions);
		add(weapons);
		add(listOfWeapons);
		add(targets);
		add(listOfTargets);
		listOfWeapons.setEditable(false);
		listOfTargets.setEditable(false);
		setLayout(new GridLayout(0, 1));
	}
}
