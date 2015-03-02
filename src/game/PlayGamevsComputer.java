/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */

package game;

import java.util.Scanner;

public class PlayGamevsComputer {
	
	public static String userMove;
	public static void main(String[] args) {
		//boolean isMoveValid = Utils.inputValidator("d1");
		//System.out.println(isMoveValid);
		boolean user=false;
		boolean isWin = false;
		boolean validInput = true;
		do{
			System.out.println("Who Plays First? ");
			System.out.println("1. User");
			System.out.println("2. Computer");
			Scanner a = new Scanner(System.in);
			String choice= a.nextLine();
			switch (choice) {
			case "1":
				user = true;
				validInput = true;
				break;

			case "2":
				user = false;
				validInput = true;
				break;

			default:
				System.out.println("Please select a valid choice...");
				validInput = false;
				break;
			}
		}while(!validInput);
		
		Utils.printGame();
		do{
			GlobalVals.staticEvalCount=0;
			if(user){
				GlobalVals.symbol="x";
				Scanner a = new Scanner(System.in);
				userMove = a.nextLine();
				user = false;
			}
			else{
				
				/*Scanner a = new Scanner(System.in);
				userMove = a.nextLine();*/
				GlobalVals.staticEvalCount=0;
				GlobalVals.noOfNodesVisited =0;
				AlphaBeta.alphaBetaNegaMax(GlobalVals.board, 0,6, -10000, 10000, user, true);
				//Killer_Heuristic.alphaBetaNegaMaxKiller(GlobalVals.board, 0, 8, -10000, 10000, user, true);
				userMove = GlobalVals.compMove;
				GlobalVals.symbol="o";
				user = true;
				System.out.println("Number of static evals"+GlobalVals.staticEvalCount);
			}
			System.out.println("Final Move: "+userMove);
			System.out.println("No of Nodes visisted : " + GlobalVals.noOfNodesVisited);
			//Utils.printGame();
			
			String[][] intermediateBoard =Utils.updateMoveArray(userMove, GlobalVals.board); 
			if( intermediateBoard == null){
				System.out.println("Invalid Move. Please re-enter move");
				user = !user;
				continue;
			}
			else {
				GlobalVals.board = intermediateBoard;
				System.out.println("global board updated");
			}
			isWin = Utils.isWin(GlobalVals.board, GlobalVals.symbol);
			if(isWin){
				Utils.printGame();
				if(!user)
					System.out.println("User wins");
				else
					System.out.println("Computer wins");
				break;
			}
			
			Utils.printGame();
		}while(!isWin);
		
	}
}
