package structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class MatrixGenerator {

	// compute filter parameters for netid ak883
	private static float fromNetID = 0.388f;
	private static float desiredDensity = 0.59f;
	private static float wMin = 0.4f * fromNetID;
	private static float wLimit = wMin + desiredDensity; 
	
	static Scanner scanner = null;
	private boolean[][] matrix = null;
	File file = null;
	int N;
	
	public MatrixGenerator(String filename) throws IOException{
		file = new File(filename);
		scanner = new Scanner(file);
		N = getMatrixSize();
		matrix = new boolean[N][N];
	}

	private int getMatrixSize() throws IOException{
		LineNumberReader  lnr = new LineNumberReader(new FileReader(file));
		lnr.skip(Long.MAX_VALUE);
		System.out.println("Lines numbers: " + lnr.getLineNumber());
		return (int) Math.sqrt(lnr.getLineNumber());
	}
	// assume 0.0 <= wMin <= wLimit <= 1.0
	private boolean getNextFilteredInput() {
		float w = nextFloat();

		if(w == -1){
			System.out.println("Reached end of file!");
			System.exit(0);
		}

		return ( ((w >= wMin) && (w < wLimit)) ? true : false );
	}
	
	public void buildMatrix(){
		for( int j = 0; j < N; j++ ) {
			for( int i = 0; i < j; i++ ) {
				matrix[i][j] = getNextFilteredInput();
				matrix[j][i] = getNextFilteredInput();
			}
			matrix[j][j] = getNextFilteredInput();
		}
	}
	
	public void printMatrix(){
		System.out.println("Matrix size: " + N);
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++)
				System.out.print(((matrix[j][i])? 1 : 0) + " ");
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

	public static void main(String[] args) throws IOException{
		MatrixGenerator mg = new MatrixGenerator("data/data2.txt");
		mg.buildMatrix();
		mg.printMatrix();
	}
}
