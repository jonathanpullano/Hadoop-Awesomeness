package step3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Map3 extends Mapper<LongWritable, Text, IntWritable, Tuple> {

    private final Text red2Word_p = new Text();
    private final Text red2Word_q = new Text();
    private Scanner scanner = null;
    private int red_p, red_q, red_g;
    private int p, g, q;

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);
        // scanner = new Scanner(new File(Constants.reducer1OutputDir));
        scanner = new Scanner(new File("data/step3/input/red1Output.txt"));
        p = scanner.nextInt();
        g = scanner.nextInt();
        q = scanner.nextInt(); // Value is never used...

        red2Word_p.set(tokenizer.nextToken());
        red2Word_q.set(tokenizer.nextToken());

        // while (tokenizer.hasMoreTokens()) {

        red_p = Integer.parseInt(red2Word_p.toString());
        red_q = Integer.parseInt(red2Word_q.toString());
        red_g = MatrixUtilities.getColumnGroup(Constants.M, Constants.g,
                MatrixUtilities.getColumn(Constants.M, red_p));

        // THE FILE COULD BE UNSORTED! (check to verify its sorted)
        while (scanner.hasNext())
            if ((g <= red_g) && (p <= red_p)) {
                context.write(new IntWritable(g), new Tuple(p, p));
                p = scanner.nextInt();
                g = scanner.nextInt();
                q = scanner.nextInt();
            } else
                break;

        context.write(new IntWritable(red_g), new Tuple(red_p, red_q));

        // TODO omit right boundry cases
        // }
    }
}
