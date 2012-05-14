package step5;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import structures.Tuple;
import constants.Constants;

public class Reducer5 extends Reducer<IntWritable, Tuple, Text, Text> {

    int total_vertices = 0;
    long weighted_total = 0;
    int total_edges = 0;
    int total_components = 0;
    int min_component_size = -1;
    int max_component_size = 0;
    int N = Constants.N;
    float raw_average = 0f;
    float weighted_average = 0f;
    float burn_count = 0f;

    @Override
    protected void reduce(final IntWritable key, final Iterable<Tuple> values,
            final Reducer<IntWritable, Tuple, Text, Text>.Context context)
            throws IOException, InterruptedException {

        for (final Tuple value : values) {
            // Get the number of nodes
            final int num_nodes = value.getFirst();
            total_vertices += num_nodes;

            // Calculate weighted total
            weighted_total += (long) num_nodes * (long) num_nodes;

            // Get max/min component size
            if (num_nodes > max_component_size)
                max_component_size = num_nodes;
            if ((num_nodes < min_component_size) || (min_component_size == -1))
                min_component_size = num_nodes;

            // Get the number of edges
            total_edges += value.getSecond();

            // Update total components
            total_components++;
        }

        if (total_components == 0)
            raw_average = 0;
        else
            raw_average = ((float) total_vertices) / ((float) total_components);

        if (total_vertices == 0)
            weighted_average = 0;
        else
            weighted_average = ((float) weighted_total)
                    / ((float) total_vertices);

        if ((N == 0) || (weighted_average == 0))
            burn_count = 0;
        else
            burn_count = (((float) total_vertices) / ((float) N))
                    * weighted_average;

        // write out final stats
        context.write(new Text("N = "), new Text(Integer.toString(Constants.N)));
        context.write(new Text("M = "), new Text(Integer.toString(Constants.M)));
        context.write(new Text("g = "), new Text(Integer.toString(Constants.g)));
        context.write(new Text("Density = "),
                new Text(Float.toString(Constants.desiredDensity)));
        context.write(new Text("From NetId = "),
                new Text(Float.toString(Constants.fromNetID)));
        context.write(new Text("Edges = "),
                new Text(Integer.toString(total_edges)));
        context.write(new Text("Vertices = "),
                new Text(Integer.toString(total_vertices)));
        context.write(new Text("Components = "),
                new Text(Integer.toString(total_components)));
        context.write(new Text("Max CC size = "),
                new Text(Integer.toString(max_component_size)));
        context.write(new Text("Min CC size = "),
                new Text(Integer.toString(min_component_size)));
        context.write(new Text("Avg CC size = "),
                new Text(Float.toString(raw_average)));
        context.write(new Text("Weighted avg CC size = "),
                new Text(Float.toString(weighted_average)));
        context.write(new Text("Avg burn count = "),
                new Text(Float.toString(burn_count)));

        /*
         * To run the extra credit, uncomment the following lines and run ExtraCreditBase.java
         */
        /*
         * final Writer out = new OutputStreamWriter(new FileOutputStream( "stats.csv", true));
         * final DecimalFormat df = new DecimalFormat("0.00"); final String desired_density =
         * df.format(Constants.desiredDensity); try { out.write(desired_density + ", " +
         * total_vertices + ", " + total_edges + ", " + total_components + ", " + max_component_size
         * + ", " + min_component_size + ", " + raw_average + ", " + weighted_average + ", " +
         * burn_count + "\n"); } finally { out.close(); }
         */

    }
}
