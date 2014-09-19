package creator;

import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * This is a simple extension of JButton to enable me to make changes
 * to JTextFields for the purpose of changing ability scores while
 * creating Pathfinder characters.
 * @author raspst
 *
 */
public class OperatorButton extends JButton {
	
	public OperatorButton(String text, JTextField attribute, JTextField modifier) {
		super();
		this.setText(text);
	}

}
