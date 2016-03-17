package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Position {
	public final int x;
	public final int y;
	public static final String label = "abcdefgh";

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(String str) {
		Pattern p = Pattern.compile("([a-hA-H])([1-8])");
		Matcher m = p.matcher(str);
		if (m.matches()) {
			x = label.indexOf(m.group(1).toLowerCase());
			y = 8 - Integer.valueOf(m.group(2));
		} else {
			System.err.println("Invalid position notation! "+str);
			System.exit(1);
			x = 0;
			y = 0;
		}
	}

	public static Position add(Position a, Position b) {
		return new Position(a.x + b.x, a.y + b.y);
	}

	public static Position sub(Position a, Position b) {
		return new Position(a.x - b.x, a.y - b.y);
	}
	
	public boolean isBlack() {
		return (x+y)%2 == 1;
	}
	
	public boolean isWhite() {
		return (x+y)%2 == 0;
	}
	
	
	public boolean isEdge() {
		return (x == 0 || x == 7 || y == 0 || y == 7);
	}

	public boolean isCorner() {
		return (x == 0 && y == 7) || (x == 7 && y == 0) || (x == 7 && y == 7) || (x == 0 && y == 0);
	}
	
	public List<Position> neighbours() {
		List<Position> neighbours = new ArrayList<Position>();
		
		for (int xx = x - 1; xx <= x + 1; xx++) {
			for (int yy = y - 1; yy <= y + 1; yy++) {
				Position p = new Position(xx, yy);
				if (p.withinBounds() && !this.equals(p))
					neighbours.add(p);
			}
		}
		
		return neighbours;
	}

	public boolean withinBounds() {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position other = (Position) o;
			return other.x == x && other.y == y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 10*x+y;
	}

	public String toString() {
		return label.charAt(x) + "" + (8-y);
	}
}