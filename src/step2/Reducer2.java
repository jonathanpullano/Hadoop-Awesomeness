package step2;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import base.PingingReducer;
import constants.Constants;

public class Reducer2 extends
        PingingReducer<IntWritable, Tuple, IntWritable, IntWritable> {

    DisjointSet set = new DisjointSet(2 * Constants.numGroups * Constants.M);
    TreeSet<Integer> inputs = new TreeSet<Integer>(); //TODO: log(n) insertions means nlogn runtime
    private final static Logger LOGGER = Logger.getLogger(Reducer2.class.getName());
    
    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> tuples,
            final Reducer<IntWritable, Tuple, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {
        if (Constants.DEBUG) LOGGER.info("Reducer2 - reduce called");

        Iterator<Tuple> iter = tuples.iterator();
        while(iter.hasNext()) {
            Tuple t1 = new Tuple(iter.next());
            set.union(t1.getFirst(), t1.getSecond());
            inputs.add(t1.getFirst());
            if (Constants.DEBUG) LOGGER.info("Reducer2 - pass1 looped");
        }

        for(Integer pVal : inputs) {
            context.write(new IntWritable(pVal), new IntWritable(set.find(pVal)));
            if (Constants.DEBUG) LOGGER.info("Reducer2 - pass2 looped");
        }
    }
}
