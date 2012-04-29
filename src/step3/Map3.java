package step3;

import java.io.File;
import java.io.FileNotFoundException;
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
    // private static Queue<Tuple> queue = null;
    private int p, g, q;
    Scanner scanner = null;

    private void init() throws FileNotFoundException {
        if (scanner != null) return;
        scanner = new Scanner(new File("data/step3/input/red1Output.txt"));
    }

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {
        init();
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);
        int red_p, red_q, red_g;
        red2Word_p.set(tokenizer.nextToken());
        red2Word_q.set(tokenizer.nextToken());

        red_p = Integer.parseInt(red2Word_p.toString());
        red_q = Integer.parseInt(red2Word_q.toString());
        red_g = MatrixUtilities.getColumnGroup(Constants.M, Constants.g,
                MatrixUtilities.getColumn(Constants.M, red_p));

        while (scanner.hasNext()) {
            p = scanner.nextInt();
            g = scanner.nextInt();
            q = scanner.nextInt(); // <- value never gets used...
            context.write(new IntWritable(g), new Tuple(p, p));
            if ((g >= red_g) && (p >= red_p)) break;
        }

        context.write(new IntWritable(red_g), new Tuple(red_p, red_q));

        while (scanner.hasNext()) {
            p = scanner.nextInt();
            g = scanner.nextInt();
            q = scanner.nextInt(); // <- value never gets used...
            context.write(new IntWritable(g), new Tuple(p, p));
        }
    }
}