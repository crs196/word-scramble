
public class Letter {

	private String letter;
	private int xLoc, yLoc;
	
	public Letter(String l, int x, int y) {
		letter = l;
		xLoc = x;
		yLoc = y;
	}

	public String getLetter() {
		return letter;
	}

	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}
	
}
