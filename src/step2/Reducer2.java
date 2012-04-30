package step2;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer2 extends
        Reducer<IntWritable, Tuple, IntWritable, IntWritable> {

    DisjointSet set = new DisjointSet(2 * Constants.numGroups * Constants.M);
    TreeSet<Integer> inputs = new TreeSet<Integer>(); //TODO: log(n) insertions means nlogn runtime
    
    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> tuples,
            final Reducer<IntWritable, Tuple, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {

        Iterator<Tuple> iter = tuples.iterator();
        while(iter.hasNext()) {
            Tuple t1 = new Tuple(iter.next());
            set.union(t1.getFirst(), t1.getSecond());
            inputs.add(t1.getFirst());
        }

        for(Integer pVal : inputs)
            context.write(new IntWritable(pVal), new IntWritable(set.find(pVal)));
    }
}
