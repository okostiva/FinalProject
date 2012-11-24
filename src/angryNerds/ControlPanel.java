package angryNerds;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ControlPanel extends JPanel {

	public enum Difficulty { EASY, HARD };
	public static final int MAX_ANGLE = 90;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717786247225180620L;
	private int angle, power;
	private JTextArea angleInput, powerInput;
	private JLabel angleLabel, powerLabel;
	private JButton enterData;
	private Difficulty difficulty = Difficulty.EASY;
	private GameBoard gameboard;
	
	public ControlPanel(GameBoard gameboard) {
		// TODO Auto-generated constructor stub
		angleLabel = new JLabel("Angle: ");
		angleInput = new JTextArea("0");
		powerLabel = new JLabel("Power: ");
		powerInput = new JTextArea("0");
		enterData = new JButton("Fire");
		angle = 0;
		power = 0;
		this.gameboard = gameboard;
		
		enterData.addActionListener(new FireListener());
		
		setLayout(new GridLayout(1, 0));
		add(angleLabel);
		add(angleInput);
		add(powerLabel);
		add(powerInput);
		add(enterData);
	}
	
	public int getPower() {
		return power;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public double getVelocityX() {
		return 0.0;
	}
	
	public double getVelocityY() {
		return 0.0;
	}
	
	public double getAngleDifference() {
		return 0.0;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void updateAngle() {
		this.angle = Integer.parseInt(angleInput.getText());
	}
	
	public void updatePower() {
		this.power = Integer.parseInt(powerInput.getText());
	}
	
	//Methods below are for testing purposes only and should not be used during 
	//implementation of the game
	public void setAngle(String angle) {
		this.angleInput.setText(angle);
	}
	
	public void setPower(String power) {
		this.powerInput.setText(power);
	}
	
	private class FireListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			updateAngle();
			updatePower();
			gameboard.toss(getAngle(), getPower());
		}
		
	}
}
