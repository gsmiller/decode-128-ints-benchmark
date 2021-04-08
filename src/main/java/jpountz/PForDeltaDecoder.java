package jpountz;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/** Utility class to encode sequences of 128 small positive integers. */
final class PForDeltaDecoder {

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

    private static void prefixSumOf(long val, long[] arr, long base) {
        System.arraycopy(IDENTITY_PLUS_ONE, 0, arr, 0, ForUtil.BLOCK_SIZE);
        for (int i = 0; i < ForUtil.BLOCK_SIZE; i++) {
            arr[i] *= val;
        }
        for (int i = 0; i < ForUtil.BLOCK_SIZE; i++) {
            arr[i] += base;
        }
    }

    private final ForUtil forUtil;
    private final byte[] byteBuff = new byte[14];

    PForDeltaDecoder(ForUtil forUtil) {
        assert ForUtil.BLOCK_SIZE <= 256 : "blocksize must fit in one byte. got " + ForUtil.BLOCK_SIZE;
        this.forUtil = forUtil;
    }

    /** Decode deltas, compute the prefix sum and add {@code base} to all decoded longs. */
    void decodeAndPrefixSum(int bitsPerValue, ByteBuffer in, byte[] exceptions, long sameVal, long base, long[] longs) throws IOException {
        if (exceptions.length == 0) {
            // handle the zero-exception case very similarly to ForDeltaUtil
            if (bitsPerValue == 0) {
                if (sameVal == 1) {
                    prefixSumOfOnes(longs, base);
                } else {
                    prefixSumOf(sameVal, longs, base);
                }
            } else {
                forUtil.decodeAndPrefixSum(bitsPerValue, in, base, longs);
            }
        } else { // we have exceptions
            // pack two values per long so we can apply prefixes two-at-a-time, just like in ForUtil
            if (bitsPerValue == 0) {
                fillSameValue(sameVal, longs);
            } else {
                forUtil.decodeTo32(bitsPerValue, in, longs);
            }
            applyExceptionsIn32Space(exceptions, longs, exceptions.length / 2, bitsPerValue);
            prefixSum(longs, base);
        }
    }

    private void fillSameValue(long sameVal, long[] longs) throws IOException {
        long val = sameVal << 32 | sameVal; // pack two values into each long
        Arrays.fill(longs, 0, ForUtil.BLOCK_SIZE / 2, val);
    }

    private void applyExceptionsIn32Space(byte[] exceptions, long[] longs, int numExceptions, int bitsPerValue) throws IOException {
        System.arraycopy(exceptions, 0, byteBuff, 0, exceptions.length);
        for (int i = 0; i < numExceptions; ++i) {
            int exceptionPos = Byte.toUnsignedInt(byteBuff[i * 2]);
            // note that we pack two values per long, so the index is [0..63] for 128 values
            int idx = exceptionPos & 0x3f; // mod 64
            // we need to shift by 1) the bpv, and 2) 32 for positions [0..63] (and no 32 shift for [64..127])
            int shift = bitsPerValue + ((1 - (exceptionPos >>> 6)) << 5);
            long exception = Byte.toUnsignedLong(byteBuff[i * 2 + 1]) << shift;
            longs[idx] |= exception;
        }
    }

    /**
     * lifted completely from ForUtil
     */
    private static void prefixSum(long[] longs, long base) {
        longs[0] += base << 32;
        innerPrefixSum32(longs);
        expand32(longs);
        long l = longs[ForUtil.BLOCK_SIZE / 2 - 1];
        for (int i = ForUtil.BLOCK_SIZE / 2; i < ForUtil.BLOCK_SIZE; ++i) {
            longs[i] += l;
        }
    }

    /**
     * lifted completely from ForUtil
     */
    private static void expand32(long[] arr) {
        for (int i = 0; i < 64; ++i) {
            long l = arr[i];
            arr[i] = l >>> 32;
            arr[64 + i] = l & 0xFFFFFFFFL;
        }
    }

    /**
     * lifted completely from ForUtil
     */
    private static void innerPrefixSum32(long[] arr) {
        arr[1] += arr[0];
        arr[2] += arr[1];
        arr[3] += arr[2];
        arr[4] += arr[3];
        arr[5] += arr[4];
        arr[6] += arr[5];
        arr[7] += arr[6];
        arr[8] += arr[7];
        arr[9] += arr[8];
        arr[10] += arr[9];
        arr[11] += arr[10];
        arr[12] += arr[11];
        arr[13] += arr[12];
        arr[14] += arr[13];
        arr[15] += arr[14];
        arr[16] += arr[15];
        arr[17] += arr[16];
        arr[18] += arr[17];
        arr[19] += arr[18];
        arr[20] += arr[19];
        arr[21] += arr[20];
        arr[22] += arr[21];
        arr[23] += arr[22];
        arr[24] += arr[23];
        arr[25] += arr[24];
        arr[26] += arr[25];
        arr[27] += arr[26];
        arr[28] += arr[27];
        arr[29] += arr[28];
        arr[30] += arr[29];
        arr[31] += arr[30];
        arr[32] += arr[31];
        arr[33] += arr[32];
        arr[34] += arr[33];
        arr[35] += arr[34];
        arr[36] += arr[35];
        arr[37] += arr[36];
        arr[38] += arr[37];
        arr[39] += arr[38];
        arr[40] += arr[39];
        arr[41] += arr[40];
        arr[42] += arr[41];
        arr[43] += arr[42];
        arr[44] += arr[43];
        arr[45] += arr[44];
        arr[46] += arr[45];
        arr[47] += arr[46];
        arr[48] += arr[47];
        arr[49] += arr[48];
        arr[50] += arr[49];
        arr[51] += arr[50];
        arr[52] += arr[51];
        arr[53] += arr[52];
        arr[54] += arr[53];
        arr[55] += arr[54];
        arr[56] += arr[55];
        arr[57] += arr[56];
        arr[58] += arr[57];
        arr[59] += arr[58];
        arr[60] += arr[59];
        arr[61] += arr[60];
        arr[62] += arr[61];
        arr[63] += arr[62];
    }
}
