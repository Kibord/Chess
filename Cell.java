
public class Cell {
	private char x = 'a';
	private int y = 8;
	private boolean isEmpty = true;
	private char fig = ' ';
	
	public Cell(char x, int y) {
		this.x = x;
		this.y = y;
		//setX(x);
		//setY(y);
	}
		
	public Cell(char x, int y, char fig) {
		this.x = x;
		this.y = y;
		this.fig = fig;
		//setX(x);
		//setY(y);
		//setFig(fig);
	}
	
	
	
	public char getX() {
		return x;
	}
	public void setX(char x) {
		if ((x >= 'A' && x <= 'H') || (x >= 'a' && x <= 'h')) this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if (y > 0 && y < 9) this.y = y;
	}
	public char getFig() {
		return fig;
	}
	public void setFig(char fig) {
		this.fig = fig;
	}	
	public boolean getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	
	
}
