package step2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer2 extends Reducer<IntWritable, Tuple, IntWritable, Tuple> {

    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> tuples,
            final Reducer<IntWritable, Tuple, IntWritable, Tuple>.Context context)
            throws IOException, InterruptedException {
        final DisjointSet set = new DisjointSet((2 * Constants.g - 2)
                * Constants.M);

        final Iterator<Tuple> it = tuples.iterator();
        Tuple prevTuple = it.next();
        prevTuple = new Tuple(prevTuple.getP(), prevTuple.getQ());

        System.out.println("**prevTuple (" + prevTuple.getP() + ", "
                + prevTuple.getQ() + ")\n");

        Tuple CurTuple = it.next();
        System.out.println("**CurTuple (" + CurTuple.getP() + ", "
                + CurTuple.getQ() + ")\n");

        for (; it.hasNext();) {
            System.out.println("Working with \n prevTuple (" + prevTuple.getP()
                    + ", " + prevTuple.getQ() + ")\n CurTuple ("
                    + CurTuple.getP() + ", " + CurTuple.getQ() + ")");

            if (prevTuple.getP() == CurTuple.getP()) {

                set.union(prevTuple.getP(), prevTuple.getQ());
                set.union(CurTuple.getP(), CurTuple.getQ());
                context.write(key,
                        new Tuple(CurTuple.getP(), set.find(CurTuple.getP())));
                context.write(key,
                        new Tuple(prevTuple.getP(), set.find(prevTuple.getP())));
            } else System.out.println("Skipped tuple \n prevTuple ("
                    + prevTuple.getP() + ", " + prevTuple.getQ()
                    + ")\n CurTuple (" + CurTuple.getP() + ", "
                    + CurTuple.getQ() + ")");

            prevTuple = CurTuple;
            CurTuple = it.next();
        }
    }
}
