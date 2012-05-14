package base;

import org.apache.hadoop.mapreduce.Reducer;

public class PingingReducer<A, B, C, D> extends Reducer<A, B, C, D> {
    long lastTime = System.currentTimeMillis();
    final static long PING_DELAY = 10000;

    public void ping(final Context context) {
        final long currtime = System.currentTimeMillis();
        if (currtime - lastTime > 10000) {
            context.progress();
            lastTime = currtime;
        }
    }
}
