package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RowLabels extends JPanel {
	public RowLabels() {
		super(new GridLayout(8, 1, 1, 1));
		for (int i = 0; i < 8; i++) {
			JLabel label = new JLabel("  "+(8-i)+"  ", SwingConstants.CENTER);
			add(label);
		}
	}
}
