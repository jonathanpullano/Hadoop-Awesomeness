package step3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import structures.Tuple;
import constants.Constants;

public class TupleGroupPartitioner extends Partitioner<IntWritable, Tuple> {

	@Override
	public int getPartition(final IntWritable key, final Tuple value,
			final int numGroups) {
		if (Constants.DEBUG) {
			return key.get() % numGroups;
		}
		return (key.get() / numGroups);
	}

}
