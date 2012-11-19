import java.util.ArrayList;

import javax.swing.JFrame;


public class GameBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580170113745359348L;
	private Nerd nerd;
	private ControlPanel controlPanel;
	private ArrayList<Target> targets;

	public GameBoard() {
		// TODO Auto-generated constructor stub
		nerd = new Nerd();
		controlPanel = new ControlPanel();
		targets = new ArrayList<Target>();
		
		setSize(150, 100);
		add(controlPanel);
	}

	public Target getTarget(int index) {
		return targets.get(index);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard gameboard = new GameBoard();
		gameboard.setVisible(true);
		gameboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
