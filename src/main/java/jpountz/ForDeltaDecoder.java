package jpountz;

import java.io.IOException;
import java.nio.ByteBuffer;

/** Utility class to encode/decode increasing sequences of 128 integers. */
public class ForDeltaDecoder {

    // IDENTITY_PLUS_ONE[i] == i+1
    private static final long[] IDENTITY_PLUS_ONE = new long[ForUtil.BLOCK_SIZE];

    static {
        for (int i = 0; i < ForUtil.BLOCK_SIZE; ++i) {
            IDENTITY_PLUS_ONE[i] = i + 1;
        }
    }

    private static void prefixSumOfOnes(long[] arr, long base) {
        System.arraycopy(IDENTITY_PLUS_ONE, 0, arr, 0, ForUtil.BLOCK_SIZE);
        // This loop gets auto-vectorized
        for (int i = 0; i < ForUtil.BLOCK_SIZE; ++i) {
            arr[i] += base;
        }
    }

    private final ForUtil forUtil;

    ForDeltaDecoder(ForUtil forUtil) {
        this.forUtil = forUtil;
    }

    /** Decode deltas, compute the prefix sum and add {@code base} to all decoded longs. */
    void decodeAndPrefixSum(int bitsPerValue, ByteBuffer in, long base, long[] longs) throws IOException {
        if (bitsPerValue == 0) {
            prefixSumOfOnes(longs, base);
        } else {
            forUtil.decodeAndPrefixSum(bitsPerValue, in, base, longs);
        }
    }
}
