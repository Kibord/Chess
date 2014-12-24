import java.util.Scanner;

public class ChessTable {

	public static void main(String[] args) {
		Cell[][] cells = getStartTable();
		showTable(getStartTable());
		System.out.println("Please, enter your moves in format: \na2 a3");
		String move = "";
		Scanner scanIn = new Scanner (System.in);
		int i = 0;
		while (i != -1){
			if (i % 2 == 0) System.out.println("White's move:");
			if (i % 2 == 1) System.out.println("Black's move:");
			move = scanIn.nextLine();
			if (move == "exit" || move == "quit"){
				scanIn.close();
				i = -1;
				System.exit(0);
			}
			else if (move.length() != 5){
				System.out.println("Wrong format, please, enter your move again:");
				continue;
				//move = scanIn.nextLine();
			}
			boolean check = checkMove(i, move, cells);
			if (!check){
				System.out.println("Wrong move, please, enter your move again:");
				continue;
			}
			if (check){
				int x0 = move.charAt(0)-97;
				int y0 = move.charAt(1)-49;
				int x1 = move.charAt(3)-97;
				int y1 = move.charAt(4)-49;
				if ((x0 < 0 || x0 > 8)||(y0 < 0 || y0 > 8)||(x1 < 0 || x1 > 8)||(y1 < 0 || y1 > 8)){
					System.out.println("Wrong format, please, enter your move again:");
					continue;
				}
				cells[x1][y1].setFig(cells[x0][y0].getFig());
				cells[x1][y1].setIsEmpty(false);
				cells[x0][y0].setIsEmpty(true);
				if ((x0 % 2 == 1 && y0 % 2 == 1)||(x0 % 2 == 0 && y0 % 2 == 0))	cells[x0][y0].setFig('#');
				else cells[x0][y0].setFig(' ');
				showTable(cells);
				i++;
			}
		}
		scanIn.close();
	}
	
	public static boolean checkMove(int i, String move, Cell[][] cells){
		int x0 = move.charAt(0)-97;
		int y0 = move.charAt(1)-49;
		if (cells[x0][y0].getIsEmpty()) return false;
		if (i % 2 == 0 && cells[x0][y0].getFig() > 'Z') return false;
		if (i % 2 == 1 && cells[x0][y0].getFig() < 'Z') return false;
		int x1 = move.charAt(3)-97;
		int y1 = move.charAt(4)-49;
		if (x0 == x1 && y0 == y1) return false;
		switch (cells[x0][y0].getFig()) {
		
		case 'P':
			if (x0 != x1){
				if ((x1 - x0 == 1 || x1 - x0 == -1) && !cells[x1][y1].getIsEmpty() && cells[x1][y1].getFig() > 'Z') return true;
				else return false;
			}
			if (x0 == x1){
				if (y1 - y0 == 1 && cells[x1][y1].getIsEmpty()) return true;
				if (y0 == 1 && y1 == 3 && cells[x1][2].getIsEmpty() && cells[x1][3].getIsEmpty()) return true;
				return false;			
			}
			break;
			
		case 'p':
			if (x0 != x1){
				if ((x1 - x0 == 1 || x1 - x0 == -1) && !cells[x1][y1].getIsEmpty() && cells[x1][y1].getFig() < 'Z') return true;
				else return false;
			}
			if (x0 == x1){
				if (y0 - y1 == 1 && cells[x1][y1].getIsEmpty()) return true;
				if (y0 == 6 && y1 == 4 && cells[x1][5].getIsEmpty() && cells[x1][4].getIsEmpty()) return true;
				return false;			
			}
			break;
			
		case 'R':
		case 'r':
			if (x0 != x1 && y0 != y1) return false;
			if (x0 == x1){
				if (y1 > y0)
					for (int j = y0 + 1; j < y1; j++)
						if (!cells[x0][j].getIsEmpty()) return false;						
				if (y1 < y0) 			
					for (int j = y0 - 1; j > y1; j--)
						if (!cells[x0][j].getIsEmpty()) return false;
			}
			if (y0 == y1){
				if (x1 > x0) 
					for (int j = x0 + 1; j < x1; j++)
						if (!cells[j][y0].getIsEmpty()) return false;
				if (x1 < x0) 			
					for (int j = x0 - 1; j > x1; j--)
						if (!cells[j][y0].getIsEmpty()) return false;
			}
			break;
			
		case 'N':
		case 'n':
			if (!((x1 - x0 == 1 || x1 - x0 == -1) && (y1 - y0 == 2 || y1 - y0 == -2)) 
					&& !((x1 - x0 == 2 || x1 - x0 == -2) && (y1 - y0 == 1 || y1 - y0 == -1))) return false;
			break;			
		
		case 'B':
		case 'b':
			if ((x1 - x0 != y1 - y0) && (x1 - x0 != y0 - y1) ) return false;
			if (x1 > x0)
				for (int j = x0 + 1; j < x1; j++){
					if (y1 > y0)
						if (!cells[j][j - x0 + y0].getIsEmpty()) return false;
					if (y1 < y0)
						if (!cells[j][y0 - j + x0].getIsEmpty()) return false;
				}
			if (x1 < x0)
				for (int j = x0 - 1; j > x1; j--){
					if (y1 > y0)
						if (!cells[j][x0 - j + y0].getIsEmpty()) return false;
					if (y1 < y0)
						if (!cells[j][y0 + j - x0].getIsEmpty()) return false;
				}
			break;
			
		case 'K':
		case 'k':
			if ((y1 - y0 > 1 || y1 - y0 < -1) && (x1 - x0 > 1 || x1 - x0 < -1)) return false;
			break;
			
		case 'Q':
		case 'q':
			if (!((x1 - x0 == y1 - y0) || (x1 - x0 == y0 - y1)) && (x0 != x1 && y0 != y1) ){ System.out.println("hello from str 132"); return false;}
			if (x1 != x0 && y1 != y0){
				if (x1 > x0)
					for (int j = x0 + 1; j < x1; j++){
						if (y1 > y0)
							if (!cells[j][j - x0 + y0].getIsEmpty()) return false;
						if (y1 < y0)
							if (!cells[j][y0 - j + x0].getIsEmpty()) return false;
					}
				if (x1 < x0)
					for (int j = x0 - 1; j > x1; j--){
						if (y1 > y0)
							if (!cells[j][x0 - j + y0].getIsEmpty()) return false;
						if (y1 < y0)
							if (!cells[j][y0 + j - x0].getIsEmpty()) return false;
					}
			}
			if (x1 == x0){
				if (y1 > y0)
					for (int j = y0 + 1; j < y1; j++)
						if (!cells[x0][j].getIsEmpty()) return false;						
				if (y1 < y0) 			
					for (int j = y0 - 1; j > y1; j--)
						if (!cells[x0][j].getIsEmpty()) return false;
			}
			if (y0 == y1){
				if (x1 > x0) 
					for (int j = x0 + 1; j < x1; j++)
						if (!cells[j][y0].getIsEmpty()) return false;
				if (x1 < x0) 			
					for (int j = x0 - 1; j > x1; j--)
						if (!cells[j][y0].getIsEmpty()) return false;
			}
			
				
		}
		
		if (cells[x1][y1].getIsEmpty()) return true;
		else if ((cells[x0][y0].getFig() < 'Z' && cells[x1][y1].getFig() > 'Z') ||
				(cells[x0][y0].getFig() > 'Z' && cells[x1][y1].getFig() < 'Z')) return true;
		return false;
	}
	
	
	public static Cell[][] getStartTable(){
		// name of figure: upper-case = white figures, lowercase = black figures
		// P,p - pawn, R,r - rook, N,n - knight, B,b - bishop, K,k - king, Q,q - queen
		Cell[][] cells = getTable();
		cells[0][7].setFig('r');
		cells[1][7].setFig('n');
		cells[2][7].setFig('b');
		cells[3][7].setFig('q');
		cells[4][7].setFig('k');
		cells[5][7].setFig('b');
		cells[6][7].setFig('n');
		cells[7][7].setFig('r');
		for (int i = 0; i < 8; i++){
			cells[i][6].setFig('p'); cells[i][6].setIsEmpty(false);
			cells[i][1].setFig('P'); cells[i][1].setIsEmpty(false);
			cells[i][7].setIsEmpty(false);
			cells[i][0].setIsEmpty(false);
		}		
		cells[0][0].setFig('R');
		cells[1][0].setFig('N');
		cells[2][0].setFig('B');
		cells[3][0].setFig('Q');
		cells[4][0].setFig('K');
		cells[5][0].setFig('B');
		cells[6][0].setFig('N');
		cells[7][0].setFig('R');	
		
		return cells;
	}
	
	
	public static Cell[][] getTable(){
		Cell[][] cells = new Cell[8][8];
		for (int i = 8; i > 0; i--){
			for (char j = 'a'; j <= 'h'; j++){
				cells[j-97][i-1] = new Cell(j,i);
				if (cells[j-97][i-1].getIsEmpty() == true){
					if ((j % 2 == 1 && i % 2 == 1)||(j % 2 == 0 && i % 2 == 0)){
						cells[j-97][i-1].setFig('#');
					}
					else cells[j-97][i-1].setFig(' ');
				}		
			}
		}	
		return cells;
	}
	
	public static void showTable(Cell[][] cells){
		for (int i = 8; i > 0; i--){
			System.out.print(Integer.toString(i) + '|');
			for (char j = 'a'; j <= 'h'; j++){
				System.out.print(Character.toString(cells[j-97][i-1].getFig()) + '|');
			}
			System.out.print('\n');			
		}
		System.out.println("  a b c d e f g h ");
	}
}
