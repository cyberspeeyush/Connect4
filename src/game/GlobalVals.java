/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */

package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalVals {
	public static String[][] board = {
		{"","","","","","", ""},
		{"","","","","","", ""},
		{"","","","","","", ""},
		{"","","","","","", ""},
		{"","","","","","", ""},
	};
	
	public static List<String> legalMoves = new ArrayList<String>();
	public static List<String> movesHistory = new ArrayList<String>();
	public static String symbol;
	public static int staticEvalCount;
	public static String compMove;
	public static int noOfNodesVisited;
	public static int max = Integer.MIN_VALUE;
}
