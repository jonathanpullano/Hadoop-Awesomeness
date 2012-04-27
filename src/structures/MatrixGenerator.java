package structures;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import constants.Constants;

public class MatrixGenerator {

	// compute filter parameters for netid ak883
	private static float fromNetID = 0.388f;
	private static float desiredDensity = 0.59f;
	private static float wMin = 0.4f * fromNetID;
	private static float wLimit = wMin + desiredDensity; 
	
	static Scanner scanner = null;
	private float[][] matrix = null;
	File file = null;
	int size;
	
	public MatrixGenerator(String filename) throws IOException{
		file = new File(filename);
		scanner = new Scanner(file);
		size = getMatrixSize();
		matrix = new float[size][size];
	}

	private int getMatrixSize() throws IOException{
		LineNumberReader  lnr = new LineNumberReader(new FileReader(file));
		lnr.skip(Long.MAX_VALUE);
		System.out.println("Lines numbers: " + lnr.getLineNumber());
		return (int) Math.sqrt(lnr.getLineNumber());
	}
	// assume 0.0 <= wMin <= wLimit <= 1.0
	private float getNextFilteredInput() {
		float w = nextFloat();

		if(w == -1){
			System.out.println("Reached end of file!");
			System.exit(0);
		}

		return ( ((w >= wMin) && (w < wLimit)) ? w : 0.000000000f );
	}
	
	public void buildMatrix(){
		for( int j = 0; j < size; j++ ) {
			for( int i = 0; i < j; i++ ) {
				int col = i;	// For readability
				int row = j;
				matrix[col][row] = getNextFilteredInput();
				col = j;	// Switch em up!
				row = i;
				matrix[col][row] = getNextFilteredInput();
			}
			matrix[j][j] = getNextFilteredInput();
		}
	}
	
	public void printMatrix(){
		System.out.println("Matrix size: " + size);
		for(int row = 0;  row < size; row++){
			for(int col = 0; col < size; col++)
				System.out.print(((matrix[col][row]))+ " ");
			System.out.println("");
		}
	}
	
	public void printPositions(){
		System.out.println("");
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				int pos = (col*size)+(row+1);
				String space = "";
				if (pos<10)
					space = "0";
				System.out.print(space + pos + " ");
			}
			System.out.println("");
		}
	}
	
	private float nextFloat(){
		float value = -1;
		if(scanner.hasNext())
			value = Float.parseFloat(scanner.next());
		else 
			scanner.close();
		return value;
	}
	
	private static void newTest(int M, int G){
		int[][] matrix1 = new int[M][M]; // this is the order that the values are added
		int[][] matrix2 = new int[M][M]; // this is the new value of the vertices
		String[][] matrix3 = new String[M][M];
		
		int div = M/G; 
		
		for(int i=1; i<M*M+1; i++){
			int sq = (int)Math.sqrt(i); 
			int diff = i - sq*sq;
			int col,row;
			if (diff==0){
			    col = sq - 1;
			    row = sq - 1;
			}
			    
			else if (diff%2 == 0){
			    col = sq;
			    row = (diff-1)/2;
			}

			else{
			    col = (diff-1)/2;
			    row = sq;
			}
			
			int column_group = Math.min(col/div, G-1);
			String group = "" + column_group;
			
			int prev_group = Math.min((col-1)/div, G-1); 
			if (col>0 && prev_group!=column_group)
				group = prev_group + "," + group;
			
			int next_group = Math.min((col+1)/div, G-1); 
			if (col<M && next_group!=column_group)
				group = group + ","+ next_group;
			
			matrix1[col][row] = i;
			matrix2[col][row] = (col*M)+(row+1);
			matrix3[col][row] = group;  
		}
		/*
		System.out.println("Position at which i is added:");
		for(int row = M-1;  row >-1; row--){
			for(int col = 0; col < M; col++)
				System.out.print(((matrix1[col][row]))+ " ");
			System.out.println("");
		}
		*/
		/*
		System.out.println("");
		System.out.println("Value given to the vertex at each position:");
		for(int row = M-1;  row >-1; row--){
			for(int col = 0; col < M; col++)
				System.out.print(((matrix2[col][row]))+ " ");
			System.out.println("");
		}
		*/
		/*
		System.out.println("");
		System.out.println("Col:");
		for(int row = M-1;  row >-1; row--){
			for(int col = 0; col < M; col++)
				System.out.print(((matrix3[col][row]))+ " ");
			System.out.println("");
		}
		*/
		System.out.println("");
		System.out.println("Columns:");
		for(int row = M-1;  row >-1; row--){
			for(int col = 0; col < M; col++){
				int c = (matrix2[col][row]-1)/M;
				System.out.print(c + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("Border Columns:");
		for(int row = M-1;  row >-1; row--){
			for(int col = 0; col < M; col++){
				int p = matrix2[col][row];
				int c = getCol(M, p);
				int out;
				int column_group = getColGroup(M, G, c);
				if (c==0 || c==M-1)
					out=0;
				else if (column_group!=getColGroup(M, G, c+1) || column_group!=getColGroup(M, G, c-1))
					out=1;
				else 
					out=0;
				System.out.print(out + " ");
			}
			System.out.println("");
		}
	}
	
	public static int getCol(int M, int p){
		return (p-1)/M;
	}
	
	public static int getColGroup(int M, int G, int col){
		int column_group = col/(M/G);
		if (column_group==G) column_group--;
		return column_group;
	}


	public static void main(String[] args) throws IOException{
		/*
		System.out.println("Min: " + wMin);
		System.out.println("Min: " + wLimit);
		mg.buildMatrix();
		mg.printMatrix();
		mg.printPositions();
		*/
		newTest(16, 3);
	}
}
