package gui;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import rules.Board;

public class ChessFrame extends JFrame {
	private InfoPanel ip;
	private BoardPanel panel;
	public ChessFrame() {
		super("Chess");
		
		
		ip = new InfoPanel();
		panel = new BoardPanel();
		ColumnLabels cl = new ColumnLabels();

		add(BorderLayout.SOUTH, ip);
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.NORTH, cl);
		add(BorderLayout.WEST, new RowLabels());
		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		pack();
		setLocation(500, 300);
	}
	
	public void connect(Board board) {
		panel.connect(board);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ChessFrame();
			}
		});
	}
}
