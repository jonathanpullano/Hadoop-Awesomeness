package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.Tuple;

public class TestReducer extends
        Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
    @Override
    /**
     * At the moment, this does nothing but emit what it's given
     */
    protected void reduce(
            final IntWritable key,
            final Iterable<IntWritable> values,
            final Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {
        
        for(IntWritable iw : values) {
            context.write(key, iw);
        }
    }
}
