package step2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;

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
			
			if (MatrixUtilities.isBoundary(p)!=0){
				context.write(new IntWritable(p), new IntWritable(q));
			}
		}
	}
	
	
}
