package step1;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author chris d
 *
 */
public class Map1 extends Mapper<LongWritable, Text, IntWritable, LongWritable> {
	//private IntWritable p = new IntWritable();
	//private IntWritable G = new IntWritable();
	private Text word = new Text();

	// compute filter parameters for netid ak883
	private static float fromNetID = 0.388f;
	private static float desiredDensity = 0.59f;
	private static float wMin = 0.4f * fromNetID;
	private static float wLimit = wMin + desiredDensity; 
	
	//TODO: THESE SHOULD NOT BE HARD CODED!!
	private static int M = 100; 									// Num of rows,cols
	private static int G = 4;									// Num of column groups

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		while (tokenizer.hasMoreTokens()) {
			//1. get the next word
			word.set(tokenizer.nextToken());
			
			//2. Convert the word to a float
			Float val = Float.parseFloat(word.toString());	
			
			//3. Test the float, add the vertex if it passes, do nothing otherwise
			if (((val >= wMin) && (val < wLimit))){
				
				//a. find entry number
				long N = key.get()/12;	
				
				//b. calculate (col,row) where this entry should be placed
				long sq = (long)Math.sqrt(N); 
				long diff = N - sq*sq;
				long col,row;
				
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
				long div = M/G;
				int col_group_int = (int)Math.min((col/div), G-1); 			// This is to correct rounding error in div, which makes last row(s) a new group
				
				IntWritable column_group = new IntWritable(col_group_int);
				LongWritable position = new LongWritable((col*M)+(row+1));
				
				// d. write out (G,p)
				context.write(column_group, position);
				
				// e. if N is on a group boundary, you must also add it to its neighboring group
				// previous group
				long prev_group = Math.min((col-1)/div, G-1); 
				if (col>0 && prev_group!=col_group_int){
					column_group = new IntWritable(col_group_int-1);
					context.write(column_group, position);
				}
				// next group
				long next_group = Math.min((col+1)/div, G-1); 
				if (col<M && next_group!=col_group_int){
					column_group = new IntWritable(col_group_int+1);
					context.write(column_group, position);
				}
			}
		}
		
	}
}
