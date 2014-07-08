//Matthew Butler
//CS 280: Programming Languages Concepts
// Section CS - 280-102
// Professor Kapleau
import java.io.*;

public class Knights {
	static int moves = 8;
	static int boardspaces = 8;

	static int[][] chessboard = new int[boardspaces][boardspaces];
	static int board[][] = { { 2, 1 }, { 2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
			{ -1, -2 }, { -2, 1 }, { -2, -1 } };

	static boolean emptyRange(int xcor, int ycor) {
		return (xcor < boardspaces && xcor >= 0 && ycor < boardspaces
				&& ycor >= 0 && chessboard[xcor][ycor] == 0);
	}

	static int moveAccess(int x, int y) {
		int acessSpaceNumber = 0;
		int loop = 0;
		for (; loop < moves; loop++)
			if (emptyRange(x + board[loop][0], y + board[loop][1]))
				acessSpaceNumber++;

		return acessSpaceNumber;
	}

	static 	void findNextPosition(int boardd[]) {
		int nextx = boardd[0];
		int nexty = boardd[1];
		int x = 0, y = 0, aN = 0, loop = 0;
		int aNum = moves;
		for (; loop < moves; loop++) {
			x = nextx + board[loop][0];
			y = nexty + board[loop][1];
			aN = moveAccess(x, y);
			if (emptyRange(x, y) && aN < aNum) {
				boardd[0] = x;
				boardd[1] = y;
				aNum = aN;
			}

		}

	}

	public static void main(String[] args) {
		int positionx = 0;
		int positiony = 0;
		int moveNum = 2;
		int[] move = new int[2];
		int loop = 0, loops = 0;

		for (; loop < boardspaces; loop++){
			for (; loops < boardspaces; loops++){
				chessboard[loop][loops] = 0;
		chessboard[positionx][positiony] = 1;
			}}
		for (loop = 1; loop < boardspaces * boardspaces; loop++) {
			move[0] = positionx;
			move[1] = positiony;
			findNextPosition(move);
			positionx = move[0];
			positiony = move[1];
			chessboard[positionx][positiony] = moveNum;
			moveNum++;
		}

		for (loop = 0; loop < boardspaces; loop++) {
			for (loops = 0; loops < boardspaces; loops++) {

			
			System.out.print(chessboard[loop][loops]+"  ");
		}
		System.out.println("\n");}

	}
}
