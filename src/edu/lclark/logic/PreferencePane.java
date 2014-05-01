package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * PreferencePane defines symbols to be used
 * throughout LogicTrainer
 *
 */
public class PreferencePane extends JFrame {
	private static final long serialVersionUID = 1L;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    
	private final String[] defaults = new String[] {"↔", "→", ".", "⋁", "-"};
    private String[] symbols;
    
    /** This is a little hacky */
    private JRadioButton[][] buttons = new JRadioButton[5][];
        
    /**
     * Function to make row creation easy
     * @param title
     * @param panel
     */
    private JPanel createRow(String title, JPanel panel) {
		JPanel newPanel = new JPanel(new GridLayout(1, 2));
		newPanel.add(new JLabel(title, JLabel.CENTER), 0);
		newPanel.add(panel, 1);
    	return newPanel;
    }
    
    /**
     * Takes an array of buttons, returns
     * a JPanel with them in it
     * @param buttons
     */
    private JPanel makeButtonPanel(JRadioButton[] buttons) {
    	JPanel newPanel = new JPanel(new GridLayout(1, buttons.length));
    	for(JRadioButton button : buttons) {
    		newPanel.add(button);
    	}
    	return newPanel;
    }
    
    /**
     * Makes buttons for a specific row with
     * the names specified in the supplied array
     * A little bit hacky (shouldn't need to take a row)
     * @param strings
     * @param row
     */
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
    
    /** Initializes a PreferencePane with buttons to select symbols */
    public PreferencePane() {
    	// TODO add default symbols
    	// TODO add submit and/or save button for preferences
    	symbols = defaults;
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(6, 1));
    	
    	panel.add(createRow("biconditional", makeButtonPanel(makeButtons(new String[]{"↔","<->","≡"}, 0))));
    	panel.add(createRow("conditional", makeButtonPanel(makeButtons(new String[]{"→","->","⊃"}, 1))));
    	panel.add(createRow("conjunction", makeButtonPanel(makeButtons(new String[]{".","&","^"}, 2))));
    	panel.add(createRow("inclusive or", makeButtonPanel(makeButtons(new String[]{"⋁","v"}, 3))));
    	panel.add(createRow("negation", makeButtonPanel(makeButtons(new String[]{"-","¬", "~"}, 4))));

    	JButton submitButton = new JButton("Submit");
    	submitButton.addActionListener(new SubmitButtonAction());
    	
    	JButton defaultsButton = new JButton("Defaults");
    	defaultsButton.addActionListener(new DefaultsButtonAction());
    	
    	JPanel buttonsRow = new JPanel();
    	buttonsRow.setLayout(new GridLayout(1, 2));
    	
    	buttonsRow.add(defaultsButton);
    	buttonsRow.add(submitButton);
    	
    	panel.add(buttonsRow);
    	
    	this.add(panel);
    	
    	this.setTitle("Preferences");
    }
    
    /** Opens the PreferencePane window */
    public void newWindow() {    
    	
		for (int row = 0; row < buttons.length; row++) {
			for (JRadioButton c : buttons[row]) {
				if (symbols[row] == c.getText()) {
					c.setSelected(true);;
				}
			}
			//System.out.println(symbols[row]);
		}
    	
		this.pack();
		this.setVisible(true);
    }
    
    /** Hides the PreferencePane window */
    private void hideWindow() {
    	this.setVisible(false);
    }
    
    /** Action for the Defaults button, makes all symbols defaults */
    private class DefaultsButtonAction extends AbstractAction {

    	private static final long serialVersionUID = 1L;

    	public void actionPerformed(ActionEvent event) {
    		symbols = defaults;
    		newWindow();
    	}
    }
    
    /** Action for the Submit button, sets symbols to selected values */
	private class SubmitButtonAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public void actionPerformed(ActionEvent event) {
			for (int row = 0; row < buttons.length; row++) {
				for (JRadioButton c : buttons[row]) {
					if (c.isSelected()) {
						symbols[row] = c.getText();
					}
				}
				//System.out.println(symbols[row]);
			}
			hideWindow();
		}
	}

	/**
	 * Returns the array of selected symbols
	 * @return symbols
	 */
	public String[] getSymbols() {
		return symbols;
	}
}
