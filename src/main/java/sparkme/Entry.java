package sparkme;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    private static final int NUM_SAMPLES = 10;

    public static void main(final String[] args) {
        try (final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("Spark Count"))) {

            final List<Integer> l = new ArrayList<>(NUM_SAMPLES);
            for (int i = 0; i < NUM_SAMPLES; i++) {
                l.add(i);
            }

            final long count = sc.parallelize(l)
                            .filter(new org.apache.spark.api.java.function.Function<Integer, Boolean>() {

                                @Override
                                public Boolean call(final Integer arg0) throws Exception {
                                    final double x = Math.random();
                                    final double y = Math.random();
                                    return x * x + y * y < 1;
                                }
                            }).count();
            System.out.println("Pi is roughly " + 4.0 * count / NUM_SAMPLES);

        }
    }
}
