/* Code written by :
 * Name        :Peeyush Shankhareman
 * Student No. :14200344
 */

package game;

public class Evaluate {
	public static int evaluate(String[][] gameBoard) {
		int score =0;
		int coeffTwo = 1;
		int coeffThree = 1;
		int coEffTwoUnFav = 1;
		int coeffThreeUnFav = 1;
		int[] noOfOccurance1 = getNoOfOccurances("x", gameBoard);
		int[] noOfOccurance2 = getNoOfOccurances("o", gameBoard);
		
		if(GlobalVals.symbol.equals("x")){
			if(noOfOccurance1[0]>=1){
				return 10000;
			}
			if(noOfOccurance2[0]>=1){
				return -10000;
			}
			coeffTwo=1;
			coeffThree=1;
			coEffTwoUnFav=-1;
			coeffThreeUnFav=-1;
		}
		else{
			if(noOfOccurance1[0]>=1){
				return -10000;
			}
			if(noOfOccurance2[0]>=1){
				return 10000;
			}
			coeffTwo=-1;
			coeffThree=-1;
			coEffTwoUnFav=1;
			coeffThreeUnFav=1;
		}
		
		
		score = coeffTwo*50*noOfOccurance1[1] + noOfOccurance2[1]*coEffTwoUnFav*50 + 
				coeffThree*300*noOfOccurance1[2] + coeffThreeUnFav*noOfOccurance2[2]*300;
		return score;
	}
	
	public static int[] getNoOfOccurances(String lsymbol, String[][] gameBoard) {
		int noOfTwos=0;
		int noOfThrees = 0;
		int noOfFours=0;
			
		int noOfBlankSpaces= 0;
		//Check row wise
				for(int i=4;i>=0;i--){
					int counter=0;
					for(int j=0;j<7;j++){
						//System.out.println("Symbol Found :" + gameBoard[i][j]);
						//System.out.println("Symbol looking for :"+GlobalVals.symbol);
						//System.out.println("a");
						if(gameBoard[i][j].equals(""))
							noOfBlankSpaces++;
						if(gameBoard[i][j].equals(lsymbol)){
							counter++;
							if(counter == 4){
								noOfFours++;
							}
							if(counter ==3){
								if(j+1<7)
									if(gameBoard[i][j+1].equals("") || noOfBlankSpaces>=1){
										noOfThrees++;
									}
							}
							if(counter==2){
								if(j+2<7)
									if(((gameBoard[i][j+1].equals("") && gameBoard[i][j+2].equals(""))) || noOfBlankSpaces>=2){
										noOfTwos++;
									}
							}
							noOfBlankSpaces=0;
						}
						else
							counter=0;
						
						//System.out.println("Counter : "+counter);
					}
					
					
				}
				
				//Check column wise
				for(int i=0;i<7;i++){
					int counter=0;
					for(int j=4;j>=0;j--){
						
						if(gameBoard[j][i].equals(""))
							noOfBlankSpaces++;
						if(gameBoard[j][i].equals(lsymbol)){
							counter++;
							//counter++;
							if(counter == 4){
								noOfFours++;
							}
							if(counter ==3){
								if(j-1>=0)
									if(gameBoard[j-1][i].equals("") || noOfBlankSpaces>=1){
										noOfThrees++;
									}
							}
							if(counter==2){
								if(j-2>=0)
									if((gameBoard[j-1][i].equals("") && gameBoard[j-2][i].equals("")) || noOfBlankSpaces>=2){
										noOfTwos++;
									}
							}
							noOfBlankSpaces=0;
						}
						else
							counter=0;
					}
					
				}

				//check diagonally
				for(int j=0;j<4;j++){
					for(int i=4;i>2;i--){
						int counter =0;
						noOfBlankSpaces=0;
						int x=j, y=i;
						do{
								//System.out.println(gameBoard[y][x]);
								if(gameBoard[y][x].equals(""))
									noOfBlankSpaces++;
								if(gameBoard[y][x].equals(lsymbol)){
									counter++;
									//System.out.println(counter);
									
									if(counter == 4){
										noOfFours++;
									}
									else if(counter==3){
										if(y-1>=0 && x+1<7)
											if(gameBoard[y-1][x+1].equals("")){
												noOfThrees++;
											}
									}
									else if (counter ==2){
										if(y-2>=0 && x+2<7)
											if(gameBoard[y-1][x+1].equals("") && gameBoard[y-2][x+2]=="")
												noOfTwos++;
									}
									noOfBlankSpaces=0;
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
						noOfBlankSpaces=0;
						int x=j, y=i;
						do{
								if(gameBoard[y][x].equals(""))
									noOfBlankSpaces++;
								if(gameBoard[y][x].equals(lsymbol)){
									counter++;
									if(counter == 4){
										noOfFours++;
									}
									else if(counter==3){
										if(y-1>=0 && x-1>=0)
											if(gameBoard[y-1][x-1].equals("")){
												noOfThrees++;
											}
									}
									else if (counter ==2){
										if(y-2>=0 && x-2>=0)
											if(gameBoard[y-1][x-1].equals("") && gameBoard[y-2][x-2].equals(""))
												noOfTwos++;
									}
									noOfBlankSpaces=0;
								}
								else
									counter=0;
							x--;
							y--;
						}while(x>=0 && y>=0);
					}
				}
				/*System.out.println("no4 :" +noOfFours +"no3: "+ noOfThrees+"no2 ; "+noOfTwos);*/ 
		int[] state= {	noOfFours, noOfThrees, noOfTwos};
		return state;
	}
}
