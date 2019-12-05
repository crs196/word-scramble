
public class Letter {

	private String letter;
	private int xLoc, yLoc;
	private boolean draw;
	
	public Letter(String l, int x, int y) {
		letter = l;
		xLoc = x;
		yLoc = y;
		draw = true;
	}
	
	public void setDraw(boolean d) {
		draw = d;
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
	
	public boolean getDraw() {
		return draw;
	}
	
}