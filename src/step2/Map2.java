package step2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import constants.Constants;
//import org.apache.hadoop.mapreduce.Mapper.Context;

public class Map2 extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
	private Text word_G = new Text();
	private Text word_p = new Text();
	private Text word_q = new Text();
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		while (tokenizer.hasMoreTokens()) {
			//1. get G, p and Q
			word_G.set(tokenizer.nextToken()); // not used
			word_p.set(tokenizer.nextToken());
			word_q.set(tokenizer.nextToken());

			int p = Integer.parseInt(word_p.toString());
			int q = Integer.parseInt(word_q.toString());
			
			if (isBoundary(p)){
				context.write(new IntWritable(p), new IntWritable(q));
			}
		}
	}
	
	private static boolean isBoundary(int position){
		int M = Constants.M;
		int g = Constants.g;
		int col = (position-1)/M;
		
		if (col==0 || col==M-1)
			return false;
		
		int column_group = getColGroup(M, g, col);
		
		if (column_group!=getColGroup(M, g, col-1) 
				|| column_group!=getColGroup(M, g, col+1))
			return true;
		else
			return false;
	}
	
	public static int getColGroup(int M, int G, int col){
		int column_group = col/(M/G);
		if (column_group==G) column_group--;
		return column_group;
	}
}
