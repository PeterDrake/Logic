package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class PreferencePane extends JFrame {
	private static final long serialVersionUID = 1L;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    
    private String[] symbols;
        
    private JPanel createRow(String title, JPanel panel) {
		JPanel newPanel = new JPanel(new GridLayout(1, 2));
		newPanel.add(new JLabel(title, JLabel.CENTER), 0);
		newPanel.add(panel, 1);
    	return newPanel;
    }
    
    private JPanel makeButtonPanel(JButton[] buttons) {
    	JPanel newPanel = new JPanel(new GridLayout(1, buttons.length));
    	for(JButton button : buttons) {
    		newPanel.add(button);
    	}
    	return newPanel;
    }
    
    private JButton[] makeButtons(String[] strings) {
    	JButton[] buttons = new JButton[strings.length];
    	int i = 0;
    	for(String string : strings) {
    		JButton button = new JButton(string);
    		button.addActionListener(new ButtonPressAction(i, string));
    		buttons[i++] = button;
    	}
    	return buttons;
    }
    
    public PreferencePane() {
    	// TODO add default symbols
    	// TODO add submit and/or save button for preferences
    	symbols = new String[] {"↔", "→", ".", "⋁", "-"};
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(5, 1));
    	
    	panel.add(createRow("biconditional", makeButtonPanel(makeButtons(new String[]{"↔","<->","≡"}))));
    	panel.add(createRow("conditional", makeButtonPanel(makeButtons(new String[]{"→","->","⊃"}))));
    	panel.add(createRow("conjunction", makeButtonPanel(makeButtons(new String[]{".","&","^"}))));
    	panel.add(createRow("inclusive or", makeButtonPanel(makeButtons(new String[]{"v","⋁"}))));
    	panel.add(createRow("negation", makeButtonPanel(makeButtons(new String[]{"-","¬", "~"}))));

    	this.add(panel);
    	
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    	this.setTitle("Preferences");
    }
    
	private class ButtonPressAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		String symbol;
		int i;
		
		public ButtonPressAction(int i, String symbol) {
			this.i = i;
			this.symbol = symbol;
		}
		
		public void actionPerformed(ActionEvent event) {
			symbols[i] = symbol;
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
    }
}
