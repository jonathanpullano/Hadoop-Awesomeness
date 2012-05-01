package step4;

import java.io.IOException;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.Tuple;
import constants.Constants;

public class Reducer4 extends
		Reducer<IntWritable, IntWritable, IntWritable, Tuple> {

	final int height = Constants.M;

	@Override
	protected void reduce(
			final IntWritable key,
			final Iterable<IntWritable> values,
			final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
			throws IOException, InterruptedException {

		int num_of_edges = 0;
		int num_of_nodes = 0;

		final TreeSet<Integer> component = new TreeSet<Integer>();

		for (final IntWritable value : values) {
			component.add(value.get());
			num_of_nodes++;
		}

		for (final int i : component) {
			if (((i % height) != 0) && component.contains(i + 1)) {
				num_of_edges++;
			}
			if (component.contains(i + height)) {
				num_of_edges++;
			}
		}

		final Tuple out_tuple = new Tuple(num_of_nodes, num_of_edges);
		context.write(new IntWritable(1), out_tuple);
		// context.write(key, out_tuple);
	}
}
