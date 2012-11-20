package angryNerds;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580170113745359348L;
	private Nerd nerd;
	private ControlPanel controlPanel;
	private ArrayList<Target> targets;
	HelpNotes hp = null;

	public GameBoard() {
		// TODO Auto-generated constructor stub
		nerd = new Nerd();
		controlPanel = new ControlPanel();
		targets = new ArrayList<Target>();
		
		setSize(200, 200);
		add(controlPanel);
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		menu.add(createFileMenu());
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
	}
}
