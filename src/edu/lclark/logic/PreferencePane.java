package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;


public class PreferencePane extends JFrame {
	private static final long serialVersionUID = 1L;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    
    private String[] symbols;
    private JRadioButton[][] buttons = new JRadioButton[5][];
        
    private JPanel createRow(String title, JPanel panel) {
		JPanel newPanel = new JPanel(new GridLayout(1, 2));
		newPanel.add(new JLabel(title, JLabel.CENTER), 0);
		newPanel.add(panel, 1);
    	return newPanel;
    }
    
    private JPanel makeButtonPanel(JRadioButton[] buttons) {
    	JPanel newPanel = new JPanel(new GridLayout(1, buttons.length));
    	for(JRadioButton button : buttons) {
    		newPanel.add(button);
    	}
    	return newPanel;
    }
    
    private JRadioButton[] makeButtons(String[] strings, int row) {
    	ButtonGroup group = new ButtonGroup();
    	JRadioButton[] buttons = new JRadioButton[strings.length];
    	int i = 0;
    	for(String string : strings) {
    		JRadioButton button = new JRadioButton(string);
    		group.add(button);
    		buttons[i++] = button;
    	}
    	this.buttons[row] = buttons;
    	return buttons;
    }
    
    public PreferencePane() {
    	// TODO add default symbols
    	// TODO add submit and/or save button for preferences
    	symbols = new String[] {"↔", "→", ".", "⋁", "-"};
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(6, 1));
    	
    	panel.add(createRow("biconditional", makeButtonPanel(makeButtons(new String[]{"↔","<->","≡"}, 0))));
    	panel.add(createRow("conditional", makeButtonPanel(makeButtons(new String[]{"→","->","⊃"}, 1))));
    	panel.add(createRow("conjunction", makeButtonPanel(makeButtons(new String[]{".","&","^"}, 2))));
    	panel.add(createRow("inclusive or", makeButtonPanel(makeButtons(new String[]{"⋁","v"}, 3))));
    	panel.add(createRow("negation", makeButtonPanel(makeButtons(new String[]{"-","¬", "~"}, 4))));

    	JButton submitButton = new JButton("Submit");
    	submitButton.addActionListener(new SubmitButtonAction());
    	panel.add(submitButton);
    	
    	this.add(panel);
    	
    	this.setTitle("Preferences");
    }
    
    public void newWindow() {    
    	
		for (int row = 0; row < buttons.length; row++) {
			for (JRadioButton c : buttons[row]) {
				if (symbols[row] == c.getText()) {
					c.setSelected(true);;
				}
			}
//			System.out.println(symbols[row]);
		}
    	
		this.pack();
		this.setVisible(true);
    }
    
    private void hideWindow() {
    	this.setVisible(false);
    }
    
	private class SubmitButtonAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public void actionPerformed(ActionEvent event) {
			for (int row = 0; row < buttons.length; row++) {
				for (JRadioButton c : buttons[row]) {
					if (c.isSelected()) {
						symbols[row] = c.getText();
					}
				}
//				System.out.println(symbols[row]);
			}
			hideWindow();
		}
	}

	public String[] getSymbols() {
		return symbols;
	}
    
    public static void main(String[] args) {
    	PreferencePane pp = new PreferencePane();
    	String[] test = pp.getSymbols();
    	for (String string : test) {
    		System.out.println(string);
    	}
    	pp.newWindow();
    }
}
