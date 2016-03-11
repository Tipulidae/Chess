package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ColumnLabels extends JPanel {
	public ColumnLabels() {
		super(new GridLayout(1, 9, 1, 1));
		String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H"};
		add(new JLabel(""));
		for (int i = 0; i < 8; i++) {
			add(new JLabel(chars[i], SwingConstants.LEFT));
		}
	}
}
