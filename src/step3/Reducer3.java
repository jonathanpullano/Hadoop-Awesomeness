package step3;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Reducer3 extends
        Reducer<IntWritable, Tuple, IntWritable, IntWritable> {

    DisjointSet set = new DisjointSet(Constants.groupSize);
    HashSet<Integer> pointMemory = new HashSet<Integer>();
    final int height = Constants.M;

    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> values,
            final Reducer<IntWritable, Tuple, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {

        set = new DisjointSet(Constants.groupSize);
        pointMemory = new HashSet<Integer>(Constants.groupSize);

        final int group = key.get();

        // Pass 1 - Build a memory to "sort" the values
        for (final Tuple tup : values) {
            int p, q;
            p = tup.getFirst();
            q = tup.getSecond();
            if (tup.getFirst() != tup.getSecond())
                set.union(p, q);
            else
                pointMemory.add(p);
        }

        final int minP = MatrixUtilities.minInGroup(group);
        int maxP = MatrixUtilities.maxInGroup(group);

        // Pass 2 - Unions
        for (int p = minP; p <= maxP; p++) {
            if (!pointMemory.contains(p))
                continue;

            if (((p > height) && pointMemory.contains(p - height))) // left
                set.union(p, p - height);

            if (((p % height != 1) && pointMemory.contains(p - 1))) // bottom
                set.union(p, p - 1);
            
            if (Constants.COMPUTE_DIAGONAL) {
                //Compute lower left diagonal
                if(p > height && p % height != 1 && pointMemory.contains(p - height - 1)) {
                    set.union(p, p - height - 1);
                }
                
                //Compute upper right diagonal
                if(p > height && p % height != 0 && pointMemory.contains(p - height)) {
                    set.union(p, p - height + 1);
                }
            }
        }

        if (group != Constants.numGroups - 1)
            maxP -= height;

        // Pass 3 - Find and output
        for (int p = minP; p <= maxP; p++) {
            if (!pointMemory.contains(p))
                continue;

            context.write(new IntWritable(p), new IntWritable(set.find(p)));
        }
        pointMemory.clear();
    }
}
