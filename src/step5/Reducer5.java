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
	int weighted_total = 0;
	int total_edges = 0;
	int total_components = 0;
	int min_component_size = -1;
	int max_component_size = 0;
	int N = Constants.N;
	float raw_average = 0f;
	float weighted_average = 0f;
	float burn_count = 0f;

	@Override
	protected void reduce(
			final IntWritable key,
			final Iterable<Tuple> values,
			final Reducer<IntWritable, Tuple, Text, FloatWritable>.Context context)
			throws IOException, InterruptedException {

		for (final Tuple value : values) {
			// Get the number of nodes
			final int num_nodes = value.getFirst();
			total_nodes += num_nodes;

			// Calculate weighted total
			weighted_total += num_nodes * num_nodes;

			// Get max/min component size
			if (num_nodes > max_component_size) {
				max_component_size = num_nodes;
			}
			if ((num_nodes < min_component_size) || (min_component_size == -1)) {
				min_component_size = num_nodes;
			}

			// Get the number of edges
			total_edges += value.getSecond();

			// Update total components
			total_components++;
		}

		if (total_components == 0) {
			raw_average = 0;
		} else {
			raw_average = ((float) total_nodes) / ((float) total_components);
		}

		if (total_nodes == 0) {
			weighted_average = 0;
		} else {
			weighted_average = ((float) weighted_total) / ((float) total_nodes);
		}

		if ((N == 0) || (weighted_average == 0)) {
			burn_count = 0;
		} else {
			burn_count = (((float) total_nodes) / ((float) N))
					* weighted_average;
		}

		context.write(new Text("total edges"), new FloatWritable(total_edges));
		context.write(new Text("total nodes"), new FloatWritable(total_nodes));
		context.write(new Text("total components"), new FloatWritable(
				total_components));
		context.write(new Text("max component size"), new FloatWritable(
				max_component_size));
		context.write(new Text("min component size"), new FloatWritable(
				min_component_size));
		context.write(new Text("raw average"), new FloatWritable(raw_average));
		context.write(new Text("weighted average"), new FloatWritable(
				weighted_average));
		context.write(new Text("burn count"), new FloatWritable(burn_count));
	}
}
