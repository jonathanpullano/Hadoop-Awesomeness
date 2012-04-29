package step3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
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
    private static Queue<Tuple> queue = null;;

    private static Queue<Tuple> initQueue() {
        if ((queue != null) && !queue.isEmpty()) return queue;
        final Queue<Tuple> tmpQueue = new LinkedList<Tuple>();
        try {
            final Scanner scanner = new Scanner(new File(
                    "data/step3/input/red1Output.txt"));
            int p, g;
            while (scanner.hasNext()) {
                p = scanner.nextInt();
                g = scanner.nextInt();
                scanner.nextInt(); // <-- Skips of q values in file

                final Tuple tuple = new Tuple(p, g);
                tmpQueue.offer(tuple);
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return tmpQueue;
    }

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {
        queue = initQueue();
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);
        int red_p, red_q, red_g;
        red2Word_p.set(tokenizer.nextToken());
        red2Word_q.set(tokenizer.nextToken());

        red_p = Integer.parseInt(red2Word_p.toString());
        red_q = Integer.parseInt(red2Word_q.toString());
        red_g = MatrixUtilities.getColumnGroup(Constants.M, Constants.g,
                MatrixUtilities.getColumn(Constants.M, red_p));
        while (!queue.isEmpty()) {
            final Tuple curTup = queue.poll();
            if ((curTup.getSecond() >= red_g) && (curTup.getFirst() >= red_p)) {
                context.write(new IntWritable(curTup.getSecond()), new Tuple(
                        curTup.getFirst(), curTup.getFirst()));
                break;
            }

            if ((curTup.getSecond() <= red_g) && (curTup.getFirst() <= red_p))
                context.write(new IntWritable(curTup.getSecond()), new Tuple(
                        curTup.getFirst(), curTup.getFirst()));
        }

        context.write(new IntWritable(red_g), new Tuple(red_p, red_q));
    }
}