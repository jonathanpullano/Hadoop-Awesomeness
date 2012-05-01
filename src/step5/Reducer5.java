package step5;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import structures.Tuple;
import constants.Constants;

public class Reducer5 extends Reducer<IntWritable, Tuple, Text, FloatWritable> {

	int total_nodes = 0;
	int total_edges = 0;
	int total_components = 0;
	int N = Constants.N;
	float average_size_of_a_component = 0f;
	float burn_count = 0f;

	@Override
	protected void reduce(
			final IntWritable key,
			final Iterable<Tuple> values,
			final Reducer<IntWritable, Tuple, Text, FloatWritable>.Context context)
			throws IOException, InterruptedException {

		for (final Tuple value : values) {
			total_nodes += value.getFirst();
			total_edges += value.getSecond();
			total_components++;
		}

		average_size_of_a_component = ((float) total_edges)
				/ ((float) total_components);

		burn_count = 0;

		context.write(new Text("total nodes"), new FloatWritable(total_nodes));
		context.write(new Text("total edges"), new FloatWritable(total_edges));
		context.write(new Text("total components"), new FloatWritable(
				total_components));
		context.write(new Text("average size of a component"),
				new FloatWritable(average_size_of_a_component));
		context.write(new Text("burn count"), new FloatWritable(burn_count));
	}
}
