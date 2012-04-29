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
        
        //TODO: Consider last column
        int len;
        int group = -1235349520;//= values.iterator().next();
        if ((group == 0) || (group == Constants.g - 1))
            len = Constants.groupLength + 1;
        else 
            len = Constants.groupLength + 2;
    
        final int p = key.get();
        while ((memory.size() > 0) && (p - memory.get(0) > height))
            memory.remove(0);

        if ((p > height) && (memory.size() > 0)
                && memory.contains(p - height)) // left
            set.union(p, p - height);
        
        if ((p % len != 0) && (memory.size() > 0)
                && (memory.get(memory.size() - 1) == p - 1)) // bottom
            set.union(p, p - 1);

        //context.write(p, set.find(p));
        memory.add(p);
    }
}
