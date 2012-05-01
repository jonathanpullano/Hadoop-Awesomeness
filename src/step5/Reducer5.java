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
			weighted_total += num_nodes * num_nodes;
			// Get the number of edges
			total_edges += value.getSecond();
			// Update total components
			total_components++;
		}

		raw_average = ((float) total_edges) / ((float) total_components);

		weighted_average = ((float) weighted_total) / ((float) total_nodes);

		burn_count = (((float) total_edges) / ((float) N)) * weighted_average;

		context.write(new Text("total nodes"), new FloatWritable(total_nodes));
		context.write(new Text("total edges"), new FloatWritable(total_edges));
		context.write(new Text("total components"), new FloatWritable(
				total_components));
		context.write(new Text("raw average"), new FloatWritable(raw_average));
		context.write(new Text("weighted average"), new FloatWritable(
				weighted_average));
		context.write(new Text("burn count"), new FloatWritable(burn_count));
	}
}
