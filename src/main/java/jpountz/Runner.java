package jpountz;

import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Runner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PackedIntsDeltaDecodeBenchmark.class.getSimpleName())
                .build();
        new org.openjdk.jmh.runner.Runner(opt).run();
    }
}
