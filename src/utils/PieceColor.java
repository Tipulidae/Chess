package utils;

public enum PieceColor {
	NONE, BLACK, WHITE;

	public static PieceColor opposite(PieceColor c) {
		switch (c) {
		case BLACK:
			return WHITE;
		case WHITE:
			return BLACK;
		default:
			return NONE;
		}
	}

	public static String name(PieceColor c) {
		switch (c) {
		case BLACK:
			return "black";
		case WHITE:
			return "white";
		default:
			return "none";
		}
	}
}