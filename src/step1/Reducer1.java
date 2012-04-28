package step1;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer1 extends
        Reducer<IntWritable, IntWritable, IntWritable, Tuple> {
    @Override
    /**
     * At the moment, this does nothing but emit what it's given
     */
    protected void reduce(
            final IntWritable key,
            final Iterable<IntWritable> values,
            final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
            throws IOException, InterruptedException {
        
        final DisjointSet set = new DisjointSet(Constants.groupSize);
        final ArrayList<Integer> memory = new ArrayList<Integer>();
        
        int len;
        if ((key.get() == 0) || (key.get() == Constants.g - 1))
            len = Constants.groupLength + 1;
        else 
            len = Constants.groupLength + 2;
        
        final int height = Constants.M;

        for (final IntWritable value : values) {
            final int p = value.get();
            while ((memory.size() > 0) && (p - memory.get(0) < height))
                memory.remove(0);

            if ((p > height) && (memory.size() > 0)
                    && memory.contains(p - height)) // left
                set.union(p, p - height);
            
            if ((p % len != 0) && (memory.size() > 0)
                    && (memory.get(memory.size() - 1) == p - 1)) // bottom
                set.union(p, p - 1);

            context.write(key, new Tuple(p, set.find(p)));
            memory.add(p);
        }
    }
}
