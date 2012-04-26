package step1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import constants.Constants;
/**
 * 
 * @author chris d
 * Reads in float values. Checks whether each value is in range. If it is, it assigns it a 
 * int representing its position (p) and a column group (G).
 */
public class Map1 extends Mapper<IntWritable, Text, IntWritable, IntWritable> {
	private Text word = new Text();

	@Override
	public void map(IntWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		while (tokenizer.hasMoreTokens()) {
			//1. get the next word
			word.set(tokenizer.nextToken());
			
			//2. Convert the word to a float
			Float val = Float.parseFloat(word.toString());	
			
			//3. Test the float, add the vertex if it passes, do nothing otherwise
			if (((val >= Constants.wMin) && (val < Constants.wLimit))){
				
				//a. find entry number
				int N = key.get()/12;	
				
				//b. calculate (col,row) where this entry should be placed
				int sq = (int)Math.sqrt(N); 
				int diff = N - sq*sq;
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
				
				// c. Once you know where it should be placed, calculate
				//    its column_group (G) and its absolute position (p)
				long div = Constants.M/Constants.g;
				int col_group_int = (int)Math.min((col/div), Constants.g-1); 			// This is to correct rounding error in div, which makes last row(s) a new group
				
				IntWritable column_group = new IntWritable(col_group_int);
				IntWritable position = new IntWritable((col*Constants.M)+(row+1));
				
				
				// d. write out (G,p)
				context.write(column_group, position);
				
				// e. if N is on a group boundary, you must also add it to its neighboring group
				// previous group
				long prev_group = Math.min((col-1)/div, Constants.g-1); 
				if (col>0 && prev_group!=col_group_int){
					column_group = new IntWritable(col_group_int-1);
					context.write(column_group, position);
				}
				
				// next group
				long next_group = Math.min((col+1)/div, Constants.g-1); 
				if (col<Constants.M && next_group!=col_group_int){
					column_group = new IntWritable(col_group_int+1);
					context.write(column_group, position);
				}
			}
		}
		
	}
}
