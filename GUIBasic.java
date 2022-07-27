import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBasic implements ActionListener {
	// is the window
	JFrame frame;
	// how things fit in the window
	JPanel panel;

	// declare variables to be put inside panel
	JTextField userInput;
	JLabel outputLabel, inputLabel;
	JButton queryButton;

	public GUIBasic() {
		// initialize the windows
		panel = new JPanel();
		frame = new JFrame();

		panel.setLayout(null);

		// set the variables inside the panel and add it inside
		inputLabel = new JLabel("input");
		inputLabel.setBounds(10, 20, 80, 25);
		panel.add(inputLabel);

		userInput = new JTextField(100);
		userInput.setBounds(100, 20, 400, 25);
		userInput.addActionListener(this);
		panel.add(userInput);

		queryButton = new JButton("search");
		queryButton.setBounds(525, 20, 80, 25);
		queryButton.addActionListener(this);
		panel.add(queryButton);

		outputLabel = new JLabel("output");
		outputLabel.setVerticalAlignment(JLabel.TOP);
		outputLabel.setBounds(100, 60, 400, 50);
		outputLabel.setOpaque(true);
		outputLabel.setBackground(Color.WHITE);
		Border outputBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		outputLabel.setBorder(outputBorder);
		panel.add(outputLabel);

		// set up frame
		frame.setSize(750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
	}

	// an example function to set output label 
	private void copyPaste(String line){
		outputLabel.setText(line);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the action event and decide what to do given the action command "String"
		switch(e.getActionCommand()){
			case "search":
				copyPaste(userInput.getText());
				userInput.setText("");
				break;
			default:
				copyPaste(e.getActionCommand());
				userInput.setText("");
			
		}
	}

	
}

// just my runner
class Test{
	public static void main(String[] args) {
		System.out.println("Howdy");
		GUIBasic gui = new GUIBasic();
	}
}