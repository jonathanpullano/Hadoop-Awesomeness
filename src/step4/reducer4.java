package step4;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.Tuple;

public class reducer4 extends
		Reducer<IntWritable, IntWritable, IntWritable, Tuple> {
	@Override
	protected void reduce(
			final IntWritable key,
			final Iterable<IntWritable> values,
			final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
			throws IOException, InterruptedException {

		final int num_of_edges = 0;
		int num_of_nodes = 0;

		for (final IntWritable value : values) {
			num_of_nodes++;
		}

		final Tuple out_tuple = new Tuple(num_of_nodes, num_of_edges);
		context.write(key, out_tuple);
	}
}
