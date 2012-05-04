package step3;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Map3 extends Mapper<LongWritable, Text, IntWritable, Tuple> {

	@Override
	public void map(final LongWritable key, final Text value,
			final Context context) throws IOException, InterruptedException {
		final String line = value.toString();
		final StringTokenizer tokenizer = new StringTokenizer(line);
		int g, p, q;

		if (tokenizer.countTokens() == 3) { // from step 1: all values
			g = Integer.parseInt(tokenizer.nextToken());
			p = Integer.parseInt(tokenizer.nextToken());
			q = Integer.parseInt(tokenizer.nextToken());
			context.write(new IntWritable(g), new Tuple(p, p));
		} else if (tokenizer.countTokens() == 2) { // from step 2: border values
			p = Integer.parseInt(tokenizer.nextToken());
			q = Integer.parseInt(tokenizer.nextToken());
			g = MatrixUtilities.getColumnGroup(Constants.M, Constants.g,
					MatrixUtilities.getColumn(Constants.M, p));
			context.write(new IntWritable(g), new Tuple(p, q));
			context.write(new IntWritable(g - 1), new Tuple(p, q));

		}
	}
}