package gui;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import rules.Board;

public class ChessFrame extends JFrame {
	private InfoPanel ip;
	private BoardPanel panel;
	public ChessFrame() {
		super("Chess");
		
		
		ip = new InfoPanel();
		panel = new BoardPanel();
		
		setLayout(new GridBagLayout());
		
		addToGrid(panel,1,1,8,8);
		addToGrid(new ColumnLabels(), 1,0,8,1);
		addToGrid(new ColumnLabels(), 1,9,8,1);
		addToGrid(new RowLabels(), 0,1,1,8);
		addToGrid(new RowLabels(), 9,1,1,8);
		addToGrid(ip, 1,10,8,1);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		pack();
		setLocation(500, 300);
	}
	
	public void connect(Board board) {
		panel.connect(board);
	}
	
	private void addToGrid(Component comp, int x, int y, int width, int height) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		c.fill = GridBagConstraints.BOTH;
		add(comp,c);
	}
}
