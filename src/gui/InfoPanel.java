package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private JLabel scoreLabel;
	private JLabel turnLabel;

	public InfoPanel() {
		super(new GridLayout(1, 1, 1, 1));

		//scoreLabel = new JLabel("SCORE");
		turnLabel = new JLabel("TURN");
		add(turnLabel);
		//add(scoreLabel);
	}
}
