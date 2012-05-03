package step3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Map3New extends Mapper<LongWritable, Text, IntWritable, Tuple> {

	@Override
	public void map(final LongWritable key, final Text value,
			final Context context) throws IOException, InterruptedException {

		final String[] nums = value.toString().split("\\s+");
		if (nums.length == 3) {
			// from red 1
			final int g = Integer.parseInt(nums[0]);
			final int p = Integer.parseInt(nums[1]);
			@SuppressWarnings("unused")
			final int q = Integer.parseInt(nums[2]);

			context.write(new IntWritable(g), new Tuple(p, p));
		} else {
			// from red 2
			final int p = Integer.parseInt(nums[0]);
			final int q = Integer.parseInt(nums[1]);
			final int infered_g = MatrixUtilities.getColumnGroup(Constants.M,
					Constants.g, MatrixUtilities.getColumn(Constants.M, p));
			context.write(new IntWritable(infered_g), new Tuple(p, q));
		}
	}
}