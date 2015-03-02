/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */

package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Killer_Heuristic {
	
	static Map<Integer, Map<String, Integer>> kMap = new HashMap<Integer, Map<String,Integer>>();
	
	public static int alphaBetaNegaMaxKiller(String[][] gameBoard, Integer depth, int maxDepth, int alpha, int beta, 
			boolean user, boolean print) {
		
		if(kMap.get(depth)==null){
			kMap.put(depth, new HashMap<>(initializeMap()));
		}
		
		if(user)
			GlobalVals.symbol="x";
		else 
			GlobalVals.symbol="o";
		
		String[][] boardGame = new String[5][7];
		System.out.println("Depth: "+depth);
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
			if(print){
				System.out.println("Level :" + depth + ", Alpha :" +alpha + ", Beta: "+beta 
						+", Value Calculated : "+score+ ", Method :  static evaluation");
			}
			return score;
		}
		
		
		moves = killerHeuristic(moves, depth);
	
		
		for(String move : moves){
			/*System.out.println(move);*/
			GlobalVals.noOfNodesVisited++;
			boardGame = Utils.updateMoveArray(move, boardGame);
			score = -alphaBetaNegaMaxKiller(boardGame,depth+1, maxDepth, -beta, -alpha, !user, print);
			/*System.out.println(score);*/
			boardGame=Utils.undoMove(boardGame, move);
			if(score>=GlobalVals.max){
				GlobalVals.max = score;
				GlobalVals.compMove = move;
			}
			System.out.println("max : "+ GlobalVals.max);
			if(score > alpha){
				alpha=score;
			}
			if(score >= beta){
				System.out.println("Cutoff has occured at: "+move);
				kMap.get(depth).put(move, kMap.get(depth).get(move)+1);
				return score;
			}
		}
		return score;
	}
	
	public static List<String> killerHeuristic (List<String> mKiller, Integer depth) {
			
		List<Integer> val = new ArrayList<>();
		List<String> updatedMoves = new ArrayList<String>();
		for(String m : mKiller){
			val.add(kMap.get(depth).get(m));
		}
		for(int i=0;i<mKiller.size();i++){
			Integer max = Collections.max(val);
			if(max==0){
				updatedMoves.addAll(mKiller);
				break;
			}
			val.remove(max);
			
			for(String key : kMap.get(depth).keySet()) {
				if(mKiller.contains(key)){
		            Integer value = kMap.get(depth).get(key);
		            if(value==max){
		            	updatedMoves.add(key);
		            	mKiller.remove(key);
		            	break;
		            }
				}
	        }
			
		}
		return updatedMoves;
	}
	public static Map<String, Integer> initializeMap() {
		Map<String, Integer> mInitialized = new HashMap<>();
		mInitialized.put("d1", 0);
		mInitialized.put("v1", 0);
		mInitialized.put("d2", 0);
		mInitialized.put("v2", 0);
		mInitialized.put("d3", 0);
		mInitialized.put("v3", 0);
		mInitialized.put("d4", 0);
		mInitialized.put("v4", 0);
		mInitialized.put("d5", 0);
		mInitialized.put("v5", 0);
		mInitialized.put("d6", 0);
		mInitialized.put("v6", 0);
		mInitialized.put("d7", 0);
		mInitialized.put("v7", 0);
		
		return mInitialized;
	}
}