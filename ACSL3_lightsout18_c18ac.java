import java.util.*;


public class ACSL3_lightsout18_c18ac {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String board = scan.nextLine();
		String initialboard = "";
		
		for(int x = 0; x < 6; x++) {
			initialboard = board;
			board = scan.nextLine();
			Integer [][] initialboardmap = makeMap(initialboard);
			Integer [][] boardmap = makeMap(board);
			Integer [][] changeMap = changeMap(initialboardmap, boardmap);
			System.out.println(answer(changeMap));

		}
		
	}
	
	public static void printMap (Integer [][] dataMap) {
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				System.out.print(dataMap[i][j]);
			}
			System.out.println();
		}
	}
	
	public static Integer[][] makeMap (String s) {
	
		String [] boardArray = s.split(" ");
		String tempBin = "";
		String binaryboard = "";

		for (int i = 0; i < boardArray.length; i++) {
			int num = Integer.parseInt(boardArray[i],16);
			tempBin = Integer.toBinaryString(num);
			while (tempBin.length() < 16){
				tempBin = "0" + tempBin;
			}
			binaryboard = binaryboard + tempBin;
		}
		
		int a = 0;
		int b = 0;
		Integer [] [] map = new Integer [8][8];
		
		for (int i = 0; i < binaryboard.length(); i++) {
			a = i % 8;
			b = (i-a)/8;
			map [a][b] = Integer.parseInt(Character.toString((binaryboard.charAt(i))));
		}
		return map;
	}
	
	public static Integer [][] changeMap (Integer [][] initialboardmap, Integer [][] boardmap) {
		Integer [][] changeMap = new Integer [8][8];
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				changeMap [i][j] = Math.abs(initialboardmap[i][j] - boardmap[i][j]);
			}
		}
		return changeMap;
	}
	
	public static String answer (Integer [][] changeMap) {
		String answer = "";
		int maxrow = 0;
		int maxrowvalue = 0;
		int maxcolumn = 0;
		int maxcolumnvalue = 0;
		Integer [] rowArray = new Integer [8];
		Integer [] columnArray = new Integer [8];
		Arrays.fill(columnArray, 0);
		Arrays.fill(rowArray, 0);

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				columnArray[i] = columnArray[i] + changeMap[i][j];
				rowArray[j] += changeMap[i][j];
			}
		}
		
		for (int i = 0; i < 8; i++){
			if (maxrowvalue < rowArray[i]){
				maxrowvalue = rowArray[i];
				maxrow = i;
			}
			if (maxcolumnvalue < columnArray[i]){
				maxcolumnvalue = columnArray[i];
				maxcolumn = i;
			}
		}
		return (maxrow+1) + "" + (maxcolumn+1);
	}
}