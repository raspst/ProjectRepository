package creator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
/**
 * This is a utility to assist in creating a Pathfinder character using a point-buy system.
 * It currently assumes a 25 point system, noted in the Pathfinder Core Rulebook as
 * categorizing an "epic fantasy" game. I mostly did that to let players get at least one
 * score to 18 and not make their other scores suck afterward. For basic rules on playing 
 * Pathfinder and fleshing out your character, buy the Pathfinder Core Rulebook or check out 
 * the Pathfinder Reference Document (PRD) at http://paizo.com/prd/
 * @author raspst
 *
 */
public class CharacterCreator {
	private static int Points = 25;

	public static void main(String[] args) {
		final int WIDTH = 800;
		final int HEIGHT = 600;
		String[] classList = {"Fighter", "Rogue", "Wizard"};
		String[] attributeNames = {"Strength", "Dexterity", "Constitution", "Intelligence",
				"Wisdom", "Charisma"};
		String[] races = {"Human", "Half-Elf", "Elf", "Dwarf", "Halfling", "Gnome", "Half-Orc"};
		final int ROWS = 7;
		final int COLUMNS = 5;
		HashMap<Integer,String> modifiers = new HashMap<Integer,String>();
		modifiers.put(7, "-2");
		modifiers.put(8, "-1");
		modifiers.put(9, "-1");
		modifiers.put(10, "0");
		modifiers.put(11, "0");
		modifiers.put(12, "+1");
		modifiers.put(13, "+1");
		modifiers.put(14, "+2");
		modifiers.put(15, "+2");
		modifiers.put(16, "+3");
		modifiers.put(17, "+3");
		modifiers.put(18, "+4");
		modifiers.put(19, "+4");
		modifiers.put(20, "+5");
		JPanel[][] panelHolder = new JPanel[ROWS][COLUMNS];
		JFrame frame = new JFrame("Pathfinder Character Creator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new GridLayout(ROWS,COLUMNS));
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				panelHolder[i][j] = new JPanel();
				frame.add(panelHolder[i][j]);
			}
		}
		
		JComboBox<String> classDropDown = new JComboBox<String>(classList);
		classDropDown.setSelectedIndex(0);
		classDropDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String classChoice = (String) cb.getSelectedItem();
			}
			
		});
		classDropDown.setSize(20,10);
		panelHolder[0][0].add(classDropDown);
		JComboBox<String> raceDropDown = new JComboBox<String>(races);
		raceDropDown.setSelectedIndex(0);
		raceDropDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String classChoice = (String) cb.getSelectedItem();
			}
			
		});
		raceDropDown.setSize(20,10);
		panelHolder[0][1].add(raceDropDown);
		JLabel pointLabel = new JLabel();
		pointLabel.setText("Points Remaining: " + Integer.toString(Points));
		panelHolder[0][2].add(pointLabel);
		for(int i = 1; i < ROWS; i++) {
			JLabel attribute = new JLabel(attributeNames[i-1]);
			JTextField t = new JTextField(Integer.toString(10));
			JTextField m = new JTextField("0");
			JButton plus = new OperatorButton("+",t,m);
			plus.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int abilityScore = Integer.parseInt(t.getText());
					if(abilityScore == 18) {
						;
					}
					else if(abilityScore == 7 || abilityScore == 13 || abilityScore == 14) {
						if(Points >= 2) {
							Points -= 2;
						abilityScore += 1;
						t.setText(Integer.toString(abilityScore));
						}
					}
					else if(abilityScore == 15 || abilityScore == 16) {
						if(Points >= 3){
							Points -= 3;
							abilityScore += 1;
							t.setText(Integer.toString(abilityScore));
						}
					}
					else if(abilityScore == 17) {
						if(Points >= 4) {
							Points -= 4;
							abilityScore += 1;
							t.setText(Integer.toString(abilityScore));
						}
					}
					else {
						if(Points >= 1) {
							Points -= 1;
							abilityScore += 1;
							t.setText(Integer.toString(abilityScore));
						}
					}
					m.setText(modifiers.get(abilityScore));
				}
				
			});
			JButton minus = new OperatorButton("-", t,m);
			minus.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int abilityScore = Integer.parseInt(t.getText());
					if(abilityScore == 7) {
						;
					}
					else if(abilityScore == 8 || abilityScore == 14 || abilityScore == 15) {
						Points += 2;
						abilityScore -= 1;
						t.setText(Integer.toString(abilityScore));
					}
					else if(abilityScore == 16 || abilityScore == 17) {
						Points += 3;
						abilityScore -= 1;
						t.setText(Integer.toString(abilityScore));
					}
					else if(abilityScore == 18) {
						Points+=4;
						abilityScore -= 1;
						t.setText(Integer.toString(abilityScore));
					}
					else {
						Points += 1;
						abilityScore -= 1;
						t.setText(Integer.toString(abilityScore));
					}
					m.setText(modifiers.get(abilityScore));
					
				}
				
			});
			panelHolder[i][0].add(attribute);
			panelHolder[i][1].add(t);
			panelHolder[i][2].add(m);
			panelHolder[i][3].add(plus);
			panelHolder[i][4].add(minus);
		}
		classDropDown.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true) {
			pointLabel.setText("Points Remaining: " + Integer.toString(Points));
		}
	}
}
