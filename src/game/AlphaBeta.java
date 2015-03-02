/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */


package game;

import java.util.List;

public class AlphaBeta {
	
	public static int alphaBetaNegaMax(String[][] gameBoard, int depth, int maxDepth, int alpha, int beta, boolean user, boolean print) {
		
		if(user)
			GlobalVals.symbol="x";
		else 
			GlobalVals.symbol="o";
		
		String[][] boardGame = new String[5][7];
		for(int i=0;i<=4;i++){
			for(int j=0;j<=6;j++){
				boardGame[i][j] = new String(gameBoard[i][j]);
			}
		}
		
		List<String> moves  = Utils.legalMoveGenerator(boardGame);
		
		int score =0;
		if(depth==maxDepth || moves==null){
			
			score = Evaluate.evaluate(boardGame);
			GlobalVals.staticEvalCount++;
			System.out.println("Level :" + depth + ", Alpha :" +alpha + ", Beta: "+beta 
					+", Value Calculated : "+score+ ", Method :  Static evaluation");
			return score;
		}
		
		for(String move : moves){
			System.out.println(move);
			GlobalVals.noOfNodesVisited++;
			boardGame = Utils.updateMoveArray(move, boardGame);
			score = -alphaBetaNegaMax(boardGame,depth+1, maxDepth, -beta, -alpha, !user, print);
			boardGame=Utils.undoMove(boardGame, move);
			
			if(score>=GlobalVals.max){
				GlobalVals.max = score;
				GlobalVals.compMove = move;
			}
			
			if(score > alpha){
				alpha=score;
			}
			if(score>=beta){
				System.out.println("Cutoff has occured at: "+ move);
				return score;
			}
		}
		return score;
	}
}