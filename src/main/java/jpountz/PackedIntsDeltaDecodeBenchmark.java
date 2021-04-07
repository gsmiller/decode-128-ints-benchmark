package jpountz;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class PackedIntsDeltaDecodeBenchmark {

  //@Benchmark
  public void forDeltaDecoder(PackedIntsDecodeState state, Blackhole bh) throws IOException {
    ForDeltaDecoder decoder = new ForDeltaDecoder(new ForUtil());
    decoder.decodeAndPrefixSum(state.bitsPerValue, state.input, state.base, state.outputLongs);
    bh.consume(state.outputLongs);
  }

  @Benchmark
  public void pForDeltaDecoder(PackedIntsDecodeState state, Blackhole bh) throws  IOException {
    PForDeltaDecoder decoder = new PForDeltaDecoder(new ForUtil());
    decoder.decodeAndPrefixSum(state.bitsPerValue, state.input, state.exceptions, state.sameVal, state.base, state.outputLongs);
    bh.consume(state.outputLongs);
  }
}
