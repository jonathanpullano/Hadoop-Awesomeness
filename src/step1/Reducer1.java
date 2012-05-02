package step1;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Reducer1 extends
		Reducer<IntWritable, IntWritable, IntWritable, Tuple> {

	DisjointSet set;
	HashSet<Integer> memory;
	final int height = Constants.M;

	@Override
	protected void reduce(
			final IntWritable key,
			final Iterable<IntWritable> values,
			final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
			throws IOException, InterruptedException {

		set = new DisjointSet(Constants.groupSize);
		memory = new HashSet<Integer>(Constants.groupSize);

		final int group = key.get();

		// Pass 1 - Build a memory to "sort" the values
		for (final IntWritable value : values) {
			memory.add(value.get());
		}

		final int minP = MatrixUtilities.minInGroup(group);
		final int maxP = MatrixUtilities.maxInGroup(group);

		// Pass 2 - Unions
		for (int p = minP; p <= maxP; p++) {
			if (!memory.contains(p)) {
				continue;
			}

			if ((p > height) && memory.contains(p - height)) {
				set.union(p, p - height);
			}
			
			if (Constants.COMPUTE_DIAGONAL) {
			    //Compute lower left diagonal
			    if(p > height && p % height != 1 && memory.contains(p - height - 1)) {
			        set.union(p, p - height - 1);
			    }
			    
			    //Compute upper right diagonal
			    if(p > height && p % height != 0 && memory.contains(p - height)) {
			        set.union(p, p - height + 1);
			    }
			}

			if (((p % height) != 1) && memory.contains(p - 1)) {
				set.union(p, p - 1);
			}
		}

		// Pass 3 - Find and output
		for (int p = minP; p <= maxP; p++) {
			if (!memory.contains(p)) {
				continue;
			}

			context.write(key, new Tuple(p, set.find(p)));
		}
		memory.clear();
	}
}
