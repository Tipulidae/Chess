package game;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import gui.ChessFrame;
import rules.Board;

public class Game {
	private Board board;
	public void playWithGUI() {
		board = new Board();
		connectGUI();
		board.setStartPositions();
	}
	
	
	private void connectGUI() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					ChessFrame chess = new ChessFrame();
					chess.connect(board);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
