/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */


package game;

import java.util.Scanner;

public class PlayGame2Players {
	
	public static String userMove;
	public static void main(String[] args) {
		//boolean isMoveValid = Utils.inputValidator("d1");
		//System.out.println(isMoveValid);
		boolean user=true;
		boolean isWin = false;
		int c=0;
		Utils.printGame();
		do{
			if(user){
				GlobalVals.symbol="x";
				Scanner a = new Scanner(System.in);
				userMove = a.nextLine();
				user = false;
			}
			else{
				GlobalVals.symbol="o";
				Scanner a = new Scanner(System.in);
				userMove = a.nextLine();
				
				user = true;
			}
			String[][] intermediateBoard =Utils.updateMoveArray(userMove, GlobalVals.board); 
			if( intermediateBoard == null){
				System.out.println("Invalid Move. Please re-enter move");
				user = !user;
				continue;
			}
			else 
				GlobalVals.board = intermediateBoard; 
			//System.out.println(Utils.isWin());
			isWin = Utils.isWin(GlobalVals.board, GlobalVals.symbol);
			if(isWin){
				Utils.printGame();
				if(!user)
					System.out.println("User 1 wins");
				else
					System.out.println("User 2 wins");
				break;
			}
			//System.out.println("Count: "+c);
			//c++;
			Utils.printGame();
		}while(!isWin);
		
	}
}
