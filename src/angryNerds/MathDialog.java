package angryNerds;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MathDialog extends JDialog {

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
	
	public class EasyDoneListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			repaint();
			setVisible(false);
		}
	}
	
	public class HardDoneListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			repaint();
			setVisible(false);
		}
	}

	private JPanel addEasyPanel() {
		panel = new JPanel();
		angleLabel = new JLabel("Enter an Angle: ");
		angleField = new JTextField(3);
		done = new JButton("DONE");

		panel.add(angleLabel);
		panel.add(angleField);
		panel.add(done);

		done.addActionListener(new EasyDoneListener());
		
		return panel;
	}
	
	private JPanel addHardPanel() {
		panel = new JPanel();
		xLabel = new JLabel("Enter X: ");
		yLabel = new JLabel("Enter Y: ");
		xField = new JTextField(3);
		yField = new JTextField(3);
		done = new JButton("DONE");
		
		panel.setLayout(new GridLayout(2,3));
		panel.add(xLabel);
		panel.add(yLabel);
		panel.add(done);
		panel.add(xField);
		panel.add(yField);
		
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
