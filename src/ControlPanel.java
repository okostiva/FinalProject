import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7717786247225180620L;
	private int angle, power;
	private JTextArea angleInput, powerInput;
	private JLabel angleLabel, powerLabel;
	private JButton enterData;
	
	public ControlPanel() {
		// TODO Auto-generated constructor stub
		angleLabel = new JLabel("Angle: ");
		angleInput = new JTextArea("0");
		powerLabel = new JLabel("Power: ");
		powerInput = new JTextArea("0");
		enterData = new JButton("Submit");
		angle = 0;
		power = 0;
		
		this.add(angleLabel);
		this.add(angleInput);
		this.add(powerLabel);
		this.add(powerInput);
		this.add(enterData);
	}
	
	public int getPower() {
		return power;
	}
	
	public int getAngle() {
		return angle;
	}
}
