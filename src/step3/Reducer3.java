package step3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer3 extends
        Reducer<IntWritable, Tuple, IntWritable, IntWritable> {
    
    DisjointSet set = new DisjointSet(Constants.groupSize);
    ArrayList<Integer> memory = new ArrayList<Integer>();
    final int height = Constants.M;
    
    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> values,
            final Reducer<IntWritable, Tuple, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {
        
    }
}
