package step1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import constants.Constants;

/**
 * 
 * @author chris d Reads in float values. Checks whether each value is in range. If it is, it
 *         assigns it a int representing its position (p) which is the key and a column group (G).
 */
public class Map1 extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    private final Text word = new Text();

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {

        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);

        while (tokenizer.hasMoreTokens()) {
            // 1. get the next word
            word.set(tokenizer.nextToken());

            // 2. Convert the word to a float
            final Float val = Float.parseFloat(word.toString());

            // 3. Test the float, add the vertex if it passes, do nothing
            // otherwise
            if (((val >= Constants.wMin) && (val < Constants.wLimit))) {

                // a. find entry number
                final int N = (int) (key.get() / 12) + 1;

                // b. calculate the (col,row) where this entry should be placed
                final int sq = (int) Math.sqrt(N);
                final int diff = N - (sq * sq);
                int col, row;

                if (diff == 0) {
                    col = sq - 1;
                    row = sq - 1;
                } else
                    if ((diff % 2) == 0) {
                        col = sq;
                        row = (diff - 1) / 2;
                    } else {
                        col = (diff - 1) / 2;
                        row = sq;
                    }

                // c. Once you know where it should be placed, calculate
                // its column_group (G) and its absolute position (p)
                final int col_group_int = MatrixUtilities.getColumnGroup(
                        Constants.M, Constants.g, col);

                final IntWritable column_group = new IntWritable(col_group_int);
                final IntWritable position = new IntWritable(
                        (col * Constants.M) + (row + 1));

                // d. write out (G,p)
                context.write(column_group, position);

                // e. if N is on a group boundary, you must also add it to
                // the group to its left.
                final boolean is_boundary = MatrixUtilities.isBoundary(position
                        .get());
                if (is_boundary)
                    context.write(new IntWritable(col_group_int - 1), position);
            }
        }
    }
}