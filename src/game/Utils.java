/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */

package game;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static boolean inputValidator(String move, String[][] gameBoard){
		boolean isMoveValid = true;
		
		if(move.length() != 2)
			return false;
		int moveCol = Integer.parseInt(Character.toString(move.charAt(1)));
		
		if(!(move.charAt(0) == 'd') && !(move.charAt(0) == 'v')) 
			return false;
		
		if( moveCol<1 || moveCol>7)
			return false;
		
		if(!isColEmpty(moveCol-1, gameBoard))
			return false;
		
		return isMoveValid;
	}
	
	public static boolean isColEmpty(int col, String[][] gameBoard) {
		Boolean isColEmpty = false;
		int counter=5;
		for(int i=0;i<5;i++){
			if(!gameBoard[i][col].equals(""))
				counter--;
		}
		if(counter>0)
			isColEmpty = true;
		
		return isColEmpty;
	}
	
	public static void printGame() {
		String[][] gameState = GlobalVals.board;
		
		printRowSeparator();
		
		for(int i =0; i<gameState.length;i++){
			System.out.print("|");
			for(int j=0;j<gameState[0].length;j++){
				if(gameState[i][j]!="")
					System.out.print(" "+gameState[i][j]+"  |");
				else
					System.out.print("    |");
			}
			System.out.println("");
			printRowSeparator();
		}
		
	}
	
	public static void printRowSeparator() {

		StringBuilder rowBuilder = new StringBuilder();
		int colWidth = 0 ;
		for (int i = 0 ; i < 7 ; i ++) {
			
			colWidth = 2 + 3;
			
			for (int j = 0; j < colWidth ; j ++) {
				if (j==0) {
					rowBuilder.append("+");
				} else if ((i+1 == 7 && j+1 == colWidth)) {//for last column close the border
					rowBuilder.append("-+");
				} else {
					rowBuilder.append("-");
				}
			}
		}
		System.out.print(rowBuilder.append("\n").toString());
	}
	
	public static String[][] updateMoveArray(String move, String[][] gameBoard){
		
		boolean isMoveValid = false;
		isMoveValid=inputValidator(move, gameBoard);
		if(!isMoveValid)
			return null;
		if(move.charAt(0) == 'v')
			GlobalVals.symbol="*";
		int col = Integer.parseInt(Character.toString(move.charAt(1)));
		for(int i=0;i<5;i++){
			for(int j=0;j<7;j++){
				if(gameBoard[i][j].equals("*"))
					gameBoard[i][j] = "";
			}
		}
		
		GlobalVals.movesHistory.add(move);
		for(int i=4;i>=0;i--){
			if(gameBoard[i][col-1].equals("")){
				gameBoard[i][col-1]=GlobalVals.symbol;
				break;
			}
		}
		return gameBoard;
	}
	
	public static List<String> legalMoveGenerator(String[][] gameBoard){
		List<String> legalmoves = new ArrayList<String>();
		int counter =-1;
		for(int i=0; i<7; i++){
			counter =-1;
			if(isColEmpty(i, gameBoard)){
				for(int j=0;j<5;j++){
					if(gameBoard[j][i].equals("*")){
						counter=0;
						break;
					}
				}
				if(counter!=0)
					legalmoves.add("d"+(i+1));
				if(isVetoLegal())
					legalmoves.add("v"+(i+1));
			}
		}
		return legalmoves;
	}
	
	public static boolean isVetoLegal() {
		boolean isVetoLegal = true;
		
		if(!GlobalVals.movesHistory.isEmpty()){
			List<String> lastTwoMoves = new ArrayList<String>();
			int sizeMoves = GlobalVals.movesHistory.size();
			lastTwoMoves.add(GlobalVals.movesHistory.get(sizeMoves-1));
			if(sizeMoves>1)
				lastTwoMoves.add(GlobalVals.movesHistory.get(sizeMoves-2));
			for(String m : lastTwoMoves){
				if(m.contains("v")){
					isVetoLegal=false;
					break;
				}
			}
		}
		
		return isVetoLegal;
	}
	
	public static boolean isWin(String[][] gameBoard, String player) {
		boolean isWin=false;
		
		//Check row wise
		for(int i=4;i>=0;i--){
			int counter=0;
			for(int j=0;j<7;j++){
				if(gameBoard[i][j].equals(player)){
					counter++;
					if(counter == 4){
						isWin = true;
						return isWin;
					}
				}
				else
					counter=0;
			}	
		}
		
		//Check column wise
		for(int i=0;i<7;i++){
			int counter=0;
			for(int j=4;j>=0;j--){
				
				if(gameBoard[j][i].equals(player)){
					counter++;
					if(counter == 4){
						isWin = true;
						return isWin;
					}
				}
				else
					counter=0;
			}
			
		}

		//check diagonally
		for(int j=0;j<4;j++){
			for(int i=4;i>2;i--){
				int counter =0;
				int x=j, y=i;
				do{
						if(gameBoard[y][x].equals(player)){
							counter++;
							if(counter == 4){
								isWin = true;
								return isWin;
							}
						}
						else
							counter=0;
					x++;
					y--;
				}while(x<7 && y>=0);
			}
		}
		for(int j=6;j>2;j--){
			for(int i=4;i>2;i--){
				int counter =0;
				int x=j, y=i;
				do{
						if(gameBoard[y][x].equals(player)){
							counter++;
							if(counter == 4){
								isWin = true;
								return isWin;
							}
						}
						else
							counter=0;
					x--;
					y--;
				}while(x>=0 && y>=0);
			}
		}
		return isWin;
	}
	
	public static String[][] undoMove(String[][] gameBoard, String move) {
		int col = Integer.parseInt(Character.toString(move.charAt(1)));
		for(int i=0; i<=4;i++){
			if(!(gameBoard[i][col-1] .equals(""))){
				gameBoard[i][col-1] = "";
				break;
			}
		}
		GlobalVals.movesHistory.remove(move);
		return gameBoard;
	}
}