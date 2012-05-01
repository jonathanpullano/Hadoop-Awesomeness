package step4;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map4 extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
	private final Text word = new Text();

	@Override
	public void map(final LongWritable key, final Text value,
			final Context context) throws IOException, InterruptedException {

		final String line = value.toString();
		final StringTokenizer tokenizer = new StringTokenizer(line);

		while (tokenizer.hasMoreTokens()) {
			// 1. get p
			word.set(tokenizer.nextToken());
			final int p = Integer.parseInt(word.toString());
			final IntWritable p_out = new IntWritable(p);

			// 3. get q
			word.set(tokenizer.nextToken());
			final int q = Integer.parseInt(word.toString());
			final IntWritable q_out = new IntWritable(q);

			context.write(q_out, p_out);
		}
	}
}
