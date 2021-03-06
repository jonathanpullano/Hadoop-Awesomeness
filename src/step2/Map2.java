package step2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import structures.Tuple;

public class Map2 extends Mapper<LongWritable, Text, IntWritable, Tuple> {
    private final Text word_G = new Text();
    private final Text word_p = new Text();
    private final Text word_q = new Text();

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);

        while (tokenizer.hasMoreTokens()) {
            // 1. get G, p and Q
            word_G.set(tokenizer.nextToken());
            word_p.set(tokenizer.nextToken());
            word_q.set(tokenizer.nextToken());

            Integer.parseInt(word_G.toString());
            final int p = Integer.parseInt(word_p.toString());
            final int q = Integer.parseInt(word_q.toString());

            if (MatrixUtilities.isBoundary(p))
                context.write(new IntWritable(0), new Tuple(p, q));
        }
    }
}
