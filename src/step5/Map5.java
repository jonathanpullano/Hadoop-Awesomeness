package step5;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.Tuple;

public class Map5 extends Mapper<LongWritable, Text, IntWritable, Tuple> {

	private final Text word = new Text();

	@Override
	public void map(final LongWritable key, final Text value,
			final Context context) throws IOException, InterruptedException {

		int num_nodes, num_edges;

		// 1. get line
		final String line = value.toString();
		final StringTokenizer tokenizer = new StringTokenizer(line);

		// 2. extract q, num_nodes, and num_edges
		word.set(tokenizer.nextToken()); // not used

		word.set(tokenizer.nextToken());
		num_nodes = Integer.parseInt(word.toString());

		word.set(tokenizer.nextToken());
		num_edges = Integer.parseInt(word.toString());

		// 3. emit 1, num_nodes, and num_edges
		final Tuple out = new Tuple(num_nodes, num_edges);
		context.write(new IntWritable(1), out);
	}
}
